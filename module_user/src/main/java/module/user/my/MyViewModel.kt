package module.user.my

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import lib.upload.CloudUploadSign
import lib.upload.FileUploadHelper
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Merchant
import module.common.data.entity.Money
import module.common.data.entity.Store
import module.common.data.entity.UserInfo
import module.common.data.request.ReqParams
import module.common.data.respository.user.UserRepository
import module.common.utils.ToastUtils
import java.io.File
import java.io.FileOutputStream
import kotlin.coroutines.resume

/**
 *@author: baizf
 *@date: 2023/3/8
 */
class MyViewModel : BaseViewModel() {

    var money: Money? = null


    var merchant: Merchant? = null
    var store: Store? = null

    val mUploadProgressLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


    val mUserBackgroundUrlLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getNewUserInfo(context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val userInfo: UserInfo = UserRepository.instance.getUserInfo(context)

        userInfo.userId?.let {
            val params = ReqParams(language)
            params.setKeyValue("friendId", it)

            val dataResult: DataResult<UserInfo?> =
                UserRepository.instance.getUserExtendInfo(context, params)

            if (dataResult.status == DataResult.SUCCESS) {
                viewModelScope.launch(Dispatchers.Main) {
                    userInfoLiveData.value = dataResult.t
                }
            }
        }
    }

    fun uploadUserPhoto(context: Context, path: String) = viewModelScope.launch(Dispatchers.IO) {
        val dataResult = UserRepository.instance.getUploadSign(context)
        dataResult.t?.let {
            val cloudUploadSign = CloudUploadSign(
                it.tmpSecretId,
                it.tmpSecretKey,
                it.sessionToken,
                it.expiredTime,
                it.beginTime

            )

            val cachePath = getCachePath(path, context)

            val uploadResultEntity = upload(context, cloudUploadSign, cachePath)
            if (uploadResultEntity.status == UploadResultEntity.Status.SUCCESS) {
                val file = File(cachePath)
                if (file.exists()) {
                    file.delete()
                }
                uploadResultEntity.path?.let { path ->
                    val map: MutableMap<String, Any> = HashMap()
                    map["photo"] = path
//                    val uploadResult: DataResult<String?> =
//                        UserRepository.instance.updateUserExtendInfo(context, map, language)
//                    viewModelScope.launch(Dispatchers.Main){
//                        if (uploadResult.status == DataResult.SUCCESS) {
//                            mUserBackgroundUrlLiveData.value = uploadResult.t
//                        } else {
//                            ToastUtils.setMessage(context, uploadResult.message)
//                        }
//                    }
                }
            } else {
                viewModelScope.launch(Dispatchers.Main) {
                    ToastUtils.setMessage(context, uploadResultEntity.message)
                }

            }
        }
    }

    private fun getCachePath(path: String, context: Context): String {
        var bitmap: Bitmap? = null
        if (path.startsWith("content://")) {
            val uri = Uri.parse(path)
            context.contentResolver.openFileDescriptor(uri, "r")?.use {
                bitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
            }
        } else {
            bitmap = BitmapFactory.decodeFile(path)
        }
        val cachePath = (context.externalCacheDir?.absolutePath
            ?: context.cacheDir.absolutePath) + "/" + System.currentTimeMillis().toString() + ".jpg"
        val file = File(cachePath)
        FileOutputStream(file).use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 90, it)
        }
        return cachePath
    }

    private suspend fun upload(context: Context, cloudUploadSign: CloudUploadSign, path: String?) =
        suspendCancellableCoroutine<UploadResultEntity> { cnt ->
            FileUploadHelper.upload2(
                context, cloudUploadSign,
                "BGImage",
                path,
                object : FileUploadHelper.UploadResultListener {
                    override fun success(remoteUrl: String?) {
                        val resultEntity = UploadResultEntity(UploadResultEntity.Status.SUCCESS)
                        resultEntity.path = remoteUrl
                        cnt.resume(resultEntity)
                    }

                    override fun progress(progress: Int) {
                        mUploadProgressLiveData.value = progress
                    }

                    override fun fail(message: String?) {
                        val resultEntity = UploadResultEntity(UploadResultEntity.Status.FAIL)
                        resultEntity.message = message
                        cnt.resume(resultEntity)
                    }
                })
        }

}


