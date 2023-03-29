package module.common.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import module.common.data.entity.UserInfo
import module.common.type.LanguageType
import module.common.utils.LogUtils

open class BaseViewModel : ViewModel() {

    lateinit var mContext: Context

    var language: Int = LanguageType.CN.value

    val userInfoLiveData: MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }

    override fun onCleared() {
        super.onCleared()
       SavedStateHandle
    }

}