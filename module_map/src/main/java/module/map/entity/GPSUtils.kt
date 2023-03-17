package module.map.entity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings


/**
 *@author: baizf
 *@date: 2023/3/15
 */
object GPSUtils {

    /**
     * 检测GPS是否打开
     *
     * @return
     */
     fun checkGPSIsOpen(activity: Activity): Boolean {
        val locationManager =
        activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    /**
     * 跳转GPS设置
     */
    fun getOpenGPSSettingsIntent(): Intent {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return intent

    }
}