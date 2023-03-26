package module.map

import android.content.Context
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import module.map.entity.MapLocation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 *@author: baizf
 *@date: 2023/3/26
 */
class LocationBroker {

    var mapHelper: LocationHelper? = null


    /*
    * @describe: 获取周边POI
    * @date: 2023/3/15
    */
    suspend fun getLocationData(context: Context, isHight: Boolean = true) = suspendCoroutine<MapLocation?> { rnt->
        mapHelper = LocationHelper(context, isHight,object : BDAbstractLocationListener() {
            override fun onReceiveLocation(location: BDLocation?) {
                location?.apply {
                    val mapLocation = MapLocation(country,countryCode,province,city,cityCode,district,street,town,address.address,latitude,longitude)
                    stop()
                    rnt.resume(mapLocation)
                } ?:   kotlin.run {
                    stop()
                    rnt.resume(null)
                }
            }

            override fun onLocDiagnosticMessage(locType: Int, diagnosticType: Int, diagnosticMessage: String?) {
                super.onLocDiagnosticMessage(locType, diagnosticType, diagnosticMessage)
            }

            override fun onConnectHotSpotMessage(p0: String?, p1: Int) {
                super.onConnectHotSpotMessage(p0, p1)
            }
        })
        mapHelper?.start()
    }

    fun stop(){
        mapHelper?.stop()
    }

}