package module.common.data.entity

/**
 *@author: baizf
 *@date: 2023/3/8
 */
//{"code":"0","detail":null,"info":"帐号或密码错误"}
data class ErrorMessage(val code: String?,val detail: String?, val info: String?
) {
}