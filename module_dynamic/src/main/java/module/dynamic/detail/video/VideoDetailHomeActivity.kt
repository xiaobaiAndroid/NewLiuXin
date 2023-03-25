package module.dynamic.detail.video

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.lxj.xpopup.core.BasePopupView
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import lib.share.ShareEntity
import module.common.base.BaseActivity
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.utils.*
import module.dynamic.R
import module.dynamic.databinding.DynamicActivityVideoDetailBinding
import module.gift.EGiveGift

private val VideoDetailHomeActivity.i: Int
    get() = intent.getIntExtra("pageNumber", 1)

/*
* @describe:
* @author: bzf
* @date: 2023/3/22
*/
class VideoDetailHomeActivity :
    BaseActivity<DynamicActivityVideoDetailBinding, VideoDetailHomeViewModel>() {


    /*是否需要加载更多*/
    var isLoadMore = false


    var giftNumberPV: BasePopupView? = null

    var numberSelectPV: BasePopupView? = null

    var eGiveGift: EGiveGift? = null

    /*视频分类的id*/
    private var mTypeId: String? = null
    private var mCityCode: String? = null



    private val mAFPort: VideoDetailActFraPort by viewModels()

    private val videoAdapter: VideoDetailFragmentAdapter by lazy {
        VideoDetailFragmentAdapter(this, mutableListOf())
    }


    override fun createViewModel(): VideoDetailHomeViewModel {
        return viewModels<VideoDetailHomeViewModel>().value
    }

    override fun getBindingView(): DynamicActivityVideoDetailBinding {
        return DynamicActivityVideoDetailBinding.inflate(layoutInflater)
    }

    override fun initStatusBar() {
        ImmersionBarUtils.init(this, module.common.R.color.cl_000000, true)
    }

    override fun initView(savedInstanceState: Bundle?) {
        val moreView =
            LayoutInflater.from(this).inflate(R.layout.dynamic_layout_more_operation, null)
        binding.actionBarView.addMoreOperationView(moreView)
        binding.actionBarView.setBackgroundColor(Color.TRANSPARENT)
        binding.actionBarView.setDarkNight(true)
        binding.actionBarView.setData(this, "")

        StatusBarUtils.setMarginStatusBarHeight(this, binding.smartRefreshLayout)

        moreView.findViewById<ImageView>(R.id.moreIV).setOnClickListener {
            showMoreOperationView()
        }

        binding.smartRefreshLayout.setRefreshHeader(ClassicsHeader(this))
        binding.smartRefreshLayout.setRefreshFooter(ClassicsFooter(this))
        binding.smartRefreshLayout.setOnLoadMoreListener {
            if (!viewModel.isLoading) {
                viewModel.getVideos(mTypeId,mCityCode)
            }
        }
        binding.smartRefreshLayout.setOnRefreshListener {
            viewModel.resetCurrentPage()
            viewModel.getVideos(mTypeId,mCityCode)
        }
        initViewPager()

        setupObserve()
    }


    override fun initData(savedInstanceState: Bundle?) {
        val pageNumber = intent.getIntExtra("pageNumber", 1)
        viewModel.setPageNumber(pageNumber)

        mAFPort.currentPlayPositionLD.value = intent.getIntExtra("playPosition", 0)
        isLoadMore = intent.getBooleanExtra("isLoadMore", false)
        mTypeId = intent.getStringExtra("typeId")
        mCityCode = intent.getStringExtra("cityCode")



        isLoadMore = intent.getBooleanExtra("isLoadMore", false)

        intent.getParcelableArrayListExtra<Dynamic?>("dynamics")?.let {
            videoAdapter.addAllData(0,it)
        }

        binding.contentVP.setCurrentItem( mAFPort.currentPlayPositionLD.value ?: 0,false)
    }


    private fun setupObserve() {
        viewModel.videosResultLD.observe(this) { dataResult ->
            val list = dataResult.t
            if (dataResult.status == DataResult.SUCCESS) {
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.smartRefreshLayout.isRefreshing)
                        binding.smartRefreshLayout.finishRefresh()
                    list?.let {
                        it.removeAt(0)
                        val startPosition = (mAFPort.currentPlayPositionLD.value ?: 0) + 1
                        videoAdapter.addAllData(startPosition,it)
                    }

                } else {
                    list?.let {
                        videoAdapter.addAllData(videoAdapter.dynamics.size,it)
                    }
                }

                list?.let {
                    if(binding.smartRefreshLayout.isLoading){
                        if (list.size > viewModel.getPageSize()) {
                            binding.smartRefreshLayout.finishLoadMore()
                        } else {
                            binding.smartRefreshLayout.finishLoadMoreWithNoMoreData()
                        }
                    }
                } ?:binding.smartRefreshLayout.finishLoadMoreWithNoMoreData()
                viewModel.setNextPage()
            } else {
                ToastUtils.setMessage(this, dataResult.message)
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.smartRefreshLayout.isRefreshing)
                        binding.smartRefreshLayout.finishRefresh()
                } else {
                    binding.smartRefreshLayout.finishLoadMore()
                }

            }
        }


    }


    private fun initViewPager() {
        videoAdapter.stateRestorationPolicy
        binding.contentVP.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.contentVP.adapter = videoAdapter
        //缓冲两个页面
        binding.contentVP.offscreenPageLimit = 2
        //取消动画
        binding.contentVP.setPageTransformer { page, position -> }

        binding.contentVP.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mAFPort.currentPlayPositionLD.value?.let {lastPlayPosition->
                    mAFPort.currentPlayPositionLD.value = position

                    //加载下一页
                    if (position > (videoAdapter.dynamics.size - 4)) {
                        if (!viewModel.isLoading) {
                            viewModel.getVideos(mTypeId,mCityCode)
                        }
                    }
                }
            }
        })

//        videoAdapter.addChildClickViewIds(R.id.unfoldTV, R.id.endorseLL, R.id.collectLL, R.id.commentLL, R.id.giftLL, R.id.chatLL, R.id.markTV)
//        videoAdapter.setOnItemChildClickListener { adapter, view, position ->
//            when (view.id){
//                R.id.unfoldTV -> {
//                    val video = videoAdapter.getItem(position)
//                    video.unfold = !video.unfold
//                    videoAdapter.notifyItemChanged(position, PayloadInfo.CONTENT_UNFOLD)
//                }
//                R.id.endorseLL -> {
//                    updateEndorseStatus(position)
//                }
//                R.id.collectLL -> {
//                    updateCollectStatus(position)
//                }
//                R.id.commentLL -> {
//                    showCommentView(videoAdapter.getItem(position))
//                }
//                R.id.giftLL -> {
//                    showGiftPopup()
//                }
//                R.id.chatLL -> {
//                    val video = videoAdapter.getItem(position)
//                    ARouterHelper.toIMUserHome(this,video.userId)
//                }
//                R.id.markTV -> {
//                    updateAttentionStatus(position)
//                }
//            }
//
//        }
    }


    private fun showMoreOperationView() {
        val shareEntity = ShareEntity()
        val video = videoAdapter.dynamics[mAFPort.currentPlayPositionLD.value ?: 0]
        shareEntity.title = video.title
        shareEntity.content = video.description
        shareEntity.showBitmapUrl = video.coverUrl
        shareEntity.videoUrl = video.mediaUrl

//        XPopup.Builder(this)
//            .hasShadowBg(false)
//            .asCustom(MoreOperationView(this, video.id, video.userId, shareEntity))
//            .show()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBackPressed() {

        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()

    }

}