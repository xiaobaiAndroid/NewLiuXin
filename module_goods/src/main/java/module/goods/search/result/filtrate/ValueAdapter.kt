package module.goods.search.result.filtrate

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.SkuAttributeValue
import module.goods.R

class ValueAdapter(data: MutableList<SkuAttributeValue>?) :
    BaseQuickAdapter<SkuAttributeValue, BaseViewHolder>(
        R.layout.goods_item_search_filtrate_value,
        data
    ), LoadMoreModule {
    override fun convert(helper: BaseViewHolder, item: SkuAttributeValue) {
        if (item.isSelect) {
            helper.setBackgroundResource(
                R.id.contentTV,
                R.drawable.goods_bg_attribute_value_selected
            )
            helper.setTextColor(
                R.id.contentTV,
                context.resources.getColor(module.common.R.color.cl_ffffff)
            )
        } else {
            helper.setBackgroundResource(
                R.id.contentTV,
                R.drawable.goods_bg_attribute_value_normal
            )
            helper.setTextColor(
                R.id.contentTV,
                context.resources.getColor(module.common.R.color.cl_313131)
            )
        }
        helper.setText(R.id.contentTV, item.frontName)
    }
}