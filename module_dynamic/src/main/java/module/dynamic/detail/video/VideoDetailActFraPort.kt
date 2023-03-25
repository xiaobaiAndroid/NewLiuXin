package module.dynamic.detail.video

import androidx.lifecycle.MutableLiveData
import module.common.base.BaseViewModel

/**
 *@author: baizf
 *@date: 2023/3/24
 */
class VideoDetailActFraPort: BaseViewModel() {

    val currentPlayPositionLD: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


}