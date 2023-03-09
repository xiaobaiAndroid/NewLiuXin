package module.common.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicCategory(
    val createBy: String?=null,
    val createTime: String?=null,
    val id: String?=null,
    val mediaMusicList: List<Music>? = null,
    val musicLable: String?=null,
    val musicName: String?=null,
    val musicTime: String?=null,
    val musicType: String?=null,
    val musicTypeName: String?=null,
    val musicUrl: String?=null,
    val updateBy: String?=null,
    val updateTime: String?=null
): Parcelable