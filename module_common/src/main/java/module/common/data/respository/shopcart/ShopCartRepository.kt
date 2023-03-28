package module.common.data.respository.shopcart

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.Banner
import module.common.data.entity.Goods
import module.common.data.entity.GoodsCategory
import module.common.data.request.AddShoppingCartReq
import module.common.data.request.BannerReq
import module.common.data.request.CateGooodsListReq
import module.common.data.request.RecommendGoodsReq
import module.common.data.respository.user.UserRepository

class ShopCartRepository private constructor() {


    private val mRemote: ShopCartRemote
    private val mLocal: ShopCartLocal

    init {
        mRemote = ShopCartRemote()
        mLocal = ShopCartLocal()
    }

    companion object {
        val instance: ShopCartRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ShopCartRepository()
        }
    }


    suspend fun addShoppingCart(
        context: Context,
        req: AddShoppingCartReq
    ): DataResult<String?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<String?> = mRemote.addShoppingCart(token,req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.addShoppingCart(token, req)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }


}