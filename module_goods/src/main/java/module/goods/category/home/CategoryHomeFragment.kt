package module.goods.category.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import module.common.base.BaseFragment
import module.common.base.ShareLazyViewModelBase
import module.common.data.entity.GoodsCategory
import module.common.utils.ARouterHelper
import module.common.utils.ActivityLauncher
import module.common.utils.StatusBarUtils
import module.common.viewmodel.MainTabShareVModel
import module.goods.R
import module.goods.databinding.GoodsFragmentCategoryHomeBinding

const val GOODS_CATEGORY_HOME_ID = "0"

/**
 *@author: baizf
 *@date: 2023/3/27
 */
class CategoryHomeFragment: BaseFragment<GoodsFragmentCategoryHomeBinding, CategoryHomeVModel>() {


    lateinit var lazyLoadViewModel: CategoryHomeShareVModel

    private val fragmentAdapter: CategoryHomeFragmentAdapter by lazy{
        CategoryHomeFragmentAdapter(requireActivity(), mutableListOf())
    }

    override fun createViewModel(): CategoryHomeVModel {
        return viewModels<CategoryHomeVModel>().value
    }

    override fun createShareViewModel(): ShareLazyViewModelBase? {
        return activityViewModels<MainTabShareVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): GoodsFragmentCategoryHomeBinding {
        return GoodsFragmentCategoryHomeBinding.inflate(layoutInflater)
    }

    override fun initData() {
        val goodsCategory = GoodsCategory()
        goodsCategory.cateName = resources.getString(R.string.goods_recommend)
        goodsCategory.id = GOODS_CATEGORY_HOME_ID
        fragmentAdapter.addData(goodsCategory)
        viewModel.getCategories()


    }

    override fun initView() {

        lazyLoadViewModel = activityViewModels<CategoryHomeShareVModel>().value

        binding.allCategoryTV.setOnClickListener {
            ARouterHelper.openBottomToTop(activity, ARouterHelper.GOODS_CATEGORIES)
        }
        binding.searchCL.setOnClickListener {
            ActivityLauncher.launchGoodsSearch(requireActivity())
        }
        binding.shoppingCartIV.setOnClickListener {
            ARouterHelper.openPath(activity, ARouterHelper.SHOPPING_CART)
        }

        binding.contentVP.adapter = fragmentAdapter
        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.contentVP,) { tab, position ->
                tab.text = fragmentAdapter.list[position].cateName
            }
        tabLayoutMediator.attach()


        binding.contentVP.registerOnPageChangeCallback(object: OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lazyLoadViewModel.positionLD.value = position
            }

        })

        setObserver()
    }

    private fun setObserver() {
        viewModel.cateGoryResultLD.observe(this){
            it?.let {list->
                fragmentAdapter.addAll(list)
            }

        }
    }
}