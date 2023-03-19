package module.dynamic.home

import androidx.lifecycle.MutableLiveData
import module.common.base.BaseViewModel

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicTabHomeViewModel: BaseViewModel() {

    val cityCodeLD: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}