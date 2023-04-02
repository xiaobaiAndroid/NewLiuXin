package module.common.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import module.common.data.db.DBTableNames.DYNAMIC_SEARCH_HISTORY_TABLE_NAME
import module.common.data.db.DBTableNames.GOODS_SEARCH_HISTORY_TABLE_NAME
import java.util.UUID

/**
 *@author: baizf
 *@date: 2023/3/26
 */
@Entity(tableName = GOODS_SEARCH_HISTORY_TABLE_NAME)
class GoodsSearchHistoryTable {

    @PrimaryKey
    var historyId: String = UUID.randomUUID().toString()

    var keyWord: String = ""

    var userId: String? = null

    var updateTime: String = "0"

}