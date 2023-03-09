package module.live.publish.category

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.CliqueCategory
import module.common.utils.StringUtils
import module.live.R

class CategoriseAdapter(data: MutableList<CliqueCategory>?) :
    BaseQuickAdapter<CliqueCategory, BaseViewHolder>(R.layout.live_publish_item_categorise, data) {
    override fun convert(helper: BaseViewHolder, item: CliqueCategory) {
        if (item != null) {
            helper.setText(R.id.contentTV, StringUtils.packNull(item.typeName))
        }
    }
}