package com.bzf.module_imageeditor

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.db.entity.MusicTable
import module.common.data.entity.Music
import module.common.data.respository.music.MusicRepository

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class PictureEditViewModel: BaseViewModel() {

    val musicLiveData: MutableLiveData<MusicTable?> by lazy {
        MutableLiveData<MusicTable?>()
    }

    val musicAllLiveData: MutableLiveData<MutableList<MusicTable>?> by lazy {
        MutableLiveData<MutableList<MusicTable>?>()
    }


    val musicExitPosition: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


    fun addMusic(context: Context, music: Music, adapterList: MutableList<MusicTable>?) = viewModelScope.launch(Dispatchers.IO) {
        adapterList?.let {musicTables->
            for ( i in 0 until musicTables.size){
                val musicTable = musicTables[i]
                if(musicTable.musicId == music.id){
                    musicExitPosition.value = i
                    return@launch
                }
            }
        }
        val musicTable =  MusicRepository.instance.addMusic(context,music)
        withContext(Dispatchers.Main){
            musicTable?.let {
                it.selected = true
                musicLiveData.value = it
            }
        }
    }

    fun queryAllLocalMusic(context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val musicTableList: MutableList<MusicTable>? =  MusicRepository.instance.queryAllLocalMusic(context)
        withContext(Dispatchers.Main){
            musicTableList?.let {
                musicAllLiveData.value = it
            }
        }
    }
}