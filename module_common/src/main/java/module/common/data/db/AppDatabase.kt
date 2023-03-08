package module.common.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import module.common.data.db.dao.ConfigDao
import module.common.data.db.dao.UserInfoDao
import module.common.data.db.dao.VideoDao
import module.common.data.db.entity.ConfigTable
import module.common.data.db.entity.UserInfoTable
import module.common.data.db.entity.VideoTable

@Database(entities = [VideoTable::class, ConfigTable::class, UserInfoTable::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun videoDao(): VideoDao

    abstract fun configDao(): ConfigDao
    abstract fun userDao(): UserInfoDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE?: synchronized(this){
              val instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"app_database").build()
                INSTANCE = instance
                // return instance
                instance
            }

        }

    }

}