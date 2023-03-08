package module.common.data.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName


@SuppressLint("ParcelCreator")
@Parcelize
data class Bill(
    @SerializedName("accountId")
    var accountId: String? = "",
    @SerializedName("changeMoney")
    var changeMoney: Int? = 0,
    @SerializedName("createBy")
    var createBy: String? = "",
    @SerializedName("createTime")
    var createTime: String? = "",
    @SerializedName("display")
    var display: Int? = 0,
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("money")
    var money: Int? = 0,
    @SerializedName("remark")
    var remark: String? = "",
    @SerializedName("sn")
    var sn: String? = ""
) : Parcelable