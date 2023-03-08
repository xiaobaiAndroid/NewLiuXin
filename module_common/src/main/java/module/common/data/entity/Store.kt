package module.common.data.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName


@SuppressLint("ParcelCreator")
@Parcelize
data class Store(

    @SerializedName("browNum")
    var browNum: Int? = 0,

    @SerializedName("browNumYesterday")
    var browNumYesterday: Int? = 0,

    @SerializedName("clientNum")
    var clientNum: Int? = 0,

    @SerializedName("clientVisitNum")
    var clientVisitNum: Int? = 0,
    @SerializedName("clientVisitNumNew")
    var clientVisitNumNew: Int? = 0,
    @SerializedName("code")
    var code: String? = "",
    @SerializedName("createBy")
    var createBy: String? = "",
    @SerializedName("createTime")
    var createTime: String? = "",

    @SerializedName("exposureNum")
    var exposureNum: Int? = 0,
    @SerializedName("favNum")
    var favNum: Int? = 0,

    @SerializedName("fullAddress")
    var fullAddress: String? = "",

    @SerializedName("id")
    var id: String? = "",

    @SerializedName("languageMarket")
    var languageMarket: Int = 0,

    @SerializedName("level")
    var level: Int? = 0,


    @SerializedName("logo")
    var logo: String? = "",
    @SerializedName("manageType")
    var manageType: String? = "",
    @SerializedName("merchantId")
    var merchantId: String? = "",

    @SerializedName("phone")
    var phone: String? = "",
    @SerializedName("productNum")
    var productNum: Int? = 0,
    @SerializedName("productNumCart")
    var productNumCart: Int? = 0,
    @SerializedName("productNumCartNew")
    var productNumCartNew: String? = "",
    @SerializedName("productNumCartYesterday")
    var productNumCartYesterday: String? = "",
    @SerializedName("productNumDown")
    var productNumDown: Int? = 0,
    @SerializedName("productNumFav")
    var productNumFav: String? = "",
    @SerializedName("productNumFavNew")
    var productNumFavNew: String? = "",
    @SerializedName("productNumFavYesterday")
    var productNumFavYesterday: String? = "",
    @SerializedName("productNumNew")
    var productNumNew: Int? = 0,
    @SerializedName("productNumSale")
    var productNumSale: Int? = 0,
    @SerializedName("productNumXj")
    var productNumXj: Int? = 0,
    @SerializedName("sales")
    var sales: String? = "",
    @SerializedName("salesNum")
    var salesNum: Int? = 0,
    @SerializedName("state")
    var state: Int? = 0,
    @SerializedName("storeName")
    var storeName: String? = "",
    @SerializedName("storeType")
    var storeType: Int? = 0,
    @SerializedName("updateBy")
    var updateBy: String? = "",
    @SerializedName("updateTime")
    var updateTime: String? = "",
    @SerializedName("userId")
    var userId: String? = "",
    @SerializedName("visitorNum")
    var visitorNum: Int? = 0,
    @SerializedName("visitorNumNew")
    var visitorNumNew: Int? = 0,
    @SerializedName("visitorNumYesterday")
    var visitorNumYesterday: Int? = 0

) : Parcelable