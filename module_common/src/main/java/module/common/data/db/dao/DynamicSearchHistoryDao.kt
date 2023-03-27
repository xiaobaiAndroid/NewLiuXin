package module.common.data.db.dao

import androidx.room.*
import module.common.data.db.DBTableNames
import module.common.data.db.entity.DynamicSearchHistoryTable

/**
 *@author: baizf
 *@date: 2023/3/26
 */
@Dao
interface DynamicSearchHistoryDao {

    @Query("SELECT * FROM ${DBTableNames.DYNAMIC_SEARCH_HISTORY_TABLE_NAME} WHERE userId= :userId ORDER BY updateTime DESC")
    suspend fun queryAll(userId: String):MutableList<DynamicSearchHistoryTable>

    @Insert
    suspend fun insert(tables: MutableList<DynamicSearchHistoryTable>)

    @Query("SELECT * FROM ${DBTableNames.DYNAMIC_SEARCH_HISTORY_TABLE_NAME} WHERE userId= :userId AND keyWord= :keyWord")
    suspend fun query(userId: String, keyWord: String):MutableList<DynamicSearchHistoryTable>

    @Delete
    suspend fun delete(table: DynamicSearchHistoryTable)

    @Query("DELETE FROM ${DBTableNames.DYNAMIC_SEARCH_HISTORY_TABLE_NAME} WHERE userId= :userId")
    suspend fun deleteAllByUserId(userId: String)

    @Update
    suspend fun update(table: DynamicSearchHistoryTable)

}