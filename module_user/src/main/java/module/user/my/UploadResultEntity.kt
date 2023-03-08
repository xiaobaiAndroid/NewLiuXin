package module.user.my

/**
 *@author: baizf
 *@date: 2023/3/8
 */
class UploadResultEntity(val status: Status) {

    var message: String? = null
    var path: String? = null

    enum class Status{
        SUCCESS,
        FAIL
    }
}