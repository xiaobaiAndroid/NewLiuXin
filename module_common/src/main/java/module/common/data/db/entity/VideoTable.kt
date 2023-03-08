package module.common.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "video_table")
class VideoTable(

    @PrimaryKey
    var id: String = "NULL",
    var age: Int? = 0,

    var avatar: String? = "",

    var birthday: String? = "",
    var cityCode: Int? = 0,
    var cityName: String? = "",
    var colleges: String? = "",
    var commentNum: String? = "",
    var coverUrl: String? = "",
    var createTime: Long? = 0,
    var description: String? = "",
    var favoriteNum: String? = "",
    var favoriteStatus: Int? = 0,

    var imageNum: Int? = 0,
    var job: String? = "",
    var lat: Double? = 0.0,
    var lng: Double? = 0.0,
    var mediaUrl: String? = "",
    var nickName: String? = "",
    var position: String? = "",
    var praiseNum: String? = "",
    var praiseStatus: Int? = 0,
    var sex: Int? = 0,
    var title: String? = "",
    var type: Int? = 0,
    var typeId: Int? = 0,
    var userId: String? = "",
    var viewNum: Int? = 0
)