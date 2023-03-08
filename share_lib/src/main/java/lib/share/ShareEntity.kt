package lib.share

import android.graphics.Bitmap

/**
 * @describe: 分享实体
 * @date: 2020/3/9
 * @author: Mr Bai
 */
class ShareEntity {
    var title: String? = null
    var content: String? = null

    /*分享到对话还是朋友圈*/
    var scene = 0
    var videoUrl: String? = null
    var url: String? = null
    var showBitmapUrl: String? = null
    var showBitmap: Bitmap? = null
    var imgResId = 0

    /*分享类型：小程序还是视频...*/
    var contentType = Type.VIDEO

    /**
     * @describe: 分享类型
     * @date: 2020/3/19
     * @author: Mr Bai
     */
    enum class Type {
        /*视频*/
        VIDEO,  //图片
        PICTURE,  //res图片
        RES_PICTURE,  /*网页类型*/
        WEB,  /*小程序*/
        MINI_APP
    }
}