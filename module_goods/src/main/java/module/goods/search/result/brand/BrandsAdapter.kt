package module.goods.search.result.brand

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.Brand
import module.common.utils.ImageLoadHelper.load
import module.common.utils.StringUtils.packNull
import module.goods.R

class BrandsAdapter(data: MutableList<Brand>?) :

    BaseQuickAdapter<Brand, BaseViewHolder>(R.layout.goods_item_brands, data) {
    override fun convert(holder: BaseViewHolder, item: Brand) {
        if (item.isSelect) {
            holder.setImageResource(R.id.statusIV, R.drawable.goods_ic_filtrate_selected)
        } else {
            holder.setImageResource(R.id.statusIV, R.drawable.goods_ic_filtrate_normal)
        }
        holder.setText(R.id.brandNameTV, packNull(item.brandName))
    }
}