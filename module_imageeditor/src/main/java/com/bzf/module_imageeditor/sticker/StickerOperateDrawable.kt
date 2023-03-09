package com.bzf.module_imageeditor.sticker

import android.graphics.*

/**
 *@author: baizf
 *@date: 2023/2/16
 */
class StickerOperateDrawable(val bitmap: Bitmap) {

    private val mOriginRect = Rect(0,0,bitmap.width, bitmap.height)
    private val mScaleRect = RectF(mOriginRect)
    var mMatrix = Matrix()
    private var mTempMatrix = Matrix()

    private var mRotateAngle = 0.0f

    fun draw(canvas: Canvas){
        canvas.save()
        canvas.setMatrix(mMatrix)
        canvas.drawBitmap(bitmap,mOriginRect,mScaleRect,null)
        canvas.restore()
    }
//
//    fun setRect(rectF: RectF, position: Position){
//        when(position){
//            Position.LEFT_TOP->{
//                val left = rectF.left - bitmap.width / 2
//                val top = rectF.top - bitmap.height / 2
//                val right = left + bitmap.width
//                val bottom = top + bitmap.height
//                mScaleRect.set(left,top,right,bottom)
//            }
//            Position.RIGHT_BOTTOM->{
//                val left = rectF.right - bitmap.width / 2
//                val top = rectF.bottom - bitmap.height / 2
//                val right = left + bitmap.width
//                val bottom = top + bitmap.height
//                mScaleRect.set(left,top,right,bottom)
//            }
//            else->{
//
//            }
//        }
//    }

    fun setMatrix(stickerDrawable: StickerDrawable, position: Position){
        val offsetPoint = stickerDrawable.getOffsetPoint()
        val stickerRect = stickerDrawable.getStickerRect()

        var centerX = 0.0f
        var centerY = 0.0f
        if(position == Position.RIGHT_BOTTOM){
            centerX = offsetPoint.x + stickerRect.width() + stickerDrawable.mStickerPadding
            centerY = offsetPoint.y + stickerRect.height() + stickerDrawable.mStickerPadding
        }else if(position == Position.LEFT_TOP){
            centerX = offsetPoint.x - stickerDrawable.mStickerPadding
            centerY = offsetPoint.y - stickerDrawable.mStickerPadding
        }
        val offsetX = centerX - mScaleRect.centerX()
        val offsetY = centerY - mScaleRect.centerY()

        mRotateAngle = stickerDrawable.getRotateAngle()

        val stickerCenterX = offsetPoint.x + stickerRect.centerX()
        val stickerCenterY = offsetPoint.y + stickerRect.centerY()
        mMatrix.reset()
        mMatrix.setTranslate(stickerCenterX,stickerCenterY)
        mMatrix.preRotate(mRotateAngle, stickerCenterX,stickerCenterY)
        mMatrix.preTranslate(stickerRect.centerX() + stickerDrawable.mStickerPadding - mScaleRect.centerX(), stickerRect.centerY() + stickerDrawable.mStickerPadding - mScaleRect.centerY())
    }

    fun isTouch(x: Float, y: Float): Boolean {
        var arr = floatArrayOf(x,y)
        mTempMatrix.set(mMatrix)
        mTempMatrix.mapPoints(arr)
        return mScaleRect.contains(arr[0], arr[1])
    }

    enum class Position{
        LEFT_TOP,LEFT_BOTTOM,RIGHT_TOP,RIGHT_BOTTOM
    }
}