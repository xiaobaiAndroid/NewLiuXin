package module.common.utils

import java.util.regex.Pattern

object NumberUtils {
    fun isNumber(str: String?): Boolean {
        val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
        return pattern.matcher(str).matches()
    }
}