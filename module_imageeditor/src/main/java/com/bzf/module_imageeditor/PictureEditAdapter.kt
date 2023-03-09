package com.bzf.module_imageeditor

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *@author: baizf
 *@date: 2023/2/1
 */
class PictureEditAdapter(fragmentActivity: FragmentActivity, val list: java.util.ArrayList<PictureEntity>): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
            return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val pictureEntity = list[position]
        val bundle = Bundle()
        bundle.putParcelable("entity", pictureEntity)

        val pictureEditFragment = PictureEditFragment()
        pictureEditFragment.arguments = bundle
        return  pictureEditFragment
    }
}