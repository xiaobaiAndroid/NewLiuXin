package module.common.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.JsonReader

/**
 * @Description：
 * @author：Mr Bai
 * @Date： 2018/11/23
 */
object GsonUtils {
    private val mGson = Gson()
    fun toJson(`object`: Any?): String {
        return if (`object` == null) {
            "NULL"
        } else mGson.toJson(`object`)
    }

    @Throws(JsonSyntaxException::class)
    fun <T> parseObject(json: String?, tClass: Class<T>?): T {
        return mGson.fromJson(json, tClass)
    }

    fun <T> parseObjectByReader(jsonReader: JsonReader?, clazz: Class<T>?): T {
        return mGson.fromJson(jsonReader, clazz)
    }
}