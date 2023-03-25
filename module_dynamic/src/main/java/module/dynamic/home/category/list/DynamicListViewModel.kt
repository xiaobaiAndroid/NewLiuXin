package module.dynamic.home.category.list

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Dynamic
import module.common.data.entity.DynamicCategory
import module.common.data.request.DynamicListReq
import module.common.data.respository.dynamic.DynamicRepository
import module.common.type.MediaType
import module.common.utils.DensityUtil.dip2px

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class DynamicListViewModel : BaseViewModel() {

    private val req = DynamicListReq()

    val dynamicDataResultLD: MutableLiveData<DataResult<MutableList<DynamicMultiEntity>?>> by lazy {
        MutableLiveData<DataResult<MutableList<DynamicMultiEntity>?>>()
    }

    fun computePictureSize(screenWidth: Int): PictureSize {
        val pictureSize = PictureSize()
        pictureSize.videoPictureSize = screenWidth

        val usableSize = screenWidth - dip2px(mContext, 24f)
        val moreImageSize = (usableSize / 3.0 * 2).toInt()
        pictureSize.morePictureSize = moreImageSize

        val least = (moreImageSize - dip2px(mContext, 6f)) / 2
        pictureSize.least = least


        val twoImageSize = (usableSize - dip2px(mContext, 6f)) / 2
        pictureSize.twoPictureSize = twoImageSize
        return pictureSize
    }

    fun getDynamicData(
        typeId: String?,
        mediaType: String?,
        cityCode: String?
    ) = viewModelScope.launch(Dispatchers.IO) {
        mediaType ?: return@launch
        req.queryObj.type = mediaType.toInt()

        var dataResult: DataResult<MutableList<Dynamic>?>
        if (DynamicCategory.Type.RECOMMEND == typeId) {
            dataResult = DynamicRepository.instance.getRecommendDynamicData(mContext, req)
        } else if (DynamicCategory.Type.FRIEND == typeId) {
            dataResult = DynamicRepository.instance.getFriendDynamicData(mContext, req)
        } else if (DynamicCategory.Type.CITY == typeId) {
            dataResult = DynamicRepository.instance.getCityDynamicData(mContext, cityCode ?: "0", req)
        } else {
            req.queryObj.typeId = typeId
            dataResult = DynamicRepository.instance.getOtherDynamicData(mContext, req)
        }
        val list = convertDynamicData(mediaType, dataResult.t)

        withContext(Dispatchers.Main) {
            val result = DataResult<MutableList<DynamicMultiEntity>?>()
            result.status = dataResult.status
            result.t = list
            result.message = dataResult.message
            dynamicDataResultLD.value = result
        }
    }

    private fun convertDynamicData(
        mediaType: String,
        dynamics: List<Dynamic>?
    ): MutableList<DynamicMultiEntity> {
        val list: MutableList<DynamicMultiEntity> = mutableListOf()
        if (dynamics != null && !dynamics.isEmpty()) {
            for (dynamic in dynamics) {
                val coverUrl = dynamic.coverUrl
                var dynamicMultiEntity: DynamicMultiEntity? = null
                var pictureList: MutableList<String>? = null
                if (mediaType == MediaType.IN_VIDEO) {
                    dynamicMultiEntity = DynamicMultiEntity(DynamicMultiEntity.TWO_ROW_VIDEO)
                    pictureList = mutableListOf()
                    pictureList.add(coverUrl)
                } else {
                    if (!TextUtils.isEmpty(coverUrl)) {
                        pictureList =
                            coverUrl.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                                .toMutableList()
                        dynamicMultiEntity =
                            DynamicMultiEntity(DynamicMultiEntity.TWO_ROW_IMAGE_TXT)
                    }
                }
                dynamicMultiEntity?.dynamic = dynamic
                dynamicMultiEntity?.pictures = pictureList
                dynamicMultiEntity?.let {
                    list.add(dynamicMultiEntity)
                }
            }
        }
        return list
    }


    fun getOriginalDynamicData(list: MutableList<DynamicMultiEntity>): ArrayList<Dynamic> {
        val dynamics = ArrayList<Dynamic>()
        for (dynamicMultiEntity in list) {
            dynamicMultiEntity.dynamic?.let { dynamics.add(it) }
        }
        return dynamics
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
