package module.common.data.respository.wallet

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.Gift
import module.common.data.entity.GiftCategory
import module.common.data.entity.Money
import module.common.data.respository.user.UserRepository

class WalletRepository private constructor() {

    private val mRemote: WalletRemote
    private val mLocal: WalletLocal

    init {
        mRemote = WalletRemote()
        mLocal = WalletLocal()
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

    suspend fun getVirtualMoney(context: Context): DataResult<Money> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<Money> = mRemote.getVirtualMoney(token)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getVirtualMoney(token)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }


    companion object {
        val instance: WalletRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            WalletRepository()
        }
    }
}