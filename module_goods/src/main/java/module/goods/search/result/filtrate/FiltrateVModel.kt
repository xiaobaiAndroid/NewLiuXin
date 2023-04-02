package module.goods.search.result.filtrate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.SkuAttribute
import module.common.data.entity.SkuAttributeValue
import module.common.data.respository.goods.GoodsRepository

/**
 *@author: baizf
 *@date: 2023/4/2
 */
class FiltrateVModel : BaseViewModel() {

    val skuFiltrateResultLD: MutableLiveData<MutableList<FiltrateEntity>> by lazy {
        MutableLiveData<MutableList<FiltrateEntity>>()
    }

    val skuIdsLD: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>()
    }


    fun getSkuFiltrateData(keyWord: String?) = viewModelScope.launch(Dispatchers.IO) {
        val list: MutableList<FiltrateEntity> = java.util.ArrayList<FiltrateEntity>()

        val attributeDR: DataResult<MutableList<SkuAttribute>?> =
            GoodsRepository.instance.getSkuAttributes(mContext, keyWord)
        val valueDR: DataResult<MutableList<SkuAttributeValue>?> =
            GoodsRepository.instance.getSkuAttributeValues(mContext, keyWord)
        val skuAttributes = attributeDR.t
        val attributeValues = valueDR.t
        if (skuAttributes != null && attributeValues != null) {
            for (skuAttribute in skuAttributes) {
                val filtrateEntity = FiltrateEntity()
                filtrateEntity.attribute = skuAttribute
                val values: MutableList<SkuAttributeValue> = java.util.ArrayList()
                for (i in attributeValues.indices) {
                    val value = attributeValues[i]
                    if (value.skuAttrId == skuAttribute.skuAttrId) {
                        values.add(value)
                    }
                }
                filtrateEntity.values = values
                list.add(filtrateEntity)
            }
        }

        for (entity in list) {
            val threeValues: MutableList<SkuAttributeValue> = mutableListOf()
            entity.values?.let { values ->
                for (i in values.indices) {
                    if (i < 3) {
                        threeValues.add(values[i])
                    }
                }
                entity.threeValues = threeValues
            }

        }
        withContext(Dispatchers.Main) {
            skuFiltrateResultLD.value = list
        }
    }

    fun getSelectedFiltrateSkus(data: MutableList<FiltrateEntity>) =
        viewModelScope.launch(Dispatchers.Default) {
            val skuIds: MutableList<String> = ArrayList()
            for (filtrateEntity in data) {
                filtrateEntity.values?.let { values ->
                    for (skuAttributeValue in values) {
                        if (skuAttributeValue.isSelect) {
                            skuIds.add(skuAttributeValue.id)
                        }
                    }
                }
            }
            withContext(Dispatchers.Main) {
                skuIdsLD.value = skuIds
            }
        }

    fun resetData(data: MutableList<FiltrateEntity>) {
        for (filtrateEntity in data) {
            filtrateEntity.values?.let {values->
                for (skuAttributeValue in values) {
                    skuAttributeValue.isSelect = false
                }
            }
            filtrateEntity.threeValues?.let { threeValues ->
                for (skuAttributeValue in threeValues) {
                    skuAttributeValue.isSelect = false
                }
            }
        }
    }
}