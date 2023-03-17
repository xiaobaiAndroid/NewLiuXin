package com.bzf.module_imageeditor.attachment.sticker.display

import android.content.Context
import android.graphics.*
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.attachment.base.AttachmentDrawBase
import com.bzf.module_imageeditor.utils.LogUtils

/**
 *@author: baizf
 *@date: 2023/2/15
 */
class StickerDrawable(context: Context, val attachment: StickerEntity): AttachmentDrawBase(context) {

    val bitmap: Bitmap = context.assets.open(attachment.path).use {
        BitmapFactory.decodeStream(it)
    }

    private var mRotateAngle: Float = 0.0f

    private var mRatioWH = 0.0f

    private var mMaxSize: Float = 0.0f
    private var mMinSize: Float = context.resources.getDimension(R.dimen.dp_30)



    private val mResizeBitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_edit_sticker_resize
    )
    private val mResizeRect = RectF()

    init {
        mOriginalRect = Rect(0, 0, bitmap.width, bitmap.height)
    }


    override fun setPosition(rect: Rect, viewWidth: Int, viewHeight: Int) {
        super.setPosition(rect, viewWidth, viewHeight)
        mRatioWH = rect.width().toFloat() / rect.height().toFloat()
        mMaxSize = viewWidth/2f
        setResizeBitmapRect()
    }


    private fun setResizeBitmapRect() {
        val resizeLeft = mSelectedFrameRect.right - mResizeBitmap.width / 2
        val resizeTop = mSelectedFrameRect.bottom - mResizeBitmap.height / 2
        val resizeRight = resizeLeft + mResizeBitmap.width
        val resizeBottom = resizeTop + mResizeBitmap.height
        mResizeRect.set(resizeLeft, resizeTop, resizeRight, resizeBottom)
    }




    override fun drawSelf(canvas: Canvas) {
        canvas.drawBitmap(bitmap, mOriginalRect, mRect, null)
    }

    override fun drawOtherOperationBitmap(canvas: Canvas) {
        super.drawOtherOperationBitmap(canvas)
        canvas.drawBitmap(mResizeBitmap,mResizeRect.left, mResizeRect.top,null)
    }


    fun getRightBottomPoint(): PointF {
        mTempRect.set(mSelectedFrameRect)
        mMatrix.mapRect(mTempRect)
        return PointF(mTempRect.right, mTempRect.bottom)
    }

    override fun setRotateAngle(offsetAngle: Float) {
        mRotateAngle = offsetAngle
//        mRotateAngle %= 360.0f
        LogUtils.printI("mRotateAngle=$mRotateAngle")

//        resetStickerMatrix()
    }

    override fun resetMatrix() {
        super.resetMatrix()
        mMatrix.preRotate(mRotateAngle, mRect.centerX(), mRect.centerY())
    }

    override fun resize(distance: Float) {
        val dx = distance
        val dy = distance / mRatioWH

        mTempRect.set(mRect)

        mTempRect.inset(-dx, -dy)
        if(mTempRect.width() <= mMinSize || mTempRect.height() <= mMinSize){
            return
        }
        if(mTempRect.width() >= mMaxSize || mTempRect.height() >= mMaxSize){
            return
        }
        mRect.set(mTempRect)

        mSelectedFrameRect.set(mRect)
        mSelectedFrameRect.inset(-mPadding, -mPadding)

        setDeleteBimapRect()
        setResizeBitmapRect()
        resetMatrix()
        LogUtils.printI("resize--dx=$distance, dy=$dy,  mRect=${mRect.toString()}")
    }

    fun getOffsetPoint(): PointF {
        return mOffsetPoint
    }

    fun getRotateAngle(): Float {
        return mRotateAngle
    }

    fun getStickerRect(): RectF {
        mTempRect.set(mRect)
        return mTempRect
    }


    override fun isTouchResize(x: Float, y: Float): Boolean {
        mTempRect.set(mResizeRect)
        mTempRect.inset(-mPadding, -mPadding)
        mMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }




}