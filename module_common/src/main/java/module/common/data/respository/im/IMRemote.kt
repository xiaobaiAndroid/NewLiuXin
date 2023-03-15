package module.common.data.respository.im

import com.google.gson.reflect.TypeToken
import module.common.data.DataResult
import module.common.data.api.BaseResp
import module.common.data.api.URLHelper
import module.common.data.entity.*
import module.common.type.LanguageType
import module.common.utils.GsonConvert
import module.common.utils.URLUtils
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp

/**
 *@author: baizf
 *@date: 2023/3/7
 */
class IMRemote {


    suspend fun getFriendGroups(token: String?): DataResult<MutableList<IMFriendGroup>?> {
        val dataResult = DataResult<MutableList<IMFriendGroup>?>()
        try {
            val fullUrl = URLHelper.instance.getFullUrl(
                URLUtils.IM_CONTACTS_GROUP_LIST,
                LanguageType.CN.value
            )
            val json = RxHttp.postJson( fullUrl + token)
                .addAll("{}")
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.setStatus(DataResult.TOKEN_PAST)
            } else {
                val type = object : TypeToken<BaseResp<MutableList<IMFriendGroup?>?>?>() {}.type
                val resp: BaseResp<MutableList<IMFriendGroup>> = GsonConvert.fromJson(json, type)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    val data = resp.data
                    dataResult.t = data
                    dataResult.status = DataResult.SUCCESS
                }
            }
        }  catch (e: HttpStatusCodeException){
            e.printStackTrace()
            if(e.statusCode == 401){
                dataResult.status = DataResult.TOKEN_PAST
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }
}