package module.dynamic.detail.imgtxt

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupAnimation
import com.lxj.xpopup.interfaces.SimpleCallback
import com.youth.banner.indicator.CircleIndicator
import lib.share.ShareEntity
import lib.svga.SVGAHelper
import module.audioplayer_lib.AudioPlayerEngine
import module.comment.CommentAdapter
import module.comment.CommentListView
import module.comment.input.InputContentView
import module.common.base.BaseActivity
import module.common.data.DataResult
import module.common.data.entity.Comment
import module.common.data.entity.Dynamic
import module.common.data.entity.Gift
import module.common.data.entity.ImgTxtData
import module.common.data.status.CommonStatus
import module.common.event.MessageEvent
import module.common.utils.*
import module.common.view.GridSpaceDecoration
import module.dynamic.R
import module.dynamic.databinding.DynamicActivityImgTxtDetailBinding
import module.dynamic.detail.imgtxt.ImgTxtBannerAdapter.Listener
import module.dynamic.detail.imgtxt.view.MoreOperationView
import module.gift.EGiveGift
import module.gift.GiftHomeView
import java.util.*

/*
* @describe:
* @author: bzf
* @date: 2023/3/19
*/
class ImgTxtDetailActivity :
    BaseActivity<DynamicActivityImgTxtDetailBinding, ImgTxtDetailViewModel>() {

    var dynamic: Dynamic? = null
    val goodsAdapter: GoodsAdapter by lazy {
        GoodsAdapter(this, null)
    }
    val commentAdapter = CommentAdapter(ArrayList<Comment>())

    private var giftPopup: BasePopupView? = null

    //    private var giftListView: GiftListView? = null
    private var giftNumberPV: BasePopupView? = null

    private val handler = Handler()
    var commentListPopup: BasePopupView? = null

    private lateinit var mAudioPlayer: AudioPlayerEngine

    private var mEGiveGift: EGiveGift?=null

    override fun createViewModel(): ImgTxtDetailViewModel {
        return viewModels<ImgTxtDetailViewModel>().value
    }

    override fun getBindingView(): DynamicActivityImgTxtDetailBinding {
        return DynamicActivityImgTxtDetailBinding.inflate(layoutInflater)
    }


    override fun disposeMessageEvent(event: MessageEvent?) {
        event ?: return
        super.disposeMessageEvent(event)
        if (MessageEvent.Type.SEND_COMMENT === event.type) {
            val content = event.obj as String
            viewModel.comment(content, dynamic)
        } else if (MessageEvent.Type.PLAY_VIDEO_SVGA === event.type) {
            mEGiveGift = event.obj as EGiveGift
            mEGiveGift?.let {
//                viewModel.giveGift(dynamic?.userId, it.selectGift.id, it.selectedNumber)

                SVGAHelper.play( binding.svgaFL, it.selectGift.giftSvgaUrl)
            }
            LogUtils.printI(GsonUtils.toJson(mEGiveGift))

            giftPopup?.dismiss()
        } else if (MessageEvent.Type.DELETE_DYNAMIC === event.type) {
            onBackPressed()
        }
    }


    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this, binding.actionBarCL)

        val layoutParams = binding.banner.layoutParams
        layoutParams.height = DensityUtil.getScreenWidth(this)

        binding.commentRV.layoutManager = LinearLayoutManager(this)
        binding.commentRV.adapter = commentAdapter

        val spanCount = 2
        binding.recommendRV.layoutManager = GridLayoutManager(this, spanCount)
        binding.recommendRV.adapter = goodsAdapter
        val spaceDecoration = GridSpaceDecoration(
            goodsAdapter,
            resources.getDimension(module.common.R.dimen.dp_12).toInt(),
            spanCount
        )
        spaceDecoration.setMargin(resources.getDimension(module.common.R.dimen.dp_12).toInt())
        binding.recommendRV.addItemDecoration(spaceDecoration)


        mAudioPlayer = AudioPlayerEngine(object: AudioPlayerEngine.Listener{
            override fun onError() {
                ToastUtils.setMessage(this@ImgTxtDetailActivity, resources.getString(R.string.play_music_not_exit))
            }

            override fun onStartPlaying() {

            }

            override fun onCompletion() {

            }

            override fun onPrepare() {
                binding.audioPlayLL.visibility = View.VISIBLE
                mAudioPlayer.play()
            }

        })

        initListener()

        setObserver()
    }

    private fun setObserver() {
        viewModel.commentsDataResultLD.observe(this) { dataResult ->
            val list = dataResult.t
            if (dataResult.status == DataResult.SUCCESS) {
                if (viewModel.getCurrentPage() == 1) {
                    commentAdapter.setList(list)
                } else {
                    list?.let { commentAdapter.addData(it) }
                }
                list?.let {
                    if (list.size > viewModel.getPageSize()) {
                        commentAdapter.loadMoreModule.loadMoreComplete()
                    } else {
                        commentAdapter.loadMoreModule.loadMoreEnd()
                    }
                } ?: commentAdapter.loadMoreModule.loadMoreEnd()
                viewModel.setNextPage()
            } else {
                ToastUtils.setMessage(this, dataResult.message)
                commentAdapter.loadMoreModule.loadMoreFail()

            }
        }

        viewModel.recommendGoodsDataResultLD.observe(this) {
            if (it.status == DataResult.SUCCESS) {
                goodsAdapter.setList(it.t)
            } else {
                ToastUtils.setMessage(this, it.message)
            }
        }

        viewModel.imgTxtResultLD.observe(this){
            if (it.status == DataResult.SUCCESS) {
                it.t?.let { imgTxt->
                    setImgTxtData(imgTxt)
                }
            } else {
                ToastUtils.setMessage(this, it.message)
            }
        }

        viewModel.endorseDataResultLD.observe(this){
            if(it.status == DataResult.SUCCESS){
                viewModel.imgTxtResultLD.value?.t?.let { imgTxt->
                    var number = imgTxt.praiseNum.toInt()
                    if (imgTxt.praiseStatus == CommonStatus.YET) {
                        imgTxt.praiseStatus = CommonStatus.NOT
                        number -= 1
                        imgTxt.praiseNum = number.toString()
                        ToastUtils.setMessage(this, resources.getString(R.string.clique_yet_cancel))
                    } else {
                        imgTxt.praiseStatus = CommonStatus.YET
                        number += 1
                        imgTxt.praiseNum = number.toString()
                        ToastUtils.setMessage(this, resources.getString(R.string.clique_endorse_success))
                    }
                    binding.endorseCountTV.text = imgTxt.praiseNum ?: "0"
                    setImgTxtData(imgTxt)
                }

            }else{

                ToastUtils.setMessage(this, resources.getString(R.string.clique_endorse_fail))
            }
            binding.endorseCountLL.isEnabled = true
        }

        viewModel.collectDataResultLD.observe(this){
            binding.collectNumberLL.isEnabled = true
            if(it.status == DataResult.SUCCESS){
                viewModel.imgTxtResultLD.value?.t?.let {imgTxt->
                    var number = imgTxt.favoriteNum.toInt()
                    if (imgTxt.favoriteStatus == CommonStatus.YET) {
                        imgTxt.favoriteStatus = CommonStatus.NOT
                        number -= 1
                        imgTxt.favoriteNum = number.toString()
                        ToastUtils.setMessage(this, resources.getString(R.string.clique_yet_cancel))
                    } else {
                        imgTxt.favoriteStatus = CommonStatus.YET
                        number += 1
                        imgTxt.favoriteNum = number.toString()
                        ToastUtils.setMessage(this, resources.getString(R.string.clique_collect_success))
                    }
                    binding.collectCountTV.text = imgTxt.favoriteNum

                    setCollectViewStatus(imgTxt)
                }
            }else{
                ToastUtils.setMessage(this, it.message)
            }
        }

        viewModel.attentionDataResultLD.observe(this){
            binding.attentionTV.isEnabled = true
            dynamic ?: return@observe
            if(it.status == DataResult.SUCCESS){
                if (dynamic!!.attentionUserStatus == CommonStatus.YET) {
                    dynamic!!.attentionUserStatus = CommonStatus.NOT
                    ToastUtils.setMessage(this, resources.getString(R.string.clique_attention_cancel))
                } else {
                    dynamic!!.attentionUserStatus = CommonStatus.YET
                    ToastUtils.setMessage(this, resources.getString(R.string.clique_yet_attention))
                }
                updateAttentionStatusView()
            }else{
                ToastUtils.setMessage(this,it.message)
            }
        }
        viewModel.attentionStateLD.observe(this){attentionState->
            attentionState?.let {
                dynamic?.attentionUserStatus = attentionState
            }
            updateAttentionStatusView()
        }

        viewModel.giveGiftResultLD.observe(this){ dataResult->
            if (dataResult.status == DataResult.SUCCESS) {
                mEGiveGift?.let {
                    playGiftAnimation(it)
                }
            } else if (dataResult.status == DataResult.NOT_MONEY) {
                ToastUtils.setMessage(this,resources.getString(R.string.clique_not_money))
            } else {
                ToastUtils.setMessage(this,dataResult.message)
            }
        }
    }

    private fun playGiftAnimation(eGiveGift: EGiveGift) {
        val gift = eGiveGift.selectGift
        if (gift.giftType == Gift.Type.SVGA) {
            SVGAHelper.play(binding.rootView, gift.giftSvgaUrl)
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

    private fun updateAttentionStatusView() {
        if (dynamic?.attentionUserStatus == CommonStatus.NOT) {
            binding.attentionTV.text = resources.getString(R.string.clique_mark)
        } else {
            binding.attentionTV.text = resources.getString(R.string.clique_yet_attention)
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        dynamic = intent?.getParcelableExtra<Dynamic?>("dynamic")
        LogUtils.printI("dynamic=" + GsonUtils.toJson(dynamic))

        dynamic ?: return

        viewModel.getImgTxtData(dynamic?.id)
        viewModel.getComments(dynamic?.id)
        viewModel.getAttentionStatusById(dynamic?.userId)
        viewModel.getRecommendGoods(dynamic?.typeId.toString())
    }

    private fun setImgTxtData(imgTxt: ImgTxtData) {
        ImageLoadHelper.loadCircle(binding.avatarIV, imgTxt.avatar)
        binding.nicknameTV.text = StringUtils.packNull(imgTxt.nickName)

        val imgTxtBannerAdapter = ImgTxtBannerAdapter(imgTxt.mediaSourceList, object : Listener {
            override fun changeSize(width: Int, height: Int) {
                binding.banner.layoutParams.height = height
                binding.banner.requestLayout()
            }

        })

        binding.banner.setAdapter(imgTxtBannerAdapter)
            .addBannerLifecycleObserver(this).indicator = CircleIndicator(this)


        binding.titleTV.text = StringUtils.packNull(imgTxt.title)
        binding.describeTV.text = StringUtils.packNull(imgTxt.description)

        val format = resources.getString(R.string.clique_format_comment_count)
        binding.commentCountTV.text =
            String.format(format, StringUtils.packNull(imgTxt.commentNum))
        binding.commentCountBottomTV.text = StringUtils.packNull(imgTxt.commentNum)

        binding.endorseCountTV.text = StringUtils.packNull(imgTxt.praiseNum)
        binding.collectCountTV.text = StringUtils.packNull(imgTxt.favoriteNum)

        setLikeStatus(imgTxt)

        setCollectViewStatus(imgTxt)

        binding.dateTV.text = DateUtils.dateToString(Date(imgTxt.createTime), DateUtils.FORMAT_3)
        binding.audioPlayTV.text = StringUtils.packNull(imgTxt.resourceName)

        binding.audioPlayLL.visibility = View.GONE
        imgTxt.musicUrl?.let {
            mAudioPlayer.prepare(it,true)
        }
    }

    private fun setCollectViewStatus(imgTxt: ImgTxtData) {
        if (imgTxt.favoriteStatus == CommonStatus.YET) {
            binding.collectIV.setImageResource(R.drawable.dynamic_ic_collect_normal)
        } else {
            binding.collectIV.setImageResource(R.drawable.dynamic_ic_collect_select)
        }
    }

    private fun setLikeStatus(imgTxt: ImgTxtData) {
        if (imgTxt.praiseStatus == CommonStatus.YET) {
            binding.endorseIV.setImageResource(R.drawable.dynamic_ic_like_selected)
        } else {
            binding.endorseIV.setImageResource(R.drawable.dynamic_ic_like_normal)
        }
    }


    private fun initListener() {
        binding.backIV.setOnClickListener {
            onBackPressed()
        }

        goodsAdapter.setOnItemChildClickListener { _, _, position ->
            val goods = goodsAdapter.getItem(position)
            val bundle = Bundle()
            bundle.putString(ARouterHelper.Key.PARAMS_GOODS_ID, goods?.id)
            ARouterHelper.openPath(this, ARouterHelper.GOODS_DETAIL, bundle)
        }

        binding.commentButtonTV.setOnClickListener {
            XPopup.Builder(this)
                .hasShadowBg(false)
                .enableDrag(false)
                .autoOpenSoftInput(true)
                .popupAnimation(PopupAnimation.TranslateFromBottom)
                .asCustom(InputContentView(this))
                .show()
        }

        binding.endorseCountLL.setOnClickListener {
            binding.endorseCountLL.isEnabled = false
            viewModel.endorse(dynamic?.id)
        }

        binding.collectNumberLL.setOnClickListener {
            binding.collectNumberLL.isEnabled = false
            viewModel.collect(dynamic)
        }

        binding.attentionTV.setOnClickListener {
            binding.attentionTV.isEnabled = false
            viewModel.attention(dynamic)
        }

        binding.giftIV.setOnClickListener {
            giftPopup?.let {
                giftPopup?.show()
            } ?: kotlin.run {
                giftPopup = XPopup.Builder(this)
                    .isViewMode(true)
                    .hasShadowBg(false)
                    .enableDrag(false)
                    .customHostLifecycle(lifecycle)
                    .popupAnimation(PopupAnimation.TranslateFromBottom)
                    .asCustom(GiftHomeView(this, dynamic?.userId))
                    .show()
            }

        }

        binding.moreOperationIV.setOnClickListener {
            showMoreOperationView()
        }

        binding.commentCountBottomTV.setOnClickListener {
            showCommentView()
        }


        binding.loadMoreTV.setOnClickListener {
            binding.loadMoreTV.isClickable = false
            viewModel.getComments(dynamic?.id)
        }

        binding.audioPlayLL.setOnClickListener {
            if(mAudioPlayer.isPlaying()){
                mAudioPlayer.pause()
            }else{
                mAudioPlayer.resume()
            }
        }

    }

    private fun showCommentView() {
        commentListPopup?.let {
            it.show()
        } ?: run {
            commentListPopup = XPopup.Builder(this)
                .hasShadowBg(false)
                .enableDrag(false)
                .isViewMode(true)
                .moveUpToKeyboard(false)
                .popupAnimation(PopupAnimation.TranslateFromBottom)
                .customHostLifecycle(lifecycle)
                .setPopupCallback(object : SimpleCallback() {
                    override fun onBackPressed(popupView: BasePopupView?): Boolean {
                        commentListPopup!!.dismiss()
                        return true
                    }

                })
                .asCustom(CommentListView(this, dynamic))
                .show()
        }
    }

    /**
     * @describe: 显示更多操作的view
     * @date: 2020/3/8
     */
    private fun showMoreOperationView() {

        val imageUrls = dynamic?.coverUrl?.split(",")
        val shareEntity = ShareEntity()
        shareEntity.title = dynamic?.title
        shareEntity.url = "https://liuxinchina.net/androidApp/imageMediaId="+ dynamic?.id
        shareEntity.contentType = ShareEntity.Type.WEB
        shareEntity.content = dynamic?.description
        shareEntity.showBitmapUrl = imageUrls?.get(0)
        shareEntity.videoUrl = dynamic?.mediaUrl
        XPopup.Builder(this)
            .hasShadowBg(false)
            .hasStatusBar(true)
            .asCustom(MoreOperationView(this, dynamic,binding.shareIV, shareEntity))
            .show()
    }


    override fun onResume() {
        mAudioPlayer.resume()
        super.onResume()
    }

    override fun onPause() {
        mAudioPlayer.pause()
        super.onPause()
    }


    override fun onDestroy() {
        mAudioPlayer.release()
        super.onDestroy()
    }

}