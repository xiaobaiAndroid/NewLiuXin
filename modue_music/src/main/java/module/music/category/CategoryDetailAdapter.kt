package module.music.category

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.Music
import module.common.utils.StringUtils
import module.music.R

/**
 *@author: baizf
 *@date: 2023/3/9
 */
class CategoryDetailAdapter: BaseQuickAdapter<Music,BaseViewHolder>(R.layout.music_item_library_content,null),
    LoadMoreModule {

    override fun convert(holder: BaseViewHolder, music: Music) {
        val length = music.musicLable + "·" + music.musicTime
        holder.setText(R.id.musicNameTV, StringUtils.packNull(music.musicName))
            .setText(R.id.lengthTV, StringUtils.packNull(length))
    }
}