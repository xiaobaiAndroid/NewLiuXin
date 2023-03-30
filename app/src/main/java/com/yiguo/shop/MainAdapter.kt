package com.yiguo.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import module.common.utils.ARouterHelper
import module.dynamic.home.DynamicTabHomeFragment
import module.goods.category.home.CategoryHomeFragment
import module.im.home.IMHomeFragment
import module.shop.near.NearShopFragment

class MainAdapter(fragmentActivity: FragmentActivity, private val size: Int) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = when (position) {
            1 -> DynamicTabHomeFragment()
            2 -> CategoryHomeFragment()
            3 -> NearShopFragment()
            4 -> ARouterHelper.getFragment(ARouterHelper.FRA_MY)
            else -> IMHomeFragment()
        }
        val bundle = Bundle()
        bundle.putInt("pagePosition",position)
        fragment.arguments = bundle
        return fragment
    }


}