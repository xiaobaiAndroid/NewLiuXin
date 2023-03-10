package module.common.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import module.common.data.db.entity.MUSIC_TABLE_NAME
import module.common.data.db.entity.MusicTable

/**
 *@author: baizf
 *@date: 2023/3/9
 */
@Dao
interface MusicDao {

    @Query("SELECT * FROM $MUSIC_TABLE_NAME WHERE musicId= :musicId AND userId= :userId")
    suspend fun queryById(userId: String, musicId: String): List<MusicTable>

    @Query("SELECT * FROM $MUSIC_TABLE_NAME WHERE userId= :userId")
    suspend fun queryAll(userId: String):MutableList<MusicTable>

    @Insert
    suspend fun insert(musicTable: MusicTable)
}