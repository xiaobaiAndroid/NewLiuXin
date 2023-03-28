package module.common.data.respository.goods

import com.google.gson.reflect.TypeToken
import module.common.base.CommonListResp
import module.common.data.DataResult
import module.common.data.api.BaseResp
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

internal class GoodsRemote {


    suspend fun getCategoryData(token: String?, req: CliqueCategoryReq): DataResult<List<DynamicCategory>?> {
        val dataResult = DataResult<List<DynamicCategory>?>()
        try {
            val json = RxHttp.postJson(URLUtils.CLIQUE_CATEGORY + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(
                    json,
                    CliqueCategoryResp::class.java
                )
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                    dataResult.t = resp.data
                }
            }
        }catch (e: HttpStatusCodeException){
            e.printStackTrace()
            if(e.statusCode == 401){
                dataResult.status = DataResult.TOKEN_PAST
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }


    suspend fun getComments(token: String?, req: CommentListReq): DataResult<CommonListResp<Comment>> {
        val dataResult = DataResult<CommonListResp<Comment>>()
        try {
            val json = RxHttp.postJson(URLUtils.COMMENT_LIST + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, CommentListResp::class.java)
                val info = resp.message.info
                val data = resp.data
                dataResult.t = data
                dataResult.status = DataResult.SUCCESS
                dataResult.message = info
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

    suspend fun getRecommendGoods(token: String?, req: RecommendGoodsReq): DataResult<MutableList<Goods>?> {
        val dataResult = DataResult<MutableList<Goods>?>()
        try {
            val json = RxHttp.postJson(URLUtils.RECOMMEND_GOODS + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(
                    json,
                    RecommendGoodsResp::class.java
                )
                dataResult.message = resp.message.info
                if(resp.message.code == DataResult.SERVICE_SUCCESS){
                    dataResult.t = resp.data.rows
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

   suspend fun getCategories(token: String?, cateId: String): DataResult<MutableList<GoodsCategory>?> {
       val dataResult = DataResult<MutableList<GoodsCategory>?>()
       try {
           val jsonObject = JSONObject()
           jsonObject.put("parentId", cateId.toLong())
           jsonObject.put("state", "1")
           val json = RxHttp.postJson(URLUtils.GOODS_CATEGORY + token)
               .addAll(jsonObject.toString())
               .toAwaitString()
               .await()

           if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
               dataResult.status = DataResult.TOKEN_PAST
           } else {
               val resp = parseObject(
                   json,
                   GoodsCategoryResp::class.java
               )
               dataResult.message = resp.message.info
               if(resp.message.code == DataResult.SERVICE_SUCCESS){
                   val categories = resp.data
                   val filtrateList: MutableList<GoodsCategory> = mutableListOf()
                   for (goodsCategory in categories) {
                       goodsCategory?.let {
                           if (ActivateStatus.USABLE.ordinal == Integer.valueOf(it.state)) {
                               filtrateList.add(it)
                           }
                       }
                   }
                   dataResult.t = filtrateList
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

    suspend fun getBannerById(req: BannerReq): DataResult<MutableList<Banner>?> {
        val dataResult = DataResult<MutableList<Banner>?>()
        try {
            val url = URLHelper.instance.getFullUrl(URLUtils.GOODS_BANNER)
            val json = RxHttp.postJson(url)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val type = object : TypeToken<BaseResp<MutableList<Banner>?>?>() {}.type
                val resp: BaseResp<MutableList<Banner>> = GsonConvert.fromJson(json, type)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.status = DataResult.SUCCESS
                    dataResult.t = resp.data
                }
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun getGoodsListByCateId(
        token: String?,
        req: CateGooodsListReq
    ): DataResult<MutableList<Goods>?> {

        val dataResult = DataResult<MutableList<Goods>?>()
        try {
            val json = RxHttp.postJson(URLUtils.CATEGORY_GOODS_LIST + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(
                    json,
                    GoodsListResp::class.java
                )
                dataResult.message = resp.message.info
                if(resp.message.code == DataResult.SERVICE_SUCCESS){
                    dataResult.t = resp.data?.rows
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

   suspend fun getGoodsDetail(token: String?, goodsId: String?, actId: String?): DataResult<Goods?> {
       val dataResult = DataResult<Goods?>()
       try {
           val goodsDetailReq = GoodsDetailReq()
           goodsDetailReq.id = goodsId
           goodsDetailReq.actId = actId
           val json = RxHttp.postJson(URLUtils.GOODS_DETAIL + token)
               .addAll(GsonUtils.toJson(goodsDetailReq))
               .toAwaitString()
               .await()

           if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
               dataResult.status = DataResult.TOKEN_PAST
           } else {
               val resp = parseObject(
                   json,
                   GoodsDetailResp::class.java
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