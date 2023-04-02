package module.goods.search.result.filtrate

import module.common.data.entity.SkuAttribute
import module.common.data.entity.SkuAttributeValue

class FiltrateEntity {
    var attribute: SkuAttribute? = null
    var values: MutableList<SkuAttributeValue>? = null
    var threeValues: MutableList<SkuAttributeValue>? = null
    var isUnfold = false
}