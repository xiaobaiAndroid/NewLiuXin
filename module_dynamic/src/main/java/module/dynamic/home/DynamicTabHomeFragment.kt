package module.dynamic.home

import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import module.common.base.BaseFragment
import module.common.event.MessageEvent
import module.common.utils.ARouterHelper
import module.common.utils.ActivityLauncher
import module.common.utils.StatusBarUtils
import module.dynamic.R
import module.dynamic.databinding.DynamicFramentTabHomeBinding
import module.dynamic.search.DynamicSearchActivity
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicTabHomeFragment: BaseFragment<DynamicFramentTabHomeBinding, DynamicTabHomeViewModel>() {

    /*是否是单行显示*/
    var singleShow = false

    var fragmentAdapter: DynamicTabHomeAdapter? = null


    override fun createViewModel(): DynamicTabHomeViewModel {
        return viewModels<DynamicTabHomeViewModel>().value
    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if(MessageEvent.Type.SELECTED_CITY === event?.type){
            viewModel.cityCodeLD.value = event.obj as String
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFramentTabHomeBinding {
        isAlone = true
        return DynamicFramentTabHomeBinding.inflate(inflater,container,false)
    }



    override fun initData() {


    }

    override fun initView() {
        binding.categoryVP.isUserInputEnabled = false

        binding.inVideoTV.typeface = Typeface.DEFAULT_BOLD

        StatusBarUtils.setMarginStatusBarHeight(activity,binding.actionBarCL)

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


        val function: (View) -> Unit = {
            ARouterHelper.openBottomToTop(activity, ARouterHelper.PUBLISH_HOME)
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
    }


}