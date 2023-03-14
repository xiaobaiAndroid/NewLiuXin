package com.bzf.module_imageeditor.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.bzf.module_imageeditor.PictureEditActivity
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.databinding.ImgLayoutNavigationBinding
import com.bzf.module_imageeditor.music.MusicSelectView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.SimpleCallback
import module.audioplayer_lib.AudioPlayerEngine
import module.common.data.db.entity.MusicTable
import module.common.utils.IconUtils
import module.common.utils.ToastUtils
import module.music.MusicHomeActivity

/**
 *@author: baizf
 *@date: 2023/3/14
 */
class NavigationBarView: FrameLayout {

    private val binding: ImgLayoutNavigationBinding
    private lateinit var mAudioPlayerEngine: AudioPlayerEngine

    private var mSoundAnimationDrawable: AnimationDrawable?=null

    var mMusicSelectView: MusicSelectView? = null

    var mActivity: PictureEditActivity?=null


    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int):super(context,attributeSet,defStyle){
        binding = ImgLayoutNavigationBinding.inflate(LayoutInflater.from(context),this,true)
        setupInit()
    }


    constructor(context: Context, attributeSet: AttributeSet?):this(context,attributeSet,0)
    constructor(context: Context):this(context,null)


    private fun setupInit() {
        IconUtils.setColor(binding.backIV, resources.getColor(R.color.cl_ffffff))

        binding.playMusicLayout.musicPlayAnimationView.apply {
            setImageResource(R.drawable.img_sound_animation)
            mSoundAnimationDrawable = drawable as AnimationDrawable
            visibility = View.INVISIBLE
        }

        mAudioPlayerEngine = AudioPlayerEngine(object : AudioPlayerEngine.Listener {
            override fun onError() {
                ToastUtils.setMessage(context, resources.getString(R.string.play_music_not_exit))
            }

            override fun onStartPlaying() {
                playSoundAnimation(true)
                playBottomSoundAnimation(true)
            }

            override fun onCompletion() {
                setupPlayButtonStatus(false)
                playSoundAnimation(false)
                playBottomSoundAnimation(false)
            }

            override fun onPrepare() {
                mAudioPlayerEngine.play()
                setupPlayButtonStatus(true)
            }
        })


        binding.backIV.setOnClickListener {
            mActivity?.release()
        }

        binding.nextStepBt.setOnClickListener {
            mActivity?.synthesisBitmap()
        }


        binding.selectMusicLayout.selectMusicCL.setOnClickListener {
            playSoundAnimation(false)
            playBottomSoundAnimation(false)
            showPlayLayoutAnim()
            showMusicView()
        }


        binding.playMusicLayout.playIV.setOnClickListener {
            if(mAudioPlayerEngine.isPlaying()){
                setupPlayButtonStatus(false)
                playSoundAnimation(false)
                mAudioPlayerEngine.pause()
            }else{
                setupPlayButtonStatus(true)
                playSoundAnimation(true)
                mAudioPlayerEngine.play()
            }

        }

        binding.playMusicLayout.playContentCL.setOnClickListener {
            isShowNextStepView(false)
            if(mAudioPlayerEngine.isPlaying()){
                playBottomSoundAnimation(true)
            }else{
                playBottomSoundAnimation(false)
            }
            showMusicView()
        }

    }

    private fun playSoundAnimation(start: Boolean) {
        if(start){
            binding.playMusicLayout.musicPlayAnimationView.visibility = VISIBLE
            mSoundAnimationDrawable?.start()
        }else{
            binding.playMusicLayout.musicPlayAnimationView.visibility = INVISIBLE
            mSoundAnimationDrawable?.stop()
        }

    }

    private fun playBottomSoundAnimation(start: Boolean) {
        if(start){
            mMusicSelectView?.updatePlayState(true)
        }else{
            mMusicSelectView?.updatePlayState(false)
        }

    }

    private fun setupPlayButtonStatus(start: Boolean) {
        if(start){
            binding.playMusicLayout.playIV.setImageResource(R.drawable.img_ic_pause)
        }else{
            binding.playMusicLayout.playIV.setImageResource(R.drawable.img_ic_play)
        }

    }


    private fun setupNextStepShowStatus(startAlpha: Float, endAlpha: Float) {
        val defaultDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        val objectAnimator1 = ObjectAnimator.ofFloat(
            binding.backIV,
            "alpha",
            startAlpha
            , endAlpha//end
        )

        val objectAnimator2 = ObjectAnimator.ofFloat(
            binding.nextStepBt,
            "alpha",
            startAlpha
            , endAlpha
        )

        val animatorSet = AnimatorSet()
        animatorSet.duration = defaultDuration.toLong()
        animatorSet.playTogether(objectAnimator1,objectAnimator2)
        animatorSet.start()
    }
    private fun showPlayLayoutAnim() {
        if(binding.selectMusicLayout.selectMusicRootCL.visibility != View.VISIBLE){
            return
        }
        val defaultDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        val objectAnimator = ObjectAnimator.ofFloat(
            binding.selectMusicLayout.selectMusicRootCL,
            "translationY",
            0f, -binding.selectMusicLayout.selectMusicRootCL.height.toFloat()
        )

        val objectAnimator1 = ObjectAnimator.ofFloat(
            binding.playMusicLayout.playMusicCL,
            "translationY",
            binding.playMusicLayout.playMusicCL.height.toFloat() //start
            , 0f //end
        )


        val animatorSet = AnimatorSet()
        animatorSet.duration = defaultDuration.toLong()
        animatorSet.playTogether(objectAnimator, objectAnimator1)
        animatorSet.addListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                binding.selectMusicLayout.selectMusicRootCL.visibility = View.GONE
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }

        })
        animatorSet.start()

        isShowNextStepView(false)
    }


    private fun showMusicView() {
        mMusicSelectView = MusicSelectView(
            mActivity!!,
            mActivity?.getMusicsData(),
            object : MusicSelectView.Listener {
                override fun openMusicLib() {
                    mAudioPlayerEngine.stop()
                    mActivity!!.startActivity(Intent(mActivity!!, MusicHomeActivity::class.java))
                }

                override fun selectedMusic(musicTable: MusicTable) {
                    preparePlay(musicTable)
                }

            })
        XPopup.Builder(mActivity!!)
            .isViewMode(true)
            .isClickThrough(true)
            .hasShadowBg(false)
            .setPopupCallback(object: SimpleCallback() {
                override fun onDismiss(popupView: BasePopupView?) {
                    super.onDismiss(popupView)
                    isShowNextStepView(true)
                }
            })
            .asCustom(mMusicSelectView)
            .show()
    }

    private fun isShowNextStepView(isShow: Boolean){
        if(isShow){
            setupNextStepShowStatus(0f,1f)
        }else{
            setupNextStepShowStatus(1f,0f)
        }
    }

    private fun preparePlay(musicTable: MusicTable) {
        showPlayLayoutAnim()

        binding.playMusicLayout.musicNameTV.text = musicTable.musicName

        musicTable.musicUrl?.let {
            mAudioPlayerEngine.prepare(it)
        }

    }

    fun onDestroy(){
        mAudioPlayerEngine.stop()
        mAudioPlayerEngine.release()
        mSoundAnimationDrawable?.stop()
    }

}