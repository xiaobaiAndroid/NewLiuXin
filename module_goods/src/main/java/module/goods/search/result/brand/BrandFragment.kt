package module.goods.search.result.brand

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import module.common.base.BaseFragment
import module.common.base.ShareLazyViewModelBase
import module.common.data.entity.Brand
import module.common.utils.DensityUtil
import module.common.utils.StatusBarUtils
import module.common.view.GridSpaceDecoration
import module.common.view.LinearDividerDecoration
import module.common.view.LinearSpaceDecoration
import module.goods.R
import module.goods.databinding.GoodsFragmentBrankListBinding
import module.goods.databinding.GoodsFragmentSearchFilterBinding
import module.goods.search.result.SearchResultShareVModel

/**
 *@author: baizf
 *@date: 2023/4/2
 */
class BrandFragment: BaseFragment<GoodsFragmentBrankListBinding, BrandVModel>() {

    val brandsAdapter = BrandsAdapter(mutableListOf<Brand>())

    override fun createViewModel(): BrandVModel {
        return viewModels<BrandVModel>().value
    }

    val brands = mutableListOf<String>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): GoodsFragmentBrankListBinding {
        return GoodsFragmentBrankListBinding.inflate(layoutInflater)
    }

    override fun createShareViewModel(): ShareLazyViewModelBase? {
        return activityViewModels<SearchResultShareVModel>().value
    }


    override fun initView() {
        binding.contentRV.layoutManager = GridLayoutManager(requireContext(),3)
        binding.contentRV.adapter = brandsAdapter


        val gridSpaceDecoration =
            GridSpaceDecoration(brandsAdapter, DensityUtil.dip2px(requireContext(), 10f), 3)
        binding.contentRV.addItemDecoration(gridSpaceDecoration)

        brandsAdapter.setOnItemClickListener { adapter, view, position ->
            val item = brandsAdapter.getItem(position)
            item.isSelect = !item.isSelect
            if(item.isSelect){
                brands.add(item.id)
            }
            brandsAdapter.notifyItemChanged(position)
        }

        binding.resetTV.setOnClickListener {
            for (brand in brandsAdapter.data){
                brand.isSelect = false
            }
            brands.clear()
            brandsAdapter.notifyDataSetChanged()
        }

        binding.confirmTV.setOnClickListener {
            val resultShareVModel = shareVModel as SearchResultShareVModel
            resultShareVModel.selectBrandsLD.value?.apply {
                clear()
                addAll(brands)
            } ?: kotlin.run {
                resultShareVModel.selectBrandsLD.value = brands
            }
        }
        setObserver()
    }

    private fun setObserver() {
        viewModel.brandsLD.observe(this){
            brandsAdapter.setList(it)
        }
    }


    override fun initData() {
        val keyword = requireArguments().getString("keyword")
        viewModel.getBrands(keyword)
    }
}