package module.dynamic.home.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.DynamicCategory
import module.common.data.request.CliqueCategoryReq
import module.common.data.respository.dynamic.DynamicRepository
import module.dynamic.view.category.CategoryMultiItem

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicCategoryHomeViewModel: BaseViewModel() {


    val mediaTypeLD: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val cityCodeLD: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val categoriseLD: MutableLiveData<MutableList<CategoryMultiItem>> by lazy {
        MutableLiveData<MutableList<CategoryMultiItem>>()
    }

    val currentPositionLD: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


    fun getCategoryData(type: String?) = viewModelScope.launch(Dispatchers.IO){
        val req = CliqueCategoryReq()
        req.id = CliqueCategoryReq.IdType.HOME
        req.type = type
        val dataResult: DataResult<List<DynamicCategory>?> = DynamicRepository.instance.getCategoryData(mContext,req)
        dataResult.t?.let {categoryList->
            val list = mutableListOf<CategoryMultiItem>()

            for (index in categoryList.indices){
                val category = categoryList.get(index)
                var multiItem: CategoryMultiItem
                if (index % 3 == 0) {
                    multiItem = CategoryMultiItem(CategoryMultiItem.LEFT,category)
                } else if ((index - 1) % 3 == 0) {
                    multiItem = CategoryMultiItem(CategoryMultiItem.CENTER,category)
                } else {
                    multiItem = CategoryMultiItem(CategoryMultiItem.RIGHT,category)
                }
                if (currentPositionLD.value == index) {
                    multiItem.isSelected = true
                }
                list.add(multiItem)
            }
            withContext(Dispatchers.Main){
                if(list.isNotEmpty()){
                    categoriseLD.value = list
                }
            }
        }

    }
}