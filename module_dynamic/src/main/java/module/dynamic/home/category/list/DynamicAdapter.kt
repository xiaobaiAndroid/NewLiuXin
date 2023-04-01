package module.dynamic.home.category.list

import android.view.ViewGroup
import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import module.common.data.entity.Dynamic
import module.common.utils.DateUtils
import module.common.utils.ImageLoadHelper
import module.common.utils.LogUtils
import module.common.utils.StringUtils
import module.dynamic.R
import java.util.*

class DynamicAdapter(data: MutableList<DynamicMultiEntity>?) :
    BaseMultiItemQuickAdapter<DynamicMultiEntity, BaseViewHolder>(data), LoadMoreModule {
    private var pictureSize: PictureSize? = null

    init {
        addItemType(DynamicMultiEntity.SINGLE_VIDEO, R.layout.clique_item_multi_single_video)
        addItemType(
            DynamicMultiEntity.SINGLE_ONE_IMAGE_TXT,
            R.layout.clique_item_multi_single_one_picture
        )
        addItemType(
            DynamicMultiEntity.SINGLE_TWO_IMAGE_TXT,
            R.layout.clique_item_multi_single_two_picture
        )
        addItemType(
            DynamicMultiEntity.SINGLE_MORE_IMAGE_TXT,
            R.layout.clique_item_multi_single_more_picture
        )
        addItemType(DynamicMultiEntity.TWO_ROW_IMAGE_TXT, R.layout.clique_item_multi_tworow_video)
        addItemType(DynamicMultiEntity.TWO_ROW_VIDEO, R.layout.clique_item_multi_tworow_video)
    }

    protected override fun convert(helper: BaseViewHolder, item: DynamicMultiEntity) {
        when (item.itemType) {
            DynamicMultiEntity.SINGLE_VIDEO -> setVideoData(helper, item)
            DynamicMultiEntity.TWO_ROW_VIDEO -> setTwoRowVideoData(helper, item)
            DynamicMultiEntity.TWO_ROW_IMAGE_TXT -> setTwoRowImgTxtData(helper, item)
            DynamicMultiEntity.SINGLE_ONE_IMAGE_TXT -> setOnePictureData(helper, item)
            DynamicMultiEntity.SINGLE_TWO_IMAGE_TXT -> setTwoPictureData(helper, item)
            DynamicMultiEntity.SINGLE_MORE_IMAGE_TXT -> setMorePictureData(helper, item)
        }


    }

    private fun setTwoRowImgTxtData(
        helper: BaseViewHolder,
        dynamicMultiEntity: DynamicMultiEntity
    ) {
        val dynamic = dynamicMultiEntity.dynamic
        if (dynamic != null) {
            helper.setGone(R.id.playIV, true)
            setTwoRowVideoData(helper, dynamicMultiEntity)
        }
    }

    private fun setTwoRowVideoData(helper: BaseViewHolder, dynamicMultiEntity: DynamicMultiEntity) {
        val dynamic = dynamicMultiEntity.dynamic
        if (dynamic != null) {
            val avatarIV: ImageView = helper.getView<ImageView>(R.id.avatarIV)
            ImageLoadHelper.loadCircle(avatarIV, dynamic.avatar, 0)
            helper.setText(R.id.nicknameTV, StringUtils.packNull(dynamic.nickName))
                .setText(R.id.titleTV, StringUtils.packNull(dynamic.title))
                .setText(R.id.praiseTV, StringUtils.packNull(dynamic.praiseNum))
                .setText(R.id.contentTV, StringUtils.packNull(dynamic.description))
            val pictureIV: ImageView = helper.getView<ImageView>(R.id.pictureIV)
            val pictures = dynamicMultiEntity.pictures
            ImageLoadHelper.load(pictureIV, pictures?.get(0))
        }
    }

    private fun setVideoData(helper: BaseViewHolder, dynamicMultiEntity: DynamicMultiEntity) {
        val dynamic = dynamicMultiEntity.dynamic
        if (dynamic != null) {
            setCommonData(helper, dynamic)
            val pictureIV: ImageView = helper.getView<ImageView>(R.id.pictureIV)
            val layoutParams: ViewGroup.LayoutParams = pictureIV.layoutParams
            if (layoutParams.width != pictureSize!!.videoPictureSize) {
                layoutParams.width = pictureSize!!.videoPictureSize
                layoutParams.height = pictureSize!!.videoPictureSize
                pictureIV.layoutParams = layoutParams
            }
            val pictures = dynamicMultiEntity.pictures
            ImageLoadHelper.loadFitCenter(pictureIV, pictures?.get(0))
        }
    }

    private fun setMorePictureData(helper: BaseViewHolder, dynamicMultiEntity: DynamicMultiEntity) {
        val dynamic = dynamicMultiEntity.dynamic
        if (dynamic != null) {
            setCommonData(helper, dynamic)
            val pictures = dynamicMultiEntity.pictures
            val pictureIV: RoundedImageView = helper.getView(R.id.pictureIV)
            val layoutParams: ViewGroup.LayoutParams = pictureIV.layoutParams
            if (layoutParams.width != pictureSize!!.morePictureSize) {
                layoutParams.width = pictureSize!!.morePictureSize
                layoutParams.height = pictureSize!!.morePictureSize
                pictureIV.setLayoutParams(layoutParams)
            }
            ImageLoadHelper.load(pictureIV, pictures?.get(0))
            val picture1IV: RoundedImageView = helper.getView(R.id.picture1IV)
            val layoutParams1: ViewGroup.LayoutParams = picture1IV.getLayoutParams()
            if (layoutParams1.width != pictureSize!!.least) {
                layoutParams1.width = pictureSize!!.least
                layoutParams1.height = pictureSize!!.least
                picture1IV.setLayoutParams(layoutParams1)
            }
            ImageLoadHelper.load(picture1IV, pictures?.get(1))
            val picture2IV: RoundedImageView = helper.getView(R.id.picture2IV)
            val layoutParams2: ViewGroup.LayoutParams = picture2IV.getLayoutParams()
            if (layoutParams2.width != pictureSize!!.least) {
                layoutParams2.width = pictureSize!!.least
                layoutParams2.height = pictureSize!!.least
                picture2IV.setLayoutParams(layoutParams2)
            }
            ImageLoadHelper.load(picture2IV, pictures?.get(2))
            helper.setText(
                R.id.pictureNumberTV,
                kotlin.String.format(
                    context.getResources().getString(R.string.clique_format_picture_number),
                    dynamic.imageNum.toString()
                )
            )
        }
    }

    private fun setTwoPictureData(helper: BaseViewHolder, dynamicMultiEntity: DynamicMultiEntity) {
        val dynamic = dynamicMultiEntity.dynamic
        if (dynamic != null) {
            setCommonData(helper, dynamic)
            val pictures = dynamicMultiEntity.pictures
            val pictureIV: RoundedImageView = helper.getView(R.id.pictureIV)
            val layoutParams: ViewGroup.LayoutParams = pictureIV.getLayoutParams()
            if (layoutParams.width != pictureSize!!.twoPictureSize) {
                layoutParams.width = pictureSize!!.twoPictureSize
                layoutParams.height = pictureSize!!.twoPictureSize
                pictureIV.setLayoutParams(layoutParams)
            }
            ImageLoadHelper.load(pictureIV, pictures?.get(0))
            val picture1IV: RoundedImageView = helper.getView(R.id.picture1IV)
            val layoutParams1: ViewGroup.LayoutParams = picture1IV.getLayoutParams()
            if (layoutParams1.width != pictureSize!!.twoPictureSize) {
                layoutParams1.width = pictureSize!!.twoPictureSize
                layoutParams1.height = pictureSize!!.twoPictureSize
                picture1IV.setLayoutParams(layoutParams1)
            }
            ImageLoadHelper.load(picture1IV, pictures?.get(1))
        }
    }

    private fun setOnePictureData(helper: BaseViewHolder, dynamicMultiEntity: DynamicMultiEntity) {
        val dynamic = dynamicMultiEntity.dynamic
        if (dynamic != null) {
            setCommonData(helper, dynamic)
            val pictures = dynamicMultiEntity.pictures
            val pictureIV: ImageView = helper.getView<ImageView>(R.id.pictureIV)
            val layoutParams: ViewGroup.LayoutParams = pictureIV.layoutParams
            if (layoutParams.width != pictureSize!!.videoPictureSize) {
                layoutParams.width = pictureSize!!.videoPictureSize
                layoutParams.height = pictureSize!!.videoPictureSize
                pictureIV.layoutParams = layoutParams
            }
            ImageLoadHelper.load(pictureIV, pictures?.get(0))
        }
    }

    private fun setCommonData(helper: BaseViewHolder, dynamic: Dynamic) {
        val avatarIV: ImageView = helper.getView<ImageView>(R.id.avatarIV)
        ImageLoadHelper.loadCircle(avatarIV, dynamic.avatar, module.common.R.drawable.ic_default_avatar)
        helper.setText(R.id.nicknameTV, StringUtils.packNull(dynamic.nickName))
            .setText(
                R.id.dateTV,
                DateUtils.dateToString(Date(dynamic.createTime), DateUtils.FORMAT_2)
            )
            .setText(R.id.titleTV, StringUtils.packNull(dynamic.title))
            .setText(R.id.praiseTV, StringUtils.packNull(dynamic.praiseNum))
            .setText(R.id.contentTV, StringUtils.packNull(dynamic.description))
    }

    fun setPictureSize(pictureSize: PictureSize?) {
        this.pictureSize = pictureSize
    }
}