package module.goods.category.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import module.common.data.entity.GoodsCategory
import module.goods.category.home.list.GoodsListFragment

/**
 *@author: baizf
 *@date: 2023/3/27
 */
class CategoryHomeFragmentAdapter(fragmentActivity: FragmentActivity, val list: MutableList<GoodsCategory>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = GoodsListFragment()
        val bundle = Bundle()
        bundle.putParcelable("category", list[position])
        bundle.putInt("pagePosition",position)
        fragment.arguments = bundle

        return fragment
    }

    fun addData(goodsCategory: GoodsCategory) {
        val size = list.size
        list.add(goodsCategory)
        notifyItemInserted(size)
    }

    fun addAll(newList: MutableList<GoodsCategory>) {
        val size = newList.size
        val start = list.size
        list.addAll(newList)
        notifyItemRangeInserted(start, size)
    }
}