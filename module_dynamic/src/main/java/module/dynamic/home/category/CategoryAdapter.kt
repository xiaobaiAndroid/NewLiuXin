package module.dynamic.home.category

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.CliqueCategory
import module.common.utils.StringUtils.packNull
import module.dynamic.R

class CategoryAdapter(data: MutableList<CliqueCategory?>) :
    BaseQuickAdapter<CliqueCategory?, BaseViewHolder>(R.layout.clique_item_category, data) {
    override fun convert(helper: BaseViewHolder, entity: CliqueCategory?) {
        if (entity != null) {
            val titleTV = helper.getView<TextView>(R.id.titleTV)
            titleTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            helper.setText(R.id.titleTV, packNull(entity.typeName))
        }
    }
}