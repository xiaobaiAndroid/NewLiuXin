package module.im.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import module.common.base.BaseFragment
import module.im.databinding.ImFragmentHomeBinding

/**
 *@author: baizf
 *@date: 2023/3/30
 */
class IMHomeFragment: BaseFragment<ImFragmentHomeBinding, IMHomeVModel>() {


    override fun createViewModel(): IMHomeVModel {
        return viewModels<IMHomeVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ImFragmentHomeBinding {
        return ImFragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initData() {
    }

    override fun initView() {
    }
}