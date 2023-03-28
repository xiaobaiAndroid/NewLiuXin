package module.goods.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Goods
import module.common.data.entity.GoodsDetailImage
import module.common.data.entity.Shop
import module.common.data.respository.goods.GoodsRepository
import module.common.data.respository.shop.ShopRepository
import module.common.type.GoodsImageType

/**
 *@author: baizf
 *@date: 2023/3/28
 */
class GoodsDetailVModel: BaseViewModel() {

    val goodsDetailLD: MutableLiveData<MutableList<DetailMultiEntity>> by lazy {
        MutableLiveData<MutableList<DetailMultiEntity>>()
    }
    val bannersLD: MutableLiveData<MutableList<GoodsDetailImage>> by lazy {
        MutableLiveData<MutableList<GoodsDetailImage>>()
    }

    val shopLD: MutableLiveData<Shop?> by lazy {
        MutableLiveData<Shop?>()
    }

    val goodsLD: MutableLiveData<Goods?> by lazy {
        MutableLiveData<Goods?>()
    }

    fun getGoodsDetail(goodsId: String?, actId: String?) = viewModelScope.launch(Dispatchers.IO) {
        val bannerList: MutableList<GoodsDetailImage> = mutableListOf()
        val detailImageList: MutableList<DetailMultiEntity> = mutableListOf()
        val dataResult: DataResult<Goods?> =
            GoodsRepository.instance.getGoodsDetail(mContext,goodsId, actId)

        dataResult.t?.let {
            it.goodsImages?.let { goodsImages->
                for (goodsImage in goodsImages) {
                    bannerList.add(goodsImage)
                }
            }
            it.descImages?.let { descImages->
                for (detailImage in descImages) {
                    val entity =
                        DetailMultiEntity(DetailMultiEntity.Type.DETAIL.ordinal)
                    entity.detailImage = detailImage
                    detailImageList.add(entity)
                }
            }
        }
        withContext(Dispatchers.Main){
            goodsLD.value = dataResult.t
            goodsDetailLD.value = detailImageList
            bannersLD.value = bannerList

            getShopInfo(dataResult.t?.storeId)
        }
    }

    private fun getShopInfo(storeId: String?) = viewModelScope.launch(Dispatchers.IO){
        val dataResult: DataResult<Shop?> = ShopRepository.instance.getShopInfo(mContext,storeId)
        withContext(Dispatchers.Main){
            shopLD.value = dataResult.t
        }
    }

    fun collectGoods(goodsId: String?) {

    }

    fun addShoppingCart(mGoods: Goods, id: String?, buyNumber: Int) {

    }
}