package module.common.data.entity
import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName


@SuppressLint("ParcelCreator")
@Parcelize
data class Video(
    @SerializedName("age")
    var age: Int? = 0,
    @SerializedName("avatar")
    var avatar: String? = "",
    @SerializedName("birthday")
    var birthday: String? = "",
    @SerializedName("cityCode")
    var cityCode: Int? = 0,
    @SerializedName("cityName")
    var cityName: String? = "",
    @SerializedName("colleges")
    var colleges: String? = "",
    @SerializedName("commentNum")
    var commentNum: String? = "",
    @SerializedName("coverUrl")
    var coverUrl: String? = "",
    @SerializedName("createTime")
    var createTime: Long? = 0,
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("favoriteNum")
    var favoriteNum: String? = "",
    @SerializedName("favoriteStatus")
    var favoriteStatus: Int? = 0,

    @SerializedName("id")
    @PrimaryKey
    var id: String? = "",

    @SerializedName("imageNum")
    var imageNum: Int? = 0,
    @SerializedName("job")
    var job: String? = "",
    @SerializedName("lat")
    var lat: Double? = 0.0,
    @SerializedName("lng")
    var lng: Double? = 0.0,
    @SerializedName("mediaUrl")
    var mediaUrl: String? = "",
    @SerializedName("nickName")
    var nickName: String? = "",
    @SerializedName("position")
    var position: String? = "",
    @SerializedName("praiseNum")
    var praiseNum: String? = "",
    @SerializedName("praiseStatus")
    var praiseStatus: Int? = 0,
    @SerializedName("sex")
    var sex: Int? = 0,
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("type")
    var type: Int? = 0,
    @SerializedName("typeId")
    var typeId: Int? = 0,
    @SerializedName("userId")
    var userId: String? = "",
    @SerializedName("viewNum")
    var viewNum: Int? = 0,

    var isPlay: Boolean = false,
    //是否展开
    var unfold: Boolean = false,

    //关注
    var attention: Boolean = false
) : Parcelable