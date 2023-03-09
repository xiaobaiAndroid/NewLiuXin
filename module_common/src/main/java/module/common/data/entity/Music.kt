package module.common.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    var id: String? = null,
    var musicType: String?= null,
    var musicTypeName: String?= null,
    var musicName: String?= null,
    var musicUrl: String?= null,

    /*缓存的路径*/
    var cancelPath: String?= null,
    var createTime: String?= null,
    var updateTime: String?= null,
    var updateBy: String?= null,

    /*音乐时长*/
    var musicTime: String?= null,
    var musicLable: String?= null,
    /*是否选中*/
    var isSelected: Boolean = false
) : Parcelable