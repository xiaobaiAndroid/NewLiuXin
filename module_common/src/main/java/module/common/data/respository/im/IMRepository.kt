package module.common.data.respository.im

import android.content.Context
import module.common.data.DataResult
import module.common.data.entity.*
import module.common.data.respository.user.UserRepository
import module.common.utils.LogUtils

/**
 * @describe: 用户仓库
 * @date: 2020/1/4
 * @author: Mr Bai
 */
class IMRepository private constructor() {

    private val mRemote: IMRemote = IMRemote()
    private val mLocal: IMLocal = IMLocal()

    suspend fun getFriends(context: Context): DataResult<MutableList<Friend>?>{
        val result = DataResult<MutableList<Friend>?>()
        val token = UserRepository.instance.getToken(context)
        var dataResult = mRemote.getFriendGroups(token)
        if(dataResult.status == DataResult.TOKEN_PAST){
            val refreshToken = UserRepository.instance.refreshToken(context)
            if(refreshToken == null){
                dataResult.status = DataResult.NEED_LOGIN
            }else{
                dataResult = mRemote.getFriendGroups(token)
            }
        }else{
            if(dataResult.status == DataResult.SUCCESS){
                dataResult.t?.let { groups->
                    val friends = mutableListOf<Friend>()
                    for (group in groups){
                        group.userFriends?.let {
                            friends.addAll(it)
                        }
                    }
                    result.t = friends
                }
            }
        }
        result.status = dataResult.status
        result.message = dataResult.message

        return result
    }




    companion object {
        val instance: IMRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            IMRepository()
        }
    }
}