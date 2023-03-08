package module.common.data.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/*
* @describe: IM群
* @author: bzf
* @date: 12/31/20
*/
@SuppressLint("ParcelCreator")
@Parcelize
data class IMTeam(
    @SerializedName("cityCode")
    var cityCode: String? = "",
    @SerializedName("cityName")
    var cityName: String? = "",
    @SerializedName("countyCode")
    var countyCode: String? = "",
    @SerializedName("countyName")
    var countyName: String? = "",
    @SerializedName("createBy")
    var createBy: String? = "",
    @SerializedName("createId")
    var createId: String? = "",
    @SerializedName("createTime")
    var createTime: String? = "",
    @SerializedName("fullAddress")
    var fullAddress: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("lat")
    var lat: String? = "",
    @SerializedName("lng")
    var lng: String? = "",
//    @SerializedName("members")
//    var members: Any? = n,
    @SerializedName("ownId")
    var ownId: String? = "",
    @SerializedName("provinceCode")
    var provinceCode: String? = "",
    @SerializedName("provinceName")
    var provinceName: String? = "",
    @SerializedName("state")
    var state: Int? = 0,
    @SerializedName("teamAvatar")
    var teamAvatar: String? = "",
    @SerializedName("teamDesc")
    var teamDesc: String? = "",
    @SerializedName("teamName")
    var teamName: String? = "",
    @SerializedName("teamNotice")
    var teamNotice: String? = "",
    @SerializedName("thirdTeamId")
    var thirdTeamId: String? = "",
    @SerializedName("type")
    var type: Int? = 0,
    @SerializedName("updateBy")
    var updateBy: String? = "",
    @SerializedName("updateTime")
    var updateTime: String? = ""
) : BaseNode(), Parcelable {
    override val childNode: MutableList<BaseNode>?
        get() = null
}