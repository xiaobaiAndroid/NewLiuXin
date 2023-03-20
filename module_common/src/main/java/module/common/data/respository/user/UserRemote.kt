package module.common.data.respository.user

import com.google.gson.reflect.TypeToken
import module.common.data.DataResult
import module.common.data.api.BaseResp
import module.common.data.api.URLHelper
import module.common.data.api.URLHelper.Companion.instance
import module.common.data.entity.*
import module.common.data.request.CliqueCategoryReq
import module.common.data.request.ReqParams
import module.common.data.request.UpdateAttentionReq
import module.common.data.response.*
import module.common.type.LanguageType
import module.common.utils.GsonConvert
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.LogUtils
import module.common.utils.URLUtils
import org.json.JSONObject
import rxhttp.toAwait
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp
import java.lang.reflect.Type

/**
 *@author: baizf
 *@date: 2023/3/7
 */
class UserRemote {

    suspend fun getLifeCliqueCategoryData(
        token: String,
        req: CliqueCategoryReq
    ): DataResult<List<CliqueCategory>> {
        return RxHttp.postJson(URLUtils.CLIQUE_CATEGORY + token)
            .addAll(GsonUtils.toJson(req))
            .toAwait<DataResult<List<CliqueCategory>>>()
            .await()
    }

    suspend fun loginByPsw(phone: String?, psw: String?): DataResult<UserInfo> {
        val dataResult = DataResult<UserInfo>()

        try {
            val jsonObject = JSONObject()
            jsonObject.put("account", phone)
            jsonObject.put("password", psw)
            jsonObject.put("userAccountType", 4)
            val json = RxHttp.postJson(URLUtils.LOGIN_PSW)
                .addAll(jsonObject.toString())
                .toAwaitString()
                .await()
            LogUtils.printI("http",json)
            if (json.contains("{\"code\":\"401\",\"detail\":\"授权过期\"}")) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = GsonUtils.parseObject(json, LoginResp::class.java)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                    dataResult.t = resp.data
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun sendVerificationCode(phone: String): DataResult<VerificationCodeResp> {
        val jsonObject = JSONObject()
        jsonObject.put("mobile", phone)
        return RxHttp.postJson(URLUtils.SEND_VERIFICATION_CODE)
            .addAll(jsonObject.toString())
            .toAwait<DataResult<VerificationCodeResp>>()
            .await()
    }

    suspend fun loginByCode(
        phone: String?,
        code: String?,
        recommendPhone: String?
    ): DataResult<UserInfo> {

        val jsonObject = JSONObject()
        jsonObject.put("mobile", phone)
        jsonObject.put("code", code)
        jsonObject.put("remandUserMobile", recommendPhone)

        return RxHttp.postJson(URLUtils.LOGIN_CODE)
            .addAll(jsonObject.toString())
            .toAwait<DataResult<UserInfo>>()
            .await()
    }

    suspend fun getContactUserInfo(token: String?, contactId: String?): DataResult<Contacts?> {
        val jsonObject = JSONObject()
        jsonObject.put("friendId", contactId)

        return RxHttp.postJson(URLUtils.CONTACTS + token)
            .addAll(jsonObject.toString())
            .toAwait<DataResult<Contacts?>>()
            .await()
    }

    suspend fun refreshToken(refreshToken: String?): DataResult<Token> {
        val dataResult = DataResult<Token>()
        try {
            val jsonObject = JSONObject()
            jsonObject.put("refresh_token", refreshToken)

            val url = URLHelper.instance.getFullUrl(URLUtils.REFRESH_TOKEN)
            val json = RxHttp.postJson(url)
                .addAll(jsonObject.toString())
                .toAwaitString()
                .await()
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.NEED_LOGIN
            } else {
                val resp = parseObject(json, RefreshTokenResp::class.java)
                val info = resp.message.info ?: ""
                dataResult.status = DataResult.SUCCESS
                dataResult.message = info
                dataResult.t = resp.data
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

    suspend fun getUserExtendInfo(token: String?, params: ReqParams): DataResult<UserInfo?> {
        val dataResult = DataResult<UserInfo?>()

        val fullUrl = URLHelper.instance.getFullUrl(URLUtils.EXTEND_USER_INFO, params.language)
        try {
            val json = RxHttp.postJson(fullUrl + token)
                .addAll(params.toJsonString())
                .toAwaitString()
                .await()

            LogUtils.printI("http",json)
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val type: Type = object : TypeToken<BaseResp<UserInfo>>() {}.type
                val resp: BaseResp<UserInfo> = GsonConvert.fromJson(json, type)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                    dataResult.t = resp.data
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun getUploadSign(token: String?): DataResult<UploadSign> {
        val dataResult = DataResult<UploadSign>()
        try {
            val json = RxHttp.postJson(URLUtils.UPLOAD_SIGN + token)
                .toAwaitString()
                .await()
            val resp = parseObject(json, UploadSignResp::class.java)
            val info = resp.message.info
            dataResult.status = DataResult.SUCCESS
            dataResult.message = info
            val uploadSign = resp.data
            dataResult.t = uploadSign
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun updateUserExtendInfo(
        token: String?,
        map: MutableMap<String, Any>,
        language: Int
    ): DataResult<String?> {
        val fullUrl = instance.getFullUrl(URLUtils.UPDATE_EXTEND_USER_INFO, language)
        return RxHttp.postJson(fullUrl + token)
            .toAwait<DataResult<String?>>()
            .await()
    }

    suspend fun getProtocol(type: Int): DataResult<Protocol> {
        val dataResult = DataResult<Protocol>()
        val jsonObject = JSONObject()
        jsonObject.put("id", type)
        try {
            val json = RxHttp.postJson(URLUtils.PROTOCOL)
                .addAll(jsonObject.toString())
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, ProtocolResp::class.java)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.t = resp.data
                    dataResult.status = DataResult.SUCCESS
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun register(username: String, code: String, recommendPhone: String): DataResult<String> {
        val dataResult = DataResult<String>()
        val jsonObject = JSONObject()
        jsonObject.put("account", username)
        jsonObject.put("code", code)
        jsonObject.put("password", code)
        jsonObject.put("userAccountType", 3)
        try {
            val json = RxHttp.postJson(URLUtils.PROTOCOL)
                .addAll(jsonObject.toString())
                .toAwaitString()
                .await()


            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, LoginResp::class.java)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun attention(token: String?, req: UpdateAttentionReq): DataResult<String> {
        val dataResult = DataResult<String>()
        try {
            val url = URLHelper.instance.getFullUrl(URLUtils.ATTENTION)
            val json = RxHttp.postJson(url + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, EndorseResp::class.java)
                val info = resp.message.info ?: ""
                dataResult.status = DataResult.SUCCESS
                dataResult.message = info
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

    suspend fun getAttentionStatusById(
        token: String?,
        userId: String?,
        likeUserId: String?
    ): DataResult<Int> {
        val dataResult = DataResult<Int>()
        try {
            val jsonObject = JSONObject()
            jsonObject.put("userId", userId)
            jsonObject.put("likeUserId", likeUserId)
            val url = URLHelper.instance.getFullUrl(URLUtils.ATTENTION_STATUS)
            val json = RxHttp.postJson(url + token)
                .addAll(jsonObject.toString())
                .toAwaitString()
                .await()
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, AttentionResp::class.java)
                val info = resp.message.info ?: ""
                dataResult.status = DataResult.SUCCESS
                dataResult.message = info
                dataResult.t = resp.data.state
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