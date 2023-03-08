package lib.upload

/**
 *@author: baizf
 *@date: 2023/3/8
 */
data class CloudUploadSign(
    val tmpSecretId: String?,
     val tmpSecretKey: String?,
     val sessionToken: String?,
     val expiredTime: Long,
     val beginTime: Long
)
