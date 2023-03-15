package com.bzf.module_imageeditor.label

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Friend
import module.common.data.respository.im.IMRepository
import module.common.utils.ARouterHelper
import module.common.utils.ToastUtils

/**
 *@author: baizf
 *@date: 2023/3/14
 */
class LabelAddressSelectViewModel: BaseViewModel() {

    val friendsLiveData: MutableLiveData<MutableList<Friend>> by lazy {
        MutableLiveData<MutableList<Friend>>()
    }

    fun getData() =viewModelScope.launch(Dispatchers.IO) {
       val dataResult: DataResult<MutableList<Friend>?>  = IMRepository.instance.getFriends(mContext)
        withContext(Dispatchers.Main){
            if(dataResult.status == DataResult.SUCCESS){
                friendsLiveData.value = dataResult.t
            }else if (dataResult.status == DataResult.NEED_LOGIN){
                ToastUtils.setMessage(mContext,mContext.resources.getString(module.common.R.string.need_anew_login))
                ARouterHelper.openPath(mContext,ARouterHelper.LOGIN_PSW)
            }else{
                ToastUtils.setMessage(mContext,dataResult.message)
            }
        }
    }
}