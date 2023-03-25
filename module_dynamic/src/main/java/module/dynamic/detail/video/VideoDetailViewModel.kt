package module.dynamic.detail.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.data.entity.DynamicCategory
import module.common.data.request.DynamicListReq
import module.common.data.request.EndorseReq
import module.common.data.request.ReqParams
import module.common.data.request.UpdateAttentionReq
import module.common.data.respository.dynamic.DynamicRepository
import module.common.data.respository.user.UserRepository
import module.common.data.respository.video.VideoRepository
import module.common.data.status.CommonStatus

/**
 *@author: baizf
 *@date: 2023/3/24
 */
class VideoDetailViewModel : BaseViewModel() {

    val collectResultLD: MutableLiveData<DataResult<String>> by lazy {
        MutableLiveData<DataResult<String>>()
    }

    val attentionResultLD: MutableLiveData<DataResult<String>> by lazy {
        MutableLiveData<DataResult<String>>()
    }

    val endorseResultLD: MutableLiveData<DataResult<String>> by lazy {
        MutableLiveData<DataResult<String>>()
    }

    val attentionStateLD: MutableLiveData<Int?> by lazy {
        MutableLiveData<Int?>()
    }

    val cityCodeLD: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }
    val typeIdLD: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }
    val dynamicLD: MutableLiveData<Dynamic?> by lazy {
        MutableLiveData<Dynamic?>()
    }

    fun updateEndorseStatus(dynamic: Dynamic) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val endorseReq = EndorseReq()
            endorseReq.mediaId = dynamic.id
            var praiseNum = dynamicLD.value?.praiseNum?.toInt() ?: 0
            if (dynamic.praiseStatus == CommonStatus.YET) {
                endorseReq.state = CommonStatus.NOT.toString()
                praiseNum -= 1
            } else {
                praiseNum += 1
                endorseReq.state = CommonStatus.YET.toString()
            }
            val dataResult: DataResult<String> =
                DynamicRepository.instance.endorse(mContext, endorseReq)

            withContext(Dispatchers.Main) {
                if(dataResult.status == DataResult.SUCCESS){
                    dynamicLD.value?.praiseStatus = endorseReq.state.toInt()
                    dynamicLD.value?.praiseNum = praiseNum.toString()
                }
                endorseResultLD.value = dataResult
            }
        }
    }

    fun updateCollectStatus(dynamic: Dynamic) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val endorseReq = EndorseReq()
            endorseReq.mediaId = dynamic.id

            var favoriteNum = dynamicLD.value?.favoriteNum?.toInt() ?: 0
            if (dynamic.favoriteStatus == CommonStatus.YET) {
                endorseReq.state = CommonStatus.NOT.toString()
                favoriteNum -= 1
            } else {
                endorseReq.state = CommonStatus.YET.toString()
                favoriteNum += 1
            }
            val dataResult: DataResult<String> =
                DynamicRepository.instance.collect(mContext, endorseReq)
            withContext(Dispatchers.Main) {
                if(dataResult.status == DataResult.SUCCESS){
                    dynamicLD.value?.favoriteStatus = endorseReq.state.toInt()
                    dynamicLD.value?.favoriteNum = favoriteNum.toString()
                }
                collectResultLD.value = dataResult
            }
        }
    }


    fun updateAttentionStatus(dynamic: Dynamic) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val req = UpdateAttentionReq()
            req.likeUserId = dynamic.userId

            if (dynamic.attentionUserStatus == CommonStatus.YET) {
                req.state = CommonStatus.NOT.toString()
            } else {
                req.state = CommonStatus.YET.toString()
            }
            val dataResult: DataResult<String> = UserRepository.instance.attention(mContext, req)

            withContext(Dispatchers.Main) {
                if(dataResult.status == DataResult.SUCCESS){
                    getAttentionStatusById(dynamic.userId)
                }
                attentionResultLD.value = dataResult
            }
        }
    }

    fun getAttentionStatusById(likeUserId: String?) = viewModelScope.launch(Dispatchers.IO) {
        val dataResult: DataResult<Int> =
            UserRepository.instance.getAttentionStatusById(mContext, likeUserId)
        withContext(Dispatchers.Main) {
            attentionStateLD.value = dataResult.t
            dynamicLD.value?.attentionUserStatus = attentionStateLD.value ?: CommonStatus.NOT

        }
    }
}