package module.goods.category.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.header.ClassicsHeader
import module.common.base.CommonListFragmentBase
import module.common.data.DataResult
import module.common.data.entity.Goods
import module.common.data.entity.GoodsCategory
import module.common.utils.ActivityLauncher
import module.common.utils.ToastUtils
import module.common.view.LinearSpaceDecoration
import module.goods.databinding.GoodsFragmentGoodsListBinding

/**
 *@author: baizf
 *@date: 2023/3/27
 */
class GoodsListFragment: CommonListFragmentBase<GoodsFragmentGoodsListBinding, GoodsListVModel>() {

    var goodsCategory: GoodsCategory? = null

    var indexHeaderView: IndexHeaderView? = null

    val goodsAdapter = GoodsAdapter(ArrayList<Goods>())

    override fun createViewModel(): GoodsListVModel {
        return viewModels<GoodsListVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): GoodsFragmentGoodsListBinding {
       return GoodsFragmentGoodsListBinding.inflate(layoutInflater,container,false)
    }

    override fun initData() {
        viewModel.getBannerData(goodsCategory?.id)
        viewModel.getChildCategory(goodsCategory?.id)
    }

    override fun initView() {
        goodsCategory  = arguments?.getParcelable<GoodsCategory>("category")

        binding.smartRefreshLayout.setRefreshHeader(ClassicsHeader(context))
        binding.contentRV.layoutManager = LinearLayoutManager(context)
        goodsAdapter.headerWithEmptyEnable = true
        binding.contentRV.adapter = goodsAdapter

        goodsAdapter.setEmptyView(getLoadingView())
        indexHeaderView = context?.let { IndexHeaderView(it,viewLifecycleOwner) }

        indexHeaderView?.let { goodsAdapter.addHeaderView(it) }

        val linearSpaceDecoration = LinearSpaceDecoration(goodsAdapter, resources.getDimension(module.common.R.dimen.dp_12).toInt())
        binding.contentRV.addItemDecoration(linearSpaceDecoration)

        initListener()

        setObserver()
    }

    private fun setObserver() {
        viewModel.bannerLD.observe(this){
            indexHeaderView?.setBannerData(it)
        }
        viewModel.categoriesLD.observe(this){
            cancelLoadingAnimation()
            indexHeaderView?.setCategories(it)
            if(!it.isNullOrEmpty()){
                viewModel.getGoodsList()
            }
        }
        viewModel.goodsListResultLD.observe(this){ dataResult->
            val list = dataResult.t
            if (dataResult.status == DataResult.SUCCESS) {
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.smartRefreshLayout.isRefreshing)
                        binding.smartRefreshLayout.finishRefresh()
                    goodsAdapter.setList(list)
                    if(goodsAdapter.data.isEmpty()){
                        goodsAdapter.setEmptyView(getEmptyView())
                    }
                } else {
                    list?.let { goodsAdapter.addData(it) }
                }
                list?.let {
                    if (list.size > viewModel.getPageSize()) {
                        goodsAdapter.loadMoreModule.loadMoreComplete()
                    } else {
                        goodsAdapter.loadMoreModule.loadMoreEnd()
                    }
                } ?: goodsAdapter.loadMoreModule.loadMoreEnd()
                viewModel.setNextPage()
            } else {
                ToastUtils.setMessage(requireContext(), dataResult.message)
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.smartRefreshLayout.isRefreshing)
                        binding.smartRefreshLayout.finishRefresh()

                    goodsAdapter.setEmptyView(getFailView())
                } else {
                    goodsAdapter.loadMoreModule.loadMoreFail()
                }

            }
        }
    }

    private fun initListener() {
        binding.smartRefreshLayout.setOnRefreshListener {
            viewModel.resetCurrentPage()
            viewModel.getBannerData(goodsCategory?.id)
            viewModel.getChildCategory(goodsCategory?.id)
        }

        goodsAdapter.loadMoreModule.setOnLoadMoreListener{
            viewModel.getGoodsList()
        }

        goodsAdapter.setOnItemClickListener { _, view, position ->
            val goods = goodsAdapter.getItem(position)
            ActivityLauncher.launchGoodsDetail(requireActivity(),goods.goodsId,goods.actId)
        }

    }
}