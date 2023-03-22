package module.gift

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import module.gift.list.GiftAdapter

/**
 * @describe: 礼物列表PagerAdapter配置类
 * @date: 2020/3/8
 * @author: Mr Bai
 */
class PagerConfig {
    var adapter: GiftAdapter? = null
        private set

    /*选中的位置*/
    var selectPosition = -1

    /*pager的位置*/
    var pagerPosition = 0
    fun setAdapter(giftAdapter: GiftAdapter) {
        adapter = giftAdapter
        giftAdapter.setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int ->
            val gift = giftAdapter.getItem(position)
            gift.isSelected = true
            giftAdapter.notifyItemChanged(position)
            if (selectPosition != -1) {
                val lastSelectedGift = giftAdapter.getItem(selectPosition)
                lastSelectedGift.isSelected = false
                giftAdapter.notifyItemChanged(selectPosition)
            }
            selectPosition = position
        }
    }
}