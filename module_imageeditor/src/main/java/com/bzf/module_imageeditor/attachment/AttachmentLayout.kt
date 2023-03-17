package com.bzf.module_imageeditor.attachment

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bzf.module_imageeditor.attachment.base.AttachmentViewBase
import com.bzf.module_imageeditor.attachment.base.IAttachmentDraw
import com.bzf.module_imageeditor.attachment.sticker.display.StickerAttachmentView
import com.bzf.module_imageeditor.attachment.sticker.display.StickerDrawable
import com.bzf.module_imageeditor.attachment.sticker.display.StickerEntity

/**
 *@author: baizf
 *@date: 2023/3/16
 */
class AttachmentLayout: View {

    private val stickerView: StickerAttachmentView = StickerAttachmentView(this)


    private val mGestureHelper = AttachmentGestureHelper(this)

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
        stickerView.delete()
    }

    fun move(currentX: Float, currentY: Float, lastX: Float, lastY: Float) {
        stickerView.move(currentX,currentY,lastX,lastY)
    }

    fun addStickerAttachment(stickerEntity: StickerEntity){
        val stickerDrawable = StickerDrawable(context, stickerEntity)
        stickerView.add(stickerDrawable)
    }


    fun isSelected(x: Float, y: Float) {
        stickerView.isTouchAtAttachment(x,y)
    }

    fun getStickers(): MutableList<IAttachmentDraw> {
        return stickerView.getStickers()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.let {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR)
            stickerView.draw(canvas)
        }
    }

    fun hideSelectedFrame() {
        stickerView.hideSelectedFrame()
        invalidate()
    }


    enum class AttachmentType{
        STICKER, LABEL
    }

}