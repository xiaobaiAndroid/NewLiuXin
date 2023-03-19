package module.publish

import android.app.Activity
import android.provider.MediaStore
import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.type.MediaType
import module.common.utils.DensityUtil
import module.common.utils.ImageLoadHelper.load
import module.common.utils.StringUtils
import module.common.utils.VideoUtils.Companion.getVideoThumbnail
import module.publish.R

class MediaAdapter(activity: Activity, data: MutableList<MediaMultiEntity>?) :
    BaseMultiItemQuickAdapter<MediaMultiEntity, BaseViewHolder>(data) {
    private var imageSize = 0

    init {
        computeImageSize(activity)
        addItemType(Integer.valueOf(MediaType.IN_VIDEO), R.layout.live_publish_item_video)
        addItemType(Integer.valueOf(MediaType.MULTI_CONTENT), R.layout.live_publish_item_imgtxt)
    }

    protected override fun convert(helper: BaseViewHolder, item: MediaMultiEntity) {
        if (item.itemType == Integer.valueOf(MediaType.IN_VIDEO)) {
            val size = DensityUtil.dip2px(context, 80f)
            val videoThumbnail = getVideoThumbnail(
                StringUtils.packNull(item.path),
                size,
                size,
                MediaStore.Images.Thumbnails.MINI_KIND
            )
            if (videoThumbnail != null) {
                helper.setImageBitmap(R.id.videoIV, videoThumbnail)
            }
        } else if (item.itemType == Integer.valueOf(MediaType.MULTI_CONTENT)) {
            val pictureIV = helper.getView<ImageView>(R.id.pictureIV)
            val layoutParams = pictureIV.layoutParams
            if (layoutParams.width != imageSize) {
                layoutParams.width = imageSize
                layoutParams.height = imageSize
                val layoutParams2 = pictureIV.layoutParams
                layoutParams2.width = imageSize
                layoutParams2.height = imageSize
            }
            load(pictureIV, item.path!!)
        }
    }

    private fun computeImageSize(activity: Activity) {
        val screenWidth = DensityUtil.getScreenWidth(activity)
        val spanNumber = 3
        val spacing = activity.resources.getDimension(module.common.R.dimen.dp_12)
        val margin = activity.resources.getDimension(module.common.R.dimen.dp_12)
        imageSize = ((screenWidth - margin * 2 - spacing * (spanNumber - 1)) / spanNumber).toInt()
    }
}