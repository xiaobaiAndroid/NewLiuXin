package com.bzf.module_imageeditor.sticker

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector

/**
 *@author: baizf
 *@date: 2023/2/16
 */
class StickerGestureHelper(val stickersImageView: StickersImageView) {

    private val mScaleGestureDetector = ScaleGestureDetector(stickersImageView.context, StickerScaleListener())
    private val mGestureDetector = GestureDetector(stickersImageView.context,StickerGestureListener())

    fun onTouchEvent(event: MotionEvent): Boolean{
        mScaleGestureDetector.onTouchEvent(event)

        if(!mScaleGestureDetector.isInProgress){
            mGestureDetector.onTouchEvent(event)
        }


        val action = event.action
        if(action == MotionEvent.ACTION_UP){
            //禁止ViewGroup拦截当前事件
            stickersImageView.parent.requestDisallowInterceptTouchEvent(false)
        }
        return true
    }

    private inner class StickerGestureListener: GestureDetector.SimpleOnGestureListener(){

        private var lastMoveX: Float = 0.0f
        private var lastMoveY: Float = 0.0f

        //用户手指松开（UP事件）的时候如果没有执行onScroll()和onLongPress()这两个回调的话，就会回调这个，说明这是一个点击抬起事件，但是不能区分是否双击事件的抬起。
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            stickersImageView.isDeleteSticker(e.x,e.y)
            return true
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            return super.onDoubleTap(e)
        }

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            stickersImageView.move(e2.x, e2.y,lastMoveX, lastMoveY)

            lastMoveX = e2.x
            lastMoveY = e2.y
            return true
        }

        override fun onDown(e: MotionEvent): Boolean {
            lastMoveX = e.x
            lastMoveY = e.y
            stickersImageView.isSelectedSticker(e.x, e.y)
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }

    private inner class StickerScaleListener: ScaleGestureDetector.SimpleOnScaleGestureListener(){

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            return super.onScale(detector)
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            return super.onScaleBegin(detector)
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            super.onScaleEnd(detector)
        }
    }
}