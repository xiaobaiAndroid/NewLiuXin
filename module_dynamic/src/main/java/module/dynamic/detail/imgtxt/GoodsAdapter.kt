package module.dynamic.detail.imgtxt

import android.app.Activity
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.Goods
import module.common.utils.DensityUtil.getScreenWidth
import module.common.utils.ImageLoadHelper.load
import module.common.utils.StringUtils.packNull
import module.dynamic.R

class GoodsAdapter(activity: Activity, data: MutableList<Goods?>?) :
    BaseQuickAdapter<Goods?, BaseViewHolder>(R.layout.clique_item_recommend_goods, data),
    LoadMoreModule {
    private var imageSize = 0

    init {
        computeImageSize(activity)
    }

    private fun computeImageSize(activity: Activity) {
        val screenWidth = getScreenWidth(activity)
        val spanNumber = 2
        val spacing = activity.resources.getDimension(module.common.R.dimen.dp_12)
        val margin = activity.resources.getDimension(module.common.R.dimen.dp_12)
        imageSize = ((screenWidth - margin * 2 - spacing * (spanNumber - 1)) / spanNumber).toInt()
    }

    override fun convert(helper: BaseViewHolder, goods: Goods?) {
        if (goods != null) {
            val format = context.resources.getString(R.string.clique_format_sell_count)
            val goodsIV = helper.getView<ImageView>(R.id.goodsIV)
            val layoutParams = goodsIV.layoutParams
            if (layoutParams.height != imageSize && layoutParams.width != imageSize) {
                layoutParams.width = imageSize
                layoutParams.height = imageSize
                goodsIV.layoutParams = layoutParams
            }
            load(goodsIV, goods.goodsUrl)
            helper.setText(R.id.goodsNameTV, packNull(goods.goodsName))
                .setText(R.id.describeTV, packNull(goods.goodsTitle))
                .setText(R.id.priceTV, MONEY_TAG + goods.salePrice)
                .setText(R.id.sellCountTV, String.format(format, goods.sales.toString() + ""))
        }
    }

    companion object {
        private const val MONEY_TAG = "￥"
    }
}