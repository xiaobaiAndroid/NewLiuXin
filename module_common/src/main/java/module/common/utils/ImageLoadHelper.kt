package module.common.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.io.File

/**
 * @describe: 图片加载工具类
 * @date: 2020/1/12
 * @author: Mr Bai
 */
object ImageLoadHelper {
    /*asset目录下的图片*/
    const val ASSET = "file:///android_asset/"

    //圆角
    fun loadRoundedCorners(imageView: ImageView, image_url: String?, radius: Int) {
        Glide.with(imageView.context)
            .load(image_url)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(radius, 0)))
            .into(imageView)
    }

    fun loadCircle(imageView: ImageView, image_url: String?, defaultDrawable: Int) {
        Glide.with(imageView.context)
            .load(image_url)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .error(defaultDrawable)
            .placeholder(defaultDrawable)
            .into(imageView)
    }

    fun loadCircle(imageView: ImageView, image_url: String?) {
        Glide.with(imageView.context)
            .load(image_url)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageView)
    }

    fun loadCricleBackcall(imageView: ImageView, image_url: String, defaultImage: Int) {
        LogUtils.printI("image_url=$image_url")
        Glide.with(imageView.context)
            .load(image_url)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    LogUtils.printI("加载失败 errorMsg:" + if (e != null) e.message else "null")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    LogUtils.printI("成功  Drawable Name:" + resource.javaClass.canonicalName)
                    return false
                }
            })
            .error(defaultImage)
            .into(imageView)
    }

    fun load(imageView: ImageView, url: String) {
        LogUtils.printI("url=$url")
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    fun load(imageView: ImageView, url: String?, defaultRes: Int) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(defaultRes)
            .centerCrop()
            .into(imageView)
    }

    fun load(requestOptions: RequestOptions?, imageView: ImageView?, url: String) {
        LogUtils.printI("url=$url")
        Glide.with(imageView!!)
            .load(url)
            .apply(requestOptions!!)
            .into(imageView)
    }

    fun defaultLoad(imageView: ImageView, url: String) {
        LogUtils.printI("url=$url")
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    //加载图片
    fun loadFitCenterListener(
        context: Context?,
        image_url: String?,
        imageViewTarget: ImageViewTarget<Drawable>
    ) {
        Glide.with(context!!)
            .load(image_url)
            .fitCenter()
            .into(imageViewTarget)
    }

    //加载图片
    fun loadFitCenterListener(
        context: Context?,
        image_url: String?,
        imageViewTarget: ImageViewTarget<Drawable>,
        defaultRes: Int
    ) {
        Glide.with(context!!)
            .load(image_url)
            .fitCenter()
            .placeholder(defaultRes)
            .into(imageViewTarget)
    }

    //加载图片
    fun loadFitCenter(imageView: ImageView, image_url: String?) {
        Glide.with(imageView.context)
            .load(image_url)
            .fitCenter()
            .into(imageView)
    }

    //加载图片
    fun loadFitCenter(imageView: ImageView, image_url: String?, resId: Int) {
        Glide.with(imageView.context)
            .load(image_url)
            .fitCenter()
            .placeholder(resId)
            .into(imageView)
    }

    //加载图片
    fun loadCenterInside(imageView: ImageView, image_url: String?) {
        Glide.with(imageView.context)
            .load(image_url)
            .centerInside()
            .into(imageView)
    }

    /**
     * @describe: 下载图片
     * @date: 2020/3/9
     */
    fun downBitmap(
        context: Context?,
        showBitmapUrl: String?,
        simpleTarget: SimpleTarget<Drawable>
    ) {
        Glide.with(context!!)
            .load(showBitmapUrl)
            .into(simpleTarget)
    }

    //    /**
    //     * @describe: 加载毛玻璃效果
    //     * @date: 2019/9/21
    //     */
    //    public static void loadBlurGlass(FragmentActivity activity, String picUrl, ImageView imageView) {
    //        Glide.with(activity)
    //                .load(picUrl)
    //                .centerCrop()
    //                .apply(RequestOptions.bitmapTransform(new BlurTransformation()))
    //                .error(R.drawable.default_error)
    //                .into(imageView);
    //    }
    fun loadListener(
        imageView: ImageView,
        image_url: String,
        listener: RequestListener<Drawable?>?
    ) {
        LogUtils.printI("image_url=$image_url")
        Glide.with(imageView.context)
            .load(image_url)
            .listener(listener)
            .into(imageView)
    }

    /**
     * @describe: 加载assets目录下的图片
     * @date: 2020/4/5
     */
    fun loadAssert(imageView: ImageView, assetFileName: String) {
        LogUtils.printI("assetFileName=$assetFileName")
        Glide.with(imageView.context)
            .load(ASSET + assetFileName)
            .fitCenter()
            .into(imageView)
    }

    /**
     * @describe: 下载图片到本地
     * @date: 2020/7/30
     */
    fun downloadOnly(context: Context?, imgUrl: String, listener: DownloadListener) {
        LogUtils.printI("downloadOnly=$imgUrl")
        val requestManager = Glide.with(context!!)
        val requestBuilder = requestManager.downloadOnly()
        requestBuilder.load(imgUrl)
            .listener(object : RequestListener<File?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<File?>,
                    isFirstResource: Boolean
                ): Boolean {
                    listener.fail(e!!.message)
                    return false
                }

                override fun onResourceReady(
                    resource: File?,
                    model: Any,
                    target: Target<File?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        LogUtils.printI(resource.absolutePath)
                    }
                    listener.success(resource)
                    return false
                }
            })
            .preload()
    }

    /**
     * 根据url获取图片缓存
     * Glide 4.x请调用此方法
     * 注意：此方法必须在子线程中进行
     *
     * @param context
     * @param url
     * @return
     */
    fun getCacheFileTo4x(context: Context?, url: String?): File? {
        return try {
            Glide.with(context!!).downloadOnly().load(url).submit().get()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun loadVideoFirstFrame(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(1000000) //加载第一秒的帧数作为封面
                    .centerCrop()
            )
            .load(url)
            .into(imageView)
    }

    interface DownloadListener {
        fun success(file: File?)
        fun fail(message: String?)
    }
}