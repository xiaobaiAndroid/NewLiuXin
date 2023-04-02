package module.common.data.db.dao

import androidx.room.*
import module.common.data.db.DBTableNames
import module.common.data.db.entity.DynamicSearchHistoryTable
import module.common.data.db.entity.GoodsSearchHistoryTable

/**
 *@author: baizf
 *@date: 2023/3/26
 */
@Dao
interface GoodsSearchHistoryDao {

    @Query("SELECT * FROM ${DBTableNames.GOODS_SEARCH_HISTORY_TABLE_NAME} WHERE userId= :userId ORDER BY updateTime DESC")
    suspend fun queryAll(userId: String):MutableList<GoodsSearchHistoryTable>

    @Insert
    suspend fun insert(tables: MutableList<GoodsSearchHistoryTable>)

    @Query("SELECT * FROM ${DBTableNames.GOODS_SEARCH_HISTORY_TABLE_NAME} WHERE userId= :userId AND keyWord= :keyWord")
    suspend fun query(userId: String, keyWord: String):MutableList<GoodsSearchHistoryTable>

    @Delete
    suspend fun delete(table: GoodsSearchHistoryTable)

    @Query("DELETE FROM ${DBTableNames.GOODS_SEARCH_HISTORY_TABLE_NAME} WHERE userId= :userId")
    suspend fun deleteAllByUserId(userId: String)

    @Update
    suspend fun update(table: GoodsSearchHistoryTable)

}