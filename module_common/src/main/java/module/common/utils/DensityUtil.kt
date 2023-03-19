package module.common.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager

object DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dip2pxFloat(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获取屏幕的宽度
     */
    fun getScreenWidth(activity: Activity): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.widthPixels
    }

    /**
     * 获取屏幕高度,不包含底部导航栏的高度
     *
     * @param activity
     * @return
     */
    fun getScreenHeight(activity: Activity): Int {
        val windowManager =
            activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val dm = DisplayMetrics()
        display.getRealMetrics(dm)
        return dm.heightPixels
    }

    /**
     * 获取density
     *
     * @param context
     * @return
     */
    fun getDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    fun getScreenRealHeight(context: Context): Int {
        var dpi = 0
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val displayMetrics = DisplayMetrics()
        val c: Class<*>
        try {
            c = Class.forName("android.view.Display")
            val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(display, displayMetrics)
            dpi = displayMetrics.heightPixels
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dpi
    }

    /**
     * sp转px
     *
     * @param context
     * @return
     */
    fun sp2px(context: Context, spVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal, context.resources.displayMetrics
        ).toInt()
    }

    /**
     * sp转px
     *
     * @param context
     * @return
     */
    fun spToFloatPx(context: Context, spVal: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal, context.resources.displayMetrics
        )
    }
}