package module.common.utils

import android.content.Context
import android.os.Build
import android.provider.Settings
import java.util.*

/**
 *@author: baizf
 *@date: 2023/3/31
 */
object DeviceUtils {

    fun getDeviceUniqueId(): String{
        //Android 10（API 级别 29）对不可重置的标识符（包括 IMEI 和序列号）添加了限制。
        // 您的应用必须是设备或个人资料所有者应用，具有特殊运营商权限或具有 READ_PRIVILEGED_PHONE_STATE 特许权限，
        // 才能访问这些标识符

        var buildId = StringBuilder()
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P){

        }else{

        }
        return buildId.toString()
    }

    /**
     * 获取厂商名
     */
    fun getDeviceManufacturer(): String? {
        return Build.MANUFACTURER
    }

    /**
     * 获取产品名
     */
    fun getDeviceProduct(): String? {
        return Build.PRODUCT
    }

    /**
     * 获取手机品牌
     */
    fun getDeviceBrand(): String? {
        return Build.BRAND
    }

    /**
     * 获取手机型号
     */
    fun getDeviceModel(): String? {
        return Build.MODEL
    }

    /**
     * 获取手机主板名
     */
    fun getDeviceBoard(): String? {
        return Build.BOARD
    }

    /**
     * 设备名
     */
    fun getDeviceDevice(): String? {
        return Build.DEVICE
    }

    /**
     *
     *
     * fingerprit 信息
     */
    fun getDeviceFubgerprint(): String? {
        return Build.FINGERPRINT
    }

    /**
     * 硬件名
     *
     */
    fun getDeviceHardware(): String? {
        return Build.HARDWARE
    }

    /**
     * ID
     *
     */
    fun getDeviceId(): String? {
        return Build.ID
    }

    /**
     * 获取手机用户名
     *
     */
    fun getDeviceUser(): String? {
        return Build.USER
    }

    /**
     * 获取手机 硬件序列号
     */
//    fun getDeviceSerial(): String? {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            return Build.getSerial()
//        }else{
//            return Build.SERIAL
//        }
//
//    }

    /**
     * 获取手机Android 系统SDK
     *
     * @return
     */
    fun getDeviceSDK(): Int {
        return Build.VERSION.SDK_INT
    }

    /**
     * 获取手机Android 版本
     *
     * @return
     */
    fun getDeviceAndroidVersion(): String? {
        return Build.VERSION.RELEASE
    }

    /**
     * 获取当前手机系统语言。
     */
    fun getDeviceDefaultLanguage(): String? {
        return Locale.getDefault().language
    }

}