package com.bzf.module_imageeditor.music

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
        holder.setText(R.id.musicNameTV, item.musicName)
            .setText(R.id.musicTypeNameTV, item.musicLable)
    }
}