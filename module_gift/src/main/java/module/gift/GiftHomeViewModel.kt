package module.gift

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.GiftCategory
import module.common.data.entity.Money
import module.common.data.request.GiveGiftReq
import module.common.data.respository.gift.GiftRepository
import module.common.data.respository.wallet.WalletRepository

/**
 *@author: baizf
 *@date: 2023/3/22
 */
class GiftHomeViewModel: BaseViewModel() {

    val balanceResultLD: MutableLiveData<Money> by lazy {
        MutableLiveData<Money>()
    }

    val categoriesResultLD: MutableLiveData<MutableList<GiftCategory>> by lazy {
        MutableLiveData<MutableList<GiftCategory>>()
    }


    fun getAccountBalance() = viewModelScope.launch(Dispatchers.IO){
        val dataResult: DataResult<Money> = WalletRepository.instance.getVirtualMoney(mContext)

        withContext(Dispatchers.Main){
            if (dataResult.status == DataResult.SUCCESS){
                balanceResultLD.value = dataResult.t
            }
        }

    }

    fun getGiftCategory() = viewModelScope.launch(Dispatchers.IO){
        val dataResult: DataResult<MutableList<GiftCategory>?> = GiftRepository.instance.getCategory(mContext)
        withContext(Dispatchers.Main){
            dataResult.t?.let {
                categoriesResultLD.value = it
            }
        }
    }




}