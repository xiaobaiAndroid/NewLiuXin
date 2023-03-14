package com.bzf.module_imageeditor.music

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.databinding.ImgLayoutMusicLibBinding
import com.bzf.module_imageeditor.view.LinearSpaceDecoration
import com.lxj.xpopup.core.BottomPopupView
import module.common.data.db.entity.MusicTable
import module.common.utils.DensityUtil

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class MusicSelectView(context: Context, private val musics: MutableList<MusicTable>?,val mListener: Listener): BottomPopupView(context) {

    private val musicAdapter = MusicSelectAdapter(musics)

    override fun getImplLayoutId(): Int {
        return R.layout.imageeditor_layout_music_select
    }

    private var lastSelectedPosition = -1
    private val headViewCount = 1

    override fun onCreate() {
        super.onCreate()

        val contentRV = findViewById<RecyclerView>(R.id.contentRV)
        contentRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        contentRV.adapter = musicAdapter

        val musicLibBinding = ImgLayoutMusicLibBinding.inflate(LayoutInflater.from(context))
        musicAdapter.setHeaderView(musicLibBinding.root, 0,LinearLayout.HORIZONTAL)

        val spaceDecoration = LinearSpaceDecoration(
            musicAdapter,
            DensityUtil.dip2px(context, 10f),
            LinearLayoutManager.HORIZONTAL
        )
        spaceDecoration.setDrawHeader(true)
        contentRV.addItemDecoration(spaceDecoration)

        musicLibBinding.musicLibCL.setOnClickListener {
            mListener.openMusicLib()
        }
        musicAdapter.setOnItemClickListener { adapter, view, position ->
            changeSelectState(position)
        }
    }

    private fun changeSelectState(position: Int) {
        if (lastSelectedPosition == position) {
            return
        }
        val musicTable = musicAdapter.getItem(position)
        musicTable.selected = true
        musicAdapter.notifyItemChanged(position + headViewCount)
        mListener.selectedMusic(musicTable)

        if (lastSelectedPosition != -1) {
            val lastMusicTable = musicAdapter.getItem(lastSelectedPosition)
            lastMusicTable.selected = false
            musicAdapter.notifyItemChanged(lastSelectedPosition + headViewCount)
        }
        lastSelectedPosition = position
    }

    fun addMusic(musicTable: MusicTable){
        for (i in 0 until musicAdapter.data.size){
            val item = musicAdapter.getItem(i)
            if(item.selected){
                item.selected = false
                musicAdapter.notifyItemChanged(i + headViewCount)
            }
        }
        mListener.selectedMusic(musicTable)
        musicAdapter.addData(0,musicTable)
        lastSelectedPosition = 0
    }

    fun changeSelect(selectedPosition: Int) {
        if(selectedPosition >= musicAdapter.data.size){
            return
        }
        changeSelectState(selectedPosition)
    }

    fun getData(): MutableList<MusicTable> {
        return musicAdapter.data
    }

    fun updatePlayState(startPlay: Boolean) {
        if(lastSelectedPosition < 0 || lastSelectedPosition >= musicAdapter.data.size){
            return
        }
        val musicTable = musicAdapter.getItem(lastSelectedPosition)
        musicTable.startPlay = startPlay
        musicAdapter.notifyItemChanged(lastSelectedPosition + headViewCount)
    }

    interface Listener{
        fun openMusicLib()
        fun selectedMusic(musicTable: MusicTable)
    }
}