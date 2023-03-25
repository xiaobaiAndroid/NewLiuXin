package module.dynamic.home.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import com.lxj.xpopup.enums.PopupPosition
import module.common.base.BaseFragment
import module.dynamic.view.category.CategorySelectView
import module.dynamic.databinding.DynamicFragmentDynamicHomeBinding

/*
* @describe:
* @author: bzf
* @date: 2023/3/19
*/
class DynamicCategoryHomeFragment: BaseFragment<DynamicFragmentDynamicHomeBinding, DynamicCategoryHomeViewModel>() {



    override fun createViewModel(): DynamicCategoryHomeViewModel {
        return viewModels<DynamicCategoryHomeViewModel>().value
    }


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFragmentDynamicHomeBinding {
        isAlone = true
        return DynamicFragmentDynamicHomeBinding.inflate(inflater,container,false)
    }

    override fun initData() {
        viewModel.categoriseLD.observe(this){
            binding.categoryVP.adapter = DynamicCategoryHomeAdapter(requireActivity(),it,viewModel.mediaTypeLD.value, viewModel.cityCodeLD.value)
            TabLayoutMediator(binding.categorySTL,binding.categoryVP
            ) { tab, position ->
                tab.text = it[position].cliqueCategory.typeName
            }.attach()
        }


        viewModel.mediaTypeLD.value = arguments?.getString("type")
        viewModel.cityCodeLD.value = arguments?.getString("cityCode")
        viewModel.getCategoryData(viewModel.mediaTypeLD.value)
    }

    override fun initView() {
        binding.categoryVP.isSaveEnabled = false
    }

}