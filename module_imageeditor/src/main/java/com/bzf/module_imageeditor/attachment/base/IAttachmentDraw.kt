package com.bzf.module_imageeditor.attachment.base

import android.graphics.Canvas
import android.graphics.PointF

/**
 *@author: baizf
 *@date: 2023/3/16
 */
interface IAttachmentDraw {

    fun draw(canvas: Canvas)

    fun isTouch(x: Float, y: Float): Boolean

    fun drawFrame(canvas: Canvas)

    fun setDeleteBimapRect()

    fun resize(distance: Float)

    fun isTouchDelete(x: Float, y: Float): Boolean

    fun changePosition(x: Float, y: Float)

    fun getCenterPoint(): PointF

    fun setRotateAngle(offsetAngle: Float)

    fun isTouchResize(x: Float, y: Float): Boolean

    fun isSelected(): Boolean

    fun setSelected(selected: Boolean)
}