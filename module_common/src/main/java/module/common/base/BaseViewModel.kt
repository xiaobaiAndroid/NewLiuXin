package module.common.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.data.entity.UserInfo
import module.common.data.entity.UserInfo.LoginStatus
import module.common.data.respository.user.UserRepository
import module.common.type.LanguageType
import module.common.utils.LogUtils

open class BaseViewModel : ViewModel() {

    lateinit var mContext: Context


    var language: Int = LanguageType.CN.value

    val userInfoLiveData: MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }



    fun isLogin(): Boolean{
        return userInfoLiveData.value?.isLogin == LoginStatus.LOGIN
    }

    fun getAccountInfo()  = viewModelScope.launch(Dispatchers.IO){
        val userInfo = UserRepository.instance.getUserInfo(mContext)
        withContext(Dispatchers.Main){
            userInfoLiveData.value = userInfo
        }
    }
}