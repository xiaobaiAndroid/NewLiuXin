package com.bzf.module_imageeditor.attachment.label

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import module.common.utils.DensityUtil

/**
 *@author: baizf
 *@date: 2023/3/15
 */
class LabelCirclePointDrawble(val context: Context, val listener: LabelDrawable.Listener) {

    private val innerCircleRadius = DensityUtil.dip2pxFloat(context,3f)
    private val outerCircleMaxRadius = DensityUtil.dip2pxFloat(context,8f)
    private var outerCircleCurrentRadius = DensityUtil.dip2pxFloat(context,3f)

    private val outerCircleColor = Color.parseColor("#40000000")

    private val mPaint: Paint by lazy {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint
    }

    private  val valueAnimator  = ValueAnimator.ofFloat(innerCircleRadius, outerCircleMaxRadius).apply {
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        duration = 1000
        addUpdateListener {
            outerCircleCurrentRadius = it.animatedValue as kotlin.Float
            listener.invalidate()
        }
    }

    fun draw(canvas: Canvas){
        canvas.save()

        mPaint.color = outerCircleColor
        canvas.drawCircle(innerCircleRadius,innerCircleRadius,outerCircleCurrentRadius,mPaint)

        mPaint.color = Color.WHITE
        canvas.drawCircle(innerCircleRadius,innerCircleRadius,innerCircleRadius,mPaint)

        canvas.restore()
    }

    fun startAnimation(){
        valueAnimator.start()
    }

    fun stopAnimation(){
        valueAnimator.end()
    }

    fun getRadius(): Float{
        return outerCircleMaxRadius
    }

    fun getWidth(): Float {
        return outerCircleMaxRadius * 2
    }
}