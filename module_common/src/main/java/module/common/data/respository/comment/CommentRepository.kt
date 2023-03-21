package module.common.data.respository.comment

import android.content.Context
import module.common.base.CommonListResp
import module.common.data.DataResult
import module.common.data.entity.Comment
import module.common.data.entity.UserInfo
import module.common.data.request.CommentListReq
import module.common.data.request.CommentReq
import module.common.data.respository.user.UserRepository

class CommentRepository private constructor() {


    private val mRemote: CommentRemote
    private val mLocal: CommentLocal

    init {
        mRemote = CommentRemote()
        mLocal = CommentLocal()
    }

    companion object {
        val instance: CommentRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CommentRepository()
        }
    }


    suspend fun getComments(context: Context, req: CommentListReq): DataResult<CommonListResp<Comment>> {
        return mRemote.getComments(UserRepository.instance.getToken(context), req)
    }

    suspend fun comment(context: Context,req: CommentReq): DataResult<String?> {
        val userInfo: UserInfo = UserRepository.instance.getUserInfo(context)
        req.userId = userInfo.userId
        var dataResult = mRemote.comment(UserRepository.instance.getToken(context), req)
        if(dataResult.status == DataResult.TOKEN_PAST){
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.comment(it, req)
            }
        }
        return dataResult
    }


}