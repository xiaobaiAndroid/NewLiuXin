package module.gift

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import module.common.data.entity.GiftCategory
import module.gift.list.GiftListFragment

class GiftPagerAdapter(fragmentActivity: FragmentActivity, val categories: MutableList<GiftCategory>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        val category = categories[position]

        val bundle = Bundle()
        bundle.putParcelable("giftCategory",category)
        bundle.putInt("position", position)

        val giftListFragment = GiftListFragment()
        giftListFragment.arguments = bundle
        return giftListFragment
    }

}