package module.goods.detail

import module.common.data.entity.GoodsDetailSku
import module.common.data.entity.GoodsSkuAttributeValue

/**
 *@author: baizf
 *@date: 2023/3/28
 */
data class SettleParams(var  selectedSkuValues: MutableList<GoodsSkuAttributeValue>, var selectedSku: GoodsDetailSku?,var buyNumber: Int, var selectSkuStr: String)