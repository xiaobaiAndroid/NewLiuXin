package module.common.data

/**
 * 数据的结果和状态
 * com.qiaocat.app.data
 * @author: Mr Bai
 * @date: 2017/11/3
 */
class DataResult<T> {
    /**
     * 结果集
     */
    var t: T? = null

    /**
     * 状态
     */
    var status = FAIL

    /**
     * 服务端返回的信息
     */
    var message = "服务端未响应"

    companion object {
        /**
         * 未登陆的标识
         */
        const val NO_LOGIN_FLAG = "\"code\":13000"

        /**
         * 成功
         */
        const val SUCCESS = 200

        /*服务端成功的标识*/
        const val SERVICE_SUCCESS = "SYS1-0001"
        const val TOKEN_PAST_LABEL = "{\"code\":\"401\",\"detail\":\"授权过期\"}"

        /*失败*/
        const val FAIL = -1

        /*余额不足*/
        const val NOT_MONEY = 201

        /*未注册*/
        const val UNREGISTER = 202

        /*token过期*/
        const val TOKEN_PAST = 401

        /*需要登录*/
        const val NEED_LOGIN = 101
    }
}