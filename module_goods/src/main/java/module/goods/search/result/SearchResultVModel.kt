package module.goods.search.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
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
class SearchResultVModel: BaseViewModel() {

    private val req = CateGooodsListReq()

    /*是否是单行显示*/
     var isSingle = false


    val searchResultLD: MutableLiveData<DataResult<MutableList<ResultMultiEntity>>> by lazy {
        MutableLiveData<DataResult<MutableList<ResultMultiEntity>>>()
    }

    val cutLayoutLD: MutableLiveData<MutableList<ResultMultiEntity>> by lazy {
        MutableLiveData<MutableList<ResultMultiEntity>>()
    }

    fun setPramsBrandIds(ids: MutableList<String>) {
        req.queryObj.brandIds = ids
    }


    fun search(keyWord: String?, cateId: String?)   = viewModelScope.launch(Dispatchers.IO){
        val resultDR = DataResult<MutableList<ResultMultiEntity>>()
        req.queryObj.goodsTitle = keyWord
        req.queryObj.cateId = cateId
        val dataResult: DataResult<MutableList<Goods>?> =
            GoodsRepository.instance.getGoodsListByCateId(mContext,req)
        resultDR.status = dataResult.status
        resultDR.message = dataResult.message
        val goodsList = dataResult.t
        if (goodsList != null) {
            resultDR.t = convertData(goodsList)
        }
        withContext(Dispatchers.Main){
            searchResultLD.value = resultDR
        }
    }

    private fun convertData(goodsList: MutableList<Goods>): MutableList<ResultMultiEntity> {
        val list: MutableList<ResultMultiEntity> = mutableListOf()
        for (goods in goodsList) {
            var resultMultiEntity: ResultMultiEntity = if (isSingle) {
                ResultMultiEntity(ResultMultiEntity.Type.SINGLE.ordinal)
            } else {
                ResultMultiEntity(ResultMultiEntity.Type.MULTI.ordinal)
            }
            resultMultiEntity.goods = goods
            list.add(resultMultiEntity)
        }
        return list
    }


    fun setParamsPriceAsc() {
        req.queryObj = CateGooodsListReq.QueryObj()
        req.queryObj.priceDesc = SortType.ASC.value
    }



    fun setParamsSkuIds(skuIds: MutableList<String>?, minPrice: String?, maxPrice: String?) {
        req.queryObj = CateGooodsListReq.QueryObj()
        req.queryObj.skuIds = skuIds
        req.queryObj.minPrice = minPrice
        req.queryObj.maxPrice = maxPrice
    }

    fun setParamsSynthesize() {
        req.queryObj = CateGooodsListReq.QueryObj()
    }

    fun setParamsPriceDesc() {
        req.queryObj = CateGooodsListReq.QueryObj()
        req.queryObj.priceDesc = SortType.DESC.value
    }

    fun setParamsSales() {
        req.queryObj = CateGooodsListReq.QueryObj()
        req.queryObj.salesDesc = SortType.DESC.value
    }

    fun cutLayout(originalList: MutableList<ResultMultiEntity>)  = viewModelScope.launch(Dispatchers.Default){
        val list: MutableList<ResultMultiEntity> = ArrayList()
        for (original in originalList) {
            val cut: ResultMultiEntity = if (isSingle) {
                ResultMultiEntity(ResultMultiEntity.Type.SINGLE.ordinal)
            } else {
                ResultMultiEntity(ResultMultiEntity.Type.MULTI.ordinal)
            }
            cut.goods = original.goods
            list.add(cut)
        }
        withContext(Dispatchers.Main){
            cutLayoutLD.value = list
        }
    }



    fun getCurrentPage(): Int {
        return req.pageNumber
    }

    fun resetCurrentPage() {
        req.pageNumber = 1
    }

    fun getPageSize(): Int {
        return req.pageSize
    }

    fun setNextPage() {
        req.pageNumber += 1
    }
}