package module.common.data.respository.goods

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.Goods
import module.common.data.request.RecommendGoodsReq
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


}