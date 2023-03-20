package module.dynamic.detail.imgtxt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.base.CommonListResp
import module.common.data.DataResult
import module.common.data.entity.Comment
import module.common.data.entity.Dynamic
import module.common.data.entity.Goods
import module.common.data.entity.ImgTxtData
import module.common.data.request.CommentListReq
import module.common.data.request.EndorseReq
import module.common.data.request.RecommendGoodsReq
import module.common.data.request.UpdateAttentionReq
import module.common.data.respository.comment.CommentRepository
import module.common.data.respository.dynamic.DynamicRepository
import module.common.data.respository.goods.GoodsRepository
import module.common.data.respository.user.UserRepository
import module.common.data.status.CommonStatus
import module.common.event.entity.EGiveGift

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class ImgTxtDetailViewModel: BaseViewModel() {

    private val req = CommentListReq()


    val imgTxtResultLD: MutableLiveData<DataResult<ImgTxtData?>> by lazy {
        MutableLiveData<DataResult<ImgTxtData?>>()
    }

    val endorseDataResultLD: MutableLiveData<DataResult<String>> by lazy {
        MutableLiveData<DataResult<String>>()
    }

    val collectDataResultLD: MutableLiveData<DataResult<String>> by lazy {
        MutableLiveData<DataResult<String>>()
    }

    val attentionDataResultLD: MutableLiveData<DataResult<String>> by lazy {
        MutableLiveData<DataResult<String>>()
    }

    val attentionStateLD: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


    val commentsDataResultLD: MutableLiveData<DataResult<MutableList<Comment>>> by lazy {
        MutableLiveData<DataResult<MutableList<Comment>>>()
    }

    val recommendGoodsDataResultLD: MutableLiveData<DataResult<MutableList<Goods>?>> by lazy {
        MutableLiveData<DataResult<MutableList<Goods>?>>()
    }

    fun comment(content: String, dynamic: Dynamic?) = viewModelScope.launch(Dispatchers.IO) {

    }

    fun giveGift(userId: String?, eGiveGift: EGiveGift, selectedNumber: String?) {

    }

    fun getComments(dynamicId: String?) = viewModelScope.launch(Dispatchers.IO) {
        val queryObj = CommentListReq.QueryObj()
        queryObj.mediaId = dynamicId
        req.queryObj = queryObj
        val dataResult: DataResult<CommonListResp<Comment>> =
            CommentRepository.instance.getComments(mContext,req)
        val result = DataResult<MutableList<Comment>>()
        result.t = dataResult.t?.rows
        result.message = dataResult.message
        result.status = dataResult.status
        withContext(Dispatchers.Main){
            commentsDataResultLD.value = result
        }

    }

    fun getAttentionStatusById(likeUserId: String?) = viewModelScope.launch(Dispatchers.IO) {
        val dataResult: DataResult<Int> =
            UserRepository.instance.getAttentionStatusById(mContext,likeUserId)
        withContext(Dispatchers.Main){
            attentionStateLD.value = dataResult.t
        }
    }

    fun getRecommendGoods(categoryId: String) = viewModelScope.launch(Dispatchers.IO) {
        val recommendGoodsReq = RecommendGoodsReq()
        val queryObj = RecommendGoodsReq.QueryObj()
        recommendGoodsReq.queryObj = queryObj
        val dataResult: DataResult<MutableList<Goods>?> =
            GoodsRepository.instance.getRecommendGoods(mContext,recommendGoodsReq)
        withContext(Dispatchers.Main){
            recommendGoodsDataResultLD.value = dataResult
        }

    }

    fun endorse(dynamicId: String?) = viewModelScope.launch(Dispatchers.IO) {
        val endorseReq = EndorseReq()
        endorseReq.mediaId = dynamicId

        val imgTxtData = imgTxtResultLD.value?.t
        if (imgTxtData?.praiseStatus == CommonStatus.YET) {
            endorseReq.state = CommonStatus.NOT.toString() + ""
        } else {
            endorseReq.state = CommonStatus.YET.toString() + ""
        }
        val dataResult: DataResult<String> = DynamicRepository.instance.endorse(mContext,endorseReq)
        withContext(Dispatchers.Main){
            endorseDataResultLD.value = dataResult
        }
    }

    fun collect(dynamic: Dynamic?)  = viewModelScope.launch(Dispatchers.IO){
        val endorseReq = EndorseReq()
        endorseReq.mediaId = dynamic!!.id
        val imgTxtData = imgTxtResultLD.value?.t
        if (imgTxtData?.favoriteStatus == CommonStatus.YET) {
            endorseReq.state = CommonStatus.NOT.toString() + ""
        } else {
            endorseReq.state = CommonStatus.YET.toString() + ""
        }
        val dataResult: DataResult<String> = DynamicRepository.instance.collect(mContext,endorseReq)
        withContext(Dispatchers.Main){
            collectDataResultLD.value = dataResult
        }
    }

    fun attention(dynamic: Dynamic?)  =  viewModelScope.launch(Dispatchers.IO){
        val req = UpdateAttentionReq()
        req.likeUserId = dynamic?.userId
        if (dynamic?.attentionUserStatus == CommonStatus.YET) {
            req.state = CommonStatus.NOT.toString() + ""
        } else {
            req.state = CommonStatus.YET.toString() + ""
        }
        val dataResult: DataResult<String> = UserRepository.instance.attention(mContext,req)
        withContext(Dispatchers.Main){
            attentionDataResultLD.value = dataResult
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

    fun getImgTxtData(dynamicId: String?) = viewModelScope.launch(Dispatchers.IO) {
       val dataResult: DataResult<ImgTxtData?> =  DynamicRepository.instance.getImgTxtData(mContext,dynamicId)
        withContext(Dispatchers.Main){
            imgTxtResultLD.value = dataResult
        }
    }
}