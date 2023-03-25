package module.dynamic.detail.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import module.comment.CommentListView
import module.common.base.BaseFragment
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.data.entity.Video
import module.common.data.status.CommonStatus
import module.common.event.MessageEvent
import module.common.status.EndorseStatus
import module.common.utils.ImageLoadHelper
import module.common.utils.LogUtils
import module.common.utils.ToastUtils
import module.dynamic.R
import module.dynamic.databinding.DynamicFragmentVideoDetailBinding
import module.gift.GiftHomeView
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/3/24
 */
internal class VideoDetailFragment: BaseFragment<DynamicFragmentVideoDetailBinding,VideoDetailViewModel>() {


    override fun createViewModel(): VideoDetailViewModel {
        return viewModels<VideoDetailViewModel>().value
    }

    private val mAFPort:VideoDetailActFraPort by activityViewModels()

    private  var position: Int = -1
    private lateinit var mDynamic: Dynamic

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFragmentVideoDetailBinding {
        return DynamicFragmentVideoDetailBinding.inflate(inflater, container,false)
    }

    override fun initData() {
        mDynamic = requireArguments().getParcelable<Dynamic?>("dynamic") ?: Dynamic()
        position = requireArguments().getInt("position")

        LogUtils.printI("fragment position=$position --- initView")

        val mediaItem = MediaItem.fromUri(mDynamic.mediaUrl ?: "")

        binding.myVideoPlayer.coverIV?.let {
            it.visibility = View.VISIBLE
            ImageLoadHelper.loadFitCenter(it, mDynamic.coverUrl)
        }

        LogUtils.printI("fragment position=$position --- mediaUrl=${mDynamic.mediaUrl}")
        binding.myVideoPlayer.player!!.setMediaItem(mediaItem)
        binding.myVideoPlayer.player!!.prepare()

        viewModel.getAttentionStatusById(mDynamic.userId)
    }

    override fun initView() {

        binding.myVideoPlayer.apply {
            setShowFastForwardButton(false)
            setShowNextButton(false)
            setShowPreviousButton(false)
            setShowShuffleButton(false)
            setShowRewindButton(false)
            setShowVrButton(false)
            controllerAutoShow = false
            setShowMultiWindowTimeBar(false)
        }

        binding.myVideoPlayer.player = ExoPlayer.Builder(requireActivity()).build()
        binding.myVideoPlayer.player?.repeatMode = Player.REPEAT_MODE_ONE
        binding.myVideoPlayer.player?.addListener(object: Player.Listener{
            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                val cause = error.cause
                if (cause is HttpDataSource.HttpDataSourceException) {
                    val httpError = cause
                    if (httpError is HttpDataSource.InvalidResponseCodeException) {
                        LogUtils.printE("videoplayer InvalidResponseCodeException")
                    } else {
                        LogUtils.printE("videoplayer unknown PlaybackException")
                    }
                }

            }

            override fun onRenderedFirstFrame() {
                super.onRenderedFirstFrame()
                binding.myVideoPlayer.hideCover()
                LogUtils.printE("videoplayer listener onRenderedFirstFrame")
            }

            override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
                super.onPlayWhenReadyChanged(playWhenReady, reason)
                LogUtils.printE("videoplayer listener onPlayWhenReadyChanged")

            }
        })

        binding.markTV.setOnClickListener {
            updateAttentionStatus()
        }
        binding.rightOperation.endorseLL.setOnClickListener {
           updateEndorseStatus()
        }
        binding.rightOperation.collectLL.setOnClickListener {
            updateCollectStatus()
        }


        mAFPort.currentPlayPositionLD.observe(this){
            binding.myVideoPlayer.player?.let { player ->
                if(it != position){
                    if(player.isPlaying){
                        player.pause()
                    }
                }else{
                    if(!player.isPlaying){
                        player.play()
                    }
                }
            }
        }


        setupObserver()
    }

    private fun setupObserver() {
        viewModel.attentionResultLD.observe(this){
            binding.markTV.isEnabled = true
            if(it.status == DataResult.SUCCESS){
                if (mDynamic.attentionUserStatus == CommonStatus.YET) {
                    mDynamic.attentionUserStatus = CommonStatus.NOT
                } else {
                    mDynamic.attentionUserStatus = CommonStatus.YET
                }
                setAttentionStatusView(mDynamic.attentionUserStatus)
                sendUpdateDynamicMessage()
            }else{
                ToastUtils.setMessage(requireContext(),it.message)
            }

        }
        viewModel.collectResultLD.observe(this){
            binding.rightOperation.collectLL.isEnabled = true
            if(it.status == DataResult.SUCCESS){
                sendUpdateDynamicMessage()
            }else{
                ToastUtils.setMessage(requireContext(),it.message)
            }
        }
        viewModel.endorseResultLD.observe(this){
            binding.rightOperation.endorseLL.isEnabled = true
            if(it.status == DataResult.SUCCESS){
                sendUpdateDynamicMessage()
            }else{
                ToastUtils.setMessage(requireContext(),it.message)
            }
        }

        viewModel.attentionStateLD.observe(this){
            it?.let {status->
                mDynamic.attentionUserStatus =status
                setAttentionStatusView(mDynamic.attentionUserStatus)
            }

        }
    }

    private fun sendUpdateDynamicMessage() {
        val messageEvent = MessageEvent(MessageEvent.Type.UPDATE_DYNAMIC_DATA)
        messageEvent.obj = mDynamic
        EventBus.getDefault().post(messageEvent)
    }


    private fun updateAttentionStatus() {
        binding.markTV.isEnabled = false
        if (mDynamic.attentionUserStatus == CommonStatus.YET) {
            viewModel.updateAttentionStatus(mDynamic.userId, CommonStatus.NOT)
        } else {
            viewModel.updateAttentionStatus(mDynamic.userId, CommonStatus.YET)
        }

    }

    private fun setAttentionStatusView(status: Int) {
        if (mDynamic.attentionUserStatus == CommonStatus.YET) {
            binding.markTV.text = resources.getString(R.string.clique_yet_attention)
        } else {
            binding.markTV.text = resources.getString(R.string.clique_mark)
        }
    }

    private fun updateEndorseStatus() {
        binding.rightOperation.endorseLL.isEnabled = false
        if (mDynamic.praiseStatus == EndorseStatus.STA_YES.value) {
            mDynamic.praiseStatus = EndorseStatus.STA_NO.value
            mDynamic.praiseNum = (mDynamic.praiseNum!!.toInt() - 1).toString()
        } else {
            mDynamic.praiseStatus = EndorseStatus.STA_YES.value
            mDynamic.praiseNum = (mDynamic.praiseNum!!.toInt() + 1).toString()
        }
        viewModel.updateEndorseStatus(mDynamic)
    }


    /**
     * @describe: 显示评论弹出框
     * @date: 2020/3/10
     */
    fun showCommentView(video: Video) {
        val dynamic = Dynamic()
        dynamic.id = video.id
        dynamic.userId = video.userId
        val commentListView = CommentListView(requireActivity(), dynamic)
        XPopup.Builder(requireActivity())
            .isViewMode(true)
            .hasShadowBg(false)
            .enableDrag(false)
            .moveUpToKeyboard(false)
            .popupAnimation(PopupAnimation.TranslateFromBottom)
            .asCustom(commentListView)
            .show()
    }


    protected fun showGiftPopup() {
        val giftListView = GiftHomeView(requireActivity(),  mDynamic.userId)
        XPopup.Builder(requireActivity())
            .isViewMode(true)
            .hasShadowBg(false)
            .enableDrag(false)
            .asCustom(giftListView)
            .show()
    }


    private fun updateCollectStatus() {
        binding.rightOperation.collectLL.isEnabled = false
        if (mDynamic.favoriteStatus == EndorseStatus.STA_YES.value) {
            mDynamic.favoriteStatus = EndorseStatus.STA_NO.value
            mDynamic.favoriteNum = (mDynamic.favoriteNum!!.toInt() - 1).toString()
        } else {
            mDynamic.favoriteStatus = EndorseStatus.STA_YES.value
            mDynamic.favoriteNum = (mDynamic.favoriteNum!!.toInt() + 1).toString()
        }
        viewModel.updateCollectStatus(mDynamic)
    }


    override fun onPause() {
        super.onPause()
        binding.myVideoPlayer.player?.let {
            if(it.isPlaying){
                it.pause()
            }
        }
        LogUtils.printI("fragment position=$position --- onPause")
    }

    override fun onResume() {
        super.onResume()
        binding.myVideoPlayer.player?.let {
            if(!it.isPlaying){
                it.play()
            }
        }
        LogUtils.printI("fragment position=$position --- onResume")
    }

    override fun onDestroyView() {
        LogUtils.printI("fragment position=$position --- onDestroyView")
        binding.myVideoPlayer.player?.stop()
        binding.myVideoPlayer.player?.release()
        super.onDestroyView()
    }
}