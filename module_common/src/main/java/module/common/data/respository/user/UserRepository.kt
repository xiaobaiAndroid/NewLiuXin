package module.common.data.respository.user

import android.content.Context
import module.common.data.DataResult
import module.common.data.api.CommonResp
import module.common.data.entity.Contacts
import module.common.data.entity.Protocol
import module.common.data.entity.UploadSign
import module.common.data.entity.UserInfo
import module.common.data.request.ReqParams
import module.common.data.response.VerificationCodeResp
import module.common.utils.GsonUtils

/**
 * @describe: 用户仓库
 * @date: 2020/1/4
 * @author: Mr Bai
 */
class UserRepository private constructor() {
    private val mRemote: UserRemote = UserRemote()
    private val mLocal: UserLocal = UserLocal()

    /**
     * @describe: 验证码登录
     * @date: 2020/5/9
     */
    suspend fun loginByCode(
        context: Context,
        phone: String?,
        code: String?,
        recommendPhone: String?
    ): DataResult<UserInfo> {
        val dataResult = mRemote.loginByCode(phone, code, recommendPhone)
        if (dataResult.status == DataResult.SUCCESS) {
            dataResult.t?.let {
                it.isLogin = UserInfo.LoginStatus.LOGIN
                mLocal.saveOfUpdate(context, it)
                val remoteUserInfo: UserInfo = getRemoteUserInfo(context, it)
                dataResult.setT(remoteUserInfo)
            }
        }
        return dataResult
    }

    private suspend fun getRemoteUserInfo(context: Context, userInfo: UserInfo): UserInfo {
        val contactsDataResult = getContactUserInfo(context, userInfo.userId)
        val info = contactsDataResult.t
        if (info != null) {
            userInfo.userId = info.id
            userInfo.avatar = info.avatar
            userInfo.cityCode = info.cityCode
            userInfo.birthday = info.birthday
            userInfo.cityName = info.cityName
            userInfo.colleges = info.colleges
            userInfo.countyCode = info.countyCode
            userInfo.countyName = info.countyName
            userInfo.fansNum = info.fansNum
            userInfo.fullAddress = info.fullAddress
            userInfo.intro = info.intro
            userInfo.job = info.job
            userInfo.lat = info.lat
            userInfo.likeNum = info.likeNum
            userInfo.lng = info.lng
            userInfo.mobile = info.mobile
            userInfo.nickName = info.nickName
            userInfo.photo = info.photo
            userInfo.praiseNum = info.praiseNum
            userInfo.provinceCode = info.provinceCode
            userInfo.provinceName = info.provinceName
            userInfo.sex = info.sex
            userInfo.storeId = info.storeId
            userInfo.registerDate = info.createTime
        }
        mLocal.saveOfUpdate(context, userInfo)
        return userInfo
    }


    /**
     * @describe: 获取联系人的用户信息
     * @date: 2020/3/26
     */
    suspend fun getContactUserInfo(context: Context, contactId: String?): DataResult<Contacts?> {
        var dataResult: DataResult<Contacts?> =
            mRemote.getContactUserInfo(getToken(context), contactId)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            val refreshToken: String? = refreshToken(context)
            if (refreshToken == null) {
                dataResult.status = DataResult.NEED_LOGIN
            }
            dataResult = mRemote.getContactUserInfo(getToken(context), contactId)
        }
        return dataResult
    }

    suspend fun refreshToken(context: Context): String? {
        return mLocal.getLoginUserInfo(context)?.let { userInfo ->
            val dataResult: DataResult<UserInfo?> = mRemote.refreshToken(userInfo.refresh_token)
            if (dataResult.status == DataResult.SUCCESS) {
                dataResult.t?.let { newUserInfo ->
                    userInfo.refresh_token = newUserInfo.refresh_token
                    userInfo.access_token = newUserInfo.access_token
                    mLocal.saveOfUpdate(context, userInfo)
                    return userInfo.access_token
                }
            }
            return null
        }
    }

    suspend fun getToken(context: Context): String? {
        return mLocal.getLoginUserInfo(context).access_token
    }


    suspend fun sendVerificationCode(phone: String): DataResult<VerificationCodeResp> {
        return mRemote.sendVerificationCode(phone)
    }

    suspend fun loginByPsw(context: Context, phone: String, psw: String): DataResult<UserInfo> {
        val dataResult = mRemote.loginByPsw(phone, psw)
        if (dataResult.status == DataResult.SUCCESS) {
            val userInfo = dataResult.t
            if (userInfo != null) {
                userInfo.isLogin = UserInfo.LoginStatus.LOGIN
                mLocal.saveOfUpdate(context, userInfo)
                val remoteUserInfo = getRemoteUserInfo(context, userInfo)
                dataResult.t = remoteUserInfo
            }
        }
        return dataResult
    }

    suspend fun getUserInfo(context: Context): UserInfo {
        return mLocal.getLoginUserInfo(context)
    }

    suspend fun getUserExtendInfo(context: Context, params: ReqParams): DataResult<UserInfo?> {
        var dataResult: DataResult<UserInfo?> =
            mRemote.getUserExtendInfo(getToken(context), params)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            val refreshToken: String? = refreshToken(context)
            if (refreshToken == null) {
                dataResult.setStatus(DataResult.NEED_LOGIN)
            } else {
                dataResult =
                    mRemote.getUserExtendInfo(getToken(context), params)
            }
        }
        return dataResult
    }

    suspend fun getUploadSign(context: Context): DataResult<UploadSign> {
        return mRemote.getUploadSign(getToken(context))
    }

    suspend fun updateUserExtendInfo(
        context: Context,
        map: MutableMap<String, Any>,
        language: Int
    ): DataResult<String?> {
        var dataResult: DataResult<String?> =
            mRemote.updateUserExtendInfo(getToken(context), map, language)
        if (dataResult.status == DataResult.TOKEN_PAST) {
            val refreshToken: String? = refreshToken(context)
            if (refreshToken == null) {
                dataResult.setStatus(DataResult.NEED_LOGIN)
            } else {
                dataResult = mRemote.updateUserExtendInfo(getToken(context), map, language)
            }
        }
        return dataResult
    }

    suspend fun getProtocol(type: Int): DataResult<Protocol> {
        return mRemote.getProtocol(type)
    }

    suspend fun register(
        mContext: Context,
        username: String,
        code: String,
        recommendPhone: String
    ): DataResult<String> {
        return mRemote.register(username,code,recommendPhone)
    }

    companion object {
        val instance: UserRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UserRepository()
        }
    }
}