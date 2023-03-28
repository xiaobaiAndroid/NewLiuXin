package module.goods.detail

import androidx.lifecycle.MutableLiveData
import module.common.base.BaseViewModel

/**
 *@author: baizf
 *@date: 2023/3/28
 */
class DetailActFraPort: BaseViewModel() {

    val settleParamsLD: MutableLiveData<SettleParams> by lazy {
        MutableLiveData<SettleParams>()
    }

    val closeSkuViewLD: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}