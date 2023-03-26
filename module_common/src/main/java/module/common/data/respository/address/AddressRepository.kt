package module.common.data.respository.address

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.*
import module.common.data.request.GiveGiftReq
import module.common.data.respository.user.UserRepository

class AddressRepository private constructor() {

    private val mRemote: AddressRemote
    private val mLocal: AddressLocal

    init {
        mRemote = AddressRemote()
        mLocal = AddressLocal()
    }


   suspend fun getProvinces(context: Context): DataResult<MutableList<City>?> {
       val token = UserRepository.instance.getToken(context)
       var dataResult: DataResult<MutableList<City>?> = mRemote.getCities(token,"0")
       if (dataResult.status == DataResult.TOKEN_PAST) {
           UserRepository.instance.refreshToken(context)?.let {
               dataResult = mRemote.getCities(token, "0")
           } ?: kotlin.run {
               dataResult.status = DataResult.NEED_LOGIN
           }
       }
       return dataResult
    }

    suspend fun getCities(context: Context, code: String): DataResult<MutableList<City>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult: DataResult<MutableList<City>?> = mRemote.getCities(token,code)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getCities(token, code)
            } ?: kotlin.run {
                dataResult.status = DataResult.NEED_LOGIN
            }
        }
        return dataResult
    }


    companion object {
        val instance: AddressRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AddressRepository()
        }
    }
}