package com.bzf.module_imageeditor.attachment.sticker.display

import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.Rect
import com.bzf.module_imageeditor.attachment.AttachmentLayout
import com.bzf.module_imageeditor.attachment.base.AttachmentViewBase
import com.bzf.module_imageeditor.attachment.base.IAttachmentDrawable
import com.bzf.module_imageeditor.utils.LogUtils
import com.bzf.module_imageeditor.utils.Point2D
import kotlin.math.max
import kotlin.math.min

/**
 *@author: baizf
 *@date: 2023/3/16
 */
class StickerAttachmentView(attachmentLayout: AttachmentLayout) :
    AttachmentViewBase(attachmentLayout) {


    override fun add(drawable: IAttachmentDrawable) {
        val scaleBitmapWidth: Int
        val scaleBitmapHeight: Int

        val drawableRect = drawable.getOriginalRect()


        val imageViewSize = max(attachmentLayout.width, attachmentLayout.height)
        val bitmapSize = max(drawableRect.width(), drawableRect.height())
        if (bitmapSize > imageViewSize) {
            val ratioW = attachmentLayout.width.toFloat() / drawableRect.width().toFloat()
            val ratioH = attachmentLayout.height.toFloat() / drawableRect.height().toFloat()

            val ratio = min(ratioW, ratioH)

            scaleBitmapWidth = (drawableRect.width() * ratio).toInt()
            scaleBitmapHeight = (drawableRect.height() * ratio).toInt()
        } else {
            scaleBitmapWidth = drawableRect.width()
            scaleBitmapHeight = drawableRect.height()
        }

        LogUtils.printI("scaleBitmapWidth=$scaleBitmapWidth, scaleBitmapHeight=$scaleBitmapHeight")

        val left: Int = (attachmentLayout.width - scaleBitmapWidth) / 2
        val top: Int = (attachmentLayout.height - scaleBitmapHeight) / 2

        LogUtils.printI("left=$left, top=$top")
        val rect = Rect(left, top, left + scaleBitmapWidth, top + scaleBitmapHeight)
        drawable.setPosition(rect, attachmentLayout.width, attachmentLayout.height)

        mAttachmentDrawables.add(drawable)
        mSelectedPosition = mAttachmentDrawables.size - 1

        attachmentLayout.invalidate()
    }


    override fun scaleAndRotate(
        drawable: IAttachmentDrawable,
        currentX: Float,
        currentY: Float,
        offsetX: Float,
        offsetY: Float
    ) {
        val stickerDrawable = drawable as StickerDrawable

        val centerPoint = stickerDrawable.getCenterPoint()
        val bottomRightPoint = stickerDrawable.getRightBottomPoint()
        val currentPoint = PointF(currentX, currentY)

        LogUtils.printI("centerPoint=$centerPoint, bottomRightPoint=$bottomRightPoint,  currentPoint=$currentPoint")

        val sourceAngle = Point2D.angleBetweenPoints(bottomRightPoint, centerPoint)
        val currentAngle = Point2D.angleBetweenPoints(currentPoint, centerPoint)

        val offsetAngle = -(currentAngle - sourceAngle)

        LogUtils.printI("sourceAngle=$sourceAngle, currentAngle=$currentAngle,  offsetAngle=$offsetAngle")

        stickerDrawable.setRotateAngle(offsetAngle)

        var offsetPoint = computeRotateOffset(offsetAngle, offsetX, offsetY)

        //计算缩放距离
        offsetPoint =
            PointF(bottomRightPoint.x + offsetPoint.x, bottomRightPoint.y + offsetPoint.y)

        val distanceOld = Point2D.distance(bottomRightPoint, centerPoint)
        val distanceNew = Point2D.distance(offsetPoint, centerPoint)

        val distance = distanceNew - distanceOld
        stickerDrawable.resize(distance.toFloat())
    }


    private fun computeRotateOffset(
        offsetAngle: Float,
        offsetX: Float,
        offsetY: Float
    ): PointF {
        val matrix = Matrix()
        matrix.postRotate(-offsetAngle)
        val offsetArr = floatArrayOf(offsetX, offsetY)
        matrix.mapPoints(offsetArr)

        val rotateOffsetX = offsetArr[0]
        val rotateOffsetY = offsetArr[1]
        return PointF(rotateOffsetX, rotateOffsetY)
    }


}