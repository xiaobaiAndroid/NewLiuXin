package module.dynamic.search

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.entity.HistorySearch
import module.common.data.respository.dynamic.DynamicRepository
import module.dynamic.R

/**
 *@author: baizf
 *@date: 2023/3/26
 */
class DynamicSearchVModel: BaseViewModel() {

    val hostListLD: MutableLiveData<MutableList<HotSearchEntity>> by lazy {
        MutableLiveData<MutableList<HotSearchEntity>>()
    }

    val historyListLD: MutableLiveData<MutableList<HistorySearch>> by lazy {
        MutableLiveData<MutableList<HistorySearch>>()
    }


    fun clearHistories() = viewModelScope.launch(Dispatchers.IO){
        DynamicRepository.instance.clearHistories(mContext)
        withContext(Dispatchers.Main){
            getHistories()
        }
    }

    fun getHotSearch() = viewModelScope.launch(Dispatchers.IO){
        val titles = mContext.resources.getStringArray(R.array.dynamic_hot_search)
        val list: MutableList<HotSearchEntity> = ArrayList()
        for (i in titles.indices) {
            val hotSearchEntity = HotSearchEntity()
            hotSearchEntity.title = titles[i]
            if (i == 0) {
                hotSearchEntity.color = Color.parseColor("#FF6468")
            } else if (i == 1) {
                hotSearchEntity.color = Color.parseColor("#6764FF")
            } else if (i == 2) {
                hotSearchEntity.color = Color.parseColor("#D96400")
            } else if (i == 3) {
                hotSearchEntity.color = Color.parseColor("#DB6300")
            } else if (i == 4) {
                hotSearchEntity.color = Color.parseColor("#D90066")
            }
            list.add(hotSearchEntity)
        }
        withContext(Dispatchers.Main){
            hostListLD.value = list
        }
    }

    fun getHistories() = viewModelScope.launch(Dispatchers.IO){
        val list: MutableList<HistorySearch> = DynamicRepository.instance.getSearchHistories(mContext)
        withContext(Dispatchers.Main){
            historyListLD.value = list
        }
    }

    fun deleteHistory(historySearch: HistorySearch) = viewModelScope.launch(Dispatchers.IO){
        DynamicRepository.instance.deleteSearchHistory(mContext,historySearch)
    }

    fun saveSearchHistory(content: String) = viewModelScope.launch(Dispatchers.IO){
        DynamicRepository.instance.saveSearchHistory(mContext,content)
        withContext(Dispatchers.Main){
            getHistories()
        }
    }
}