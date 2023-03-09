package module.live

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import module.common.base.BaseFragment
import module.live.databinding.LiveFragmentHomeBinding
import module.live.publish.entrance.PublishEntranceActivity

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class LiveHomeFragment: BaseFragment<LiveFragmentHomeBinding,LiveHomeViewModel>() {
    override fun createViewModel(): LiveHomeViewModel {
        return viewModels<LiveHomeViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LiveFragmentHomeBinding {
        return LiveFragmentHomeBinding.inflate(inflater, container,false)
    }

    override fun initData() {

    }

    override fun initView() {
        startActivity(Intent(activity, PublishEntranceActivity::class.java))
    }
}