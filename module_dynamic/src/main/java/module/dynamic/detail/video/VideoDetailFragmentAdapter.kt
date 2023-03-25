package module.dynamic.detail.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import module.common.data.entity.Dynamic

/**
 *@author: baizf
 *@date: 2023/3/24
 */
class VideoDetailFragmentAdapter(fragmentActivity: FragmentActivity, val dynamics: MutableList<Dynamic>): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return dynamics.size
    }

    override fun createFragment(position: Int): Fragment {
        val videoDetailFragment = VideoDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable("dynamic", dynamics[position])
        bundle.putInt("position", position)
        videoDetailFragment.arguments = bundle
        return videoDetailFragment
    }

    fun addData(dynamic: Dynamic) {
        dynamics.add(dynamic)
        notifyItemInserted(0)
    }

    fun addAllData(positionStart: Int,list: List<Dynamic>) {
        dynamics.addAll(list)
        notifyItemRangeInserted(positionStart,list.size)
    }

}