package module.dynamic.detail.imgtxt

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.core.BasePopupView
import com.youth.banner.indicator.CircleIndicator
import module.audioplayer_lib.AudioPlayerEngine
import module.common.base.BaseActivity
import module.common.data.DataResult
import module.common.data.entity.Comment
import module.common.data.entity.Dynamic
import module.common.data.entity.ImgTxtData
import module.common.data.status.CommonStatus
import module.common.event.MessageEvent
import module.common.event.entity.EGiveGift
import module.common.utils.*
import module.common.view.GridSpaceDecoration
import module.dynamic.R
import module.dynamic.databinding.DynamicActivityImgTxtDetailBinding
import module.dynamic.detail.imgtxt.ImgTxtBannerAdapter.*
import java.util.*
import kotlin.collections.ArrayList

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
            val eGiveGift = event.obj as EGiveGift
            viewModel.giveGift(dynamic?.userId, eGiveGift, eGiveGift.selectedNumber)
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

        binding.banner.setAdapter(ImgTxtBannerAdapter(imgTxt.mediaSourceList, object : Listener {
            override fun changeSize(width: Int, height: Int) {
                if (binding.banner.layoutParams.height != height) {
                    binding.banner.layoutParams.height = height
                    binding.banner.requestLayout()
                }
            }

        }))
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
//            XPopup.Builder(this)
//                .hasShadowBg(false)
//                .enableDrag(false)
//                .autoOpenSoftInput(true)
//                .popupAnimation(PopupAnimation.TranslateFromBottom)
//                .asCustom(InputContentView(this))
//                .show()
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
//            giftListView = GiftListView(this)
//            giftPopup = XPopup.Builder(this)
//                .hasShadowBg(false)
//                .enableDrag(false)
//                .popupAnimation(PopupAnimation.TranslateFromBottom)
//                .setPopupCallback(object : XPopupCallback {
//                    override fun onBackPressed(popupView: BasePopupView?): Boolean {
//                        giftListView?.destory()
//                        return true
//                    }
//
//                    override fun onDrag(
//                        popupView: BasePopupView?,
//                        value: Int,
//                        percent: Float,
//                        upOrLeft: Boolean
//                    ) {
//                    }
//
//                    override fun onDismiss(popupView: BasePopupView?) {
//                    }
//
//                    override fun onKeyBoardStateChanged(popupView: BasePopupView?, height: Int) {
//                    }
//
//                    override fun beforeShow(popupView: BasePopupView?) {
//                    }
//
//                    override fun onCreated(popupView: BasePopupView?) {
//                    }
//
//                    override fun beforeDismiss(popupView: BasePopupView?) {
//                    }
//
//                    override fun onShow(popupView: BasePopupView?) {
//                    }
//
//                })
//                .asCustom(giftListView)
//                .show()
        }

        binding.moreOperationIV.setOnClickListener {
            showMoreOperationView()
        }

        binding.commentCountBottomTV.setOnClickListener {
//            commentListPopup = XPopup.Builder(this)
//                .hasShadowBg(false)
//                .enableDrag(false)
//                .moveUpToKeyboard(false)
//                .popupAnimation(PopupAnimation.TranslateFromBottom)
//                .setPopupCallback(object : SimpleCallback() {
//                    override fun onBackPressed(popupView: BasePopupView?): Boolean {
//                        commentListPopup!!.dismiss()
//                        return true
//                    }
//
//                })
//                .asCustom(CommentListView(this, dynamic))
//                .show()
        }

        binding.audioPlayLL.setOnClickListener {
//            if (AudioPlayerHelper.instance.isPlaying()) {
//                AudioPlayerHelper.instance.pause()
//                audioPlayTV.visibility = View.GONE
//            } else {
//                if (AudioPlayerHelper.instance.isCompletion()) {
//                    createAudioPlayer()
//                } else {
//                    AudioPlayerHelper.instance.start()
//                }
//                audioPlayTV.visibility = View.VISIBLE
//            }
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

    /**
     * @describe: 显示更多操作的view
     * @date: 2020/3/8
     */
    private fun showMoreOperationView() {

//        val imageUrls = dynamic.coverUrl.split(",")
//        val shareEntity = ShareEntity()
//        shareEntity.title = dynamic.title
//        shareEntity.contentType = ShareEntity.Type.MINI_APP
//        shareEntity.content = dynamic.description
//        shareEntity.showBitmapUrl = imageUrls[0]
//        shareEntity.videoUrl = dynamic.mediaUrl
//        XPopup.Builder(this)
//            .hasShadowBg(false)
//            .asCustom(MoreOperationView(this, dynamic,shareIV, shareEntity))
//            .show()
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