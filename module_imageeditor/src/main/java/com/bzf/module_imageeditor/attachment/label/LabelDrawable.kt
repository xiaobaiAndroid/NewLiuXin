package com.bzf.module_imageeditor.attachment.label

import android.content.Context
import android.graphics.*
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.attachment.base.AttachmentDrawBase
import com.bzf.module_imageeditor.attachment.base.IAttachmentDrawable
import com.bzf.module_imageeditor.label.select.LabelEntity
import com.bzf.module_imageeditor.utils.LogUtils
import module.common.utils.DensityUtil
import java.security.SecureRandom

/**
 *@author: baizf
 *@date: 2023/3/15
 */
class LabelDrawable(context: Context, val label: LabelEntity, val listener: Listener): AttachmentDrawBase(context) {

    private val mCirclePointBgPadding = DensityUtil.dip2pxFloat(context,8f)

    val mLabelDrawHeight = DensityUtil.dip2pxFloat(context,40f)

    private val mCirclePointDrawable  = LabelCirclePointDrawble(context,listener)
    private val mContentDrawable = LabelContentDrawable(context,mLabelDrawHeight,label.name)

    val mLabelDrawWidth: Float = mContentDrawable.width + mCirclePointDrawable.getWidth() + mCirclePointBgPadding




    init {
        mPadding = DensityUtil.dip2pxFloat(context,12f)
        mOriginalRect.set(0,0,mLabelDrawWidth.toInt(), mLabelDrawHeight.toInt())

    }



    override fun drawSelf(canvas: Canvas) {
        drawCirclePoint(canvas)
        drawText(canvas)
    }

    private fun drawText(canvas: Canvas) {
        canvas.save()
        canvas.translate(mCirclePointDrawable.getWidth() + mCirclePointBgPadding,0f)
        mContentDrawable.draw(canvas)
        canvas.restore()
    }


    private fun drawCirclePoint(canvas: Canvas) {
        canvas.save()
        canvas.translate(mCirclePointDrawable.getRadius(), mLabelDrawHeight/2.0f)
        mCirclePointDrawable.draw(canvas)
        canvas.restore()
    }


    override fun resize(distance: Float) {

    }




    fun startAnimation(){
        mCirclePointDrawable.startAnimation()
    }

    override fun destroy() {
        mCirclePointDrawable.stopAnimation()
    }

    interface Listener{

        fun invalidate()
    }

}