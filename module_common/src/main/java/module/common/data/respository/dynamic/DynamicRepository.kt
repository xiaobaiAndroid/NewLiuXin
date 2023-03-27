package module.common.data.respository.dynamic

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.*
import module.common.data.request.*
import module.common.data.respository.user.UserRepository

class DynamicRepository private constructor() {

    private val mRemote: DynamicRemote
    private val mLocal: DynamicLocal

    init {
        mRemote = DynamicRemote()
        mLocal = DynamicLocal()
    }

    companion object {
        val instance: DynamicRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DynamicRepository()
        }
    }


    suspend fun getCategoryData(
        context: Context,
        req: CliqueCategoryReq
    ): DataResult<List<DynamicCategory>?> {
        val userId = UserRepository.instance.getUserInfo(context).userId ?: ""

        val list: List<DynamicCategory> = mLocal.getCategoryData(context, userId, req.type.toInt())
        if (list.isEmpty()) {
            var dataResult: DataResult<List<DynamicCategory>?> =
                mRemote.getCategoryData(UserRepository.instance.getToken(context), req)
            if (dataResult.status == DataResult.TOKEN_PAST) {
                UserRepository.instance.refreshToken(context)?.let {
                    dataResult = mRemote.getCategoryData(it, req)
                }
            }
            if (dataResult.status == DataResult.SUCCESS) {
                dataResult.t?.let {
                    mLocal.saveCategoryData(context, userId, req.type.toInt(), it)
                }
            }
            return dataResult
        } else {
            var dataResult = DataResult<List<DynamicCategory>?>()
            dataResult.status = DataResult.SUCCESS
            dataResult.t = list
            return dataResult
        }
    }

    suspend fun getRecommendDynamicData(
        mContext: Context,
        req: DynamicListReq
    ): DataResult<MutableList<Dynamic>?> {
        var dataResult: DataResult<MutableList<Dynamic>?> =
            mRemote.getRecommendDynamicData(UserRepository.instance.getToken(mContext), req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(mContext)?.let {
                dataResult =
                    mRemote.getRecommendDynamicData(UserRepository.instance.getToken(mContext), req)
            }
        }
        return dataResult
    }

    suspend fun getImgTxtData(context: Context, dynamicId: String?): DataResult<ImgTxtData?> {
        var token = UserRepository.instance.getToken(context)
        var dataResult = mRemote.getImgTxtData(token, dynamicId)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getImgTxtData(token, dynamicId)
            }
        }
        return dataResult
    }

    suspend fun endorse(mContext: Context, endorseReq: EndorseReq): DataResult<String> {
        val userInfo: UserInfo = UserRepository.instance.getUserInfo(mContext)
        endorseReq.userId = userInfo.userId
        return mRemote.endorse(userInfo.access_token, endorseReq)
    }

    suspend fun collect(context: Context, endorseReq: EndorseReq): DataResult<String> {
        val userInfo: UserInfo = UserRepository.instance.getUserInfo(context)
        endorseReq.userId = userInfo.userId
        return mRemote.collect(userInfo.access_token, endorseReq)
    }

    suspend fun getFriendDynamicData(
        context: Context,
        req: DynamicListReq
    ): DataResult<MutableList<Dynamic>?> {
        var dataResult: DataResult<MutableList<Dynamic>?> =
            mRemote.getFriendDynamicData(UserRepository.instance.getToken(context), req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult =
                    mRemote.getFriendDynamicData(UserRepository.instance.getToken(context), req)
            }
        }
        return dataResult
    }

    suspend fun getCityDynamicData(
        context: Context,
        cityCode: String,
        req: DynamicListReq
    ): DataResult<MutableList<Dynamic>?> {
        var dataResult: DataResult<MutableList<Dynamic>?> =
            mRemote.getCityDynamicData(UserRepository.instance.getToken(context), cityCode, req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getCityDynamicData(
                    UserRepository.instance.getToken(context),
                    cityCode,
                    req
                )
            }
        }
        return dataResult
    }

    suspend fun getOtherDynamicData(
        context: Context,
        req: DynamicListReq
    ): DataResult<MutableList<Dynamic>?> {
        var dataResult: DataResult<MutableList<Dynamic>?> =
            mRemote.getOtherDynamicData(UserRepository.instance.getToken(context), req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult =
                    mRemote.getOtherDynamicData(UserRepository.instance.getToken(context), req)
            }
        }
        return dataResult
    }

    suspend fun comment(context: Context, req: CommentReq): DataResult<String?> {
        var dataResult: DataResult<String?> =
            mRemote.comment(UserRepository.instance.getToken(context), req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.comment(UserRepository.instance.getToken(context), req)
            }
        }
        return dataResult
    }

    suspend fun getSearchHistories(context: Context): MutableList<HistorySearch> {
        val userInfo = UserRepository.instance.getUserInfo(context)
        return mLocal.getSearchHistories(context,userInfo.userId)
    }

    suspend fun saveSearchHistory(context: Context,content: String) {
        val userInfo = UserRepository.instance.getUserInfo(context)
        return mLocal.saveSearchHistory(context,content,userInfo.userId)
    }

    suspend fun deleteSearchHistory(context: Context, historySearch: HistorySearch) {
        return mLocal.deleteSearchHistory(context,historySearch)
    }

    suspend fun clearHistories(context: Context) {
        val userInfo = UserRepository.instance.getUserInfo(context)
        return mLocal.clearHistories(context,userInfo.userId)
    }

    suspend fun searchByKeyword(context: Context, req: SearchReq): DataResult<MutableList<Dynamic>?> {
        var dataResult: DataResult<MutableList<Dynamic>?> =
            mRemote.searchByKeyword(UserRepository.instance.getToken(context), req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.searchByKeyword(UserRepository.instance.getToken(context), req)
            }
        }
        return dataResult
    }

}