package module.goods.detail.sku

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.entity.*
import module.common.utils.GsonUtils.toJson
import module.common.utils.LogUtils

/**
 *@author: baizf
 *@date: 2023/3/28
 */
class SkuSelectVModel: BaseViewModel() {

    val filtrateSkuOptionsLD: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val colorPictureUrlLD: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }
    val goodsDetailSkuLD: MutableLiveData<GoodsDetailSku?> by lazy {
        MutableLiveData<GoodsDetailSku?>()
    }

    fun filtrateSkuOptions(
        selectvalue: GoodsSkuAttributeValue,
        position: Int,
        goods: Goods
    )  = viewModelScope.launch(Dispatchers.IO){
        val skuAttrItemId: String = selectvalue.skuAttrItemId
        val attrList: List<GoodsSkuAttribute> = goods.attrList
        if (attrList.size > position + 1) {
            val needSetAttributeValues: List<GoodsSkuAttributeValue> =
                attrList[position + 1].getItems()
            val findItemIds: MutableList<String> = mutableListOf()
            /*查询sku对应的关系*/
            for (sku in goods.goodsSkuList) {
                /*sku层级数量*/
                val hierarchies: List<String> = sku.skuCode.split(",")
                /*当前的层级*/
                val currentHierarchItemId = hierarchies[position]
                if (currentHierarchItemId == skuAttrItemId) { //设置为可选状态
                    if (hierarchies.size > position + 1) {
                        val hierarchyId = hierarchies[position + 1]
                        if (!findItemIds.contains(hierarchyId)) {
                            findItemIds.add(hierarchyId)
                        }
                    }
                }
            }
            LogUtils.printI("findItemIds=" + toJson(findItemIds))

            /*设置可选状态*/
            for (attributeValue in needSetAttributeValues) {
                attributeValue.isEnable = findItemIds.contains(attributeValue.skuAttrItemId)
            }

            /*将选择的属性下层所有子属性都设置为不可选状态*/
            for (i in position + 2 until attrList.size) {
                val goodsSkuAttribute = attrList[i]
                val items = goodsSkuAttribute.items
                if (items != null) {
                    for (value in items) {
                        value.isEnable = false
                    }
                }
            }
            setFirstItemEnable(attrList)
        } else if (attrList.size == 1) {
            setFirstItemEnable(attrList)
        }
        withContext(Dispatchers.Main){
            filtrateSkuOptionsLD.value = true
        }
    }

    /**
     * @describe: 设置第一个sku可选
     * @date: 2020/7/4
     */
    private fun setFirstItemEnable(attrList: List<GoodsSkuAttribute>) {
        val needSetAttributeValues = attrList[0].items
        /*设置可选状态*/for (i in needSetAttributeValues.indices) {
            val attributeValue = needSetAttributeValues[i]
            attributeValue.isEnable = true
        }
    }

    fun getColorPictureById(skuAttrItemId: String?, colorImages: List<GoodsColorImage>?) = viewModelScope.launch(Dispatchers.IO) {
        var colorPictureUrl = ""
        if (colorImages != null) {
            for (colorImage in colorImages) {
                if (colorImage.colorId.equals(skuAttrItemId)) {
                    colorPictureUrl = colorImage.url
                    break
                }
            }
        }
        withContext(Dispatchers.Main){
            colorPictureUrlLD.value = colorPictureUrl
        }
    }

    fun getSelectedSku(skuAttrItemId: String?, goodsSkuList: List<GoodsDetailSku>?, position: Int) = viewModelScope.launch(Dispatchers.IO) {
        var sku: GoodsDetailSku? = GoodsDetailSku()
        if (goodsSkuList != null) {
            for (goodsDetailSku in goodsSkuList) {
                val skuCodes: List<String> = goodsDetailSku.skuCode.split(",")
                if (skuCodes.size > position) {
                    if (skuCodes[position] == skuAttrItemId) {
                        sku = goodsDetailSku
                        break
                    }
                }
            }
        }
        withContext(Dispatchers.Main){
            goodsDetailSkuLD.value = sku
        }
    }
}