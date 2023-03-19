package module.common.utils

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.os.Process
import android.provider.Settings
import androidx.core.content.FileProvider
import module.common.utils.LogUtils.printI
import java.io.File

/**
 * app相关辅助类
 */
object AppUtils {
    /**
     * 获取应用程序名称
     *
     * @param context
     * @return
     */
    fun getAppName(context: Context): String? {
        val packageManager = context.packageManager
        try {
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @param context
     * @return 当前应用的版本名称
     */
    fun getVersionName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取应用程序的版本Code信息
     *
     * @param context
     * @return 版本code
     */
    fun getVersionCode(context: Context): Int {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * @describe: 请求白名单
     * @date: 2019/8/23
     */
    fun requestWhiteList(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            val hasIgnored = powerManager.isIgnoringBatteryOptimizations(context.packageName)
            //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
            if (!hasIgnored) {
                val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                intent.data = Uri.parse("package:" + context.packageName)
                context.startActivity(intent)
            }
        }
    }

    /**
     * @param packageName 应用包名
     * @Description： 判断手机中是否安装了指定APP
     * @author：Mr Bai
     * @Date： 2018/11/7
     */
    fun isAppAvilible(context: Context, packageName: String): Boolean {
        val packageManager = context.packageManager // 获取packagemanager
        val pinfo = packageManager.getInstalledPackages(0) // 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (i in pinfo.indices) {
                val pn = pinfo[i].packageName
                if (pn == packageName) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 判断Application是否只初始化了一次,是：true 否:false
     *
     * @return
     */
    fun applicationIsOnlyInit(context: Context): Boolean {
        val processAppName = getAppProcessName(context, Process.myPid())
        printI("processAppname=$processAppName")

        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        return if (processAppName == null || !processAppName.equals(
                context.packageName,
                ignoreCase = true
            )
        ) {
            false
        } else true
    }

    fun getAppProcessName(cxt: Context, pid: Int): String? {
        val am = cxt.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = am.runningAppProcesses ?: return null
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return null
    }

    /**
     * @Description： 根据包名启动app
     * @author：Mr Bai
     * @Date： 2019/1/7
     */
    fun startApp(context: Context, packname: String): Boolean {
        return if (isAppAvilible(context, packname)) {
            val intent = context.packageManager.getLaunchIntentForPackage(packname)
            if (intent != null) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            true
        } else {
            false
        }
    }

    /**
     * @Description： 打開指定包名的指定的Activity
     * @author：Mr Bai
     * @Date： 2019/1/9
     */
    fun openSepcifiedActivity(context: Context, packname: String?, classname: String?) {
        val intent = Intent(Intent.ACTION_MAIN)
        //前提：知道要跳转应用的包名、类名
        val componentName = ComponentName(packname!!, classname!!)
        intent.component = componentName
        context.startActivity(intent)
    }

    /** * 忽略电池优化  */
    fun ignoreBatteryOptimization(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val powerManager = activity.getSystemService(Context.POWER_SERVICE) as PowerManager
            val hasIgnored = powerManager.isIgnoringBatteryOptimizations(activity.packageName)
            //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
            printI("hasIgnore$hasIgnored")
            if (!hasIgnored) {
                val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                intent.data = Uri.parse("package:" + activity.packageName)
                //跳转之前判断intent是否存在，否则有的机型会报找不到activity
                if (intent.resolveActivity(activity.packageManager) != null) {
                    activity.startActivityForResult(intent, 2)
                }
            }
        }
    }

    fun installApk(context: Context, apkFile: File) {
        printI("安装apk：" + apkFile.absolutePath)
        val install = Intent(Intent.ACTION_VIEW)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val apkUri = FileProvider.getUriForFile(context, "net.yijupay.fileprovider", apkFile)
            install.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            install.setDataAndType(apkUri, "application/vnd.android.package-archive")
            context.startActivity(install)
        } else {
            install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(install)
        }
    }

    /**
     * @describe: 获取APP包名
     * @date: 2020/1/2
     */
    fun getAppPackageName(application: Application): String? {
        try {
            printI("packageName=" + application.packageName)
            val packageManager = application.packageManager
            val packageInfo = packageManager.getPackageInfo(
                application.packageName, 0
            )
            printI("packageInfo=" + packageInfo.packageName)
            return packageInfo.packageName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * @describe: 判断Android 版本是否大于10
     * @date: 2020/3/14
     */
    fun versionIsAndroid10(): Boolean {
        val version = Build.VERSION.SDK_INT
        return version >= Build.VERSION_CODES.Q
    }
}