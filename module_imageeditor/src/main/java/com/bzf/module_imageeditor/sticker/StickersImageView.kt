package com.bzf.module_imageeditor.sticker

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bzf.module_imageeditor.utils.LogUtils
import com.bzf.module_imageeditor.utils.Point2D

/**
 *@author: baizf
 *@date: 2023/2/15
 */
class StickersImageView : View {

    companion object {
        const val NO_SELECTED = -1
    }

    var mSelectedPosition = NO_SELECTED

    val mStickers = mutableListOf<StickerDrawable>()

    private var mOperateType = StickerOperateType.NONE


    private val mGestureHelper = StickerGestureHelper(this)

    constructor(context: Context, attributes: AttributeSet?, defaultAttr: Int) : super(
        context,
        attributes,
        defaultAttr
    ) {

    }

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0) {
    }

    constructor(context: Context) : this(context, null, 0) {

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawColor(0, PorterDuff.Mode.CLEAR)
            if (mStickers.isEmpty()) {
                return
            }

            for (i in mStickers.indices) {
                val sticker = mStickers[i]
                sticker.isSelected = (i == mSelectedPosition)
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
        if (mStickers.isNotEmpty()) {
            mOperateType = StickerOperateType.NONE

            var tempSelectPosition = NO_SELECTED
            for (i in mStickers.indices) {
                val stickerDraw = mStickers.get(i);
                if (stickerDraw.isTouch(x, y)) {
                    tempSelectPosition = i
                }
            }
            if (tempSelectPosition == NO_SELECTED) { //点击事件都不在所有贴纸上,在看是不是在操作按钮上
                if(isStickerSelected()){
                    val stickerDrawable = mStickers[mSelectedPosition]
                    if(stickerDrawable.isTouchDelete(x,y)){
                        mOperateType = StickerOperateType.DELETE
                    }else if(stickerDrawable.isTouchResize(x,y)){
                        mOperateType = StickerOperateType.RESIZE
                    }else{
                        hideSelectedFrame()
                    }
                }else{
                    hideSelectedFrame()
                }
            } else {
                mStickers[tempSelectPosition].isSelected = true
                cancelSelectedSticker()
                mSelectedPosition = tempSelectPosition
                mOperateType = StickerOperateType.MOVE
            }
            invalidate()
        }
    }

    private fun cancelSelectedSticker() {
        if (isStickerSelected()) {
            mStickers[mSelectedPosition].isSelected = false
        }
    }

    fun addSticker(stickerDrawable: StickerDrawable) {
        mStickers.add(stickerDrawable)
        mSelectedPosition = mStickers.size - 1
    }

    fun move(currentX: Float, currentY: Float, lastX: Float, lastY: Float) {
        if (isStickerSelected() && mStickers.isNotEmpty()) {
            //禁止ViewGroup拦截当前事件
            parent.requestDisallowInterceptTouchEvent(true)

            val offsetX = currentX - lastX
            val offsetY = currentY - lastY

            val stickerDrawable = mStickers[mSelectedPosition]
            when(mOperateType){
                StickerOperateType.MOVE->{
                    stickerDrawable.changePosition(offsetX, offsetY)
                }
                StickerOperateType.RESIZE->{
                    computeAngleAndScale(stickerDrawable, currentX, currentY, offsetX, offsetY)
                }
                else->{

                }
            }
            invalidate()
        } else {
            //不禁止ViewGroup拦截当前事件
            parent.requestDisallowInterceptTouchEvent(false)
        }
    }

    /*
    * @describe: 计算旋转和缩放
    * @date: 2023/2/17
    */
    private fun computeAngleAndScale(
        stickerDrawable: StickerDrawable,
        currentX: Float,
        currentY: Float,
        offsetX: Float,
        offsetY: Float
    ) {
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

    private fun isStickerSelected(): Boolean {
        return mSelectedPosition != NO_SELECTED
    }

    fun isDeleteSticker(x: Float, y: Float) {
        if (isStickerSelected() && mOperateType == StickerOperateType.DELETE) {
            mStickers.removeAt(mSelectedPosition)
            mSelectedPosition = NO_SELECTED
            invalidate()
        }
    }

    fun hideSelectedFrame() {
        cancelSelectedSticker()
        mSelectedPosition = NO_SELECTED
        mOperateType = StickerOperateType.NONE
    }
}