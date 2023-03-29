package com.yiguo.shop

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.luck.picture.lib.utils.SpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import module.common.base.BaseViewModel
import module.common.utils.SPHelper

/**
 *@author: baizf
 *@date: 2023/3/7
 */
class MainViewModel(): BaseViewModel() {
    fun saveStatusHeight(statusHeight: Int)  = viewModelScope.launch(Dispatchers.IO){
        if(!SPHelper.contains(mContext,SPHelper.STATUS_HEIGHT)){
            SPHelper.put(mContext,SPHelper.STATUS_HEIGHT, statusHeight)
        }
    }


}