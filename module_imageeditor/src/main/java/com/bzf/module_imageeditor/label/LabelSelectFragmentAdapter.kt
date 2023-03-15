package com.bzf.module_imageeditor.label

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *@author: baizf
 *@date: 2023/3/14
 */
class LabelSelectFragmentAdapter(fragmentActivity: FragmentActivity):  FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return LabelUserSelectFragment()
    }
}