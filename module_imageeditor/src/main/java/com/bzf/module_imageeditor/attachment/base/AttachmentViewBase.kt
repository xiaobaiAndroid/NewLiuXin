package com.bzf.module_imageeditor.attachment.base

import android.graphics.Canvas
import com.bzf.module_imageeditor.attachment.AttachmentLayout

/**
 *@author: baizf
 *@date: 2023/3/16
 */
abstract class AttachmentViewBase(val attachmentLayout: AttachmentLayout):
    IAttachmentOperation {

    companion object {
        const val NO_SELECTED = -1
    }

   protected var mAttachmentDrawables = mutableListOf<IAttachmentDrawable>()

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

    override fun delete() {
        if (isSelected() && mOperateType == AttachmentOperationType.DELETE) {
            mAttachmentDrawables.removeAt(mSelectedPosition)
            mSelectedPosition = NO_SELECTED
            attachmentLayout.invalidate()
        }
    }

    override fun isSelected(): Boolean {
        return mSelectedPosition != NO_SELECTED
    }

    override fun isTouchAtAttachment(x: Float, y: Float): Boolean {
        var isTouch = false
        if (mAttachmentDrawables.isNotEmpty()) {
            mOperateType = AttachmentOperationType.NONE

            var tempSelectPosition = NO_SELECTED
            for (i in mAttachmentDrawables.indices) {
                val drawable = mAttachmentDrawables[i]
                if (drawable.isTouch(x, y)) {
                    tempSelectPosition = i
                }
            }
            if (tempSelectPosition == NO_SELECTED) { //点击事件都不在所有贴纸上,在看是不是在操作按钮上
                if(isSelected()){
                    val stickerDrawable = mAttachmentDrawables[mSelectedPosition]
                    if(stickerDrawable.isTouchDelete(x,y)){
                        mOperateType = AttachmentOperationType.DELETE
                        isTouch = true
                    }else if(stickerDrawable.isTouchResize(x,y)){
                        mOperateType = AttachmentOperationType.RESIZE
                        isTouch = true
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
                isTouch = true
            }
            attachmentLayout.invalidate()
        }
        return isTouch
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

    fun getDrawables(): MutableList<IAttachmentDrawable> {
        return mAttachmentDrawables
    }


    override fun move(currentX: Float, currentY: Float, lastX: Float, lastY: Float) {
        if (isSelected() && mAttachmentDrawables.isNotEmpty()) {
            //禁止ViewGroup拦截当前事件
            attachmentLayout.parent.requestDisallowInterceptTouchEvent(true)

            val offsetX = currentX - lastX
            val offsetY = currentY - lastY

            val drawable = mAttachmentDrawables[mSelectedPosition]
            when(mOperateType){
                AttachmentOperationType.MOVE->{
                    drawable.changePosition(offsetX, offsetY)
                }
                AttachmentOperationType.RESIZE->{
                    scaleAndRotate(drawable, currentX, currentY, offsetX, offsetY)
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

    override fun destroy() {
        for (attachment in mAttachmentDrawables){
            attachment.destroy()
        }
    }


}