package com.bzf.module_imageeditor.attachment.sticker.select

import android.graphics.BitmapFactory
import android.widget.ImageView
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.attachment.sticker.display.StickerEntity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *@author: baizf
 *@date: 2023/2/2
 */
class StickerAdapter(list: MutableList<StickerEntity>): BaseQuickAdapter<StickerEntity, BaseViewHolder>(R.layout.imgedit_item_sticker, list) {


    override fun convert(holder: BaseViewHolder, sticker: StickerEntity) {
        val stickerIV = holder.getView<ImageView>(R.id.stickerIV)
        val bitmap = context.assets.open(sticker.path).use {
            BitmapFactory.decodeStream(it)
        }
        stickerIV.setImageBitmap(bitmap)
    }

}