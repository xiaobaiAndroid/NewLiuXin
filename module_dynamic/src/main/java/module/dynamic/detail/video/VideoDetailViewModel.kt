package module.dynamic.detail.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.data.request.ReqParams
import module.common.data.request.UpdateAttentionReq
import module.common.data.respository.user.UserRepository
import module.common.data.respository.video.VideoRepository
import module.common.data.status.CommonStatus

/**
 *@author: baizf
 *@date: 2023/3/24
 */
class VideoDetailViewModel: BaseViewModel() {

    val collectResultLD: MutableLiveData<DataResult<String?>> by lazy {
        MutableLiveData<DataResult<String?>>()
    }

    val attentionResultLD: MutableLiveData<DataResult<String>> by lazy {
        MutableLiveData<DataResult<String>>()
    }

    val endorseResultLD: MutableLiveData<DataResult<String?>> by lazy {
        MutableLiveData<DataResult<String?>>()
    }

    val attentionStateLD: MutableLiveData<Int?> by lazy {
        MutableLiveData<Int?>()
    }

    fun updateEndorseStatus(dynamic: Dynamic) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            val params = ReqParams(language)
            params.setKeyValue("state",dynamic.praiseStatus.toString())
            params.setKeyValue("mediaId", dynamic.id)
            params.setKeyValue("userId", UserRepository.instance.getUserInfo(mContext).userId)

            val dataResult: DataResult<String?> = VideoRepository.instance.updateEndorseStatus(mContext,params)
            withContext(Dispatchers.Main){
                endorseResultLD.value = dataResult
            }
        }
    }

    fun updateCollectStatus(dynamic: Dynamic) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            val params = ReqParams(language)
            params.setKeyValue("state",dynamic.favoriteStatus.toString())
            params.setKeyValue("mediaId", dynamic.id)
            params.setKeyValue("userId", UserRepository.instance.getUserInfo(mContext).userId)

            val dataResult: DataResult<String?> = VideoRepository.instance.updateCollectStatus(mContext,params)
            withContext(Dispatchers.Main){
                collectResultLD.value = dataResult
            }
        }
    }

    fun updateAttentionStatus(likeUserId: String?, status: Int)= viewModelScope.launch {
        withContext(Dispatchers.IO){
            val req = UpdateAttentionReq()
            req.likeUserId = likeUserId
            req.state = status.toString()

            val dataResult: DataResult<String> = UserRepository.instance.attention(mContext,req)
            withContext(Dispatchers.Main){
                attentionResultLD.value = dataResult
            }
        }
    }

    fun getAttentionStatusById(likeUserId: String?) = viewModelScope.launch(Dispatchers.IO) {
        val dataResult: DataResult<Int> =
            UserRepository.instance.getAttentionStatusById(mContext,likeUserId)
        withContext(Dispatchers.Main){
            attentionStateLD.value = dataResult.t
        }
    }
}