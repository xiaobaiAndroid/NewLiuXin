package module.common.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import module.common.data.db.DBTableNames
import module.common.data.db.entity.MusicTable

/**
 *@author: baizf
 *@date: 2023/3/9
 */
@Dao
interface MusicDao {

    @Query("SELECT * FROM ${DBTableNames.MUSIC_TABLE_NAME} WHERE musicId= :musicId AND userId= :userId")
    suspend fun queryById(userId: String, musicId: String): List<MusicTable>

    @Query("SELECT * FROM ${DBTableNames.MUSIC_TABLE_NAME} WHERE userId= :userId ORDER BY addDate DESC")
    suspend fun queryAll(userId: String):MutableList<MusicTable>

    @Insert
    suspend fun insert(musicTable: MusicTable)
}