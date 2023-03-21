package module.common.data.respository.comment

import module.common.base.CommonListResp
import module.common.data.DataResult
import module.common.data.entity.DynamicCategory
import module.common.data.entity.Comment
import module.common.data.request.CliqueCategoryReq
import module.common.data.request.CommentListReq
import module.common.data.request.CommentReq
import module.common.data.response.CliqueCategoryResp
import module.common.data.response.CommentListResp
import module.common.data.response.CommentResp
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.URLUtils
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp

internal class CommentRemote {


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

    suspend fun comment(token: String?, req: CommentReq): DataResult<String?> {
        val dataResult = DataResult<String?>()
        try {
            val json = RxHttp.postJson(URLUtils.PUBLISH_COMMENT + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()

            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, CommentResp::class.java)
                val info = resp.message.info
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


}