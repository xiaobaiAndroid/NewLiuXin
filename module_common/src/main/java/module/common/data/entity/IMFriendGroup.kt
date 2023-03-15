package module.common.data.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/*
* @describe: 分组
* @author: bzf
* @date: 12/26/20
*/

@Parcelize
data class IMFriendGroup(

        @SerializedName("groupName")
        var groupName: String? = "",
        @SerializedName("id")
        var id: String? = "",
        @SerializedName("sort")
        var sort: Int? = 0,
        @SerializedName("userFriends")
        var userFriends: MutableList<Friend>?,
        @SerializedName("userId")
        var userId: String? = null,

        //联系人是否处于当前组,临时属性
        var currentGroup: Boolean = false

 ) : Parcelable{

}
