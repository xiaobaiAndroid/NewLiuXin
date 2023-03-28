package module.goods.detail.sku

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import lib.flowlayout.FlowLayout
import lib.flowlayout.TagAdapter
import module.common.data.entity.GoodsSkuAttributeValue
import module.common.utils.StringUtils
import module.goods.R

/**
 *@author: baizf
 *@date: 2023/3/28
 */
class SkuTagAdapter(list: MutableList<GoodsSkuAttributeValue>): TagAdapter<GoodsSkuAttributeValue>(list) {

    override fun getView(
        parent: FlowLayout?,
        position: Int,
        entity: GoodsSkuAttributeValue
    ): View {
        val valueTV = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.goods_item_detail_sku_attribute_value, null) as TextView
        setSelectedStatus(valueTV, entity)
        valueTV.text = StringUtils.packNull(entity.skuAttrItemName)
        return valueTV
    }

    override fun onSelected(position: Int, view: View?) {
        super.onSelected(position, view)
        val valueTV = view as TextView?
        mTagDatas?.let { values->
            for (i in values.indices) {
                val attributeValue = values[i]
                if (attributeValue.isSelected) {
                    attributeValue.isSelected = false
                    setSelectedStatus(valueTV, attributeValue)
                    break
                }
            }
            val attributeValue = values[position]
            attributeValue.isSelected = true
            setSelectedStatus(valueTV, attributeValue)
        }

    }

    override fun unSelected(position: Int, view: View?) {
        super.unSelected(position, view)
        val valueTV = view as TextView?
        mTagDatas?.get(position)?.let {attributeValue->
            attributeValue.isSelected = false
            setSelectedStatus(valueTV, attributeValue)
        }

    }

    /**
     * @describe: 设置选中状态
     * @date: 2020/5/5
     */
    private fun setSelectedStatus(valueTV: TextView?, value: GoodsSkuAttributeValue) {
        if (value.isEnable) {
            if (value.isSelected) {
                valueTV!!.setTextColor(valueTV.context.resources.getColor(module.common.R.color.cl_13b5b1))
                valueTV.setBackgroundResource(R.drawable.goods_bg_detail_sku_select)
            } else {
                valueTV!!.setTextColor(valueTV.context.resources.getColor(module.common.R.color.cl_707070))
                valueTV.setBackgroundResource(R.drawable.goods_bg_detail_sku_normal)
            }
        } else {
            valueTV!!.setTextColor(valueTV.context.resources.getColor(module.common.R.color.cl_ffffff))
            valueTV.setBackgroundResource(R.drawable.goods_bg_detail_sku_not_enable)
        }
    }

    interface Listener{

    }
}