package module.common.data.respository.shopcart

import com.google.gson.reflect.TypeToken
import module.common.base.CommonListResp
import module.common.data.DataResult
import module.common.data.api.BaseResp
import module.common.data.api.CommonResp
import module.common.data.api.URLHelper
import module.common.data.entity.*
import module.common.data.request.*
import module.common.data.response.*
import module.common.status.ActivateStatus
import module.common.utils.GsonConvert
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.URLUtils
import org.json.JSONObject
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp

internal class ShopCartRemote {


    suspend fun addShoppingCart(token: String?, req: AddShoppingCartReq): DataResult<String?> {
        val dataResult = DataResult<String?>()
        try {
            val json = RxHttp.postJson(URLUtils.ADD_SHOPPING_CART + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(
                    json,
                    CommonResp::class.java
                )
                dataResult.message = resp.message.info
                if(resp.message.code == DataResult.SERVICE_SUCCESS){
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