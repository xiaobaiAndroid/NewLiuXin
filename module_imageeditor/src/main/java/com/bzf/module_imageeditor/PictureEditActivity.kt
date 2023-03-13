package com.bzf.module_imageeditor

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.OrientationEventListener
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bzf.module_imageeditor.adjust.AdjustSelectView
import com.bzf.module_imageeditor.databinding.ActivityPictureEditBinding
import com.bzf.module_imageeditor.entity.ConcatBitmap
import com.bzf.module_imageeditor.filter.FilterSelectView
import com.bzf.module_imageeditor.music.MusicSelectView
import com.bzf.module_imageeditor.sticker.StickerSelectView
import com.bzf.module_imageeditor.utils.LogUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.audioplayer_lib.AudioPlayerEngine
import module.common.data.db.entity.MusicTable
import module.common.data.entity.Music
import module.common.event.MessageEvent
import module.music.MusicHomeActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class PictureEditActivity : FragmentActivity() {

    private lateinit var  binding: ActivityPictureEditBinding
    private val mList = java.util.ArrayList<PictureEntity>()
    private val savePath: String by lazy{
        externalCacheDir?.absolutePath ?: cacheDir.absolutePath
    }

    private val mAudioPlayerEngine = AudioPlayerEngine()

    private val viewModel: PictureEditViewModel by lazy {
        viewModels<PictureEditViewModel>().value
    }

    private val mAdapter: PictureEditAdapter by lazy {
        PictureEditAdapter(this, mList)
    }

    private var mLastScreenDegree: Int = 0
    private var mCurrentScreenDegree: Int = 0

    var loadingPopupView: LoadingPopupView ?= null

    private var mConcatBitmaps = arrayListOf<ConcatBitmap>()
    private var mTempBitmapMap = hashMapOf<Int,ConcatBitmap>()

    private var mMusicSelectView: MusicSelectView?=null

    private val mOrientationListener:OrientationEventListener  by lazy{
        object: OrientationEventListener(applicationContext){
            override fun onOrientationChanged(orientation: Int) {
                Log.i("bzf","OrientationEventListener---orientation=$orientation")
                if(orientation>350 || orientation < 10){ //0°
                    mCurrentScreenDegree = 0
                }else if(orientation >80 || orientation < 100 ){ //90°
                    mCurrentScreenDegree = 90
                }else if(orientation >170 || orientation < 190 ){ //180°
                    mCurrentScreenDegree = 180
                }else if(orientation >260 || orientation < 280 ){ //270°
                    mCurrentScreenDegree = 270
                }else{
                    mCurrentScreenDegree =  OrientationEventListener.ORIENTATION_UNKNOWN
                }

                if(mCurrentScreenDegree != mLastScreenDegree){
                    val messageEvent =
                        MessageEvent(MessageEvent.Type.SCREEN_ORIENTATION)
                    messageEvent.obj = mCurrentScreenDegree
                    EventBus.getDefault().post(messageEvent)
                    mLastScreenDegree = mCurrentScreenDegree
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPictureEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }

        val list = intent.getStringArrayListExtra("paths")
        list?.let {
            for (index in 0 until it.size){
                val path = it[index]
                Log.i("bzf", "path=$path")
                val id = UUID.randomUUID().toString().replace("-", "").lowercase(Locale.ROOT)
                LogUtils.printI("imageId=$id")
                mList.add(PictureEntity(id,path, savePath, index))
            }
        }

        initTabView()

        binding.backIV.setOnClickListener {
            release()
        }

        binding.nextStepBt.setOnClickListener {
            synthesisBitmap()
        }

        binding.selectMusicCL.setOnClickListener {
            showMusicView()
        }

        val deviceSupportsAEP: Boolean =
            packageManager.hasSystemFeature(PackageManager.FEATURE_OPENGLES_EXTENSION_PACK)
        if(deviceSupportsAEP){
            Log.i("bzf","支持AEP")
        }else{
            Log.i("bzf","不支持AEP")
        }

        iniData()
    }

    private fun iniData() {
        viewModel.musicExitPosition.observe(this){
            mMusicSelectView?.changeSelect(it)
        }
        viewModel.musicLiveData.observe(this){
            it?.let {musicTable->
                mMusicSelectView?.addMusic(musicTable)
            }

        }

        viewModel.queryAllLocalMusic(applicationContext)
    }

    private fun showMusicView() {
        mMusicSelectView = MusicSelectView(this,viewModel.musicAllLiveData.value,object: MusicSelectView.Listener{
            override fun openMusicLib() {
                mAudioPlayerEngine.stop()
                startActivity(Intent(this@PictureEditActivity,MusicHomeActivity::class.java))
            }

            override fun selectedMusic(musicTable: MusicTable) {
                preparePlay(musicTable)
            }

        })
         XPopup.Builder(this)
            .isViewMode(true)
            .hasShadowBg(false)
            .asCustom(mMusicSelectView)
            .show()
    }

    private fun preparePlay(musicTable: MusicTable) {
        musicTable.musicUrl?.let {
            lifecycleScope.launch(Dispatchers.IO) {
                mAudioPlayerEngine.prepare(it)
                withContext(Dispatchers.Main) {
                    mAudioPlayerEngine.play()
                }
            }
        }
    }


    override fun onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
        mAudioPlayerEngine.release()

        super.onDestroy()
    }

    private fun synthesisBitmap() {
        loadingPopupView  = XPopup.Builder(this)
            .dismissOnTouchOutside(false)
            .dismissOnBackPressed(false)
            .asLoading(resources.getString(R.string.processing))
        loadingPopupView?.show()

        EventBus.getDefault().post(MessageEvent(MessageEvent.Type.SAVE))
    }

    private fun initTabView() {
        binding.contentVP.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.contentVP.offscreenPageLimit = mList.size
        binding.contentVP.adapter = mAdapter

        binding.contentVP.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.pageNumberTV.text = (position + 1).toString() + "/" + mAdapter.itemCount.toString()
            }
        })

        binding.textTab.tabIV.setImageResource(R.drawable.imageeditor_text)
        binding.textTab.tabNameTV.text = resources.getString(R.string.text)

        binding.stickerTab.tabIV.setImageResource(R.drawable.imageeditor_sticker)
        binding.stickerTab.tabNameTV.text = resources.getString(R.string.sticker)

        binding.filterTab.tabIV.setImageResource(R.drawable.imageeditor_filter)
        binding.filterTab.tabNameTV.text = resources.getString(R.string.filter)

        binding.tagTab.tabIV.setImageResource(R.drawable.imageeditor_flag)
        binding.tagTab.tabNameTV.text = resources.getString(R.string.flag)

        binding.adjustTab.tabIV.setImageResource(R.drawable.imageeditor_adjust)
        binding.adjustTab.tabNameTV.text = resources.getString(R.string.adjust)

        binding.clipTab.tabIV.setImageResource(R.drawable.imageeditor_clip)
        binding.clipTab.tabNameTV.text = resources.getString(R.string.clip)

        binding.filterTab.tabLL.setOnClickListener {
            showFilterView()
        }
        binding.adjustTab.tabLL.setOnClickListener {
            showAdjustView()
        }
        binding.stickerTab.tabLL.setOnClickListener {
            showStickView()
        }

    }


    private fun showStickView() {
        val pictureEntity = mList[binding.contentVP.currentItem]
        XPopup.Builder(this)
            .isViewMode(true)
            .hasShadowBg(false)
            .asCustom(StickerSelectView(this,pictureEntity.id))
            .show()
    }

    private fun showAdjustView() {
        val pictureEntity = mList[binding.contentVP.currentItem]
        XPopup.Builder(this)
            .isViewMode(true)
            .hasShadowBg(false)
            .asCustom(AdjustSelectView(this,pictureEntity.id))
            .show()
    }

    private fun showFilterView() {
        val pictureEntity = mList[binding.contentVP.currentItem]
        XPopup.Builder(this)
            .isViewMode(true)
            .hasShadowBg(false)
            .asCustom(FilterSelectView(this,pictureEntity.id))
            .show()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            release()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun release(){
        EventBus.getDefault().post(MessageEvent(MessageEvent.Type.RELEASE))
        finish()
    }


    override fun onStop() {
        super.onStop()
        if(mOrientationListener.canDetectOrientation()){
            mOrientationListener.disable()
        }
    }

    override fun onStart() {
        super.onStart()
        if(mOrientationListener.canDetectOrientation()){ //判断设备是否支持旋转
            mOrientationListener.enable()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent){
        if(event.type == MessageEvent.Type.CONCAT_BITMAP_RESULT){
            val concatBitmap = event.obj as ConcatBitmap
            if(!mTempBitmapMap.containsValue(concatBitmap)){
                for (i in mList.indices){
                    val pictureEntity = mList[i]
                    if(pictureEntity.id == concatBitmap.id){
                        concatBitmap.position = i
                        mTempBitmapMap[i] = concatBitmap
                        break
                    }
                }
            }
            if(mTempBitmapMap.size == mList.size){
                sendBitmapData()
            }
        }else if (event.type === MessageEvent.Type.MUSIC_SELECT_MUSIC){
            val music = event.obj as Music
            viewModel.addMusic(applicationContext,music,mMusicSelectView?.getData())
        }
    }

    private fun sendBitmapData() {
        for (i in mList.indices){
           mConcatBitmaps.add(mTempBitmapMap[i]!!)
        }
        loadingPopupView?.dismiss()
//        val intent = Intent()
//        intent.putParcelableArrayListExtra("images",mConcatBitmaps)
//        startActivity(intent)
    }

    companion object {
        // Used to load the 'module_imageeditor' library on application startup.
        init {
            System.loadLibrary("module_imageeditor")
        }
    }
}