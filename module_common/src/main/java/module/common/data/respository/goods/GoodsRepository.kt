package module.common.data.respository.goods

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.Banner
import module.common.data.entity.Goods
import module.common.data.entity.GoodsCategory
import module.common.data.request.BannerReq
import module.common.data.request.CateGooodsListReq
import module.common.data.request.RecommendGoodsReq
import module.common.data.request.ReqParams
import module.common.data.respository.user.UserRepository

class GoodsRepository private constructor() {


    private val mRemote: GoodsRemote
    private val mLocal: GoodsLocal

    init {
        mRemote = GoodsRemote()
        mLocal = GoodsLocal()
    }

    companion object {
        val instance: GoodsRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            GoodsRepository()
        }
    }



    suspend fun getRecommendGoods(
        context: Context,
        req: RecommendGoodsReq
    ): DataResult<MutableList<Goods>?> {
        return mRemote.getRecommendGoods(UserRepository.instance.getToken(context), req)
    }

   suspend fun getCategories(context: Context, cateId: String): DataResult<MutableList<GoodsCategory>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<GoodsCategory>?> = mRemote.getCategories(token,cateId)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getCategories(token, cateId)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }

   suspend fun getBannerById(req: BannerReq): DataResult<MutableList<Banner>?> {
        return mRemote.getBannerById(req)
    }

    suspend fun getGoodsListByCateId(context: Context, req: CateGooodsListReq): DataResult<MutableList<Goods>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<Goods>?> = mRemote.getGoodsListByCateId(token,req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getGoodsListByCateId(token, req)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }


}