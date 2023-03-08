package module.common.data.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName


/*
* @describe: 身份认证信息
* @author: bzf
* @date: 1/10/21
*/
@SuppressLint("ParcelCreator")
@Parcelize
data class IDCard(
    @SerializedName("backImg")
    var backImg: String? = "",
    @SerializedName("createBy")
    var createBy: String? = "",
    @SerializedName("createTime")
    var createTime: String? = "",
    @SerializedName("frontImg")
    var frontImg: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("idCard")
    var idCard: String? = "",
    @SerializedName("idType")
    var idType: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("updateBy")
    var updateBy: String? = "",
    @SerializedName("updateTime")
    var updateTime: String? = "",
    @SerializedName("userId")
    var userId: String? = "",
    @SerializedName("userType")
    var userType: Int? = 0
) : Parcelable