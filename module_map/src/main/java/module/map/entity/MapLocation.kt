package module.map.entity

/**
 *@author: baizf
 *@date: 2023/3/26
 */
data class MapLocation(
    val country: String?,
    val countryCode: String?,
    val province: String?,
    val city: String?,
    val cityCode: String?,
    val district: String?,//获取区县
    val street: String?, //获取街道信息
    val town: String?,  //获取乡镇信息
    val address: String?,

    val latitude: Double,
    val longitude: Double
)

