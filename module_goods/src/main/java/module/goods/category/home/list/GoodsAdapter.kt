package module.goods.category.home.list

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.Goods
import module.common.utils.ImageLoadHelper.load
import module.common.utils.LogUtils
import module.goods.R
import java.math.BigDecimal

class GoodsAdapter(data: MutableList<Goods>?) :
    BaseQuickAdapter<Goods, BaseViewHolder>(R.layout.goods_item_goods, data), LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: Goods) {
        val goodsIV = holder.getView<ImageView>(R.id.goodsIV)
        load(goodsIV, item.goodsUrl)
        val income1 = item.income1
        val shareMoney = BigDecimal(income1).divide(BigDecimal(100.00)).toDouble()
        val sellPrice = BigDecimal(item.salePrice).divide(BigDecimal(100.00)).toDouble()
        holder.setText(R.id.titleTV, item.goodsTitle)
            .setText(R.id.shareMoneyTV, "分享红利￥" + String.format("%.2f", shareMoney))
            .setText(R.id.sellNumberTV, "已售" + item.sales)
            .setText(R.id.priceTV, "￥" + String.format("%.2f", sellPrice))

    }
}