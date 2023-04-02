package module.goods.search.result.filtrate

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.utils.DensityUtil.dip2px
import module.common.utils.StringUtils.packNull
import module.common.view.GridSpaceDecoration
import module.goods.R

class FiltrateAdapter(data: MutableList<FiltrateEntity>?) :
    BaseQuickAdapter<FiltrateEntity, BaseViewHolder>(R.layout.goods_item_search_filtrate, data),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: FiltrateEntity) {
        holder.setText(R.id.titleTV, packNull(item.attribute!!.frontName))
        val arrowIV = holder.getView<ImageView>(R.id.arrowIV)
        val valueAdapter: ValueAdapter
        if (item.isUnfold) {
            arrowIV.rotation = 180f
            valueAdapter = ValueAdapter(item.values)
        } else {
            arrowIV.rotation = 0f
            valueAdapter = ValueAdapter(item.threeValues)
        }
        val valueRV = holder.getView<RecyclerView>(R.id.valueRV)
        val spanCount = 3
        valueRV.layoutManager = GridLayoutManager(context, spanCount)
        valueRV.setItemViewCacheSize(100)
        val itemAnimator = valueRV.itemAnimator as SimpleItemAnimator?
        itemAnimator!!.supportsChangeAnimations = false
        valueRV.adapter = valueAdapter
        valueAdapter.setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int ->
            val value = valueAdapter.getItem(position)
            if (!value.isSelect) {
                value.isSelect = true
                if (position < 3) {
                    val threeValues = item.threeValues
                    threeValues!![position].isSelect = true
                }
                val originalValues = item.values
                originalValues!![position].isSelect = true
                valueAdapter.notifyItemChanged(position)
            }
        }
        val itemDecorationCount = valueRV.itemDecorationCount
        if (itemDecorationCount > 0) {
            valueRV.removeItemDecorationAt(0)
        }
        val gridSpaceDecoration =
            GridSpaceDecoration(valueAdapter, dip2px(context, 8f), spanCount)
        gridSpaceDecoration.setMargin(dip2px(context, 8f))
        valueRV.addItemDecoration(gridSpaceDecoration, 0)
    }
}