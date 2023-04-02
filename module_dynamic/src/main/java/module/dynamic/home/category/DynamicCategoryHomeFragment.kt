package module.dynamic.home.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import com.lxj.xpopup.enums.PopupPosition
import module.common.base.BaseFragment
import module.common.base.ShareLazyViewModelBase
import module.common.event.MessageEvent
import module.dynamic.view.category.CategorySelectView
import module.dynamic.databinding.DynamicFragmentDynamicHomeBinding
import module.dynamic.home.CategoryTabHomeShareVModel

/*
* @describe:
* @author: bzf
* @date: 2023/3/19
*/
class DynamicCategoryHomeFragment: BaseFragment<DynamicFragmentDynamicHomeBinding, DynamicCategoryHomeViewModel>() {

    lateinit var lazyLoadViewModel: CategoryHomeShareVModel


    override fun createViewModel(): DynamicCategoryHomeViewModel {
        return viewModels<DynamicCategoryHomeViewModel>().value
    }

    override fun createShareViewModel(): ShareLazyViewModelBase? {
        return activityViewModels<CategoryTabHomeShareVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFragmentDynamicHomeBinding {
        
        return DynamicFragmentDynamicHomeBinding.inflate(inflater,container,false)
    }

    override fun initData() {
        viewModel.categoriseLD.observe(this){
            val homeAdapter = DynamicCategoryHomeAdapter(
                requireActivity(),
                it,
                viewModel.mediaTypeLD.value,
                viewModel.cityCodeLD.value
            )
            binding.categoryVP.adapter = homeAdapter


            TabLayoutMediator(binding.categorySTL,binding.categoryVP
            ) { tab, position ->
                tab.text = it[position].cliqueCategory.typeName
            }.attach()
        }

        binding.categorySTL.addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                lazyLoadViewModel.positionLD.value = tab?.position ?: -1
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewModel.mediaTypeLD.value = arguments?.getString("type")
        viewModel.setMediaType(viewModel.mediaTypeLD.value)

        viewModel.cityCodeLD.value = arguments?.getString("cityCode")
        viewModel.getCategoryData(viewModel.mediaTypeLD.value)
    }

    override fun initView() {
        lazyLoadViewModel = activityViewModels<CategoryHomeShareVModel>().value

        binding.categoryVP.isSaveEnabled = false
    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if(MessageEvent.Type.LOGIN_SUCCESS === event?.type){
            if(isLoaded){
                viewModel.getCategoryData(viewModel.mediaTypeLD.value)
            }
        }
    }

}