package com.bzf.module_imageeditor.attachment.sticker.display

import android.content.Context
import android.graphics.*
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.attachment.base.AttachmentDrawBase
import com.bzf.module_imageeditor.utils.LogUtils
import java.security.SecureRandom

/**
 *@author: baizf
 *@date: 2023/2/15
 */
class StickerDrawable(context: Context, val attachment: StickerEntity): AttachmentDrawBase(context) {

    val bitmap: Bitmap = context.assets.open(attachment.path).use {
        BitmapFactory.decodeStream(it)
    }

    private val mOriginalRect = Rect(0, 0, bitmap.width, bitmap.height)

    private var mRotateAngle: Float = 0.0f

    private var mRatioWH = 0.0f

    private var mMaxSize: Float = 0.0f
    private var mMinSize: Float = context.resources.getDimension(R.dimen.dp_30)



    private val mResizeBitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_edit_sticker_resize
    )
    private val mResizeRect = RectF()




    fun setPosition(rect: Rect, viewWidth: Int, viewHeight: Int) {
        mRatioWH = rect.width().toFloat() / rect.height().toFloat()

        mOffsetPoint.set(createRandomOffset(viewWidth, viewHeight, rect))

        mRect.set(
            0f,
            0f,
            rect.width().toFloat(),
            rect.height().toFloat()
        )

        mSelectedFrameRect.set(mRect)
        mSelectedFrameRect.inset(-mStickerPadding, -mStickerPadding)

        setDeleteBimapRect()
        setResizeBitmapRect()
        resetMatrix()

        mMaxSize = viewWidth/2f

        setSelected(true)
    }


    private fun setResizeBitmapRect() {
        val resizeLeft = mSelectedFrameRect.right - mResizeBitmap.width / 2
        val resizeTop = mSelectedFrameRect.bottom - mResizeBitmap.height / 2
        val resizeRight = resizeLeft + mResizeBitmap.width
        val resizeBottom = resizeTop + mResizeBitmap.height
        mResizeRect.set(resizeLeft, resizeTop, resizeRight, resizeBottom)
    }

    private fun createRandomOffset(
        viewWidth: Int,
        viewHeight: Int,
        rect: Rect
    ): PointF {
        val rangeX = viewWidth / 4
        val rangeY = viewHeight / 4

        val random = SecureRandom()
        val isLeft = random.nextInt(100)

        val randomX = SecureRandom.getInstance("SHA1PRNG")
        val randomY = SecureRandom.getInstance("SHA1PRNG")

        val offsetX: Int
        val offsetY: Int
        if (isLeft < 50) {
            offsetX = rect.left - randomX.nextInt(rangeX)
            offsetY = rect.top - randomY.nextInt(rangeY)
        } else {
            offsetX = rect.left - randomX.nextInt(rangeX)
            offsetY = rect.top - randomY.nextInt(rangeY)
        }
        LogUtils.printI("offsetX=$offsetX, offsetY=$offsetY")

        return PointF(offsetX.toFloat(), offsetY.toFloat())
    }


    override fun drawSelf(canvas: Canvas) {
        canvas.drawBitmap(bitmap, mOriginalRect, mRect, null)
    }

    override fun drawOtherOperationBitmap(canvas: Canvas) {
        super.drawOtherOperationBitmap(canvas)
        canvas.drawBitmap(mResizeBitmap,mResizeRect.left, mResizeRect.top,null)
    }

    override fun getCenterPoint(): PointF {
        mTempRect.set(mSelectedFrameRect)
        mMatrix.mapRect(mTempRect)
        return PointF(mTempRect.centerX(),mTempRect.centerY())
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
        mSelectedFrameRect.inset(-mStickerPadding, -mStickerPadding)

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
        mTempRect.inset(-mStickerPadding, -mStickerPadding)
        mMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }


}