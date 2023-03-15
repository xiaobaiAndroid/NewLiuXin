package module.common.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatTeam(
    val cityCode: String,
    val cityName: String,
    val classify: Int,
    val countyCode: String,
    val countyName: String,
    val createBy: String,
    val createId: String,
    val createTime: String,
    val fullAddress: String,
    val id: String,
    val lat: String,
    val lng: String,
    val members: String,
    val ownId: String,
    val provinceCode: String,
    val provinceName: String,
    val state: Int,
    val teamAvatar: String,
    val teamDesc: String,
    val teamName: String,
    val teamNotice: String,
    val thirdTeamId: String,
    val type: Int,
    val updateBy: String,
    val updateTime: String,
//    val userFriends: Any
): Parcelable