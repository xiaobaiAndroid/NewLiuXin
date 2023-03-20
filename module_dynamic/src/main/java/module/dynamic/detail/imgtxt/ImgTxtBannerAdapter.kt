package module.dynamic.detail.imgtxt

import android.app.Activity
import android.graphics.Bitmap
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.target.ImageViewTarget
import com.youth.banner.adapter.BannerAdapter
import module.common.data.entity.MediaSource
import module.common.utils.DensityUtil
import module.common.utils.ImageLoadHelper
import module.common.utils.LogUtils
import kotlin.math.max


/**
 *@author: baizf
 *@date: 2023/3/19
 */
class ImgTxtBannerAdapter(list: List<MediaSource>, val listener: Listener): BannerAdapter<MediaSource, ImgTxtBannerAdapter.BannerViewHolder>(list) {


    var isSetSize = false
    class BannerViewHolder(view: ImageView) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView

        init {
            imageView = view
        }
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

    override fun onBindView(holder: BannerViewHolder?, data: MediaSource?, position: Int, size: Int) {
        holder?.imageView?.let {
            ImageLoadHelper.loadFitCenterListener(it.context,data?.url,object: ImageViewTarget<Bitmap>(holder.imageView){

                override fun setResource(bitmap: Bitmap?) {
                    bitmap?.let {
                        holder.imageView.setImageBitmap(it)
                        if(isSetSize){
                            return@let
                        }
                        isSetSize = true

                        val screenWidth =
                            DensityUtil.getScreenWidth(holder.imageView.context as Activity)
                        val screenHeight =
                            DensityUtil.getScreenHeight(holder.imageView.context as Activity)

                        //以宽为准
                        val widthRatio =  it.width /screenWidth.toFloat()
//                        val heightRatio = it.height/screenHeight.toFloat()

//                        val ratio = max(widthRatio, heightRatio)

//                        LogUtils.printI("widthRatio=$widthRatio, heightRatio=$heightRatio")

//                        val width = it.width / ratio
                        val height = (it.height / widthRatio).toInt()

                        listener.changeSize(screenWidth,height)
                    }
                }

            })
        }

    }

    interface Listener{
        fun changeSize(width: Int, height: Int)
    }
}