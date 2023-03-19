package module.dynamic.view.category

import com.chad.library.adapter.base.entity.MultiItemEntity
import module.common.data.entity.CliqueCategory

class CategoryMultiItem(override val itemType: Int, val cliqueCategory: CliqueCategory) : MultiItemEntity {

    /*是否选中*/
    var isSelected = false


    companion object {
        const val LEFT = 0
        const val CENTER = 1
        const val RIGHT = 2
    }
}