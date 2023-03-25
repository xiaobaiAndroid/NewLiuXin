package module.dynamic.detail.more

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import lib.share.ShareView
import module.common.base.BaseFragment
import module.common.data.entity.UserInfo
import module.dynamic.databinding.DynamicFragmentMoreOperationBinding

/**
 *@author: baizf
 *@date: 2023/3/23
 */
class MoreOperationFragment: BaseFragment<DynamicFragmentMoreOperationBinding, MoreOperationViewModel>() {

    override fun createViewModel(): MoreOperationViewModel {
        return viewModels<MoreOperationViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DynamicFragmentMoreOperationBinding {
        return DynamicFragmentMoreOperationBinding.inflate(layoutInflater,container,false)
    }

    override fun initData() {
//        viewModel.getUserInfo {
//            val userInfo = it as UserInfo
//            if(userInfo.userId==dynamic?.userId){
//                binding.deleteTV.visibility = View.VISIBLE
//                binding.noSelfOperationLL.visibility = View.GONE
//            }else{
//                binding.deleteTV.visibility = View.GONE
//                binding.noSelfOperationLL.visibility = View.VISIBLE
//            }
//        }
    }

    override fun initView() {
        binding.reportTV.visibility = View.GONE
        binding.deleteTV.visibility = View.GONE
        binding.noSelfOperationLL.visibility = View.GONE

        binding.shareTV.setOnClickListener {
//            val shareView = ShareView(requireContext(),shareEntity)
//            XPopup.Builder(context)
//                .hasShadowBg(false)
//                .enableDrag(false)
//                .popupAnimation(PopupAnimation.TranslateFromBottom)
//                .asCustom(shareView)
//                .show()
//            dismiss()
        }

        /*举报*/
        binding.reportTV.setOnClickListener {

        }

        binding.deleteTV.setOnClickListener {

        }

        /*屏蔽*/
        binding.blacklistTV.setOnClickListener {

        }

        binding.cancelTV.setOnClickListener {

        }
        initData()
    }
}