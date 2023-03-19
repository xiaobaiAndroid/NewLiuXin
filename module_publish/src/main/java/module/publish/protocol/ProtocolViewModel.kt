package module.publish.protocol

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Protocol
import module.common.data.respository.user.UserRepository

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class ProtocolViewModel: BaseViewModel() {

    val protocolLiveData: MutableLiveData<Protocol> by lazy {
        MutableLiveData<Protocol>()
    }

    fun getProtocolData(type: Int) = viewModelScope.launch(Dispatchers.IO){
        val dataResult: DataResult<Protocol> = UserRepository.instance.getProtocol(type)
        withContext(Dispatchers.Main){
            if(dataResult.status == DataResult.SUCCESS){
                protocolLiveData.value = dataResult.t
            }
        }
    }
}