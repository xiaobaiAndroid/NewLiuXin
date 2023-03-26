package module.address.city

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.address.R

/**
 *
 * @author: baizhengfu
 * @date: 2017/12/27
 */
class CityAdapter(data: MutableList<CitySuspension>?) :
    BaseQuickAdapter<CitySuspension, BaseViewHolder>(R.layout.map_item_choose_city, data) {
    override fun convert(holder: BaseViewHolder, item: CitySuspension) {
        if (item.city != null) {
            holder.setText(R.id.letterTV, item.city)
        } else {
            holder.setText(R.id.letterTV, "")
        }
    }
}