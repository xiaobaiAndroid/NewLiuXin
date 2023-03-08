package module.common.data.respository.user

import com.google.gson.reflect.TypeToken
import module.common.data.DataResult
import module.common.data.api.BaseResp
import module.common.data.api.URLHelper
import module.common.data.api.URLHelper.Companion.instance
import module.common.data.entity.CliqueCategory
import module.common.data.entity.Contacts
import module.common.data.entity.UploadSign
import module.common.data.entity.UserInfo
import module.common.data.request.CliqueCategoryReq
import module.common.data.request.ReqParams
import module.common.data.response.LoginResp
import module.common.data.response.UploadSignResp
import module.common.data.response.VerificationCodeResp
import module.common.utils.GsonConvert
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.LogUtils
import module.common.utils.URLUtils
import org.json.JSONObject
import rxhttp.toAwait
import rxhttp.toAwaitString
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
            val json = RxHttp.postJson(URLUtils.LOGIN_PSW)
                .addAll(jsonObject.toString())
                .toAwaitString()
                .await()
            LogUtils.i("http",json)
            if (json.contains("{\"code\":\"401\",\"detail\":\"授权过期\"}")) {
                dataResult.setStatus(DataResult.TOKEN_PAST)
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

    suspend fun refreshToken(refreshToken: String?): DataResult<UserInfo?> {
        val jsonObject = JSONObject()
        jsonObject.put("refresh_token", refreshToken)

        return RxHttp.postJson(URLUtils.REFRESH_TOKEN)
            .addAll(jsonObject.toString())
            .toAwait<DataResult<UserInfo?>>()
            .await()
    }

    suspend fun getUserExtendInfo(token: String?, params: ReqParams): DataResult<UserInfo?> {
        val dataResult = DataResult<UserInfo?>()

        val fullUrl = URLHelper.instance.getFullUrl(URLUtils.EXTEND_USER_INFO, params.language)
        try {
            val json = RxHttp.postJson(fullUrl + token)
                .addAll(params.toJsonString())
                .toAwaitString()
                .await()

            LogUtils.i("http",json)
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.setStatus(DataResult.TOKEN_PAST)
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
            dataResult.setT(uploadSign)
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
}