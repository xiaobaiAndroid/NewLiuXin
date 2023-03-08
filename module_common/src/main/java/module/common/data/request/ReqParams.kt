package module.common.data.request

import module.common.utils.GsonUtils

/*
* @describe: 请求参数
* @author: bzf
* @date: 12/23/20
*/
class ReqParams(val language: Int) {

    var queryObj: MutableMap<String, Any?> = HashMap()

    init {
        setLanguage(language)
    }

    fun setLanguage(language: Int){
        queryObj.put("languageMarket",language)
    }


    fun setKeyValue(key: String, value: Any?){
        queryObj.put(key,value)
    }

    fun toJsonString(): String{
        return GsonUtils.toJson(queryObj)
    }
}