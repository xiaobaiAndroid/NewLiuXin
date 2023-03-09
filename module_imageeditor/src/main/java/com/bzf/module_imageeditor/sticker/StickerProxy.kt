package com.bzf.module_imageeditor.sticker

import android.graphics.BitmapFactory
import android.graphics.Rect
import com.bzf.module_imageeditor.utils.LogUtils
import kotlin.math.max
import kotlin.math.min

/**
 *@author: baizf
 *@date: 2023/2/15
 */
class StickerProxy(private val stickersImageView: StickersImageView) {

    private val context = stickersImageView.context


    fun addSticker(sticker: Sticker) {
        val stickerBitmap = context.assets.open(sticker.path).use {
            BitmapFactory.decodeStream(it)
        }
        val stickerDrawable = StickerDrawable(context, stickerBitmap)

        val scaleBitmapWidth: Int
        val scaleBitmapHeight: Int

        val imageViewSize = max(stickersImageView.width, stickersImageView.height)
        val bitmapSize = max(stickerBitmap.width, stickerBitmap.height)
        if(bitmapSize > imageViewSize){
            val ratioW = stickersImageView.width.toFloat() / stickerBitmap.width.toFloat()
            val ratioH = stickersImageView.height.toFloat()/stickerBitmap.height.toFloat()

            val ratio = min(ratioW, ratioH)

            scaleBitmapWidth = (stickerBitmap.width * ratio).toInt()
            scaleBitmapHeight = (stickerBitmap.height * ratio).toInt()
        }else{
            scaleBitmapWidth = stickerBitmap.width
            scaleBitmapHeight = stickerBitmap.height
        }

        LogUtils.printI("scaleBitmapWidth=$scaleBitmapWidth, scaleBitmapHeight=$scaleBitmapHeight")

        val left: Int = (stickersImageView.width - scaleBitmapWidth) / 2
        val top: Int = (stickersImageView.height - scaleBitmapHeight) / 2

        LogUtils.printI("left=$left, top=$top")
        val rect = Rect(left, top, left + scaleBitmapWidth, top + scaleBitmapHeight)
        stickerDrawable.setPosition(rect,stickersImageView.width, stickersImageView.height)

        stickersImageView.addSticker(stickerDrawable)

        stickersImageView.invalidate()
    }
}