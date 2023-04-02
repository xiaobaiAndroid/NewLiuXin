package module.goods.search.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.base.ShareLazyViewModelBase
import module.common.data.DataResult
import module.common.data.entity.Brand
import module.common.data.entity.Goods
import module.common.data.entity.SkuAttribute
import module.common.data.entity.SkuAttributeValue
import module.common.data.request.CateGooodsListReq
import module.common.data.respository.goods.GoodsRepository
import module.common.type.SortType
import module.goods.search.result.filtrate.FiltrateEntity

/**
 *@author: baizf
 *@date: 2023/4/2
 */
class SearchResultShareVModel: ShareLazyViewModelBase() {


    val selectBrandsLD: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>()
    }


}