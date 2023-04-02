package module.common.data.respository.goods

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.*
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

    suspend fun getCategories(
        context: Context,
        cateId: String
    ): DataResult<MutableList<GoodsCategory>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<GoodsCategory>?> =
            mRemote.getCategories(token, cateId)
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

    suspend fun getGoodsListByCateId(
        context: Context,
        req: CateGooodsListReq
    ): DataResult<MutableList<Goods>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<Goods>?> = mRemote.getGoodsListByCateId(token, req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getGoodsListByCateId(token, req)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }

    suspend fun getGoodsDetail(
        context: Context,
        goodsId: String?,
        actId: String?
    ): DataResult<Goods?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<Goods?> = mRemote.getGoodsDetail(token, goodsId, actId)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getGoodsDetail(token, goodsId, actId)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }

    suspend fun getSearchHistories(context: Context): MutableList<HistorySearch> {
        val userInfo = UserRepository.instance.getUserInfo(context)
        return mLocal.getSearchHistories(context, userInfo.userId)
    }

    suspend fun saveSearchHistory(context: Context, content: String) {
        val userInfo = UserRepository.instance.getUserInfo(context)
        return mLocal.saveSearchHistory(context, content, userInfo.userId)
    }

    suspend fun deleteSearchHistory(context: Context, historySearch: HistorySearch) {
        return mLocal.deleteSearchHistory(context, historySearch)
    }

    suspend fun clearHistories(context: Context) {
        val userInfo = UserRepository.instance.getUserInfo(context)
        return mLocal.clearHistories(context, userInfo.userId)
    }

    suspend fun getSkuAttributes(
        context: Context,
        keyWord: String?
    ): DataResult<MutableList<SkuAttribute>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<SkuAttribute>?> = mRemote.getSkuAttributes(token, keyWord)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getSkuAttributes(token, keyWord)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }

   suspend fun getSkuAttributeValues(
       context: Context,
        keyWord: String?
    ): DataResult<MutableList<SkuAttributeValue>?> {
       val token = UserRepository.instance.getToken(context)
       var dataResult: DataResult<MutableList<SkuAttributeValue>?> = mRemote.getSkuAttributeValues(token, keyWord)
       if (dataResult.status == DataResult.TOKEN_PAST) {
           UserRepository.instance.refreshToken(context)?.let {
               dataResult = mRemote.getSkuAttributeValues(token, keyWord)
           } ?: kotlin.run {
               dataResult.status = DataResult.NEED_LOGIN
           }
       }
       return dataResult
    }

    suspend fun getBrands(context: Context, keyword: String?): DataResult<MutableList<Brand>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<Brand>?> = mRemote.getBrands(token, keyword)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getBrands(token, keyword)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }


}