package com.bzf.module_imageeditor.attachment.base

import android.content.Context
import android.graphics.*
import com.bzf.module_imageeditor.R
import module.common.utils.DensityUtil

/**
 *@author: baizf
 *@date: 2023/3/17
 */
abstract class AttachmentDrawBase(val context: Context): IAttachmentDraw {


    protected val mRect = RectF()
    protected val mTempRect = RectF()

    protected val mDeleteBitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_edit_sticker_delete
    )
    protected val mDeleteRect = RectF()

    protected val mLinePaint: Paint by lazy {
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = context.resources.getColor(R.color.cl_ffffff)
        paint.strokeWidth = context.resources.getDimension(R.dimen.dp_1)

        val dashPathEffect = DashPathEffect(floatArrayOf(DensityUtil.dip2pxFloat(context,10f), DensityUtil.dip2pxFloat(context,5f)), 0f)
        paint.pathEffect = dashPathEffect

        paint
    }
    protected val mLinePath = Path()
    protected var mSelectedFrameRect = RectF()


    protected var mStickerPadding = context.resources.getDimension(R.dimen.dp_6)


    protected val mMatrix = Matrix()

    protected var mOffsetPoint = PointF()

    protected var selectedStatus = false

    override fun isSelected(): Boolean {
        return selectedStatus
    }

    override fun setSelected(selected: Boolean) {
        selectedStatus = selected
    }


    override fun setDeleteBimapRect() {
        val deleteLeft = mSelectedFrameRect.left - mDeleteBitmap.width / 2
        val deleteTop = mSelectedFrameRect.top - mDeleteBitmap.height / 2
        val deleteRight = deleteLeft + mDeleteBitmap.width
        val deleteBottom = deleteTop + mDeleteBitmap.height
        mDeleteRect.set(deleteLeft, deleteTop, deleteRight, deleteBottom)
    }



    override fun isTouchDelete(x: Float, y: Float): Boolean {
        mTempRect.set(mDeleteRect)
        mTempRect.inset(-mStickerPadding, -mStickerPadding)
        mMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }

    override fun isTouch(x: Float, y: Float): Boolean {
        mTempRect.set(mRect)
        mMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }



    override fun draw(canvas: Canvas) {

        canvas.save()
        canvas.setMatrix(mMatrix)

        drawSelf(canvas)

        if(isSelected()){
            drawFrame(canvas)
            canvas.drawBitmap(mDeleteBitmap,mDeleteRect.left,mDeleteRect.top,null)
            drawOtherOperationBitmap(canvas)
        }
        canvas.restore()
    }

    override fun drawFrame(canvas: Canvas) {
        mLinePath.reset()
        mLinePath.addRect(mSelectedFrameRect, Path.Direction.CW)
        canvas.drawPath(mLinePath, mLinePaint)
    }

    override fun changePosition(x: Float, y: Float) {
        mOffsetPoint.set(mOffsetPoint.x + x, mOffsetPoint.y + y)
        resetMatrix()
    }

    open fun resetMatrix() {
        mMatrix.reset()
        mMatrix.setTranslate(mOffsetPoint.x, mOffsetPoint.y)
    }



    abstract fun drawSelf(canvas: Canvas)

    open fun drawOtherOperationBitmap(canvas: Canvas){

    }
}