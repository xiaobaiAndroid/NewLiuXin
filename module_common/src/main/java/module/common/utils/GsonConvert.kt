package module.common.utils

/**
 *@author: baizf
 *@date: 2023/3/8
 */
import com.google.gson.*
import com.google.gson.stream.JsonReader
import java.io.Reader
import java.lang.reflect.Type


object GsonConvert {
    private fun create(): Gson {
        return GsonHolder.gson
    }

//    @Throws(JsonIOException::class, JsonSyntaxException::class)
//    fun <T> fromJson(json: String?, type: Class<T>?): T {
//        return create().fromJson(json, type)
//    }

    fun <T> fromJson(json: String?, type: Type?): T {
        return create().fromJson(json, type)
    }

//    @Throws(JsonIOException::class, JsonSyntaxException::class)
//    fun <T> fromJson(reader: JsonReader?, typeOfT: Type?): T {
//        return create().fromJson(reader, typeOfT)
//    }

//    @Throws(JsonSyntaxException::class, JsonIOException::class)
//    fun <T> fromJson(json: Reader?, classOfT: Class<T>?): T {
//        return create().fromJson(json, classOfT)
//    }

    fun toJson(src: Any?): String {
        return create().toJson(src)
    }

    fun toJson(src: Any?, typeOfSrc: Type?): String {
        return create().toJson(src, typeOfSrc)
    }

    @Throws(JsonIOException::class, JsonSyntaxException::class)
    fun <T> fromJson(json: Reader?, typeOfT: Type?): T {
        return create().fromJson(json, typeOfT)
    }




    private object GsonHolder {
        val gson = Gson()
    }
}