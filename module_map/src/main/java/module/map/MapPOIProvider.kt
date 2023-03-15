package module.map

import android.content.Context
import android.util.Log
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.Poi
import module.map.entity.MapPoi
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 *@author: baizf
 *@date: 2023/3/15
 */
class MapPOIProvider {

    var mapHelper: LocationHelper? = null


    /*
    * @describe: 获取周边POI
    * @date: 2023/3/15
    */
    suspend fun getPOIList(context: Context,isHight: Boolean = false) = suspendCoroutine<MutableList<MapPoi>> { rnt->
        mapHelper = LocationHelper(context, isHight,object : BDAbstractLocationListener() {
            override fun onReceiveLocation(location: BDLocation?) {
                val list: MutableList<MapPoi> = assemble(location)
                rnt.resume(list)
            }

            override fun onLocDiagnosticMessage(locType: Int, diagnosticType: Int, diagnosticMessage: String?) {
                super.onLocDiagnosticMessage(locType, diagnosticType, diagnosticMessage)
                Log.i("location","locType=$locType----diagnosticType=$diagnosticType----diagnosticMessage$diagnosticMessage")
            }

        })
        mapHelper?.start()
    }

    private fun assemble(bdLocation: BDLocation?): MutableList<MapPoi> {
        bdLocation ?: return mutableListOf()
        val locType = bdLocation.locType
        Log.i("map","locType=$locType")

        bdLocation.poiList ?: return mutableListOf()

        val locations: MutableList<MapPoi> = mutableListOf()

        val latitude: Double = bdLocation.latitude
        val longitude: Double = bdLocation.longitude

        Log.i("bzf","poiList=${bdLocation.poiList}")

        val poiList: List<Poi> = bdLocation.poiList


        for (poi in poiList) {
            val location =
                MapPoi(
                    poi.id,
                    poi.name,
                    poi.addr,
                    latitude,
                    longitude,
                    bdLocation.city + bdLocation.district + bdLocation.town + poi.name,
                    bdLocation.cityCode,
                    bdLocation.city,
                    "",
                    bdLocation.province
                )
            locations.add(location)
        }
        return locations
    }

    fun stop(){
        mapHelper?.stop()
    }
}