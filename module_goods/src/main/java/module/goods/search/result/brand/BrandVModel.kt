package module.goods.search.result.brand

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Brand
import module.common.data.respository.goods.GoodsRepository

/**
 *@author: baizf
 *@date: 2023/4/2
 */
class BrandVModel : BaseViewModel() {

    val brandsLD: MutableLiveData<MutableList<Brand>?> by lazy {
        MutableLiveData<MutableList<Brand>?>()
    }



    fun getBrands(keyword: String?) = viewModelScope.launch(Dispatchers.IO) {
        val dataResult: DataResult<MutableList<Brand>?> = GoodsRepository.instance.getBrands(mContext,keyword)
        withContext(Dispatchers.Main) {
            brandsLD.value = dataResult.t
        }
    }

}