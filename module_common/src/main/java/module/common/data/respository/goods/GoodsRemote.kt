package module.common.data.respository.goods

import com.google.gson.JsonSyntaxException
import module.common.base.CommonListResp
import module.common.data.DataResult
import module.common.data.entity.CliqueCategory
import module.common.data.entity.Comment
import module.common.data.entity.Goods
import module.common.data.request.CliqueCategoryReq
import module.common.data.request.CommentListReq
import module.common.data.request.RecommendGoodsReq
import module.common.data.response.CliqueCategoryResp
import module.common.data.response.CommentListResp
import module.common.data.response.RecommendGoodsResp
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.GsonUtils.toJson
import module.common.utils.URLUtils
import okhttp3.Response
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp
import java.io.IOException

internal class GoodsRemote {


    suspend fun getCategoryData(token: String?, req: CliqueCategoryReq): DataResult<List<CliqueCategory>?> {
        val dataResult = DataResult<List<CliqueCategory>?>()
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
                dataResult.t = resp.data.rows
                dataResult.status = DataResult.SUCCESS
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