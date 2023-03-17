package com.bzf.module_imageeditor.attachment

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bzf.module_imageeditor.attachment.base.AttachmentOperationType
import com.bzf.module_imageeditor.attachment.base.IAttachmentDrawable
import com.bzf.module_imageeditor.attachment.label.LabelAttachmentView
import com.bzf.module_imageeditor.attachment.label.LabelDrawable
import com.bzf.module_imageeditor.attachment.sticker.display.StickerAttachmentView
import com.bzf.module_imageeditor.attachment.sticker.display.StickerDrawable
import com.bzf.module_imageeditor.attachment.sticker.display.StickerEntity
import com.bzf.module_imageeditor.label.select.LabelEntity

/**
 *@author: baizf
 *@date: 2023/3/16
 */
class AttachmentLayout: View {

    private val stickerView: StickerAttachmentView = StickerAttachmentView(this)
    private val labelView: LabelAttachmentView = LabelAttachmentView(this)


    private val mGestureHelper = AttachmentGestureHelper(this)

    private var currentOperationType = AttachmentType.NONE

    constructor(context: Context, attributes: AttributeSet?, defaultAttr: Int) : super(
        context,
        attributes,
        defaultAttr
    )

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)
    constructor(context: Context) : this(context, null, 0)


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return event?.let {
            mGestureHelper.onTouchEvent(it)
        } ?: return super.onTouchEvent(event)
    }

    fun isDelete(x: Float, y: Float) {
        if(currentOperationType == AttachmentType.STICKER){
            stickerView.delete()
        }else if(currentOperationType == AttachmentType.LABEL){
            labelView.delete()
        }
    }

    fun move(currentX: Float, currentY: Float, lastX: Float, lastY: Float) {
        if(currentOperationType == AttachmentType.STICKER){
            stickerView.move(currentX,currentY,lastX,lastY)
        }else if(currentOperationType == AttachmentType.LABEL){
            labelView.move(currentX,currentY,lastX,lastY)
        }

    }

    fun addStickerAttachment(stickerEntity: StickerEntity){
        val stickerDrawable = StickerDrawable(context, stickerEntity)
        stickerView.add(stickerDrawable)
        labelView.cancelSelected()
        currentOperationType = AttachmentType.STICKER
    }

    fun addLabelAttachment(labelEntity: LabelEntity){
        val labelDrawable = LabelDrawable(context, labelEntity,object: LabelDrawable.Listener{
            override fun invalidate() {
               this@AttachmentLayout.invalidate()
            }
        })
        stickerView.cancelSelected()
        labelView.add(labelDrawable)
        currentOperationType = AttachmentType.LABEL
    }


    fun isSelected(x: Float, y: Float) {
        val labelIsTouch = labelView.isTouchAtAttachment(x, y)
        val stickerIsTouch = stickerView.isTouchAtAttachment(x, y)
        if(labelIsTouch){
            stickerView.hideSelectedFrame()
            currentOperationType = AttachmentType.LABEL
        }else if(stickerIsTouch){
            currentOperationType = AttachmentType.STICKER
        }else{
            currentOperationType = AttachmentType.NONE
        }
    }

    fun getStickers(): MutableList<IAttachmentDrawable> {
        return stickerView.getDrawables()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.let {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR)
            stickerView.draw(canvas)
            labelView.draw(canvas)
        }
    }

    fun hideSelectedFrame() {
        stickerView.hideSelectedFrame()
        labelView.hideSelectedFrame()
        invalidate()
    }

    fun destroy() {
        stickerView.destroy()
        labelView.destroy()
    }


    enum class AttachmentType{
        NONE,STICKER, LABEL
    }

}