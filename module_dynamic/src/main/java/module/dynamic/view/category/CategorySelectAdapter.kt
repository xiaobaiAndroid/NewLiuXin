package module.dynamic.view.category

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.utils.StringUtils
import module.dynamic.R

class CategorySelectAdapter(data: MutableList<CategoryMultiItem>?) : BaseMultiItemQuickAdapter<CategoryMultiItem, BaseViewHolder>(data), LoadMoreModule {

    init {
        addItemType(CategoryMultiItem.LEFT, R.layout.clique_item_mul_select_category_left)
        addItemType(CategoryMultiItem.CENTER, R.layout.clique_item_mul_select_category_center)
        addItemType(CategoryMultiItem.RIGHT, R.layout.clique_item_mul_select_category_right)
    }

    override fun convert(helper: BaseViewHolder, item: CategoryMultiItem) {
        val category = item.cliqueCategory
        if (item.isSelected) {
            helper.setBackgroundResource(R.id.contentTV, R.drawable.clique_bg_category)
                .setTextColor(
                    R.id.contentTV,
                    context.resources.getColor(module.common.R.color.cl_ffffff)
                )
                .setText(R.id.contentTV, StringUtils.packNull(category.typeName))
        } else {
            helper.setBackgroundResource(R.id.contentTV, R.drawable.clique_bg_category1)
                .setTextColor(
                    R.id.contentTV,
                    context.resources.getColor(module.common.R.color.cl_13b5b1)
                )
                .setText(R.id.contentTV, StringUtils.packNull(category.typeName))
        }
    }
}