package module.goods.detail

import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter
import module.common.data.entity.Banner
import module.common.data.entity.GoodsDetailImage
import module.common.utils.ImageLoadHelper


/**
 *@author: baizf
 *@date: 2023/3/19
 */
class GoodsDetailBannerAdapter(list: MutableList<GoodsDetailImage>): BannerAdapter<GoodsDetailImage, GoodsDetailBannerAdapter.BannerViewHolder>(list) {


    class BannerViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {

    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent!!.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: GoodsDetailImage?, position: Int, size: Int) {

        holder?.imageView?.let {
            ImageLoadHelper.load(it,data?.url, module.common.R.drawable.ic_default_photo)
        }

    }

}