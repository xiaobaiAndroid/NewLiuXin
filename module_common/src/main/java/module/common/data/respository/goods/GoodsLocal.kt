package module.common.data.respository.goods

import android.content.Context
import module.common.data.db.AppDatabase
import module.common.data.db.entity.DynamicSearchHistoryTable
import module.common.data.db.entity.GoodsSearchHistoryTable
import module.common.data.db.entity.MusicTable
import module.common.data.entity.HistorySearch
import module.common.data.entity.Music
import module.common.utils.LogUtils
import java.util.*

internal class GoodsLocal {

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

    suspend fun getSearchHistories(context: Context, userId: String?): MutableList<HistorySearch> {
        val dao = AppDatabase.getDatabase(context).goodsSearchHistoryDao()
        val tables = dao.queryAll(userId ?: "")
        val list = mutableListOf<HistorySearch>()
        for (table in tables){
            val historySearch = HistorySearch()
            historySearch.historyId = table.historyId
            historySearch.userId = table.userId
            historySearch.keyWord = table.keyWord
            historySearch.updateTime = table.updateTime.toLong()
            list.add(historySearch)
        }
        return list
    }

    suspend fun saveSearchHistory(context: Context, content: String, userId: String?) {
        val dao = AppDatabase.getDatabase(context).goodsSearchHistoryDao()
        val tables = dao.query(userId ?: "", content)
        if(tables.isNotEmpty()){
            tables[0].updateTime = System.currentTimeMillis().toString()
            dao.update(tables[0])
        }else{
            val table = GoodsSearchHistoryTable()
            table.userId = userId
            table.keyWord = content
            table.updateTime = System.currentTimeMillis().toString()
            dao.insert(mutableListOf(table))
        }
    }

    suspend fun deleteSearchHistory(context: Context, historySearch: HistorySearch) {
        val dao = AppDatabase.getDatabase(context).goodsSearchHistoryDao()
        val table = GoodsSearchHistoryTable()
        table.historyId = historySearch.historyId
        table.userId = historySearch.userId
        table.keyWord = historySearch.keyWord
        table.updateTime = historySearch.updateTime.toString()
        dao.delete(table)
    }

    suspend fun clearHistories(context: Context, userId: String?) {
        val dao = AppDatabase.getDatabase(context).goodsSearchHistoryDao()
        dao.deleteAllByUserId(userId ?: "")
    }
}