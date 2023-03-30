package module.shop.near

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import module.common.base.BaseFragment
import module.shop.databinding.ShopFragmentNearShopBinding

/**
 *@author: baizf
 *@date: 2023/3/30
 */
class NearShopFragment: BaseFragment<ShopFragmentNearShopBinding, NearShopVModel>() {


    override fun createViewModel(): NearShopVModel {
        return viewModels<NearShopVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ShopFragmentNearShopBinding {
        return ShopFragmentNearShopBinding.inflate(inflater, container, false)
    }

    override fun initData() {
    }

    override fun initView() {
    }
}