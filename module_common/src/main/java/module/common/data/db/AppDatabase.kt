package module.common.data.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import module.common.data.db.dao.ConfigDao
import module.common.data.db.dao.MusicDao
import module.common.data.db.dao.UserInfoDao
import module.common.data.db.dao.VideoDao
import module.common.data.db.entity.*

@Database(
    version = 1,
    entities = [VideoTable::class, ConfigTable::class, UserInfoTable::class, MusicTable::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

    abstract fun configDao(): ConfigDao
    abstract fun userDao(): UserInfoDao

    abstract fun musicDao(): MusicDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
//                    .addMigrations(MIGRATION_3_4)
                    .build()
                INSTANCE = instance
                instance
            }

        }

//        val MIGRATION_3_4 = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL(
//                    "CREATE TABLE `$MUSIC_TABLE_NAME` (`localId` VARCHAR(100), `musicId` VARCHAR(100)," +
//                            " `userId` VARCHAR(100), `musicType`  VARCHAR(100), `musicTypeName` VARCHAR(100), `musicName` VARCHAR(200)," +
//                            "`musicUrl` VARCHAR(300), `musicTime` VARCHAR(100), `musicLable` VARCHAR(100), `addDate` VARCHAR(100)," +
//                            "PRIMARY KEY(`localId`))"
//                )
//            }
//        }


    }



}