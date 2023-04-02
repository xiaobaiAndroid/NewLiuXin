package module.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *@author: baizf
 *@date: 2023/3/30
 */
open class ShareLazyViewModelBase: ViewModel() {

    val positionLD: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        positionLD.value = 0
    }
}