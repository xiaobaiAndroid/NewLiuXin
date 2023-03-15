package module.map

import android.content.Context
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption


/**
 * @describe: 地图帮助类
 * @date: 2020/3/13
 * @author: baizhengfu
 * @isHight: 是否使用高精度定位
 */
class LocationHelper(context: Context, val isHight: Boolean = false, listener: BDAbstractLocationListener?) {

    var mLocationClient: LocationClient?=null

    init {

        try {
            mLocationClient = LocationClient(context)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val option = LocationClientOption()

        if(isHight){
            /*高精度定位模式,，会同时使用网络定位和GNSS定位，优先返回最高精度的定位结果*/
            option.locationMode = LocationClientOption.LocationMode.Hight_Accuracy
            option.isOpenGnss = true
        }else{
            /*低功耗定位模式,不会使用GPS，只会使用网络定位（Wi-Fi和基站定位）*/
            option.locationMode = LocationClientOption.LocationMode.Battery_Saving
            option.isOpenGnss = false
        }


        /*
        * //可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true
        *
        * */
        option.setIsNeedAddress(true)

        //可选，设置是否需要最新版本的地址信息。默认不需要，即参数为false
        option.setNeedNewVersionRgc(true)


        //可选，是否需要周边POI信息，默认为不需要，即参数为false
//如果开发者需要获得周边POI信息，此处必须为true
        option.setIsNeedLocationPoiList(true)

        option.setOnceLocation(true)
        //速度优先，首次定位时会降低定位准确性，提升定位速度
        option.setFirstLocType(LocationClientOption.FirstLocType.SPEED_IN_FIRST_LOC)


        //可选，设置返回经纬度坐标类型，默认GCJ02
//GCJ02：国测局坐标；
//BD09ll：百度经纬度坐标；
//BD09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标
        option.setCoorType("bd09ll")


        mLocationClient?.locOption = option

        //声明LocationClient类
        mLocationClient?.registerLocationListener(listener)
    }


    fun start() {
        mLocationClient?.start()
    }

    fun stop() {
        mLocationClient?.stop()
    }

    companion object{

        /*
        * @describe: /setAgreePrivacy接口需要在LocationClient实例化之前调用
        //如果setAgreePrivacy接口参数设置为了false，则定位功能不会实现
        //true，表示用户同意隐私合规政策
        //false，表示用户不同意隐私合规政策
        * @date: 2023/3/15
        */
        fun agreePrivacy(){
            LocationClient.setAgreePrivacy(true)
        }
    }

}