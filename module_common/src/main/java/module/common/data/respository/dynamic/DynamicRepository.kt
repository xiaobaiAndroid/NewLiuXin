package module.common.data.respository.dynamic

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.CliqueCategory
import module.common.data.entity.Dynamic
import module.common.data.entity.ImgTxtData
import module.common.data.entity.UserInfo
import module.common.data.request.CliqueCategoryReq
import module.common.data.request.DynamicListReq
import module.common.data.request.EndorseReq
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
    ): DataResult<List<CliqueCategory>?> {
       var dataResult: DataResult<List<CliqueCategory>?> =
           mRemote.getCategoryData(UserRepository.instance.getToken(context), req)
       if (dataResult.status == DataResult.TOKEN_PAST) {
           UserRepository.instance.refreshToken(context)?.let {
               dataResult = mRemote.getCategoryData(it, req)
           }
       }
       return dataResult
    }

    suspend fun getDynamicData(
        mContext: Context,
        typeId: String?,
        cityCode: String?,
        req: DynamicListReq
    ): DataResult<List<Dynamic>?> {
        var dataResult: DataResult<List<Dynamic>?> =
            mRemote.getDynamicData(UserRepository.instance.getToken(mContext), typeId, cityCode, req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(mContext)?.let{
                dataResult = mRemote.getDynamicData(
                    UserRepository.instance.getToken(mContext),
                    typeId,
                    cityCode,
                    req
                )
            }
        }
        return dataResult
    }

    suspend fun getImgTxtData(context: Context, dynamicId: String?): DataResult<ImgTxtData?> {
        var token = UserRepository.instance.getToken(context)
        var dataResult = mRemote.getImgTxtData(token, dynamicId)
        if(dataResult.status == DataResult.TOKEN_PAST){
            UserRepository.instance.refreshToken(context)?.let{
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

    suspend fun collect(context: Context,endorseReq: EndorseReq): DataResult<String> {
        val userInfo: UserInfo = UserRepository.instance.getUserInfo(context)
        endorseReq.userId = userInfo.userId
        return mRemote.collect(userInfo.access_token, endorseReq)
    }

}