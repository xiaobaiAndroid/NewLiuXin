package module.common.data.respository.address

import module.common.data.DataResult
import module.common.data.entity.*
import module.common.data.response.*
import module.common.utils.GsonUtils.parseObject
import module.common.utils.LogUtils
import module.common.utils.URLUtils
import org.json.JSONObject
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp

internal class AddressRemote {

    suspend fun getCities(token: String?, code: String): DataResult<MutableList<City>?> {
        val dataResult = DataResult<MutableList<City>?>()
        try {
            val jsonObject = JSONObject()
            jsonObject.put("parentCode", code)
            val json = RxHttp.postJson(URLUtils.ADDRESS + token)
                .addAll(jsonObject.toString())
                .toAwaitString()
                .await()
            LogUtils.printI("http",json)
            val string = json.toString()
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, CitiesResp::class.java)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                    dataResult.t = resp.data
                }
            }
        } catch (e: HttpStatusCodeException){
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