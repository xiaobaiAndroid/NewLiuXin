package module.common.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowInsets
import kotlin.math.abs

/**
 * @describe: 状态栏工具类
 * @date: 2019/12/31
 * @author: Mr Bai
 */
object StatusBarUtils {
    /**
     * 获取状态栏高度
     *
     * @return > 0 success; <= 0 fail
     */
    fun getStatusHeight(activity: Activity): Int {
        var height = 0
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val decorView: View = activity.window.decorView
            decorView.rootWindowInsets?.let {
                val insets = it.getInsets(WindowInsets.Type.statusBars())
                height = abs(insets.bottom - insets.top)
            }
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView: View = activity.window.decorView
            decorView.rootWindowInsets?.let {
                height = it.systemWindowInsetTop
            }
        } else {
            val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                height = activity.resources.getDimensionPixelSize(resourceId)
            }
        }
        LogUtils.printI("StatusHeight=$height")
        return height
    }

    /**
     * @describe: 设置距离状态栏的高度
     * @date: 2019/12/31
     */
    fun setMarginStatusBarHeight(activity: Activity?, view: View?) {
        if (activity == null || view == null) {
            return
        }
        var statusHeight = getStatusHeight(activity)
        if(statusHeight == 0){
            statusHeight = SPHelper.get(activity.applicationContext,SPHelper.STATUS_HEIGHT,0) as Int
        }

        val layoutParams = view.layoutParams as MarginLayoutParams
        layoutParams.topMargin = statusHeight
        view.layoutParams = layoutParams
    }

}