package module.login.password

import android.content.Context
import androidx.lifecycle.ViewModel
import module.common.data.DataResult
import module.common.data.entity.ErrorMessage
import module.common.data.entity.UserInfo
import module.common.data.respository.user.UserRepository
import module.common.utils.GsonUtils

/**
 *@author: baizf
 *@date: 2023/3/7
 */
class LoginPswViewModel: ViewModel() {

    suspend fun login(context: Context, phone: String, psw: String): DataResult<UserInfo> {
        return UserRepository.instance.loginByPsw(context, phone, psw)
    }
}