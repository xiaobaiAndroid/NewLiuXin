package module.common.data.respository.dynamic

import android.content.Context
import module.common.data.db.AppDatabase
import module.common.data.db.entity.DynamicCategoryTable
import module.common.data.db.entity.DynamicSearchHistoryTable
import module.common.data.entity.DynamicCategory
import module.common.data.entity.HistorySearch

internal class DynamicLocal {


    suspend fun getCategoryData(context: Context, userId: String, categoryType: Int): List<DynamicCategory> {
        val dao = AppDatabase.getDatabase(context).dynamicCategoryDao()
        val categoryTableList = dao.queryAll(userId,categoryType)
        val list = mutableListOf<DynamicCategory>()
        for (categoryTable in categoryTableList){
            val dynamicCategory = DynamicCategory()
            dynamicCategory.id = categoryTable.id
            dynamicCategory.type = categoryTable.type
            dynamicCategory.typeName = categoryTable.typeName
            dynamicCategory.displayOrder = categoryTable.displayOrder.toString()
            list.add(dynamicCategory)
        }
        return list
    }

    suspend fun saveCategoryData(context: Context, userId: String, categoryType: Int,list: List<DynamicCategory>) {
        val dao = AppDatabase.getDatabase(context).dynamicCategoryDao()

        val localList = mutableListOf<DynamicCategoryTable>()
        for (category in list){
            val table = DynamicCategoryTable()
            table.id = category.id
            table.type = category.type
            table.typeName = category.typeName
            table.displayOrder = category.displayOrder?.toInt() ?: 0
            table.userId = userId
            table.categoryType = categoryType
            localList.add(table)
        }
        dao.insert(localList)
    }

    suspend fun getSearchHistories(context: Context, userId: String?): MutableList<HistorySearch> {
        val dao = AppDatabase.getDatabase(context).dynamicSearchHistoryDao()
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
        val dao = AppDatabase.getDatabase(context).dynamicSearchHistoryDao()
       val tables = dao.query(userId ?: "", content)
       if(tables.isNotEmpty()){
           tables[0].updateTime = System.currentTimeMillis().toString()
           dao.update(tables[0])
       }else{
           val table = DynamicSearchHistoryTable()
           table.userId = userId
           table.keyWord = content
           table.updateTime = System.currentTimeMillis().toString()
           dao.insert(mutableListOf(table))
       }
    }

   suspend fun deleteSearchHistory(context: Context, historySearch: HistorySearch) {
        val dao = AppDatabase.getDatabase(context).dynamicSearchHistoryDao()
        val table = DynamicSearchHistoryTable()
        table.historyId = historySearch.historyId
        table.userId = historySearch.userId
        table.keyWord = historySearch.keyWord
        table.updateTime = historySearch.updateTime.toString()
        dao.delete(table)
    }

    suspend fun clearHistories(context: Context, userId: String?) {
        val dao = AppDatabase.getDatabase(context).dynamicSearchHistoryDao()
        dao.deleteAllByUserId(userId ?: "")
    }
}