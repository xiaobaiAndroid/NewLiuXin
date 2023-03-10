package com.bzf.module_imageeditor.music

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.bzf.module_imageeditor.R

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class MusicLibView: View {

    constructor(context: Context, attributeSet: AttributeSet?,defStyle: Int):super(context,attributeSet,defStyle){

    }
    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0)
    constructor(context: Context):this(context,null,0)


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        canvas?.let {
            val centerX = width / 2.0f
            val centerY = height / 2.0f
            var radius = centerX
            paint.color = resources.getColor(module.common.R.color.cl_999999)
            it.drawCircle(centerX, centerY, radius, paint)

            radius -= resources.getDimension(R.dimen.dp_2)
            paint.color = resources.getColor(module.common.R.color.cl_000000)
            it.drawCircle(centerX, centerY, radius, paint)

            radius = centerX/3.0f
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = centerX/3.0f - resources.getDimension(R.dimen.dp_1)
            paint.color = resources.getColor(module.common.R.color.cl_ff5722)
            it.drawCircle(centerX,centerY,radius, paint)
        }
    }
}