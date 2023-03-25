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

    fun updateAttentionStatus(dynamic: Dynamic)= viewModelScope.launch {
        withContext(Dispatchers.IO){
            val req = UpdateAttentionReq()
            req.likeUserId = dynamic.userId
            if (dynamic.attentionUserStatus == CommonStatus.YET) {
                req.state = CommonStatus.NOT.toString() + ""
            } else {
                req.state = CommonStatus.YET.toString() + ""
            }

            val dataResult: DataResult<String> = UserRepository.instance.attention(mContext,req)
            withContext(Dispatchers.Main){
                attentionResultLD.value = dataResult
            }
        }
    }
}