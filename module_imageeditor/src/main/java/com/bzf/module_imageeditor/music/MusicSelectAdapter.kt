package com.bzf.module_imageeditor.music

import android.widget.FrameLayout
import com.bzf.module_imageeditor.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.db.entity.MusicTable
import module.common.data.entity.Music

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class MusicSelectAdapter(musics: MutableList<MusicTable>?): BaseQuickAdapter<MusicTable, BaseViewHolder>(R.layout.imageeditor_item_music_select,musics){

    override fun convert(holder: BaseViewHolder, item: MusicTable) {
        val musicItemFL = holder.getView<FrameLayout>(R.id.musicItemFL)
        if(item.selected){
            musicItemFL.setBackgroundResource(R.drawable.img_bg_music_selected)
        }else{
            musicItemFL.setBackgroundResource(0)
        }
        holder.setText(R.id.musicNameTV, item.musicName)
            .setText(R.id.musicTypeNameTV, item.musicLable)
    }
}