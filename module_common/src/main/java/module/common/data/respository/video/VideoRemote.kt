package module.common.data.respository.video

import com.google.gson.reflect.TypeToken
import module.common.base.CommonListResp
import module.common.data.DataResult
import module.common.data.api.BaseResp
import module.common.data.api.URLHelper
import module.common.data.entity.DynamicCategory
import module.common.data.entity.Dynamic
import module.common.data.entity.ImgTxtData
import module.common.data.entity.Video
import module.common.data.request.*
import module.common.data.response.CliqueCategoryResp
import module.common.data.response.DynamicListResp
import module.common.data.response.EndorseResp
import module.common.data.response.ImgTxtDataResp
import module.common.type.LanguageType
import module.common.utils.GsonConvert
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.GsonUtils.toJson
import module.common.utils.URLUtils
import org.json.JSONObject
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp

internal class VideoRemote {



    suspend fun getVideos(token: String?, req: ListReqParams): DataResult<MutableList<Video>?> {
        val dataResult = DataResult<MutableList<Video>?>()
        try {

            val url = URLHelper.instance.getFullUrl(URLUtils.CLIQUE_MEDIA_LIST)
            val json = RxHttp.postJson(url + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val type = object : TypeToken<BaseResp<CommonListResp<Video>>>() {}.type
                val resp = GsonConvert.fromJson<BaseResp<CommonListResp<Video>>>(json, type)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                    dataResult.t = resp.data?.rows
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

   suspend fun updateCollectStatus(token: String?, req: ReqParams): DataResult<String?> {
        val dataResult = DataResult<String?>()
        try {

            val url = URLHelper.instance.getFullUrl(URLUtils.COLLECT_DYNAMIC)
            val json = RxHttp.postJson(url + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val type = object : TypeToken<BaseResp<Any>>() {}.type
                val resp = GsonConvert.fromJson<BaseResp<Any>>(json, type)
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

    suspend fun updateAttentionStatus(token: String?, req: ReqParams): DataResult<String?> {
        val dataResult = DataResult<String?>()
        try {

            val url = URLHelper.instance.getFullUrl(URLUtils.ATTENTION_USER)
            val json = RxHttp.postJson(url + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val type = object : TypeToken<BaseResp<Any>>() {}.type
                val resp = GsonConvert.fromJson<BaseResp<Any>>(json, type)
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

    suspend fun updateEndorseStatus(token: String?, req: ReqParams): DataResult<String?> {
        val dataResult = DataResult<String?>()
        try {

            val url = URLHelper.instance.getFullUrl(URLUtils.ENDORSE)
            val json = RxHttp.postJson(url + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val type = object : TypeToken<BaseResp<Any>>() {}.type
                val resp = GsonConvert.fromJson<BaseResp<Any>>(json, type)
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


}