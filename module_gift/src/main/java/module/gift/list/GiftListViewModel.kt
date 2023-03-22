package module.gift.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Gift
import module.common.data.respository.gift.GiftRepository

/**
 *@author: baizf
 *@date: 2023/3/22
 */
class GiftListViewModel: BaseViewModel() {

    val giftsResultLD: MutableLiveData<MutableList<Gift>> by lazy {
        MutableLiveData<MutableList<Gift>>()
    }

    fun getData(giftTypeId: Int)  = viewModelScope.launch(Dispatchers.IO){
        val dataResult: DataResult<MutableList<Gift>?> = GiftRepository.instance.getGiftList(mContext,giftTypeId)
        withContext(Dispatchers.Main){
            dataResult.t?.let {
                giftsResultLD.value = it
            }
        }
    }
}