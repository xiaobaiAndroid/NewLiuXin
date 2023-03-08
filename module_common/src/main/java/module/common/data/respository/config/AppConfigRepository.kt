package module.common.data.respository.config

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import module.common.data.db.dao.ConfigDao
import module.common.data.db.entity.ConfigTable
import module.common.utils.LanguageConfigUtils

class AppConfigRepository(val configDao: ConfigDao) {


     suspend fun getConfigTable(context: Context): ConfigTable{
        val all = configDao.getAll()
         if(all == null || all.isEmpty()){
            val configTable = ConfigTable()
             configTable.language = LanguageConfigUtils.getCurrentSetLanguage(context)
            configDao.inset(configTable)
            return configTable
        }else{
            val config = all.get(0)
            return config
        }

    }

    @WorkerThread
    suspend fun updateConfigTable(configTable: ConfigTable){
        configDao.update(configTable)
    }
}