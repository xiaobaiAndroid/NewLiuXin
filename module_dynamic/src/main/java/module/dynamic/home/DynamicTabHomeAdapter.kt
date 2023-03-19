package module.dynamic.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import module.common.type.MediaType
import module.dynamic.home.category.DynamicCategoryHomeFragment

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicTabHomeAdapter(activity: FragmentActivity, private val cityCode: String?): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val homeFragment = DynamicCategoryHomeFragment()

        val bundle = Bundle()
        if (position == 0) {
            bundle.putString("type", MediaType.IN_VIDEO)
        } else {
            bundle.putString("type", MediaType.MULTI_CONTENT)
        }
        bundle.putString("cityCode", cityCode)
        homeFragment.arguments = bundle
        return homeFragment
    }
}