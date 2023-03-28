package module.common.data.respository.shop

import android.content.Context
import module.common.data.db.AppDatabase
import module.common.data.db.entity.MusicTable
import module.common.data.entity.Music
import module.common.utils.LogUtils
import java.util.*

internal class ShopLocal {

    suspend fun addMusic(context: Context, userId: String,music: Music): MusicTable {

        val musicDao = AppDatabase.getDatabase(context).musicDao()

        val musicTableList = musicDao.queryById(userId, music.id ?: "")
        if(musicTableList.isNotEmpty()){
            return musicTableList[0]
        }else{
            val musicTable = MusicTable()
            musicTable.localId = UUID.randomUUID().toString()
            musicTable.musicId = music.id
            musicTable.userId = userId
            musicTable.addDate = System.currentTimeMillis().toString()
            musicTable.musicName = music.musicName
            musicTable.musicLable = music.musicLable
            musicTable.musicTypeName = music.musicTypeName
            musicTable.musicUrl = music.musicUrl
            musicTable.musicType = music.musicType
            musicTable.musicTime = music.musicTime
            musicDao.insert(musicTable)
            return musicTable
        }
    }

    suspend fun queryAllMusic(context: Context, userId: String): MutableList<MusicTable> {
        val musicDao = AppDatabase.getDatabase(context).musicDao()
        val musicTableList = musicDao.queryAll(userId)
        LogUtils.printI("queryAllMusic---size=${musicTableList.size}")
        return musicTableList
    }
}