package com.bzf.module_imageeditor.music

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bzf.module_imageeditor.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.db.entity.MusicTable
import module.common.data.entity.Music

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class MusicSelectAdapter(musics: MutableList<MusicTable>?) :
    BaseQuickAdapter<MusicTable, BaseViewHolder>(R.layout.imageeditor_item_music_select, musics) {

    override fun convert(holder: BaseViewHolder, item: MusicTable) {
        val musicItemFL = holder.getView<FrameLayout>(R.id.musicItemFL)
        val soundIV = holder.getView<ImageView>(R.id.soundIV)

        val drawable: AnimationDrawable? = soundIV.drawable as? AnimationDrawable?
        if(drawable == null){
            soundIV.setImageResource(R.drawable.img_sound_animation)
            soundIV.visibility = View.GONE
        }else{
            drawable.stop()
            soundIV.visibility = View.GONE
        }

        if (item.selected) {
            if (item.startPlay) {
                soundIV.visibility = View.VISIBLE
                drawable?.start()
            }
            musicItemFL.setBackgroundResource(R.drawable.img_bg_music_selected)
        } else {
            musicItemFL.setBackgroundResource(0)
        }
        holder.setText(R.id.musicNameTV, item.musicName)
            .setText(R.id.musicTypeNameTV, item.musicLable)
    }
}