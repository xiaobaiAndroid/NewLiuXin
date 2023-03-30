package module.dynamic.home.category.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseFragment
import module.common.base.CommonListFragmentBase
import module.common.base.ShareLazyViewModelBase
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.event.MessageEvent
import module.common.type.MediaType
import module.common.utils.ActivityLauncher
import module.common.utils.DensityUtil
import module.common.utils.ToastUtils
import module.common.view.GridSpaceDecoration
import module.dynamic.databinding.DynamicFramentListBinding
import module.dynamic.home.CategoryTabHomeShareVModel
import module.dynamic.home.category.CategoryHomeShareVModel

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicListFragment : CommonListFragmentBase<DynamicFramentListBinding, DynamicListViewModel>() {

    var typeId: String? = null
    var mediaType: String? = null
    var dynamicAdapter = DynamicAdapter(mutableListOf())

    var lastPosition = 0

    var cityCode: String? = null

    override fun createViewModel(): DynamicListViewModel {
        return viewModels<DynamicListViewModel>().value
    }

    override fun createShareViewModel(): ShareLazyViewModelBase? {
        return activityViewModels<CategoryHomeShareVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFramentListBinding {
        
        return DynamicFramentListBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        viewModel.dynamicDataResultLD.observe(this) { dataResult ->
            val list = dataResult.t
            cancelLoadingAnimation()
            binding.srl.setEnableRefresh(true)
            if (dataResult.status == DataResult.SUCCESS) {
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.srl.isRefreshing)
                        binding.srl.finishRefresh()
                    dynamicAdapter.setList(list)

                    if(dynamicAdapter.data.isEmpty()){
                        dynamicAdapter.setEmptyView(getEmptyView())
                    }
                } else {
                    list?.let { dynamicAdapter.addData(it) }
                }
                list?.let {
                    if (list.size > viewModel.getPageSize()) {
                        dynamicAdapter.loadMoreModule.loadMoreComplete()
                    } else {
                        dynamicAdapter.loadMoreModule.loadMoreEnd()
                    }
                } ?: dynamicAdapter.loadMoreModule.loadMoreEnd()
                viewModel.setNextPage()
            } else {
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.srl.isRefreshing)
                        binding.srl.finishRefresh()
                    dynamicAdapter.setEmptyView(getFailView())
                } else {
                    dynamicAdapter.loadMoreModule.loadMoreFail()
                }
            }
        }

        viewModel.resetCurrentPage()
        viewModel.getDynamicData(typeId, mediaType, cityCode)

    }

    override fun initView() {
        binding.srl.setEnableRefresh(false)

        binding.srl.setRefreshHeader(ClassicsHeader(context))
        binding.srl.setOnRefreshListener {
            if (!dynamicAdapter.loadMoreModule.isLoading) {
                viewModel.resetCurrentPage()
                viewModel.getDynamicData(typeId, mediaType, cityCode)
            }
        }

        typeId = arguments?.getString("typeId")
        cityCode = arguments?.getString("cityCode")
        mediaType = arguments?.getString("mediaType")

        val spanCount = 2
        binding.contentRV.layoutManager = GridLayoutManager(context, spanCount)
        val decoration = GridSpaceDecoration(
            dynamicAdapter,
            resources.getDimension(module.common.R.dimen.dp_8).toInt(),
            spanCount
        )
        decoration.setMargin(resources.getDimension(module.common.R.dimen.dp_8).toInt())
        decoration.setDrawHeader(true)
        binding.contentRV.addItemDecoration(decoration)

        binding.contentRV.adapter = dynamicAdapter

        dynamicAdapter.setEmptyView(getLoadingView())

        lifecycleScope.launch(Dispatchers.IO) {
            val pictureSize =
                viewModel.computePictureSize(DensityUtil.getScreenWidth(requireActivity()))
            withContext(Dispatchers.Main) {
                dynamicAdapter.setPictureSize(pictureSize)
            }
        }

        dynamicAdapter.loadMoreModule.setOnLoadMoreListener {
            if (!binding.srl.isRefreshing) {
                viewModel.getDynamicData(typeId, mediaType, cityCode)
            }
        }

        dynamicAdapter.setOnItemClickListener { _, _, position ->
            lastPosition = position
            if (mediaType == MediaType.IN_VIDEO) {
                lifecycleScope.launch(Dispatchers.Default){
                    val dynamics = viewModel.getOriginalDynamicData(dynamicAdapter.data)
                    withContext(Dispatchers.Main){
                        ActivityLauncher.launchVideoDetail(
                            requireActivity(),
                            dynamics,
                            viewModel.getCurrentPage(),
                            lastPosition,
                            typeId,
                            cityCode,
                            true
                        )
                    }
                }

            } else {
                val multiEntity = dynamicAdapter.getItem(position)
                multiEntity.dynamic?.let {
                    ActivityLauncher.launchImgTxtDetail(requireActivity(), it, typeId)
                }

            }
        }

    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if(event?.type === MessageEvent.Type.UPDATE_DYNAMIC_DATA){
            val updateDynamic = event.obj  as Dynamic?
            updateDynamic?.let {
                for (index in dynamicAdapter.data.indices){
                    val entity = dynamicAdapter.getItem(index)
                    entity.dynamic?.apply {
                        if (id == updateDynamic.id) {
                            attentionUserStatus = updateDynamic.attentionUserStatus
                            commentNum = updateDynamic.commentNum
                            praiseStatus = updateDynamic.praiseStatus
                            praiseNum = updateDynamic.praiseNum
                            favoriteStatus = updateDynamic.favoriteStatus
                            favoriteNum = updateDynamic.favoriteNum
                            dynamicAdapter.notifyItemChanged(index)
                            return@let
                        }
                    }
                }
            }
        }
    }
}