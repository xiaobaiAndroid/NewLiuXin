package module.login.code

import android.content.Context
import androidx.lifecycle.ViewModel
import module.common.data.DataResult
import module.common.data.db.AppDatabase
import module.common.data.entity.UserInfo
import module.common.data.response.VerificationCodeResp
import module.common.data.respository.user.UserRepository

/**
 *@author: baizf
 *@date: 2023/3/7
 */
class LoginCodeViewModel: ViewModel() {


    suspend fun sendVerificationCode(phone: String):DataResult<VerificationCodeResp> {
        return UserRepository.instance.sendVerificationCode(phone)
    }

    suspend fun login(context: Context, phone: String, code: String): DataResult<UserInfo> {
        return UserRepository.instance.loginByCode(context,phone,code,"")
    }
}