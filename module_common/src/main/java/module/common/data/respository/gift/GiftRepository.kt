package module.common.data.respository.gift

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.*
import module.common.data.request.GiveGiftReq
import module.common.data.respository.user.UserRepository

class GiftRepository private constructor() {

    private val mRemote: GiftRemote
    private val mLocal: GiftLocal

    init {
        mRemote = GiftRemote()
        mLocal = GiftLocal()
    }


    suspend fun getCategory(context: Context): DataResult<MutableList<GiftCategory>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<GiftCategory>?> = mRemote.getCategory(token)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getCategory(token)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }

    suspend fun getGiftList(context: Context, giftTypeId: Int): DataResult<MutableList<Gift>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<Gift>?> = mRemote.getGiftList(token, giftTypeId)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getGiftList(token,giftTypeId)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }

   suspend fun giveGift(context: Context, req: GiveGiftReq): DataResult<String?> {

       val userInfo: UserInfo = UserRepository.instance.getUserInfo(context)
       req.fromUId = userInfo.userId
       val token = UserRepository.instance.getToken(context)
       var dataResult: DataResult<String?> = mRemote.giveGift(token, req)
       if (dataResult.status == DataResult.TOKEN_PAST) {
           UserRepository.instance.refreshToken(context)?.let {
               dataResult = mRemote.giveGift(token,req)
           } ?: kotlin.run {
               dataResult.status = DataResult.NEED_LOGIN
           }
       }
       return dataResult
    }


    companion object {
        val instance: GiftRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            GiftRepository()
        }
    }
}