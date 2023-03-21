package module.publish.category

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.DynamicCategory
import module.common.utils.StringUtils
import module.publish.R

class CategoriseAdapter(data: MutableList<DynamicCategory>?) :
    BaseQuickAdapter<DynamicCategory, BaseViewHolder>(R.layout.live_publish_item_categorise, data) {
    override fun convert(helper: BaseViewHolder, item: DynamicCategory) {
        if (item != null) {
            helper.setText(R.id.contentTV, StringUtils.packNull(item.typeName))
        }
    }
}