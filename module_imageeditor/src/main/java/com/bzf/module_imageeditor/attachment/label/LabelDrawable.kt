package com.bzf.module_imageeditor.attachment.label

import android.content.Context
import android.graphics.*
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.attachment.base.IAttachmentDraw
import com.bzf.module_imageeditor.label.select.LabelEntity
import com.bzf.module_imageeditor.utils.LogUtils
import module.common.utils.DensityUtil
import java.security.SecureRandom

/**
 *@author: baizf
 *@date: 2023/3/15
 */
class LabelDrawable(val context: Context, val label: LabelEntity, val listener: Listener): IAttachmentDraw {

    val mLabelBorderPadding = DensityUtil.dip2pxFloat(context,12f)

    val mLabelDrawWidth: Float by lazy {
        mContentDrawable.width + mCirclePointDrawable.getWidth() + mCirclePointBgPadding
    }
    val mLabelDrawHeight = DensityUtil.dip2pxFloat(context,40f)


    private val mCirclePointBgPadding = DensityUtil.dip2pxFloat(context,8f)


    private val mLabelRect: RectF by lazy {
        val rect = RectF()
        rect.set(0f,0f,mLabelDrawWidth, mLabelDrawHeight)
        rect
    }




    private val mLabelFrameRect: RectF by lazy {
        val rectF = RectF()
        rectF.set(mLabelRect)
        rectF.left -= mLabelBorderPadding/2
        rectF.right += mLabelBorderPadding
        rectF.top -= mLabelBorderPadding
        rectF.bottom += mLabelBorderPadding

        rectF
    }

    private val mLabelMatrix = Matrix()
    private val mTempRect = RectF()

    private var mOffsetPoint = PointF()


    private val mDeleteBitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_edit_sticker_delete
    )
    private val mDeleteRect = RectF()


    private val mCirclePointDrawable  = LabelCirclePointDrawble(context,listener)
    private val mContentDrawable = LabelContentDrawable(context,mLabelDrawHeight,label.name)


    private val mLinePaint: Paint by lazy {
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = context.resources.getColor(R.color.cl_ffffff)
        paint.strokeWidth = context.resources.getDimension(R.dimen.dp_1)

        val dashPathEffect = DashPathEffect(floatArrayOf(DensityUtil.dip2pxFloat(context,10f), DensityUtil.dip2pxFloat(context,5f)), 0f)
        paint.pathEffect = dashPathEffect

        paint
    }
    private val mLinePath = Path()

    override fun draw(canvas: Canvas) {
        canvas.save()
        canvas.setMatrix(mLabelMatrix)
        drawCirclePoint(canvas)
        drawText(canvas)

        if(isSelected()){
            drawFrame(canvas)
            canvas.drawBitmap(mDeleteBitmap,mDeleteRect.left,mDeleteRect.top,null)
        }
        canvas.restore()
    }

    private fun drawText(canvas: Canvas) {
        canvas.save()
        canvas.translate(mCirclePointDrawable.getWidth() + mCirclePointBgPadding,0f)
        mContentDrawable.draw(canvas)
        canvas.restore()
    }


    private fun drawCirclePoint(canvas: Canvas) {
        canvas.save()
        canvas.translate(mCirclePointDrawable.getRadius(), mLabelDrawHeight/2.0f)
        mCirclePointDrawable.draw(canvas)
        canvas.restore()
    }


    override fun drawFrame(canvas: Canvas) {
        mLinePath.reset()
        mLinePath.addRect(mLabelFrameRect, Path.Direction.CW)
        canvas.drawPath(mLinePath, mLinePaint)
    }


    fun setPosition(viewWidth: Int, viewHeight: Int) {
        val left: Int = (viewWidth - mLabelDrawWidth.toInt()) / 2
        val top: Int = (viewHeight - mLabelDrawHeight.toInt()) / 2
        val rect = Rect(
            left,
            top,
            mLabelDrawWidth.toInt() - left,
            mLabelDrawHeight.toInt() - top
        )

        mOffsetPoint.set(createRandomOffset(viewWidth, viewHeight, rect))

        setDeleteBimapRect()
        resetLabelMatrix()

        setSelected(true)
    }

    override fun setDeleteBimapRect() {
        val deleteLeft = mLabelFrameRect.left - mDeleteBitmap.width / 2
        val deleteTop = mLabelFrameRect.top - mDeleteBitmap.height / 2
        val deleteRight = deleteLeft + mDeleteBitmap.width
        val deleteBottom = deleteTop + mDeleteBitmap.height
        mDeleteRect.set(deleteLeft, deleteTop, deleteRight, deleteBottom)
    }

    override fun resize(distance: Float) {
        TODO("Not yet implemented")
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

    override fun isTouch(x: Float, y: Float): Boolean {
//        var arr = floatArrayOf(x,y)
        mTempRect.set(mLabelRect)
        mLabelMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }


    override fun changePosition(x: Float, y: Float) {
        mOffsetPoint.set(mOffsetPoint.x + x, mOffsetPoint.y + y)
        resetLabelMatrix()
    }

    override fun getCenterPoint(): PointF {
        mTempRect.set(mLabelFrameRect)
        mLabelMatrix.mapRect(mTempRect)
        return PointF(mTempRect.centerX(),mTempRect.centerY())
    }

    override fun setRotateAngle(offsetAngle: Float) {
        TODO("Not yet implemented")
    }

    override fun isTouchResize(x: Float, y: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSelected(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSelected(selected: Boolean) {
        TODO("Not yet implemented")
    }

    fun getRightBottomPoint(): PointF {
        mTempRect.set(mLabelFrameRect)
        mLabelMatrix.mapRect(mTempRect)
        return PointF(mTempRect.right, mTempRect.bottom)
    }

    private fun resetLabelMatrix() {
        mLabelMatrix.reset()
        mLabelMatrix.setTranslate(mOffsetPoint.x, mOffsetPoint.y)
    }


    fun getOffsetPoint(): PointF {
        return mOffsetPoint
    }


    fun getLabelRect(): RectF {
        mTempRect.set(mLabelRect)
        return mTempRect
    }

    override fun isTouchDelete(x: Float, y: Float): Boolean {
        mTempRect.set(mDeleteRect)
        mTempRect.inset(-mLabelBorderPadding, -mLabelBorderPadding)
        mLabelMatrix.mapRect(mTempRect)
        return mTempRect.contains(x, y)
    }

    fun startAnimation(){
        mCirclePointDrawable.startAnimation()
    }

    fun destroy() {
        mCirclePointDrawable.stopAnimation()
    }

    interface Listener{

        fun invalidate()
    }

}