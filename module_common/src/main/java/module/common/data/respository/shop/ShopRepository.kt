package module.common.data.respository.shop

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.Banner
import module.common.data.entity.Goods
import module.common.data.entity.GoodsCategory
import module.common.data.entity.Shop
import module.common.data.request.BannerReq
import module.common.data.request.CateGooodsListReq
import module.common.data.request.RecommendGoodsReq
import module.common.data.respository.user.UserRepository

class ShopRepository private constructor() {


    private val mRemote: ShopRemote
    private val mLocal: ShopLocal

    init {
        mRemote = ShopRemote()
        mLocal = ShopLocal()
    }

    companion object {
        val instance: ShopRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ShopRepository()
        }
    }


    suspend fun getShopInfo(context: Context, storeId: String?): DataResult<Shop?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<Shop?> = mRemote.getShopInfo(token,storeId)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getShopInfo(token, storeId)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }


}