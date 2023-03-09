package com.bzf.module_imageeditor.sticker

import android.content.Context
import android.graphics.*
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.utils.LogUtils
import java.security.SecureRandom
import kotlin.math.abs

/**
 *@author: baizf
 *@date: 2023/2/15
 */
class StickerDrawable(val context: Context, val bitmap: Bitmap) {


    val mStickerPadding = context.resources.getDimension(R.dimen.dp_6)

    private var mStickerRect = RectF()
    private val mOriginalRect = Rect(0, 0, bitmap.width, bitmap.height)

    private var mStickerFrameRect = RectF()
    private val mStickerMatrix = Matrix()
    private val mTempRect = RectF()

    private var mRotateAngle: Float = 0.0f
    private var mOffsetPoint = PointF()

    private var mRatioWH = 0.0f

    private var mMaxSize: Float = 0.0f
    private var mMinSize: Float = context.resources.getDimension(R.dimen.dp_30)

    private val mDeleteBitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_edit_sticker_delete
    )
    private val mDeleteRect = RectF()


    private val mResizeBitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_edit_sticker_resize
    )
    private val mResizeRect = RectF()

    var isSelected = false


    private val mLinePaint: Paint by lazy {
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = context.resources.getColor(R.color.cl_ffffff)
        paint.strokeWidth = context.resources.getDimension(R.dimen.dp_1)
        paint
    }
    private val mLinePath = Path()

    fun draw(canvas: Canvas) {
        canvas.save()
        canvas.setMatrix(mStickerMatrix)
        canvas.drawBitmap(bitmap, mOriginalRect, mStickerRect, null)

        if(isSelected){
            drawFrame(canvas)
            canvas.drawBitmap(mDeleteBitmap,mDeleteRect.left,mDeleteRect.top,null)
            canvas.drawBitmap(mResizeBitmap,mResizeRect.left, mResizeRect.top,null)
        }
        canvas.restore()
    }


    private fun drawFrame(canvas: Canvas) {
        mLinePath.reset()
        mLinePath.addRect(mStickerFrameRect, Path.Direction.CW)
        canvas.drawPath(mLinePath, mLinePaint)
    }


    fun setPosition(rect: Rect, viewWidth: Int, viewHeight: Int) {
        mRatioWH = rect.width().toFloat() / rect.height().toFloat()

        mOffsetPoint.set(createRandomOffset(viewWidth, viewHeight, rect))

        mStickerRect.set(
            0f,
            0f,
            rect.width().toFloat(),
            rect.height().toFloat()
        )

        mStickerFrameRect.set(mStickerRect)
        mStickerFrameRect.inset(-mStickerPadding, -mStickerPadding)

        setDeleteBimapRect()
        setResizeBitmapRect()
        resetStickerMatrix()

        mMaxSize = viewWidth - mStickerPadding * 4

        isSelected = true
    }

    private fun setDeleteBimapRect() {
        val deleteLeft = mStickerFrameRect.left - mDeleteBitmap.width / 2
        val deleteTop = mStickerFrameRect.top - mDeleteBitmap.height / 2
        val deleteRight = deleteLeft + mDeleteBitmap.width
        val deleteBottom = deleteTop + mDeleteBitmap.height
        mDeleteRect.set(deleteLeft, deleteTop, deleteRight, deleteBottom)
    }

    private fun setResizeBitmapRect() {
        val resizeLeft = mStickerFrameRect.right - mResizeBitmap.width / 2
        val resizeTop = mStickerFrameRect.bottom - mResizeBitmap.height / 2
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

    fun isTouch(x: Float, y: Float): Boolean {
//        var arr = floatArrayOf(x,y)
        mTempRect.set(mStickerRect)
        mStickerMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }


    fun changePosition(x: Float, y: Float) {
        mOffsetPoint.set(mOffsetPoint.x + x, mOffsetPoint.y + y)
        resetStickerMatrix()
    }

    fun getCenterPoint(): PointF {
        mTempRect.set(mStickerFrameRect)
        mStickerMatrix.mapRect(mTempRect)
        return PointF(mTempRect.centerX(),mTempRect.centerY())
    }

    fun getRightBottomPoint(): PointF {
        mTempRect.set(mStickerFrameRect)
        mStickerMatrix.mapRect(mTempRect)
        return PointF(mTempRect.right, mTempRect.bottom)
    }

    fun setRotateAngle(offsetAngle: Float) {
        mRotateAngle = offsetAngle
//        mRotateAngle %= 360.0f
        LogUtils.printI("mRotateAngle=$mRotateAngle")

//        resetStickerMatrix()
    }

    private fun resetStickerMatrix() {
        mStickerMatrix.reset()
        mStickerMatrix.setTranslate(mOffsetPoint.x, mOffsetPoint.y)
        mStickerMatrix.preRotate(mRotateAngle, mStickerRect.centerX(), mStickerRect.centerY())
    }

    fun resize(distance: Float) {
        val dx = distance
        val dy = distance / mRatioWH

        mTempRect.set(mStickerRect)

        mTempRect.inset(-dx, -dy)
        if(mTempRect.width() <= mMinSize || mTempRect.height() <= mMinSize){
            return
        }
        if(mTempRect.width() >= mMaxSize || mTempRect.height() >= mMaxSize){
            return
        }
        mStickerRect.set(mTempRect)

        mStickerFrameRect.set(mStickerRect)
        mStickerFrameRect.inset(-mStickerPadding, -mStickerPadding)

        setDeleteBimapRect()
        setResizeBitmapRect()
        resetStickerMatrix()
        LogUtils.printI("resize--dx=$distance, dy=$dy,  mStickerRect=${mStickerRect.toString()}")
    }

    fun getOffsetPoint(): PointF {
        return mOffsetPoint
    }

    fun getRotateAngle(): Float {
        return mRotateAngle
    }

    fun getStickerRect(): RectF {
        mTempRect.set(mStickerRect)
        return mTempRect
    }

    fun isTouchDelete(x: Float, y: Float): Boolean {
        mTempRect.set(mDeleteRect)
        mTempRect.inset(-mStickerPadding, -mStickerPadding)
        mStickerMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }

    fun isTouchResize(x: Float, y: Float): Boolean {
        mTempRect.set(mResizeRect)
        mTempRect.inset(-mStickerPadding, -mStickerPadding)
        mStickerMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }

}