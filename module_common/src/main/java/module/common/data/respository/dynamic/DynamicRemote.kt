package module.common.data.respository.dynamic

import com.google.gson.JsonSyntaxException
import module.common.data.DataResult
import module.common.data.api.URLHelper
import module.common.data.entity.CliqueCategory
import module.common.data.entity.Dynamic
import module.common.data.entity.ImgTxtData
import module.common.data.request.CliqueCategoryReq
import module.common.data.request.DynamicListReq
import module.common.data.request.EndorseReq
import module.common.data.response.CliqueCategoryResp
import module.common.data.response.DynamicListResp
import module.common.data.response.EndorseResp
import module.common.data.response.ImgTxtDataResp
import module.common.type.LanguageType
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.GsonUtils.toJson
import module.common.utils.URLUtils
import okhttp3.Response
import org.json.JSONObject
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp
import java.io.IOException

internal class DynamicRemote {


    suspend fun getCategoryData(token: String?, req: CliqueCategoryReq): DataResult<List<CliqueCategory>?> {
        val dataResult = DataResult<List<CliqueCategory>?>()
        try {
            val json = RxHttp.postJson(URLUtils.CLIQUE_CATEGORY + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(
                    json,
                    CliqueCategoryResp::class.java
                )
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                    dataResult.t = resp.data
                }
            }
        }catch (e: HttpStatusCodeException){
            e.printStackTrace()
            if(e.statusCode == 401){
                dataResult.status = DataResult.TOKEN_PAST
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

   suspend fun getDynamicData(
        token: String?,
        typeId: String?,
        cityCode: String?,
        req: DynamicListReq
    ): DataResult<List<Dynamic>?> {
       val dataResult = DataResult<List<Dynamic>?>()
       try {
           var url: String? = null
           var reqJson = toJson(req)
           if (CliqueCategory.Type.RECOMMEND == typeId) {
               url = URLUtils.DYNAMIC_RECOMMEND_LIST
           } else if (CliqueCategory.Type.FRIEND == typeId) {
               url = URLUtils.DYNAMIC_FRIEND_LIST
           } else if (CliqueCategory.Type.CITY == typeId) {
               url = URLUtils.DYNAMIC_CITY_LIST
               val queryObj = JSONObject()
               queryObj.put("type", req.queryObj.type)
               queryObj.put("cityCode", cityCode)
               val jsonObject = JSONObject(reqJson)
               jsonObject.put("queryObj", queryObj)
               jsonObject.put("pageNumber", req.pageNumber)
               jsonObject.put("pageSize", req.pageSize)
               reqJson = jsonObject.toString()
           } else {
               url = URLUtils.DYNAMIC_OTHER_LIST
               val queryObj = JSONObject()
               queryObj.put("type", req.queryObj.type)
               queryObj.put("typeId", typeId)
               val jsonObject = JSONObject(reqJson)
               jsonObject.put("queryObj", queryObj)
               jsonObject.put("pageNumber", req.pageNumber)
               jsonObject.put("pageSize", req.pageSize)
               reqJson = jsonObject.toString()
           }
           val json = RxHttp.postJson(url + token)
               .addAll(reqJson)
               .toAwaitString()
               .await()
           if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
               dataResult.status = DataResult.TOKEN_PAST
           } else {
               val resp = parseObject(json, DynamicListResp::class.java)
               dataResult.message = resp.message.info
               if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                   dataResult.t = resp.data.rows
                   dataResult.status = DataResult.SUCCESS
               }
           }
       } catch (e: HttpStatusCodeException){
           e.printStackTrace()
           if(e.statusCode == 401){
               dataResult.status = DataResult.TOKEN_PAST
           }
       } catch (e: java.lang.Exception) {
           e.printStackTrace()
       }
       return dataResult
    }

    suspend fun getImgTxtData(token: String?, dynamicId: String?): DataResult<ImgTxtData?> {
        val dataResult = DataResult<ImgTxtData?>()
        try {
            val url = URLHelper.instance.getFullUrl(URLUtils.IMGTXT_DATA, LanguageType.CN.value)
            val jsonObject = JSONObject()
            jsonObject.put("id",dynamicId)
            val json = RxHttp.postJson(url + token)
                .addAll(jsonObject.toString())
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(
                    json,
                    ImgTxtDataResp::class.java
                )
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                    dataResult.t = resp.data
                }
            }
        }catch (e: HttpStatusCodeException){
            e.printStackTrace()
            if(e.statusCode == 401){
                dataResult.status = DataResult.TOKEN_PAST
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun endorse(token: String?, endorseReq: EndorseReq): DataResult<String> {
        val dataResult = DataResult<String>()
        try {
            val url = URLHelper.instance.getFullUrl(URLUtils.ENDORSE, LanguageType.CN.value)
            val json = RxHttp.postJson(url + token)
                .addAll(GsonUtils.toJson(endorseReq))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(
                    json,
                    EndorseResp::class.java
                )
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                }
            }
        }catch (e: HttpStatusCodeException){
            e.printStackTrace()
            if(e.statusCode == 401){
                dataResult.status = DataResult.TOKEN_PAST
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun collect(token: String?, endorseReq: EndorseReq): DataResult<String> {
        val dataResult = DataResult<String>()
        try {
            val url = URLHelper.instance.getFullUrl(URLUtils.COLLECT_DYNAMIC, LanguageType.CN.value)
            val json = RxHttp.postJson(url + token)
                .addAll(GsonUtils.toJson(endorseReq))
                .toAwaitString()
                .await()
            val resp = parseObject(json, EndorseResp::class.java)
            val info = resp.message.info ?: ""
            dataResult.status = DataResult.SUCCESS
            dataResult.message = info
        }catch (e: HttpStatusCodeException){
            e.printStackTrace()
            if(e.statusCode == 401){
                dataResult.status = DataResult.TOKEN_PAST
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }


}