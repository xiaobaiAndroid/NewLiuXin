package module.dynamic.home.category.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseFragment
import module.common.data.DataResult
import module.common.type.MediaType
import module.common.utils.ARouterHelper
import module.common.utils.DensityUtil
import module.common.utils.ToastUtils
import module.common.view.GridSpaceDecoration
import module.dynamic.databinding.DynamicFramentListBinding

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicListFragment : BaseFragment<DynamicFramentListBinding, DynamicListViewModel>() {

    var typeId: String? = null
    var mediaType: String? = null
    var dynamicAdapter = DynamicAdapter(mutableListOf())

    var lastPosition = 0

    var cityCode: String? = null

    override fun createViewModel(): DynamicListViewModel {
        return viewModels<DynamicListViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFramentListBinding {
        isAlone = true
        return DynamicFramentListBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        viewModel.dynamicDataResultLD.observe(this) { dataResult ->
            val list = dataResult.t
            if (dataResult.status == DataResult.SUCCESS) {
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.srl.isRefreshing)
                        binding.srl.finishRefresh()
                    dynamicAdapter.setList(list)
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
                ToastUtils.setMessage(requireContext(), dataResult.message)
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.srl.isRefreshing)
                        binding.srl.finishRefresh()
                } else {
                    dynamicAdapter.loadMoreModule.loadMoreFail()
                }

            }
        }

        binding.srl.autoRefresh(100)
    }

    override fun initView() {
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
                lifecycleScope.launch(Dispatchers.IO) {
                    val dynamics = viewModel.getOriginalDynamicData(dynamicAdapter.data)
                    withContext(Dispatchers.Main) {
                        val bundle = Bundle()
                        bundle.putInt("pageNumber", viewModel.getCurrentPage())
                        bundle.putInt("playPosition", lastPosition)
                        bundle.putString("typeId", typeId)
                        bundle.putBoolean("isLoadMore", true)
                        bundle.putParcelableArrayList("dynamics", dynamics)
                        ARouterHelper.openPath(activity, ARouterHelper.VIDEO_DETAIL, bundle)
                    }
                }

            } else {
                val multiEntity = dynamicAdapter.getItem(position)
                val dynamic = multiEntity.dynamic
                if (dynamic != null) {
                    val bundle = Bundle()
                    bundle.putParcelable("dynamic", dynamic)
                    bundle.putString("categoryId", typeId)
                    ARouterHelper.openPath(activity, ARouterHelper.IMGTXT_DETAIL, bundle)
                }
            }
        }

    }
}