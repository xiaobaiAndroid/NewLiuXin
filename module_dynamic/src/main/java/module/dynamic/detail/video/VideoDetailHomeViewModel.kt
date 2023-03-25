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
import module.common.data.entity.Video
import module.common.data.request.DynamicListReq
import module.common.data.respository.dynamic.DynamicRepository

/**
 *@author: baizf
 *@date: 2023/3/22
 */
class VideoDetailHomeViewModel: BaseViewModel() {

    val req: DynamicListReq by lazy {
        val params = DynamicListReq()
        params.queryObj.typeId = DynamicCategory.Type.RECOMMEND
        params.queryObj.type = 2
        params.queryObj.mediaStatus = 0
        params
    }

    val videosResultLD: MutableLiveData<DataResult<MutableList<Dynamic>?>> by lazy {
        MutableLiveData<DataResult<MutableList<Dynamic>?>>()
    }



    var isLoading = false

    fun getVideos() = viewModelScope.launch {
        isLoading = true
        withContext(Dispatchers.IO){
            val dataResult = DynamicRepository.instance.getRecommendDynamicData(mContext,req)
            withContext(Dispatchers.Main){
                videosResultLD.value = dataResult
                isLoading = false
            }
        }
    }

    fun isRefresh(): Boolean{
        return req.pageNumber == 1
    }

    fun setNextPage(videos: List<Video>?) {
        if(!videos.isNullOrEmpty()){
            req.pageNumber = (req.pageNumber + 1)
        }
    }



    fun getCurrentPage(): Int {
        return req.pageNumber
    }

    fun resetCurrentPage() {
        req.pageNumber = 1
    }

    fun getPageSize(): Int {
        return req.pageSize
    }

    fun setNextPage() {
        req.pageNumber += 1
    }

}