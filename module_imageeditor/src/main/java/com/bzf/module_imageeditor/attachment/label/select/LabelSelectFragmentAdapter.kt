package com.bzf.module_imageeditor.attachment.label.select

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *@author: baizf
 *@date: 2023/3/14
 */
class LabelSelectFragmentAdapter(fragmentActivity: FragmentActivity, val picturePosition: Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putInt("position", picturePosition)
        return when (position) {
            0 -> {
                val labelAddressSelectFragment = LabelAddressSelectFragment()

                labelAddressSelectFragment.arguments = bundle
                labelAddressSelectFragment
            }
            else -> {
                val labelUserSelectFragment = LabelUserSelectFragment()
                labelUserSelectFragment.arguments = bundle
                labelUserSelectFragment
            }
        }

    }
}