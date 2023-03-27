package module.dynamic.search.result

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.CommonListActivityBase
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.type.MediaType
import module.common.utils.ActivityLauncher
import module.common.utils.ToastUtils
import module.common.view.GridSpaceDecoration
import module.dynamic.R
import module.dynamic.databinding.DynamicActivitySearchResultBinding
import module.dynamic.home.category.list.DynamicAdapter

/*
* @describe:
* @author: bzf
* @date: 2023/3/27
*/
class SearchResultActivity : CommonListActivityBase<DynamicActivitySearchResultBinding,SearchResultVModel>() {


    val dynamicAdapter = DynamicAdapter(mutableListOf())

    var lastPosition = 0

    var keyword: String?=null

    override fun createViewModel(): SearchResultVModel {
        return viewModels<SearchResultVModel>().value
    }

    override fun getBindingView(): DynamicActivitySearchResultBinding {
        return DynamicActivitySearchResultBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {

        binding.actionBarView.setData(this,resources.getString(R.string.clique_search_result))
        binding.srl.setRefreshHeader(ClassicsHeader(this))

        binding.srl.setOnRefreshListener {
            viewModel.resetCurrentPage()
            viewModel.search(keyword)
        }

        val spanCount = 2
        binding.contentRV.layoutManager = GridLayoutManager(this,spanCount)
        binding.contentRV.adapter = dynamicAdapter
        val decoration = GridSpaceDecoration(dynamicAdapter, resources.getDimension(module.common.R.dimen.dp_8).toInt(), spanCount)
        decoration.setMargin(resources.getDimension(module.common.R.dimen.dp_8).toInt())
        decoration.setDrawHeader(true)
        binding.contentRV.addItemDecoration(decoration)

        dynamicAdapter.setEmptyView(getLoadingView())
        dynamicAdapter.loadMoreModule.setOnLoadMoreListener{
            viewModel.search(keyword)
        }


        dynamicAdapter.setOnItemClickListener { adapter, view, position ->
            lastPosition = position
            val entity = dynamicAdapter.getItem(position)
            if (entity.dynamic?.type == MediaType.IN_VIDEO.toInt()) {
                val item = dynamicAdapter.getItem(position)
                item.dynamic?.let {
                    ActivityLauncher.launchVideoDetail(
                        this,
                        arrayListOf(it),
                        1,
                        0,
                        MediaType.IN_VIDEO,
                        "",
                        false
                    )
                }
            }else{
                val multiEntity = dynamicAdapter.getItem(position)
                val dynamic = multiEntity.dynamic
                dynamic?.let {
                    ActivityLauncher.launchImgTxtDetail(this,it,entity.dynamic?.typeId.toString())
                }
            }
        }

        setObserver()
    }

    private fun setObserver() {
        viewModel.searResultLD.observe(this){ dataResult->
            val list = dataResult.t
            cancelLoadingAnimation()
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
                ToastUtils.setMessage(this, dataResult.message)
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.srl.isRefreshing)
                        binding.srl.finishRefresh()

                    dynamicAdapter.setEmptyView(getFailView())
                } else {
                    dynamicAdapter.loadMoreModule.loadMoreFail()
                }

            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        keyword = intent.getStringExtra("keyword")
        viewModel.search(keyword)
    }



}