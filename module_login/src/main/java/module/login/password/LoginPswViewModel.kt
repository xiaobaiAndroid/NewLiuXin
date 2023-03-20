package module.login.password

import android.content.Context
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.UserInfo
import module.common.data.respository.user.UserRepository

/**
 *@author: baizf
 *@date: 2023/3/7
 */
class LoginPswViewModel: BaseViewModel() {

    suspend fun login(context: Context, phone: String, psw: String): DataResult<UserInfo> {
        return UserRepository.instance.loginByPsw(context, phone, psw)
    }
}