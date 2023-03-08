package module.common.data.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName


/*
* @describe: 后台系统用户信息
* @author: bzf
* @date: 12/11/20
*/
@SuppressLint("ParcelCreator")
@Parcelize
data class SystemUserInfo(
        @SerializedName("createBy")
    var createBy: String? = "",
        @SerializedName("createTime")
    var createTime: String? = "",
        @SerializedName("id")
    var id: String? = "",
        @SerializedName("merchant")
    var merchant: Merchant? = Merchant(),
        @SerializedName("merchantId")
    var merchantId: String? = "",
        @SerializedName("role")
    var role: Role? = Role(),
        @SerializedName("state")
    var state: Int? = 0,
        @SerializedName("storeId")
    var storeId: String? = "",
        @SerializedName("store")
        var store: Store? = Store(),
        @SerializedName("updateBy")
    var updateBy: String? = "",
        @SerializedName("updateTime")
    var updateTime: String? = "",

        @SerializedName("userAccounts")
    var userAccounts: List<UserAccount>? = listOf()
) : Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
data class Role(
    @SerializedName("createBy")
    var createBy: String? = "",
    @SerializedName("createTime")
    var createTime: String? = "",
    @SerializedName("creatorId")
    var creatorId: String? = "",
    @SerializedName("id")
    var id: String? = "",

    @SerializedName("roleName")
    var roleName: String? = "",
    @SerializedName("updateBy")
    var updateBy: String? = "",
    @SerializedName("updateTime")
    var updateTime: String? = ""
) : Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
data class UserAccount(
    @SerializedName("account")
    var account: String? = "",
    @SerializedName("createBy")
    var createBy: String? = "",
    @SerializedName("createTime")
    var createTime: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("state")
    var state: Int? = 0,
    @SerializedName("updateBy")
    var updateBy: String? = "",
    @SerializedName("updateTime")
    var updateTime: String? = "",
    @SerializedName("userAccountType")
    var userAccountType: Int? = 0,
    @SerializedName("userId")
    var userId: String? = ""
) : Parcelable