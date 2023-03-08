package lib.share

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File

/**
 *@author: baizf
 *@date: 2023/3/8
 */
object ImageLoadHelper {

    /**
     * @describe: 下载图片到本地
     * @date: 2020/7/30
     */
    fun downloadOnly(context: Context?, imgUrl: String?, listener: DownloadListener) {
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
                    listener.success(resource)
                    return false
                }
            })
            .preload()
    }

    interface DownloadListener {
        fun success(file: File?)
        fun fail(message: String?)
    }
}