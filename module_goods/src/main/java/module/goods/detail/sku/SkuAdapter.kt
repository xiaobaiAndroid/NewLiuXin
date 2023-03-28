package module.goods.detail.sku

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import lib.flowlayout.FlowLayout
import lib.flowlayout.TagAdapter
import lib.flowlayout.TagFlowLayout
import lib.flowlayout.TagFlowLayout.OnTagClickListener
import module.common.data.entity.GoodsSkuAttribute
import module.common.data.entity.GoodsSkuAttributeValue
import module.common.utils.GsonUtils.toJson
import module.common.utils.LogUtils.printI
import module.common.utils.StringUtils.packNull
import module.goods.R

class SkuAdapter(data: MutableList<GoodsSkuAttribute>?) :
    BaseQuickAdapter<GoodsSkuAttribute, BaseViewHolder>(
        R.layout.goods_item_detail_sku_attribute,
        data
    ), LoadMoreModule {
    private var mListener: SkuListener? = null
    fun setmListener(mListener: SkuListener?) {
        this.mListener = mListener
    }

    override fun convert(helper: BaseViewHolder, item: GoodsSkuAttribute) {
        helper.setText(R.id.titleTV, packNull(item.skuAttrName))
        val valueTFL = helper.getView<TagFlowLayout<GoodsSkuAttributeValue>>(R.id.valueTFL)
        val values = item.items
        if (values != null && values.isNotEmpty()) {
            valueTFL.adapter = getTagAdapter(helper, getItemPosition(item), values)
            valueTFL.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
                override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                    val value = values[position]
                    mListener?.let {
                        if (value.isEnable) {
                            mListener!!.onClick(value, getItemPosition(item))
                        }
                    }
                    return true
                }
            })
        }
    }

    private fun getTagAdapter(
        helper: BaseViewHolder,
        itemPosition: Int,
        values: MutableList<GoodsSkuAttributeValue>
    ): TagAdapter<GoodsSkuAttributeValue> {
        printI("adapter--GoodsSkuAttributeValue=" + toJson(values))
        val tagAdapter: TagAdapter<GoodsSkuAttributeValue> = SkuTagAdapter(values)


        if (itemPosition == 0) {
            if (values.size == 1) {
                //预先设置选中
                tagAdapter.setSelectedList(0)
            }
        }
        return tagAdapter
    }

    interface SkuListener {
        fun onClick(value: GoodsSkuAttributeValue?, position: Int)
    }

}
