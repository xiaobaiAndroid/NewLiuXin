package module.comment

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
import module.common.data.request.CommentListReq
import module.common.data.request.CommentReq
import module.common.data.respository.comment.CommentRepository
import module.common.utils.ToastUtils

/**
 *@author: baizf
 *@date: 2023/3/21
 */
class CommentListViewModel: BaseViewModel() {

    private val req = CommentListReq()


    var totalCount = 0

    val commentsResultLD: MutableLiveData<DataResult<MutableList<Comment>>> by lazy {
        MutableLiveData<DataResult<MutableList<Comment>>>()
    }

    val dynamicLD: MutableLiveData<Dynamic?> by lazy {
        MutableLiveData<Dynamic?>()
    }

    fun getCommentList() = viewModelScope.launch(Dispatchers.IO) {
        req.queryObj?.mediaId = dynamicLD.value?.id
        val dataResult: DataResult<CommonListResp<Comment>> =
            CommentRepository.instance.getComments(mContext,req)
        val listDR = DataResult<MutableList<Comment>>()
        val commonListResp = dataResult.t
        if (commonListResp != null) {
            totalCount = commonListResp.total
            listDR.t = commonListResp.rows
        }
        listDR.status = dataResult.status
        listDR.message = dataResult.message

        withContext(Dispatchers.Main){
            commentsResultLD.value = listDR
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

    fun comment(content: String, dynamic: Dynamic?) = viewModelScope.launch(Dispatchers.IO){
        val commentReq = CommentReq()
        commentReq.content = content
        commentReq.replyUserId = dynamic!!.userId
        commentReq.mediaId = dynamic.id
        val dataResult: DataResult<String?> = CommentRepository.instance.comment(mContext,commentReq)

        withContext(Dispatchers.Main){
            if(dataResult.status == DataResult.SUCCESS){
                resetCurrentPage()
                getCommentList()
                ToastUtils.setMessage(mContext,dataResult.message)
            }else{
                ToastUtils.setMessage(mContext,dataResult.message)
            }
        }
    }
}