package module.dynamic.home.category.list

import com.chad.library.adapter.base.entity.MultiItemEntity
import module.common.data.entity.Dynamic

class DynamicMultiEntity(override val itemType: Int) : MultiItemEntity {
    var dynamic: Dynamic? = null
    var pictures: MutableList<String>? = null

    companion object {
        /*单行：一张图片*/
        const val SINGLE_ONE_IMAGE_TXT = 1

        /*单行：两张图片*/
        const val SINGLE_TWO_IMAGE_TXT = 2

        /*单行：多张图片*/
        const val SINGLE_MORE_IMAGE_TXT = 3

        /*两行：显示图文*/
        const val TWO_ROW_IMAGE_TXT = 4

        /*单行：视频*/
        const val SINGLE_VIDEO = 5

        /*两行：视频*/
        const val TWO_ROW_VIDEO = 6
    }
}