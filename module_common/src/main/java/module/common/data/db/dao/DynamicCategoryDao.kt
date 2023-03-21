package module.common.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import module.common.data.db.DBTableNames
import module.common.data.db.entity.DynamicCategoryTable

/**
 *@author: baizf
 *@date: 2023/3/21
 */
@Dao
interface DynamicCategoryDao {


    @Query("SELECT * FROM ${DBTableNames.DYNAMIC_CATEGORY_TABLE_NAME} WHERE userId= :userId AND categoryType= :categoryType ORDER BY displayOrder ASC")
    suspend fun queryAll(userId: String, categoryType: Int):MutableList<DynamicCategoryTable>

    @Insert
    suspend fun insert(tables: List<DynamicCategoryTable>)
}