package module.common.data.respository.video

import android.content.Context
import module.common.data.db.AppDatabase
import module.common.data.db.entity.DynamicCategoryTable
import module.common.data.entity.DynamicCategory

internal class VideoLocal {


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
}