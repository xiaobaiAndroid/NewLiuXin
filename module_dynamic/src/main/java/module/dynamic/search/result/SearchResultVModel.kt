package module.dynamic.search.result

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.data.request.SearchReq
import module.common.data.respository.dynamic.DynamicRepository
import module.common.type.MediaType
import module.dynamic.home.category.list.DynamicMultiEntity
import java.util.*

/**
 *@author: baizf
 *@date: 2023/3/26
 */
class SearchResultVModel: BaseViewModel() {

    private val req = SearchReq()

    val searResultLD: MutableLiveData<DataResult<MutableList<DynamicMultiEntity>>> by lazy {
        MutableLiveData<DataResult<MutableList<DynamicMultiEntity>>>()
    }

    fun search(keyword: String?) = viewModelScope.launch(Dispatchers.IO){
        req.queryObj.title = keyword
        val dataResult: DataResult<MutableList<Dynamic>?> =
            DynamicRepository.instance.searchByKeyword(mContext,req)
        val list: MutableList<DynamicMultiEntity> = convertDynamicData(dataResult.t)
        val localDR = DataResult<MutableList<DynamicMultiEntity>>()
        localDR.t = list
        localDR.status = dataResult.status
        localDR.message = dataResult.message
        withContext(Dispatchers.Main){
            searResultLD.value = localDR
        }
    }

    fun getOriginalDynamicData(data: MutableList<DynamicMultiEntity>) {

    }


    private suspend fun convertDynamicData(dynamics: List<Dynamic>?): MutableList<DynamicMultiEntity> {
        val list: MutableList<DynamicMultiEntity> = mutableListOf()
        if (dynamics != null && dynamics.isNotEmpty()) {
            for (dynamic in dynamics) {
                val coverUrl = dynamic.coverUrl
                var dynamicMultiEntity: DynamicMultiEntity? = null
                var pictureList: MutableList<String?>? = null
                if (dynamic.type == Integer.valueOf(MediaType.IN_VIDEO)) {
                    dynamicMultiEntity = DynamicMultiEntity(DynamicMultiEntity.TWO_ROW_VIDEO)
                    pictureList = ArrayList()
                    pictureList.add(coverUrl)
                } else {
                    if (!TextUtils.isEmpty(coverUrl)) {
                        val pictures =
                            coverUrl.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                                .toTypedArray()
                        pictureList = pictures.toMutableList()
                        dynamicMultiEntity =
                            DynamicMultiEntity(DynamicMultiEntity.TWO_ROW_IMAGE_TXT)
                    }
                }
                dynamicMultiEntity?.let {
                    it.dynamic = dynamic
                    it.pictures = pictureList
                    list.add(it)
                }

            }
        }
        return list
    }

    fun getCurrentPage(): Int {
        return req.pageNumber
    }

    fun resetCurrentPage() {
        req.pageNumber = 1
    }

    fun getPageSize(): Int {
        return req.pageSize
    }

    fun setNextPage() {
        req.pageNumber += 1
    }
}