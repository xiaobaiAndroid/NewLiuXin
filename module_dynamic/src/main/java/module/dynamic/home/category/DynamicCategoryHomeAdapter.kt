package module.dynamic.home.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import module.dynamic.home.category.list.DynamicListFragment
import module.dynamic.view.category.CategoryMultiItem

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicCategoryHomeAdapter(activity: FragmentActivity, private val list: MutableList<CategoryMultiItem>, private val mediaType: String?, private val cityCode: String?): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val homeFragment = DynamicListFragment()

        val bundle = Bundle()
        bundle.putString("typeId", list[position].cliqueCategory.id)
        bundle.putString("mediaType", mediaType)
        bundle.putString("cityCode", cityCode)
        bundle.putInt("pagePosition",position)
        homeFragment.arguments = bundle
        return homeFragment
    }
}