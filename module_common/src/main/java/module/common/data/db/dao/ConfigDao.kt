package module.common.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import module.common.data.db.entity.ConfigTable
import module.common.data.db.entity.VideoTable

@Dao
interface ConfigDao {

    @Query("Select * FROM config_table")
    suspend fun getAll(): List<ConfigTable>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inset(configTable: ConfigTable)

    @Update
    suspend fun update(configTable: ConfigTable)

}