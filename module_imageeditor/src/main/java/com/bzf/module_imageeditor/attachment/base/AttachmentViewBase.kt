package com.bzf.module_imageeditor.attachment.base

import android.graphics.Canvas
import android.graphics.PorterDuff
import com.bzf.module_imageeditor.attachment.AttachmentLayout

/**
 *@author: baizf
 *@date: 2023/3/16
 */
abstract class AttachmentViewBase<T: IAttachmentDraw>(val attachmentLayout: AttachmentLayout):
    IAttachmentOperation<T> {

    companion object {
        const val NO_SELECTED = -1
    }

   protected var mAttachmentDrawables = mutableListOf<IAttachmentDraw>()

    var mSelectedPosition = NO_SELECTED
    protected var mOperateType = AttachmentOperationType.NONE

    open fun draw(canvas: Canvas){
        if (mAttachmentDrawables.isEmpty()) {
            return
        }
        for (i in mAttachmentDrawables.indices) {
            val drawable = mAttachmentDrawables[i]
            drawable.setSelected(i == mSelectedPosition)
            drawable.draw(canvas)
        }
    }

}