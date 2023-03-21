package module.common.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import module.common.data.db.DBTableNames.DYNAMIC_CATEGORY_TABLE_NAME
import java.util.UUID


/**
 *@author: baizf
 *@date: 2023/3/21
 */
@Entity(tableName = DYNAMIC_CATEGORY_TABLE_NAME)
class DynamicCategoryTable {

    @PrimaryKey
    var localId: String = UUID.randomUUID().toString()

    /*	分类id*/
    var id: String? = null

    var userId: String?=null

    // 1图文， 2视频
    var categoryType = 0

    /*媒体类型 1图文， 2视频*/
    var type = 0

    /*上级分类id*/
    var typeName: String? = null

    /*排序的序号*/
    var displayOrder: Int = 0
}