package module.common.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import module.common.data.db.entity.VideoTable

@Dao
interface VideoDao {

    @Query("Select * FROM video_table ORDER BY createTime ASC")
    suspend fun getAllVideo(): List<VideoTable>

    @Query("DELETE FROM video_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inset(videoTable: VideoTable)


}