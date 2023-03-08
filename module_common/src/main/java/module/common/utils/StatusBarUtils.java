package module.common.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * @describe: 状态栏工具类
 * @date: 2019/12/31
 * @author: Mr Bai
 */
public class StatusBarUtils {


    /**
     * 获取状态栏高度
     *
     * @return > 0 success; <= 0 fail
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass
                        .getField("status_bar_height").get(localObject)
                        .toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }


    /**
     * @describe: 设置距离状态栏的高度
     * @date: 2019/12/31
     */
    public static void setMarginStatusBarHeight(Activity activity,View view){
        if(activity==null || view==null){
            return;
        }
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.topMargin = getStatusHeight(activity);
        view.setLayoutParams(layoutParams);
    }
}
