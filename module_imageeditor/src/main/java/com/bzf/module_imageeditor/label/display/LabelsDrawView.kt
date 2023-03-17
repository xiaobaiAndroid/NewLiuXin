package com.bzf.module_imageeditor.label.display

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bzf.module_imageeditor.attachment.label.LabelDrawable
import com.bzf.module_imageeditor.label.select.LabelEntity

/**
 *@author: baizf
 *@date: 2023/3/15
 */
class LabelsDrawView: View {

    companion object {
        const val NO_SELECTED = -1
    }

    var mSelectedPosition = NO_SELECTED

    val mLables = mutableListOf<LabelDrawable>()

    private var mOperateType = LabelOperateType.NONE


    private val mGestureHelper = LabelGestureHelper(this)

    constructor(context: Context, attributes: AttributeSet?, defaultAttr: Int) : super(
        context,
        attributes,
        defaultAttr
    )
    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)
    constructor(context: Context) : this(context, null, 0)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            it.drawColor(0, PorterDuff.Mode.CLEAR)
            if (mLables.isEmpty()) {
                return
            }

            for (i in mLables.indices) {
                val sticker = mLables[i]
                sticker.setSelected((i == mSelectedPosition))
                sticker.draw(it)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return event?.let {
            mGestureHelper.onTouchEvent(it)
        } ?: return super.onTouchEvent(event)
    }


    fun isSelectedSticker(x: Float, y: Float) {
        if (mLables.isNotEmpty()) {
            mOperateType = LabelOperateType.NONE

            var tempSelectPosition = NO_SELECTED
            for (i in mLables.indices) {
                val labelDrawable = mLables[i]
                if (labelDrawable.isTouch(x, y)) {
                    tempSelectPosition = i
                }
            }
            if (tempSelectPosition == NO_SELECTED) { //点击事件都不在所有贴纸上,在看是不是在操作按钮上
                if(isLabelSelected()){
                    val labelDrawable = mLables[mSelectedPosition]
                    if(labelDrawable.isTouchDelete(x,y)){
                        mOperateType = LabelOperateType.DELETE
                    }else{
                        hideSelectedFrame()
                    }
                }else{
                    hideSelectedFrame()
                }
            } else {
                mLables[tempSelectPosition].setSelected(true)
                cancelSelectedSticker()
                mSelectedPosition = tempSelectPosition
                mOperateType = LabelOperateType.MOVE
            }
            invalidate()
        }
    }

    private fun cancelSelectedSticker() {
        if (isLabelSelected()) {
            mLables[mSelectedPosition].setSelected(false)
        }
    }

    fun addLabel(labelEntity: LabelEntity) {
        val labelDrawable = LabelDrawable(context, labelEntity,object: LabelDrawable.Listener{
            override fun invalidate() {
                this@LabelsDrawView.invalidate()
            }
        })
        labelDrawable.setPosition(width, height)
        mLables.add(labelDrawable)
        mSelectedPosition = mLables.size - 1

        invalidate()

        labelDrawable.startAnimation()
    }

    fun move(currentX: Float, currentY: Float, lastX: Float, lastY: Float) {
        if (isLabelSelected() && mLables.isNotEmpty()) {
            //禁止ViewGroup拦截当前事件
            parent.requestDisallowInterceptTouchEvent(true)

            val offsetX = currentX - lastX
            val offsetY = currentY - lastY

            val labelDrawable = mLables[mSelectedPosition]
            labelDrawable.changePosition(offsetX, offsetY)
            invalidate()
        } else {
            //不禁止ViewGroup拦截当前事件
            parent.requestDisallowInterceptTouchEvent(false)
        }
    }

    private fun isLabelSelected(): Boolean {
        return mSelectedPosition != NO_SELECTED
    }

    fun isDeleteSticker(x: Float, y: Float) {
        if (isLabelSelected() && mOperateType == LabelOperateType.DELETE) {
            mLables.removeAt(mSelectedPosition)
            mSelectedPosition = NO_SELECTED
            invalidate()
        }
    }

    fun hideSelectedFrame() {
        cancelSelectedSticker()
        mSelectedPosition = NO_SELECTED
        mOperateType = LabelOperateType.NONE
    }

    fun destroy(){
        for ( labelDrawable in mLables){
            labelDrawable.destroy()
        }
    }


}