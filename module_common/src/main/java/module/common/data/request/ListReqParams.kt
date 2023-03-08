package module.common.data.request

import module.common.utils.GsonUtils
import kotlin.collections.HashMap

class ListReqParams(val language: Int) {

    var pageNumber: Int = 1
    var pageSize: Int = 15
    var languageMarket: Int = 0
    var cateLanguage: Int = 0

    var queryObj: MutableMap<String, Any?> = HashMap()

    init {
        setLanguage(language)
    }


    fun setLanguage(language: Int){
        languageMarket = language
        cateLanguage = language
        queryObj.put("languageMarket",language)
    }


    fun setKeyValue(key: String, value: Any?){
        queryObj.put(key,value)
    }

    fun toJsonString(): String{
        return GsonUtils.toJson(this)
    }
}