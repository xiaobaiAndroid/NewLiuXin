package module.common.utils

import android.text.TextUtils
import java.util.regex.Pattern

/**
 * @describe: 字符串工具类
 * @date: 2019/9/2
 * @author: Mr Bai
 */
object StringUtils {
    /**
     * @describe: 包装空字符串
     * @date:2019/9/2
     */
    fun packNull(content: String?): String {
        var content = content
        if (content == null) {
            content = ""
        }
        return content
    }

    fun isPhoneNo(phone: String?): Boolean {
        val pattern = "^\\d{8}$|^1[\\d]{10}$"
        return Pattern.matches(pattern, phone)
    }

    fun format(format: String?, number: String?): String {
        return String.format(format!!, number)
    }

    fun phonePartHide(phone: String?): String? {
        if (!TextUtils.isEmpty(phone)) {
            val start = phone!!.substring(0, 3)
            val end = phone.substring(phone.length - 4)
            return "$start***$end"
        }
        return ""
    }
}