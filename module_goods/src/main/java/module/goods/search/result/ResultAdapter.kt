package module.goods.search.result

import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.utils.ImageLoadHelper.loadRoundedCorners
import module.common.utils.StringUtils.packNull
import module.goods.R
import java.math.BigDecimal

class ResultAdapter(data: MutableList<ResultMultiEntity>?) :
    BaseMultiItemQuickAdapter<ResultMultiEntity, BaseViewHolder>(data), LoadMoreModule {
    private var pictureSize = 0

    init {
        addItemType(ResultMultiEntity.Type.SINGLE.ordinal, R.layout.goods_item_search_single)
        addItemType(ResultMultiEntity.Type.MULTI.ordinal, R.layout.goods_item_search_two)
    }

    protected override fun convert(helper: BaseViewHolder, item: ResultMultiEntity) {
        if (item != null) {
            val goods = item.goods
            if (goods != null) {
                val pictureIV = helper.getView<ImageView>(R.id.pictureIV)
                if (item.itemType == ResultMultiEntity.Type.MULTI.ordinal) {
                    val layoutParams = pictureIV.layoutParams
                    if (layoutParams.width != pictureSize) {
                        layoutParams.width = pictureSize
                        layoutParams.height = pictureSize
                        pictureIV.layoutParams = layoutParams
                    }
                }
                loadRoundedCorners(
                    pictureIV,
                    goods.goodsUrl,
                    context.resources.getDimension(module.common.R.dimen.dp_6).toInt()
                )
                val income1 = goods.income1
                val shareMoney = BigDecimal(income1).divide(BigDecimal(100.00)).toDouble()
                val sellPrice = BigDecimal(goods.salePrice).divide(BigDecimal(100.00)).toDouble()
                helper.setText(R.id.goodsNameTV, packNull(goods.goodsTitle))
                    .setText(R.id.priceTV, "￥" + String.format("%.2f", sellPrice))
                    .setText(R.id.shareMoneyTV, "分享红利￥" + String.format("%.2f", shareMoney))
                    .setText(R.id.saleNumberTV, "已售" + goods.sales)
            }
        }
    }

    fun setPictureSize(pictureSize: Int) {
        this.pictureSize = pictureSize
    }
}