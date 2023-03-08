package lib.upload

import com.tencent.qcloud.core.auth.BasicLifecycleCredentialProvider
import com.tencent.qcloud.core.auth.QCloudLifecycleCredentials
import com.tencent.qcloud.core.auth.SessionQCloudCredentials
import com.tencent.qcloud.core.common.QCloudClientException

/**
 * @describe: 自定义响应体授权
 * @date: 2020/3/13
 * @author: Mr Bai
 */
class MyCredentialProvider(private val uploadSign: CloudUploadSign) :
    BasicLifecycleCredentialProvider() {
    @Throws(QCloudClientException::class)

    override fun fetchNewCredentials(): QCloudLifecycleCredentials {

        // 最后返回临时密钥信息对象
        return SessionQCloudCredentials(
            uploadSign.tmpSecretId,
            uploadSign.tmpSecretKey,
            uploadSign.sessionToken,
            uploadSign.beginTime,
            uploadSign.expiredTime
        )
    }
}