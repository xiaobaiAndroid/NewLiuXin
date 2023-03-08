package module.common.utils;

import android.util.Log;

public class LogUtils {

    /*是否打印日志*/
    private static boolean isOpen = true;

    private static final String TAG = "LogUtils";

    public static void i(String tag,String msg){
        if(isOpen){
            Log.i(tag, msg);
        }
    }

    public static void e(String tag,String msg){
        if(isOpen){
            Log.e(tag, msg);
        }
    }

    public static void i(String msg){
        if(isOpen){
            Log.i(TAG, msg);
        }
    }

    public static void e(String msg){
        if(isOpen){
            Log.e(TAG, msg);
        }
    }


    public static void setIsOpen(boolean isOpen) {
        LogUtils.isOpen = isOpen;
    }

    public static boolean isIsOpen() {
        return isOpen;
    }
}
