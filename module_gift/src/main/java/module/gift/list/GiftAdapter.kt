package module.gift.list

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.Gift
import module.common.utils.ImageLoadHelper.loadFitCenter
import module.common.utils.StringUtils.packNull
import module.gift.R

class GiftAdapter(data: MutableList<Gift>?) :
    BaseQuickAdapter<Gift, BaseViewHolder>(R.layout.gift_item_gift, data), LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: Gift) {
        val itemRootCL = holder.getView<ConstraintLayout>(R.id.itemRootCL)
        if (item.isSelected) {
            itemRootCL.setBackgroundResource(R.drawable.gift_bg_gift_selected)
        } else {
            itemRootCL.setBackgroundResource(0)
        }
        val price = String.format(
            context.resources.getString(R.string.gift_formant_price),
            item.giftPrice
        )
        val giftIV = holder.getView<ImageView>(R.id.giftIV)
        loadFitCenter(giftIV, item.giftUrl)
        holder.setText(R.id.giftNameTV, packNull(item.giftName))
            .setText(R.id.priceTV, price)
    }
}