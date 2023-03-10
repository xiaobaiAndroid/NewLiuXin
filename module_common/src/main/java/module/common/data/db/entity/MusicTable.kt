package module.common.data.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

const val MUSIC_TABLE_NAME = "music_table"

/**
 *@author: baizf
 *@date: 2023/3/9
 */
@Entity(tableName = MUSIC_TABLE_NAME)
class MusicTable {

    @PrimaryKey
    var localId: String = ""

    var musicId: String? = null

    var userId: String? = null

    var addDate: String = "0"

    var musicType: String?= null
    var musicTypeName: String?= null
    var musicName: String?= null
    var musicUrl: String?= null

    var musicTime: String?= null

    var musicLable: String?= null

    @Ignore
    var selected = false
}