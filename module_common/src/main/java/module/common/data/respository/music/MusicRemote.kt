package module.common.data.respository.music

import module.common.data.DataResult
import module.common.data.entity.Music
import module.common.data.entity.MusicCategory
import module.common.data.request.MusicsReq
import module.common.data.response.MusicCategoryResp
import module.common.data.response.MusicsResp
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.LogUtils
import module.common.utils.URLUtils
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp

internal class MusicRemote {

    suspend fun getCategories(token: String?): DataResult<List<MusicCategory>> {
        val dataResult = DataResult<List<MusicCategory>>()
        try {
            val json = RxHttp.postJson(URLUtils.MUSIC_CATEGORY + token)
                .addAll("{}")
                .toAwaitString()
                .await()
            LogUtils.printI("http",json)
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.setStatus(DataResult.TOKEN_PAST)
            } else {
                val resp = parseObject(json, MusicCategoryResp::class.java)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.t = resp.data
                    dataResult.status = DataResult.SUCCESS
                }
            }
        } catch (e: HttpStatusCodeException){
            e.printStackTrace()
            if(e.statusCode == 401){
                dataResult.status = DataResult.TOKEN_PAST
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }

    suspend fun getMusicsByCategoryId(
        token: String?,
        req: MusicsReq?
    ): DataResult<MutableList<Music>?> {
        val dataResult = DataResult<MutableList<Music>?>()

        try {
            val json = RxHttp.postJson(URLUtils.MUSICS_BY_CATEGORY + token)
                .addAll(GsonUtils.toJson(req))
                .toAwaitString()
                .await()
            LogUtils.printI("http",json)
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.setStatus(DataResult.TOKEN_PAST)
            } else {
                val resp = parseObject(json, MusicsResp::class.java)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.t = resp.data.rows
                    dataResult.status = DataResult.SUCCESS
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataResult
    }
}