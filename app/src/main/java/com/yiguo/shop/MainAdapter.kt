package com.yiguo.shop

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import module.common.utils.ARouterHelper

class MainAdapter(fm: FragmentManager?, private val size: Int) : FragmentPagerAdapter(
    fm!!
) {
    override fun getItem(position: Int): Fragment {
        val fragment: Fragment = when (position) {
            1 -> ARouterHelper.getFragment(ARouterHelper.FRA_MY)
            2 -> ARouterHelper.getFragment(ARouterHelper.FRA_MY)
            3 -> ARouterHelper.getFragment(ARouterHelper.FRA_MY)
            4 -> ARouterHelper.getFragment(ARouterHelper.FRA_MY)
            0 -> ARouterHelper.getFragment(ARouterHelper.FRA_MY)
            else -> ARouterHelper.getFragment(ARouterHelper.FRA_MY)
        }
        return fragment
    }

    override fun getCount(): Int {
        return size
    }
}