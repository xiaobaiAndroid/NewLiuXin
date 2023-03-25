package module.common.data.respository.wallet

import module.common.data.DataResult
import module.common.data.entity.*
import module.common.data.request.GiftsReq
import module.common.data.request.MusicsReq
import module.common.data.response.*
import module.common.utils.GsonUtils
import module.common.utils.GsonUtils.parseObject
import module.common.utils.LogUtils
import module.common.utils.URLUtils
import rxhttp.toAwaitString
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.param.RxHttp

internal class WalletRemote {

    suspend fun getCategories(token: String?): DataResult<List<MusicCategory>> {
        val dataResult = DataResult<List<MusicCategory>>()
        try {
            val json = RxHttp.postJson(URLUtils.MUSIC_CATEGORY + token)
                .addAll("{}")
                .toAwaitString()
                .await()
            LogUtils.printI("http",json)
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
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
                dataResult.status = DataResult.TOKEN_PAST
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

   suspend fun getCategory(token: String?): DataResult<MutableList<GiftCategory>?> {
        val dataResult = DataResult<MutableList<GiftCategory>?>()

        try {
            val json = RxHttp.postJson(URLUtils.GIFT_CATEGORY + token)
                .toAwaitString()
                .await()
            LogUtils.printI("http",json)
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, GiftCategoryResp::class.java)
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

   suspend fun getGiftList(token: String?, giftTypeId: Int): DataResult<MutableList<Gift>?> {
        val dataResult = DataResult<MutableList<Gift>?>()

        try {
            val giftsReq = GiftsReq()
            giftsReq.queryObj.giftTypeId = giftTypeId

            val json = RxHttp.postJson(URLUtils.GIFT_LIST + token)
                .addAll(GsonUtils.toJson(giftsReq))
                .toAwaitString()
                .await()
            LogUtils.printI("http",json)
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, GiftsResp::class.java)
                dataResult.message = resp.message.info
                if (resp.message.code == DataResult.SERVICE_SUCCESS) {
                    dataResult.t = resp.data?.rows
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

    suspend fun getVirtualMoney(token: String?): DataResult<Money> {
        val dataResult = DataResult<Money>()

        try {
            val json = RxHttp.postJson(URLUtils.WALLET + token)
                .toAwaitString()
                .await()
            LogUtils.printI("http",json)
            if (json.contains(DataResult.TOKEN_PAST_LABEL)) {
                dataResult.status = DataResult.TOKEN_PAST
            } else {
                val resp = parseObject(json, MoneyResp::class.java)
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
}