package module.common.data.request
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName
import module.common.type.MerchantType


/*{
	"merchantTypeTitle": "非自营",
	"merchantName": "简介",
	"companyDetail": "测试",
	"provinceCode": "110000",
	"registeredPic": "http:\/\/liuxin-1301077617.cos.ap-guangzhou.myqcloud.com\/2020\/12\/icon\/21-18-47489351224542769152.png",
	"streetName": "北太平庄街道",
	"streetCode": "110108008",
	"mainBusiness": "哦哦",
	"countyName": "海淀区",
	"countyCode": "110108",
	"provinceName": "北京市",
	"email": "709889312@qq.com",
	"phone": "020-34203487",
	"mobile": "18027736400",
	"AddressDetails": "嗯",
	"cityCode": "110100",
	"registeredTime": "2010-01-01",
	"merchantType": "2",
	"logo": "http:\/\/liuxin-1301077617.cos.ap-guangzhou.myqcloud.com\/2020\/12\/icon\/21-17-37489351224542769152.png",
	"manager": "哦哦",
	"cityName": "北京市"
}*/

@SuppressLint("ParcelCreator")
@Parcelize
data class StoreApplyReq(
        @SerializedName("AddressDetails")
    var addressDetails: String? = "",
        @SerializedName("cityCode")
    var cityCode: Int = 0,
        @SerializedName("cityName")
    var cityName: String? = "",
        @SerializedName("companyDetail")
    var companyDetail: String? = "",
        @SerializedName("countyCode")
    var countyCode: Int = 0,
        @SerializedName("countyName")
    var countyName: String? = "",
        @SerializedName("email")
    var email: String? = "",
        @SerializedName("logo")
    var logo: String? = "",
        @SerializedName("mainBusiness")
    var mainBusiness: String? = "",
        @SerializedName("manager")
    var manager: String? = "",
        @SerializedName("merchantName")
    var merchantName: String? = "",

        @SerializedName("merchantType")
    var merchantType: String = MerchantType.NOT_SELF_SUPPORT.value,

        @SerializedName("merchantTypeTitle")
    var merchantTypeTitle: String? = "",
        @SerializedName("mobile")
    var mobile: String? = "",
        @SerializedName("phone")
    var phone: String? = "",
        @SerializedName("provinceCode")
    var provinceCode: Int=0,
        @SerializedName("provinceName")
    var provinceName: String? = "",
        @SerializedName("registeredPic")
    var registeredPic: String? = "",
        @SerializedName("registeredTime")
    var registeredTime: String? = "",
        @SerializedName("streetCode")
    var streetCode: Int = 0,
        @SerializedName("streetName")
    var streetName: String? = ""
) : Parcelable