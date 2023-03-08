package module.common.utils;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.POWER_SERVICE;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.List;

/**
 * app相关辅助类
 */
public class AppUtils {


    /**
     * 获取应用程序名称
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {

        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序的版本Code信息
     *
     * @param context
     * @return 版本code
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @describe: 请求白名单
     * @date: 2019/8/23
     */
    public static void requestWhiteList(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);

            boolean hasIgnored = powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
            //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
            if (!hasIgnored) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent);
            }
        }
    }

    /**
     * @param packageName 应用包名
     * @Description： 判断手机中是否安装了指定APP
     * @author：Mr Bai
     * @Date： 2018/11/7
     */
    public static boolean isAppAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 判断Application是否只初始化了一次,是：true 否:false
     *
     * @return
     */
    public static boolean applicationIsOnlyInit(Context context) {
        String processAppName = getAppProcessName(context,android.os.Process.myPid());
        LogUtils.i("processAppname="+processAppName);

        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
            return false;
        }
        return true;
    }


    public static String getAppProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * @Description： 根据包名启动app
     * @author：Mr Bai
     * @Date： 2019/1/7
     */
    public static boolean startApp(Context context,String packname){
        if(isAppAvilible(context,packname)){
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packname);
            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * @Description： 打開指定包名的指定的Activity
     * @author：Mr Bai
     * @Date： 2019/1/9
     */
    public static void openSepcifiedActivity(Context context,String packname,String classname){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        //前提：知道要跳转应用的包名、类名
        ComponentName componentName = new ComponentName(packname, classname);
        intent.setComponent(componentName);
        context.startActivity(intent);
    }

    /** * 忽略电池优化 */
    public static void ignoreBatteryOptimization(Activity activity) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            PowerManager powerManager = (PowerManager) activity.getSystemService(POWER_SERVICE);
            boolean hasIgnored = powerManager.isIgnoringBatteryOptimizations(activity.getPackageName());
            //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
            LogUtils.i("hasIgnore"+hasIgnored);
            if(!hasIgnored) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:"+activity.getPackageName()));
                //跳转之前判断intent是否存在，否则有的机型会报找不到activity
                if (intent.resolveActivity(activity.getPackageManager()) != null) {
                    activity.startActivityForResult(intent, 2);
                }
            }
        }
    }



    public static void installApk(Context context, File apkFile) {
        LogUtils.i("安装apk："+apkFile.getAbsolutePath());
        Intent install = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(context, "net.yijupay.fileprovider", apkFile);

            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            install.setDataAndType(apkUri, "application/vnd.android.package-archive");

            context.startActivity(install);
        } else {
            install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }

    }

    /**
     * @describe: 获取APP包名
     * @date: 2020/1/2
     */
    public static String getAppPackageName(Application application) {
        try {
            LogUtils.i("packageName="+application.getPackageName());
            PackageManager packageManager = application.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    application.getPackageName(), 0);
            LogUtils.i("packageInfo="+packageInfo.packageName);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @describe: 判断Android 版本是否大于10
     * @date: 2020/3/14
     */
    public static boolean versionIsAndroid10() {
        int version = Build.VERSION.SDK_INT;
        return version>=Build.VERSION_CODES.Q;
    }
}

