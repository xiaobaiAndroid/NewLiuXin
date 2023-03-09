package module.music

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.Music
import module.common.data.entity.MusicCategory
import module.common.utils.StringUtils

class MusicAdapter(data: MutableList<MusicMultiEntity>?) : BaseMultiItemQuickAdapter<MusicMultiEntity, BaseViewHolder>() {

    init {
        addItemType(
            MusicMultiEntity.Type.CATEGORY.ordinal,
            R.layout.music_item_library_category
        )
        addItemType(MusicMultiEntity.Type.CONTENT.ordinal, R.layout.music_item_library_content)
    }


    override fun convert(helper: BaseViewHolder, item: MusicMultiEntity) {
        if (item.itemType == MusicMultiEntity.Type.CATEGORY.ordinal) {
            val category: MusicCategory = item.category
            helper.setText(R.id.categoryNameTV, StringUtils.packNull(category.musicTypeName))
        } else {
            val music: Music = item.music
            val length = music.musicLable + "·" + music.musicTime
            helper.setText(R.id.musicNameTV, StringUtils.packNull(music.musicName))
                .setText(R.id.lengthTV, StringUtils.packNull(length))
        }
    }
}