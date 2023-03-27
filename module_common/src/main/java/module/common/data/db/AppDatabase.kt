package module.common.data.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import module.common.data.db.dao.*
import module.common.data.db.entity.*

@Database(
    version = 2,
    entities = [VideoTable::class, ConfigTable::class, UserInfoTable::class, MusicTable::class, DynamicCategoryTable::class, DynamicSearchHistoryTable::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

    abstract fun configDao(): ConfigDao
    abstract fun userDao(): UserInfoDao

    abstract fun musicDao(): MusicDao
    abstract fun dynamicCategoryDao(): DynamicCategoryDao
    abstract fun dynamicSearchHistoryDao(): DynamicSearchHistoryDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "db_liuxin"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }

        }

        //手动迁移
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE `${DBTableNames.DYNAMIC_SEARCH_HISTORY_TABLE_NAME}` (`historyId` TEXT PRIMARY KEY UNIQUE NOT NULL, " +
                            "`keyWord` TEXT NOT NULL, `userId` TEXT, `updateTime` TEXT NOT NULL)")
            }
        }


    }


    //自动迁移
    class MyAutoMigration : AutoMigrationSpec {

        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            super.onPostMigrate(db)

        }
    }


}