package com.bzf.module_imageeditor.utils

import android.graphics.PointF
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

/**
 *@author: baizf
 *@date: 2023/2/17
 */
object Point2D {

    private const val errorValue = 0.00001f

    /*
    * @describe: 计算两个点的组成的向量与x轴的夹角
    * @date: 2023/2/17
    */
    fun angleBetweenPoints(point1: PointF, point2: PointF): Float {
        val x1 = point1.x
        val y1 = point1.y
        val x2 = point2.x
        val y2 = point2.y

        val offsetX = abs(x1 - x2)
        val offsetY = abs(y1 - y2)
        if (offsetX < errorValue && offsetY < errorValue) return 0.0f
        val gradiant = atan2(x1 - x2, y1 - y2)
        val angle: Double = degrees(gradiant)
        return angle360(angle.toFloat())
    }

    private fun angle360(angle: Float): Float {
        var angle = angle
        if (angle < 0.0f) angle = angle % -360.0f + 360.0f else {
            angle %= 360.0f
        }
        return angle
    }

    private fun degrees(radians: Float): Double {
        return radians * 57.29577951308232
    }

    fun distance(pt1: PointF, pt2: PointF): Double {
        return distance(pt1.x, pt1.y, pt2.x, pt2.y)
    }

   private fun distance(x2: Float, y2: Float, x1: Float, y1: Float): Double {
        //勾股定理，求斜边的距离
        return sqrt((x2 - x1).toDouble().pow(2.0) + (y2 - y1).toDouble().pow(2.0))
    }
}