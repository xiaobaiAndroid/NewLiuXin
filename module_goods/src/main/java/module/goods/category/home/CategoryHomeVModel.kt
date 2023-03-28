package module.goods.category.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.GoodsCategory
import module.common.data.respository.goods.GoodsRepository

/**
 *@author: baizf
 *@date: 2023/3/27
 */
class CategoryHomeVModel: BaseViewModel() {

    val cateGoryResultLD: MutableLiveData<MutableList<GoodsCategory>?> by lazy {
        MutableLiveData<MutableList<GoodsCategory>?>()
    }

    fun getCategories() = viewModelScope.launch(Dispatchers.IO){
        val categories = GoodsRepository.instance.getCategories(mContext, "13")
        withContext(Dispatchers.Main){
            cateGoryResultLD.value = categories.t
        }
    }
}