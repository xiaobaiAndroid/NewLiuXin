package module.common.data.api

import module.common.type.LanguageType
import module.common.utils.URLUtils

class URLHelper private constructor(){

    companion object{
        val instance = Holder.holder
    }


    fun getFullUrl(url: String, language: Int = LanguageType.CN.value): String{
        var host: String
        if(language == LanguageType.CN.value){
            host = URLUtils.HOST_CN
        }else{
            host = URLUtils.HOST_EN
        }

        return host+url
    }


    private object Holder{
        val holder = URLHelper()

    }

}