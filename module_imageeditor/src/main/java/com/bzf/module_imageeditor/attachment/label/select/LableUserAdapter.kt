package com.bzf.module_imageeditor.attachment.label.select

import android.widget.ImageView
import com.bzf.module_imageeditor.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.base.BaseViewModel
import module.common.data.entity.Friend
import module.common.utils.ImageLoadHelper

/**
 *@author: baizf
 *@date: 2023/3/14
 */
class LableUserAdapter: BaseQuickAdapter<Friend, BaseViewHolder>(R.layout.img_item_lable_user,null) {
    override fun convert(holder: BaseViewHolder, item: Friend) {
        val labelIV = holder.getView<ImageView>(R.id.labelIV)
        ImageLoadHelper.load(labelIV, item.extend.avatar, module.common.R.drawable.ic_default_avatar)

        holder.setText(R.id.titleTV,item.extend.nickName)
            .setText(R.id.contentTV, item.extend.mobile)
    }
}