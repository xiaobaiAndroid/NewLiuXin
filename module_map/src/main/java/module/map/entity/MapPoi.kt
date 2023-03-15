package module.map.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author: baizf
 * @date: 2023/3/15
 */
/**
 * @describe: 位置信息
 * @date: 2020/3/13
 * @author: Mr Bai
 */
@Parcelize
data class MapPoi(
    var id: String?=null,
    var name: String?=null,

    var poiAddress: String?=null,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,

    /*详细地址*/
    var address: String? = null,

    /*城市编码*/
    var cityCode: String? = null,
    var cityName: String? = null,
    var provinceCode: String? = null,
    var provinceName: String? = null,


):Parcelable