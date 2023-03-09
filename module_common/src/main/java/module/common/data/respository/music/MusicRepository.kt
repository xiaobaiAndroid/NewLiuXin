package module.common.data.respository.music

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.Music
import module.common.data.entity.MusicCategory
import module.common.data.request.MusicsReq
import module.common.data.respository.user.UserRepository

class MusicRepository private constructor() {

    private val mRemote: MusicRemote
    private val mLocal: MusicLocal

    init {
        mRemote = MusicRemote()
        mLocal = MusicLocal()
    }

    suspend fun getCategories(context: Context): DataResult<List<MusicCategory>> {
        val token = UserRepository.instance.getToken(context)
        var dataResult = mRemote.getCategories(token)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            val refreshToken: String? = UserRepository.instance.refreshToken(context)
            if (refreshToken == null) {
                dataResult.setStatus(DataResult.NEED_LOGIN)
            } else {
                dataResult = mRemote.getCategories(token)
            }
        }
        return dataResult
    }

    suspend fun getMusicsByCategoryId(context: Context, req: MusicsReq): DataResult<MutableList<Music>?>{
        val token = UserRepository.instance.getToken(context)
        return mRemote.getMusicsByCategoryId(token,req)
    }


    companion object {
        val instance: MusicRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MusicRepository()
        }
    }
}