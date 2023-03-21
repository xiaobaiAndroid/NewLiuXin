package module.dynamic.view.category

import com.chad.library.adapter.base.entity.MultiItemEntity
import module.common.data.entity.DynamicCategory

class CategoryMultiItem(override val itemType: Int, val cliqueCategory: DynamicCategory) : MultiItemEntity {

    /*是否选中*/
    var isSelected = false


    companion object {
        const val LEFT = 0
        const val CENTER = 1
        const val RIGHT = 2
    }
}