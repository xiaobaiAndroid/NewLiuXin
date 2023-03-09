package com.bzf.module_imageeditor.filter

import com.bzf.module_imageeditor.FilterType
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 *@author: baizf
 *@date: 2023/2/2
 */
class FilterEntity(override var itemType: Int, val filterType: FilterType,val filterResId: Int, val filterName: String) : MultiItemEntity {

    var imageId: String?=null

    enum class Type{
        //选中
        SELECTED,
        //未选中
        UNSELECTED
    }
}