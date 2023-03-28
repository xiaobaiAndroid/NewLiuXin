package module.goods.category.home.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Banner
import module.common.data.entity.Goods
import module.common.data.entity.GoodsCategory
import module.common.data.request.BannerReq
import module.common.data.request.CateGooodsListReq
import module.common.data.request.ReqParams
import module.common.data.respository.goods.GoodsRepository
import module.goods.R
import module.goods.category.home.GOODS_CATEGORY_HOME_ID

/**
 *@author: baizf
 *@date: 2023/3/27
 */
class GoodsListVModel : BaseViewModel() {

    private val req = CateGooodsListReq()

    val bannerLD: MutableLiveData<MutableList<Banner>?> by lazy {
        MutableLiveData<MutableList<Banner>?>()
    }

    val categoriesLD: MutableLiveData<MutableList<GoodsCategory>> by lazy {
        MutableLiveData<MutableList<GoodsCategory>>()
    }

    val goodsListResultLD: MutableLiveData<DataResult<MutableList<Goods>?>> by lazy {
        MutableLiveData<DataResult<MutableList<Goods>?>>()
    }

    fun getBannerData(id: String?) = viewModelScope.launch(Dispatchers.IO) {
        val req = BannerReq()
        req.siteParentId = "0"
        if(id == GOODS_CATEGORY_HOME_ID){
            req.cateId = "13"
        }else{
            req.cateId = id
        }

        val dataResult: DataResult<MutableList<Banner>?> =
            GoodsRepository.instance.getBannerById(req)
        withContext(Dispatchers.Main) {
            bannerLD.value = dataResult.t
        }
    }

    fun getChildCategory(id: String?) = viewModelScope.launch(Dispatchers.IO) {

        val list: MutableList<GoodsCategory> = mutableListOf()
        if (GOODS_CATEGORY_HOME_ID == id) {
            val titles = mContext.resources.getStringArray(R.array.goods_home_child_category)
            list.add(GoodsCategory("0", titles[0], R.drawable.goods_ic_home_cate0))
            list.add(GoodsCategory("0", titles[1], R.drawable.goods_ic_home_cate1))
            list.add(GoodsCategory("0", titles[2], R.drawable.goods_ic_home_cate2))
            list.add(GoodsCategory("0", titles[3], R.drawable.goods_ic_home_cate3))
            list.add(GoodsCategory("0", titles[4], R.drawable.goods_ic_home_cate4))
            list.add(GoodsCategory("0", titles[5], R.drawable.goods_ic_home_cate5))
            list.add(GoodsCategory("0", titles[6], R.drawable.goods_ic_home_cate6))
            list.add(GoodsCategory("0", titles[7], R.drawable.goods_ic_home_cate7))
            setCateIds(list)
        } else {
            val dr: DataResult<MutableList<GoodsCategory>?> =
                GoodsRepository.instance.getCategories(mContext, id ?: "")
            dr.t?.let {
                list.addAll(it)
                setCateIds(list)
            }
        }
        withContext(Dispatchers.Main) {
            categoriesLD.value = list
        }
    }

    private fun setCateIds(categoryList: MutableList<GoodsCategory>) {
        val cateIds: MutableList<String> = java.util.ArrayList()
        for (goodsCategory in categoryList) {
            if (goodsCategory.id != GOODS_CATEGORY_HOME_ID) {
                cateIds.add(goodsCategory.id)
            }
        }
        req.queryObj.cateIds = cateIds
    }

    fun getGoodsList() = viewModelScope.launch(Dispatchers.IO) {
        val dataResult: DataResult<MutableList<Goods>?> =
            GoodsRepository.instance.getGoodsListByCateId(mContext, req)
        withContext(Dispatchers.Main) {
            goodsListResultLD.value = dataResult
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