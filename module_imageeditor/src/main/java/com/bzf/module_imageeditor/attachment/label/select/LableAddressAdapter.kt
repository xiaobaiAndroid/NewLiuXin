package com.bzf.module_imageeditor.attachment.label.select

import android.widget.ImageView
import com.bzf.module_imageeditor.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.map.entity.MapPoi

/**
 *@author: baizf
 *@date: 2023/3/14
 */
class LableAddressAdapter: BaseQuickAdapter<MapPoi, BaseViewHolder>(R.layout.img_item_lable_user,null) {

    override fun convert(holder: BaseViewHolder, item: MapPoi) {
        val labelIV = holder.getView<ImageView>(R.id.labelIV)

        holder.setImageResource(R.id.labelIV, R.drawable.img_ic_location)
            .setText(R.id.titleTV,item.name)
            .setText(R.id.contentTV, item.poiAddress)
    }
}