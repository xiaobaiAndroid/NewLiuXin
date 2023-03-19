package module.publish

import com.chad.library.adapter.base.entity.MultiItemEntity

class MediaMultiEntity(override val itemType: Int) : MultiItemEntity {
    var path: String? = null

}