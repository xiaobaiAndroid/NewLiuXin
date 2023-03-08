package module.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import module.common.data.entity.UserInfo
import module.common.type.LanguageType

open class BaseViewModel : ViewModel() {


    var language: Int = LanguageType.CN.value

    val userInfoLiveData: MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }


}