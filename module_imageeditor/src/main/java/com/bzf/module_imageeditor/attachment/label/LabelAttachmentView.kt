package com.bzf.module_imageeditor.attachment.label

import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.Rect
import com.bzf.module_imageeditor.attachment.AttachmentLayout
import com.bzf.module_imageeditor.attachment.base.AttachmentOperationType
import com.bzf.module_imageeditor.attachment.base.AttachmentViewBase
import com.bzf.module_imageeditor.attachment.base.IAttachmentDrawable
import com.bzf.module_imageeditor.utils.LogUtils

/**
 *@author: baizf
 *@date: 2023/3/16
 */
class LabelAttachmentView(attachmentLayout: AttachmentLayout): AttachmentViewBase(attachmentLayout) {


    override fun add(drawable: IAttachmentDrawable) {

        val originalRect = drawable.getOriginalRect()
        val left: Int = (attachmentLayout.width - originalRect.width()) / 2
        val top: Int = (attachmentLayout.height - originalRect.height()) / 2

        val rect = Rect(left, top, left + originalRect.width(), top + originalRect.height())

        mAttachmentDrawables.add(drawable)
        drawable.setPosition(rect,attachmentLayout.width, attachmentLayout.height)

        mSelectedPosition = mAttachmentDrawables.size - 1

        attachmentLayout.invalidate()

        (drawable as LabelDrawable).startAnimation()
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
        drawable: IAttachmentDrawable,
        currentX: Float,
        currentY: Float,
        offsetX: Float,
        offsetY: Float
    ) {

    }


}