package com.bzf.module_imageeditor.attachment.sticker.display

import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.Rect
import com.bzf.module_imageeditor.attachment.AttachmentLayout
import com.bzf.module_imageeditor.attachment.base.AttachmentOperationType
import com.bzf.module_imageeditor.attachment.base.AttachmentViewBase
import com.bzf.module_imageeditor.attachment.base.IAttachmentDraw
import com.bzf.module_imageeditor.utils.LogUtils
import com.bzf.module_imageeditor.utils.Point2D
import kotlin.math.max
import kotlin.math.min

/**
 *@author: baizf
 *@date: 2023/3/16
 */
class StickerAttachmentView(attachmentLayout: AttachmentLayout): AttachmentViewBase<StickerDrawable>(attachmentLayout) {


    override fun add(drawable: StickerDrawable) {
        val scaleBitmapWidth: Int
        val scaleBitmapHeight: Int

        val stickerBitmap = drawable.bitmap

        val imageViewSize = max(attachmentLayout.width, attachmentLayout.height)
        val bitmapSize = max(stickerBitmap.width, stickerBitmap.height)
        if(bitmapSize > imageViewSize){
            val ratioW = attachmentLayout.width.toFloat() / stickerBitmap.width.toFloat()
            val ratioH = attachmentLayout.height.toFloat()/stickerBitmap.height.toFloat()

            val ratio = min(ratioW, ratioH)

            scaleBitmapWidth = (drawable.bitmap.width * ratio).toInt()
            scaleBitmapHeight = (stickerBitmap.height * ratio).toInt()
        }else{
            scaleBitmapWidth = stickerBitmap.width
            scaleBitmapHeight = stickerBitmap.height
        }

        LogUtils.printI("scaleBitmapWidth=$scaleBitmapWidth, scaleBitmapHeight=$scaleBitmapHeight")

        val left: Int = (attachmentLayout.width - scaleBitmapWidth) / 2
        val top: Int = (attachmentLayout.height - scaleBitmapHeight) / 2

        LogUtils.printI("left=$left, top=$top")
        val rect = Rect(left, top, left + scaleBitmapWidth, top + scaleBitmapHeight)
        drawable.setPosition(rect,attachmentLayout.width, attachmentLayout.height)

        mAttachmentDrawables.add(drawable)
        mSelectedPosition = mAttachmentDrawables.size - 1

        attachmentLayout.invalidate()
    }

    override fun delete() {
        if (isSelected() && mOperateType == AttachmentOperationType.DELETE) {
            mAttachmentDrawables.removeAt(mSelectedPosition)
            mSelectedPosition = NO_SELECTED
            attachmentLayout.invalidate()
        }
    }

    override fun move(currentX: Float, currentY: Float, lastX: Float, lastY: Float) {
        if (isSelected() && mAttachmentDrawables.isNotEmpty()) {
            //禁止ViewGroup拦截当前事件
            attachmentLayout.parent.requestDisallowInterceptTouchEvent(true)

            val offsetX = currentX - lastX
            val offsetY = currentY - lastY

            val stickerDrawable = mAttachmentDrawables[mSelectedPosition]
            when(mOperateType){
                AttachmentOperationType.MOVE->{
                    stickerDrawable.changePosition(offsetX, offsetY)
                }
                AttachmentOperationType.RESIZE->{
                    scaleAndRotate(stickerDrawable as StickerDrawable, currentX, currentY, offsetX, offsetY)
                }
                else->{

                }
            }
            attachmentLayout.invalidate()
        } else {
            //不禁止ViewGroup拦截当前事件
            attachmentLayout.parent.parent.requestDisallowInterceptTouchEvent(false)
        }
    }

    override fun scaleAndRotate(
        drawable: StickerDrawable,
        currentX: Float,
        currentY: Float,
        offsetX: Float,
        offsetY: Float
    ) {
        val centerPoint = drawable.getCenterPoint()
        val bottomRightPoint = drawable.getRightBottomPoint()
        val currentPoint = PointF(currentX, currentY)

        LogUtils.printI("centerPoint=$centerPoint, bottomRightPoint=$bottomRightPoint,  currentPoint=$currentPoint")

        val sourceAngle = Point2D.angleBetweenPoints(bottomRightPoint, centerPoint)
        val currentAngle = Point2D.angleBetweenPoints(currentPoint, centerPoint)

        val offsetAngle = -(currentAngle - sourceAngle)

        LogUtils.printI("sourceAngle=$sourceAngle, currentAngle=$currentAngle,  offsetAngle=$offsetAngle")

        drawable.setRotateAngle(offsetAngle)

        var offsetPoint = computeRotateOffset(offsetAngle, offsetX, offsetY)

        //计算缩放距离
        offsetPoint =
            PointF(bottomRightPoint.x + offsetPoint.x, bottomRightPoint.y + offsetPoint.y)

        val distanceOld = Point2D.distance(bottomRightPoint, centerPoint)
        val distanceNew = Point2D.distance(offsetPoint, centerPoint)

        val distance = distanceNew - distanceOld
        drawable.resize(distance.toFloat())
    }


    override fun isSelected(): Boolean {
        return mSelectedPosition != NO_SELECTED
    }

    override fun isTouchAtAttachment(x: Float, y: Float) {
        if (mAttachmentDrawables.isNotEmpty()) {
            mOperateType = AttachmentOperationType.NONE

            var tempSelectPosition = NO_SELECTED
            for (i in mAttachmentDrawables.indices) {
                val stickerDraw = mAttachmentDrawables[i]
                if (stickerDraw.isTouch(x, y)) {
                    tempSelectPosition = i
                }
            }
            if (tempSelectPosition == NO_SELECTED) { //点击事件都不在所有贴纸上,在看是不是在操作按钮上
                if(isSelected()){
                    val stickerDrawable = mAttachmentDrawables[mSelectedPosition]
                    if(stickerDrawable.isTouchDelete(x,y)){
                        mOperateType = AttachmentOperationType.DELETE
                    }else if(stickerDrawable.isTouchResize(x,y)){
                        mOperateType = AttachmentOperationType.RESIZE
                    }else{
                        hideSelectedFrame()
                    }
                }else{
                    hideSelectedFrame()
                }
            } else {
                mAttachmentDrawables[tempSelectPosition].setSelected(true)
                cancelSelected()
                mSelectedPosition = tempSelectPosition
                mOperateType = AttachmentOperationType.MOVE
            }
            attachmentLayout.invalidate()
        }
    }

    override fun cancelSelected() {
        if (isSelected()) {
            mAttachmentDrawables[mSelectedPosition].setSelected(false)
        }
    }

    fun hideSelectedFrame() {
        cancelSelected()
        mSelectedPosition = NO_SELECTED
        mOperateType = AttachmentOperationType.NONE
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

    fun getStickers(): MutableList<IAttachmentDraw> {
        return mAttachmentDrawables
    }


}