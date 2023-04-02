package module.goods.search.result

import com.chad.library.adapter.base.entity.MultiItemEntity
import module.common.data.entity.Goods

class ResultMultiEntity(override val itemType: Int) : MultiItemEntity {
    var goods: Goods? = null

    enum class Type {
        /*单行*/
        SINGLE,  /*多行*/
        MULTI
    }
}