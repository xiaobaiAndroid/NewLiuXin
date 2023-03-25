package module.common.data.respository.video

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.*
import module.common.data.request.*
import module.common.data.respository.user.UserRepository

class VideoRepository private constructor() {

    private val mRemote: VideoRemote
    private val mLocal: VideoLocal

    init {
        mRemote = VideoRemote()
        mLocal = VideoLocal()
    }

    companion object {
        val instance: VideoRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            VideoRepository()
        }
    }



    suspend fun getVideos(context: Context, req: ListReqParams): DataResult<MutableList<Video>?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult = mRemote.getVideos(token, req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.getVideos(token, req)
            }
        }
        return dataResult
    }

    suspend fun updateCollectStatus( context: Context,req: ReqParams): DataResult<String?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult = mRemote.updateCollectStatus(token, req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.updateCollectStatus(token, req)
            }
        }
        return dataResult
    }

    suspend fun updateAttentionStatus(context: Context, req: ReqParams): DataResult<String?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult = mRemote.updateAttentionStatus(token, req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.updateAttentionStatus(token, req)
            }
        }
        return dataResult
    }

    suspend fun updateEndorseStatus(context: Context, req: ReqParams): DataResult<String?> {
        val token = UserRepository.instance.getToken(context)
        var dataResult = mRemote.updateEndorseStatus(token, req)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            UserRepository.instance.refreshToken(context)?.let {
                dataResult = mRemote.updateEndorseStatus(token, req)
            }
        }
        return dataResult
    }

}