package module.user.register

import androidx.lifecycle.MutableLiveData
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.UserInfo
import module.common.data.respository.user.UserRepository

/**
 *@author: baizf
 *@date: 2023/3/17
 */
class RegisterViewModel : BaseViewModel() {

    val vcodeLiveData: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    suspend fun register(phone: String, code: String, recommendPhone: String): DataResult<String> {
        return UserRepository.instance.register(mContext, phone, code, recommendPhone)
    }
}