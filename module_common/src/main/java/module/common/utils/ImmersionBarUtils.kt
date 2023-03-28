package module.common.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import module.common.R

/**
 * @describe: 沉浸式工具栏
 * @date: 2019/7/11
 * @author: Mr Bai
 */
object ImmersionBarUtils {
    //
    fun init(
        activity: FragmentActivity?,
        statusBarResColor: Int = R.color.cl_ffffff,
        statusBarFontWhite: Boolean = false,
        isOverlay: Boolean = false
    ) {
        val flymeOSStatusBarFontColor = if (statusBarFontWhite) {
            R.color.cl_ffffff
        } else {
            R.color.cl_000000
        }
        val immersionBar = ImmersionBar.with(activity!!)
            .statusBarDarkFont(!statusBarFontWhite)
            .flymeOSStatusBarFontColor(flymeOSStatusBarFontColor) //修改flyme OS状态栏字体颜色
            .statusBarColor(statusBarResColor)
            .keyboardEnable(true)
        if (!isOverlay) {
            immersionBar.fitsSystemWindows(true)
        }


        immersionBar.init() //必须调用方可应用以上所配置的参数
    }

//    fun defaultBuildFragment(fragment: Fragment?,statusBarResColor: Int = R.color.cl_ffffff) {
//        ImmersionBar.with(fragment!!)
//            .statusBarDarkFont(false)
//            .flymeOSStatusBarFontColor(R.color.cl_ffffff) //修改flyme OS状态栏字体颜色
//            .keyboardEnable(true)
//            .init() //必须调用方可应用以上所配置的参数
//    }


//    fun buildBarDarkFragment(fragment: Fragment?) {
//        ImmersionBar.with(fragment!!)
//            .statusBarDarkFont(true) //状态栏字体是深色，不写默认为亮色
//            .keyboardEnable(true)
//            .flymeOSStatusBarFontColor(R.color.cl_000000) //修改flyme OS状态栏字体颜色
//            .init() //必须调用方可应用以上所配置的参数
//    }
//
//    fun fullScreenBuild(activity: Activity?) {
//        ImmersionBar.with(activity!!)
//            .fullScreen(true)
//            .hideBar(BarHide.FLAG_HIDE_BAR)
//            .init() //必须调用方可应用以上所配置的参数
//    }
}