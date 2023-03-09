package module.music.category

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.Music
import module.common.data.request.MusicsReq
import module.common.data.respository.music.MusicRepository
import module.common.utils.ARouterHelper
import module.common.utils.ToastUtils
import module.music.R

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class MusicCategoryDetailViewModel: BaseViewModel() {

    val musicsLiveData: MutableLiveData<MutableList<Music>> by lazy {
        MutableLiveData<MutableList<Music>>()
    }

    val req = MusicsReq()


    fun setMusicType(musicType: String){
        val queryObj = MusicsReq.QueryObj()
        queryObj.musicType = Integer.valueOf(musicType)
        req.pageSize = 30
        req.queryObj = queryObj
    }

    fun getData(context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val dataResult: DataResult<MutableList<Music>?> =
            MusicRepository.instance.getMusicsByCategoryId(context,req)
        withContext(Dispatchers.Main) {
            if (dataResult.status == DataResult.SUCCESS) {
                req.pageNumber += 1
                musicsLiveData.value?.clear()
                musicsLiveData.value = dataResult.t
            } else if (dataResult.status == DataResult.NEED_LOGIN) {
                ToastUtils.setMessage(context, context.getString(R.string.music_need_anew_login))
                ARouterHelper.openPath(context, ARouterHelper.LOGIN_PSW)
            } else {
                ToastUtils.setMessage(context, dataResult.message)
            }

        }
    }
}