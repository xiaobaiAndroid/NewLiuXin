package module.music

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.MusicCategory
import module.common.data.request.MusicsReq
import module.common.data.respository.music.MusicRepository
import module.common.utils.ARouterHelper
import module.common.utils.ToastUtils

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class MusicHomeViewModel : BaseViewModel() {

//    val categoriesLiveData: MutableLiveData<List<MusicCategory>> by lazy {
//        MutableLiveData<List<MusicCategory>>()
//    }

    val musicEntitesLiveData: MutableLiveData<MutableList<MusicMultiEntity>> by lazy {
        MutableLiveData<MutableList<MusicMultiEntity>>()
    }

    fun getData(context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val categoryDataResult: DataResult<List<MusicCategory>> =
            MusicRepository.instance.getCategories(context)
        val categories = categoryDataResult.t
        val musicMultiEntities = mutableListOf<MusicMultiEntity>()

        for (category in categories) {
            val categoryMultiEntity = MusicMultiEntity(MusicMultiEntity.Type.CATEGORY.ordinal)
            categoryMultiEntity.category = category
            musicMultiEntities.add(categoryMultiEntity)
            category.mediaMusicList?.let { musicList ->
                for (music in musicList) {
                    val musicMultiEntity = MusicMultiEntity(MusicMultiEntity.Type.CONTENT.ordinal)
                    musicMultiEntity.music = music
                    musicMultiEntities.add(musicMultiEntity)
                }
            }
        }
        withContext(Dispatchers.Main) {
            if (categoryDataResult.status == DataResult.SUCCESS) {
//                categoriesLiveData.value = categories

                musicEntitesLiveData.value?.clear()
                musicEntitesLiveData.value = musicMultiEntities
            } else if (categoryDataResult.status == DataResult.NEED_LOGIN) {
                ToastUtils.setMessage(context, context.getString(R.string.music_need_anew_login))
                ARouterHelper.openPath(context, ARouterHelper.LOGIN_PSW)
            } else {
                ToastUtils.setMessage(context, categoryDataResult.message)
            }

        }
    }
}