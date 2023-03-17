package com.bzf.module_imageeditor.attachment.label

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.TextPaint
import com.bzf.module_imageeditor.R
import module.common.utils.DensityUtil
import kotlin.math.abs

/**
 *@author: baizf
 *@date: 2023/3/16
 */
class LabelContentDrawable(val context: Context, val height: Float,val content: String?) {

    private val mTextPaint: TextPaint  =  initTextPaint()
    private val mDrawText: String = getDrawText()

    private val mTextPadding = DensityUtil.dip2pxFloat(context,4f)

    private val mCorner = DensityUtil.dip2pxFloat(context,8f)

    private val mTextStartX: Float  = mTextPadding
    private val mTextStartY: Float by lazy {
        val fontMetrics = mTextPaint.fontMetrics
        val textHeight = abs(fontMetrics.ascent) + abs(fontMetrics.descent)
        val value = mBackgroundRect.height()/2 + textHeight/2 - abs(fontMetrics.bottom)/2
        value
    }

    val width: Float =  mTextPaint.measureText(mDrawText) + mTextPadding * 2 + mTextStartX * 2

    private val mBackgroundRect: RectF = RectF(0f,0f,width,height)



    private val mBackgroundPaint: Paint by lazy {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint.color = context.resources.getColor(R.color.cl_333333)
        paint
    }

    private fun getDrawText() = content?.let {
        if (it.length > 8) {
            it.substring(0, 8) + "..."
        } else {
            it
        }
    } ?: ""


    private fun initTextPaint(): TextPaint {
        val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = DensityUtil.spToFloatPx(context, 14f)
        paint.color = context.resources.getColor(R.color.cl_ffffff)
        return paint
    }

    fun draw(canvas: Canvas){
        canvas.save()
        canvas.drawRoundRect(mBackgroundRect,mCorner,mCorner,mBackgroundPaint)
        canvas.translate(mBackgroundRect.left + mTextPadding,0f)
        canvas.drawText(mDrawText,mTextStartX,mTextStartY,mTextPaint)
        canvas.restore()
    }


}