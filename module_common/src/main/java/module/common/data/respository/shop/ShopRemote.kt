package module.common.data.respository.shop

import module.common.data.DataResult
import module.common.data.entity.*
import module.common.data.request.*
import module.common.data.response.*
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.URLUtils
import org.json.JSONObject
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp

internal class ShopRemote {


   suspend fun getShopInfo(token: String?, storeId: String?): DataResult<Shop?> {
       val dataResult = DataResult<Shop?>()
       try {
           val jsonObject = JSONObject()
           jsonObject.put("id", storeId)
           val json = RxHttp.postJson(URLUtils.SHOP_INFO + token)
               .addAll(jsonObject.toString())
               .toAwaitString()
               .await()

           if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
               dataResult.status = DataResult.TOKEN_PAST
           } else {
               val resp = parseObject(
                   json,
                   ShopInfoResp::class.java
               )
               dataResult.message = resp.message.info
               if(resp.message.code == DataResult.SERVICE_SUCCESS){
                   dataResult.t = resp.data
                   dataResult.status = DataResult.SUCCESS
               }
           }
       } catch (e: HttpStatusCodeException){
           e.printStackTrace()
           if(e.statusCode == 401){
               dataResult.status = DataResult.TOKEN_PAST
           }
       } catch (e: Exception) {
           e.printStackTrace()
       }
       return dataResult
    }


}