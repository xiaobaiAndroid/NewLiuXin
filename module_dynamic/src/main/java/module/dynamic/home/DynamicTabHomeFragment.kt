package module.dynamic.home

import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import module.common.base.BaseFragment
import module.common.base.ShareLazyViewModelBase
import module.common.event.MessageEvent
import module.common.utils.ARouterHelper
import module.common.utils.ActivityLauncher
import module.common.utils.StatusBarUtils
import module.common.viewmodel.MainTabShareVModel
import module.dynamic.databinding.DynamicFramentTabHomeBinding
import module.dynamic.search.DynamicSearchActivity

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicTabHomeFragment: BaseFragment<DynamicFramentTabHomeBinding, DynamicTabHomeViewModel>() {

    /*是否是单行显示*/
    var singleShow = false

    var fragmentAdapter: DynamicTabHomeAdapter? = null

    lateinit var lazyLoadViewModel: ShareLazyViewModelBase
    override fun createViewModel(): DynamicTabHomeViewModel {
        return viewModels<DynamicTabHomeViewModel>().value
    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if(MessageEvent.Type.SELECTED_CITY === event?.type){
            viewModel.cityCodeLD.value = event.obj as String
        }
    }

    override fun createShareViewModel(): ShareLazyViewModelBase? {
        return activityViewModels<MainTabShareVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFramentTabHomeBinding {

        return DynamicFramentTabHomeBinding.inflate(inflater,container,false)
    }



    override fun initData() {

    }

    override fun initView() {

        lazyLoadViewModel = activityViewModels<CategoryTabHomeShareVModel>().value

        binding.categoryVP.isUserInputEnabled = false

        binding.inVideoTV.typeface = Typeface.DEFAULT_BOLD

//        StatusBarUtils.setMarginStatusBarHeight(activity,binding.actionBarCL)

        binding.inVideoTV.setOnClickListener {
            binding.inVideoTV.typeface = Typeface.DEFAULT_BOLD
            binding.multiContentTV.typeface = Typeface.DEFAULT
            binding.categoryVP.setCurrentItem(0,false)
        }
        binding.multiContentTV.setOnClickListener {
            binding.inVideoTV.typeface = Typeface.DEFAULT
            binding.multiContentTV.typeface = Typeface.DEFAULT_BOLD
            binding.categoryVP.setCurrentItem(1,false)
        }


        binding.cameraIV.setOnClickListener{
            ARouterHelper.openPath(requireActivity(),ARouterHelper.PUBLISH_ENTRANCE)
        }

        binding.searchIV.setOnClickListener {
            startActivity(Intent(requireActivity(),DynamicSearchActivity::class.java))
        }

        binding.locationCL.setOnClickListener {
            ActivityLauncher.launchChooseCity(requireActivity())
        }
        fragmentAdapter = DynamicTabHomeAdapter(requireActivity(),viewModel.cityCodeLD.value)
        binding.categoryVP.adapter = fragmentAdapter
        binding.categoryVP.isSaveEnabled = false

        binding.categoryVP.registerOnPageChangeCallback(object: OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lazyLoadViewModel.positionLD.value = position
            }
        })

    }


}