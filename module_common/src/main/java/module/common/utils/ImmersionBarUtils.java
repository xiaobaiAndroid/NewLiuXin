package module.common.utils;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import module.common.R;

/**
 * @describe: 沉浸式工具栏
 * @date: 2019/7/11
 * @author: Mr Bai
 */
public class ImmersionBarUtils {

//
    public static void defaultBuild(Activity activity) {
        ImmersionBar.with(activity)
                .statusBarDarkFont(false)
                .flymeOSStatusBarFontColor(R.color.cl_ffffff)  //修改flyme OS状态栏字体颜色
                .keyboardEnable(true)
                .init();  //必须调用方可应用以上所配置的参数
    }

    public static void defaultBuildFragment(Fragment fragment) {
        ImmersionBar.with(fragment)
                .statusBarDarkFont(false)
                .flymeOSStatusBarFontColor(R.color.cl_ffffff)  //修改flyme OS状态栏字体颜色
                .keyboardEnable(true)
                .init();  //必须调用方可应用以上所配置的参数
    }


    public static void buildBarDark(Activity activity) {
        ImmersionBar.with(activity)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .keyboardEnable(true)
                .flymeOSStatusBarFontColor(R.color.cl_000000)  //修改flyme OS状态栏字体颜色
                .init();  //必须调用方可应用以上所配置的参数
    }

    public static void buildBarDarkFragment(Fragment fragment) {
        ImmersionBar.with(fragment)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .keyboardEnable(true)
                .flymeOSStatusBarFontColor(R.color.cl_000000)  //修改flyme OS状态栏字体颜色
                .init();  //必须调用方可应用以上所配置的参数
    }

    public static void fullScreenBuild(Activity activity) {
        ImmersionBar.with(activity)
                .fullScreen(true)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();  //必须调用方可应用以上所配置的参数
    }


}
