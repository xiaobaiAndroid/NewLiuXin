package lib.svga

import android.content.Context
import android.net.http.HttpResponseCache
import android.view.View
import android.view.ViewGroup
import com.opensource.svgaplayer.*
import java.io.File
import java.net.URL

/**
 * @describe: svga动画帮助类
 * @date: 2020/3/8
 * @author: baizhengfu
 */
object SVGAHelper {

    /**
     * @describe: 必须在使用 SVGAParser 单例前初始化
     * @date: 2020/3/8
     */
    fun play(rootView: ViewGroup, url: String?) {
        val svgaImageView = SVGAImageView(rootView.context)
        svgaImageView.clearsAfterStop = true
        svgaImageView.loops = 1
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        svgaImageView.layoutParams = layoutParams
        rootView.addView(svgaImageView)

        svgaImageView.callback = object: SVGACallback{
            override fun onFinished() {
                rootView.visibility = View.GONE
            }

            override fun onPause() {

            }

            override fun onRepeat() {

            }

            override fun onStep(frame: Int, percentage: Double) {

            }
        }

        val shareParser = SVGAParser.shareParser()
        shareParser.init(rootView.context)
        shareParser.decodeFromURL(URL(url), object : SVGAParser.ParseCompletion {

            override fun onComplete(videoItem: SVGAVideoEntity) {
                rootView.visibility = View.VISIBLE
                val svgaDrawable = SVGADrawable(videoItem)
                svgaImageView.setImageDrawable(svgaDrawable)
                svgaImageView.startAnimation()
            }

            override fun onError() {
//                    ToastUtils.setMessage(rootView.context,"播放动画错误")
            }

        })
    }

    /**
     * @describe: SVGAParser 不会管理缓存，你需要自行实现缓存器。
     * @date: 2020/3/8
     */
    fun addCache(context: Context) {
            val cacheDir = File(context.applicationContext.externalCacheDir, "svga")
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128)
    }


}