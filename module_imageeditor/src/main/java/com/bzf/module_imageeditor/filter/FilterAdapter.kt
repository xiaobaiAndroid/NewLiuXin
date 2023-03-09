package com.bzf.module_imageeditor.filter

import com.bzf.module_imageeditor.R
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *@author: baizf
 *@date: 2023/2/2
 */
class FilterAdapter: BaseMultiItemQuickAdapter<FilterEntity, BaseViewHolder>() {
    init {
        addItemType(FilterEntity.Type.SELECTED.ordinal, R.layout.imgedit_item_filter_selected)
        addItemType(FilterEntity.Type.UNSELECTED.ordinal, R.layout.imgedit_item_filter_unselected)
    }

    override fun convert(holder: BaseViewHolder, item: FilterEntity) {
        holder.setImageResource(R.id.filterIV, item.filterResId)
        holder.setText(R.id.filterNameTV, item.filterName)
    }
}