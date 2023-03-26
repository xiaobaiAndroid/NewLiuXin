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
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupAnimation
import lib.svga.SVGAHelper
import module.comment.CommentListView
import module.common.base.BaseFragment
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.data.entity.Gift
import module.common.data.entity.Video
import module.common.data.status.CommonStatus
import module.common.event.MessageEvent
import module.common.status.EndorseStatus
import module.common.utils.IconUtils
import module.common.utils.ImageLoadHelper
import module.common.utils.LogUtils
import module.common.utils.ToastUtils
import module.dynamic.R
import module.dynamic.databinding.DynamicFragmentVideoDetailBinding
import module.gift.EGiveGift
import module.gift.GiftHomeView
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/3/24
 */
internal class VideoDetailFragment :
    BaseFragment<DynamicFragmentVideoDetailBinding, VideoDetailViewModel>() {


    override fun createViewModel(): VideoDetailViewModel {
        return viewModels<VideoDetailViewModel>().value
    }

    private val mAFPort: VideoDetailActFraPort by activityViewModels()

    private var position: Int = -1

    private var mEGiveGift: EGiveGift?=null
    private var giftPopup: BasePopupView? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFragmentVideoDetailBinding {
        return DynamicFragmentVideoDetailBinding.inflate(inflater, container, false)
    }


    override fun disposeMessageEvent(event: MessageEvent?) {
        event ?: return
        super.disposeMessageEvent(event)
         if (MessageEvent.Type.PLAY_VIDEO_SVGA === event.type) {
            mEGiveGift = event.obj as EGiveGift
            mEGiveGift?.let {
                viewModel.giveGift(viewModel.dynamicLD.value?.userId, it.selectGift.id, it.selectedNumber)
            }
            giftPopup?.dismiss()
        }
    }

    override fun initData() {
        viewModel.dynamicLD.value =
            requireArguments().getParcelable<Dynamic?>("dynamic") ?: Dynamic()
        position = requireArguments().getInt("position")

        viewModel.cityCodeLD.value = requireArguments().getString("cityCode")
        viewModel.typeIdLD.value = requireArguments().getString("typeId")

        LogUtils.printI("fragment position=$position --- initView")

        viewModel.dynamicLD.value?.apply {
            val mediaItem = MediaItem.fromUri(mediaUrl ?: "")

            binding.myVideoPlayer.coverIV?.let {
                it.visibility = View.VISIBLE
                ImageLoadHelper.loadFitCenter(it, coverUrl)
            }

            LogUtils.printI("fragment position=$position --- mediaUrl=${mediaUrl}")
            binding.myVideoPlayer.player!!.setMediaItem(mediaItem)
            binding.myVideoPlayer.player!!.prepare()

            viewModel.getAttentionStatusById(userId)
            setEndorseStatusView(praiseStatus, praiseNum ?: "0")
            setCollectStatusView(favoriteStatus, favoriteNum ?: "0")

            binding.contentTV.text = description
        }
    }

    private fun setCollectStatusView(favoriteStatus: Int, favoriteNum: String) {
        if (favoriteStatus == CommonStatus.YET) {
            binding.rightOperation.collectIV.setImageResource(R.drawable.dynamic_ic_collect_select)
        } else {
            binding.rightOperation.collectIV.setImageResource(R.drawable.video_ic_collect_normal)
        }
        binding.rightOperation.collectTV.text = favoriteNum
    }

    private fun setEndorseStatusView(praiseStatus: Int, praiseNum: String) {
        if (praiseStatus == CommonStatus.YET) {
            binding.rightOperation.endorseIV.setImageResource(R.drawable.dynamic_ic_like_selected)
        } else {
            binding.rightOperation.endorseIV.setImageResource(R.drawable.video_ic_praise_normal)
        }
        binding.rightOperation.endorseTV.text = praiseNum
    }


    override fun initView() {

        binding.myVideoPlayer.player = ExoPlayer.Builder(requireActivity()).build()
        binding.myVideoPlayer.player?.repeatMode = Player.REPEAT_MODE_ONE
        binding.myVideoPlayer.player?.addListener(object : Player.Listener {
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
        binding.rightOperation.giftLL.setOnClickListener {
            showGiftPopup()
        }
        binding.rightOperation.commentLL.setOnClickListener {
            showCommentView()
        }
        binding.rightOperation.chatLL.setOnClickListener {

        }

        mAFPort.currentPlayPositionLD.observe(this) {
            binding.myVideoPlayer.player?.let { player ->
                if (it != position) {
                    if (player.isPlaying) {
                        player.pause()
                    }
                } else {
                    if (!player.isPlaying) {
                        player.play()
                    }
                }
            }
        }
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.attentionResultLD.observe(this) {
            binding.markTV.isEnabled = true
            if (it.status != DataResult.SUCCESS) {
                ToastUtils.setMessage(requireContext(), it.message)
            }

        }
        viewModel.collectResultLD.observe(this) {
            binding.rightOperation.collectLL.isEnabled = true
            if (it.status == DataResult.SUCCESS) {
                sendUpdateDynamicMessage()
                viewModel.dynamicLD.value?.let { dynamic->
                    setCollectStatusView(dynamic.favoriteStatus,dynamic.favoriteNum)
                }
            } else {
                ToastUtils.setMessage(requireContext(), it.message)
            }
        }
        viewModel.endorseResultLD.observe(this) {
            binding.rightOperation.endorseLL.isEnabled = true
            if (it.status == DataResult.SUCCESS) {
                sendUpdateDynamicMessage()
                viewModel.dynamicLD.value?.let { dynamic->
                    setEndorseStatusView(dynamic.praiseStatus,dynamic.praiseNum)
                }
            } else {
                ToastUtils.setMessage(requireContext(), it.message)
            }
        }

        viewModel.attentionStateLD.observe(this) {
            it?.let { status ->
                setAttentionStatusView(status)
                sendUpdateDynamicMessage()
            }
        }

        viewModel.giveGiftResultLD.observe(this){ dataResult->
            if (dataResult.status == DataResult.SUCCESS) {
                mEGiveGift?.let {
                    playGiftAnimation(it)
                }
            } else if (dataResult.status == DataResult.NOT_MONEY) {
                ToastUtils.setMessage(requireContext(),resources.getString(R.string.clique_not_money))
            } else {
                ToastUtils.setMessage(requireContext(),dataResult.message)
            }
        }
    }

    private fun sendUpdateDynamicMessage() {
        val messageEvent = MessageEvent(MessageEvent.Type.UPDATE_DYNAMIC_DATA)
        messageEvent.obj = viewModel.dynamicLD.value
        EventBus.getDefault().post(messageEvent)
    }


    private fun updateAttentionStatus() {

        viewModel.dynamicLD.value?.let {
            binding.markTV.isEnabled = false
            viewModel.updateAttentionStatus(it)
        }
    }

    private fun setAttentionStatusView(status: Int) {
        if (status == CommonStatus.YET) {
            binding.markTV.text = resources.getString(R.string.clique_yet_attention)
        } else {
            binding.markTV.text = resources.getString(R.string.clique_mark)
        }
    }

    private fun updateEndorseStatus() {
        viewModel.dynamicLD.value?.let {
            binding.rightOperation.endorseLL.isEnabled = false
            viewModel.updateEndorseStatus(it)
        }

    }


    /**
     * @describe: 显示评论弹出框
     * @date: 2020/3/10
     */
    fun showCommentView() {
        val commentListView = CommentListView(requireActivity(), viewModel.dynamicLD.value)
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
        viewModel.dynamicLD.value?.let {
            val giftListView = GiftHomeView(requireActivity(), it.userId)
            giftPopup = XPopup.Builder(requireActivity())
                .isViewMode(true)
                .hasShadowBg(false)
                .enableDrag(false)
                .asCustom(giftListView)
                .show()
        }
    }


    private fun updateCollectStatus() {
        viewModel.dynamicLD.value?.let {dynamic->
            binding.rightOperation.collectLL.isEnabled = false
            viewModel.updateCollectStatus(dynamic)
        }
    }


    override fun onPause() {
        super.onPause()
        binding.myVideoPlayer.player?.let {
            if (it.isPlaying) {
                it.pause()
            }
        }
        LogUtils.printI("fragment position=$position --- onPause")
    }

    override fun onResume() {
        super.onResume()
        binding.myVideoPlayer.player?.let {
            if (!it.isPlaying) {
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

    private fun playGiftAnimation(eGiveGift: EGiveGift) {
        val gift = eGiveGift.selectGift
        if (gift.giftType == Gift.Type.SVGA) {
            SVGAHelper.play(binding.svgaFL, gift.giftSvgaUrl)
        } else if (gift.giftType == Gift.Type.GIF) {

        } else if (gift.giftType == Gift.Type.PNG) {

            //开启雪花飘落效果
//            floatView.visibility = View.VISIBLE
//            floatView.start(shareIV,eGiveGift.selectGift.giftUrl,40)

        }
//        giftNumberPV = XPopup.Builder(this)
//            .hasShadowBg(false)
//            .popupAnimation(PopupAnimation.ScaleAlphaFromRightTop)
//            .isCenterHorizontal(true)
//            .atView(moreOperationIV)
//            .offsetY(resources.getDimension(R.dimen.dp_60).toInt()) //弹窗在y方向的偏移量
//            .offsetX(-resources.getDimension(R.dimen.dp_60).toInt())
//            .popupPosition(PopupPosition.Bottom)
//            .asCustom(GiftNumberView(this, eGiveGift.selectedNumber))
//            .show()

//        handler.postDelayed({
//            giftNumberPV?.dismiss()
//        }, 2000)
    }
}