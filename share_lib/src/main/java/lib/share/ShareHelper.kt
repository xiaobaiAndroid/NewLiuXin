package lib.share

import android.content.Context
import android.graphics.Bitmap
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.openapi.WXAPIFactory


/**
 * @describe: 分享帮助类
 * @date: 2020/3/9
 * @author: baizhengfu
 */
class ShareHelper {

    companion object{
        const val APP_KEY = "wx2686f55c694547d9"


        /**
         * @describe: 分享视频
         * @date: 2020/3/9
         */
        fun video(context:Context,shareEntity:ShareEntity){
//            LogUtils.i("share------video="+ GsonUtils.toJson(shareEntity))

            val wxapi = WXAPIFactory.createWXAPI(context, APP_KEY)
            //初始化一个WXVideoObject，填写url
            //初始化一个WXVideoObject，填写url
            val video = WXVideoObject()
            video.videoUrl = shareEntity.videoUrl

//用 WXVideoObject 对象初始化一个 WXMediaMessage 对象
            //用 WXVideoObject 对象初始化一个 WXMediaMessage 对象
            val msg = WXMediaMessage(video)
            msg.title = shareEntity.title
            msg.description = shareEntity.content

            msg.thumbData = WxUtil.bmpToByteArray(shareEntity.showBitmap, false)

            //构造一个Req
            //构造一个Req
            val req = SendMessageToWX.Req()
            req.transaction = buildTransaction("video")
            req.message = msg
            req.scene = shareEntity.scene

            //调用api接口，发送数据到微信
            //调用api接口，发送数据到微信
            wxapi.sendReq(req)
        }

        private fun buildTransaction(type: String?): String {
            return if (type == null) System.currentTimeMillis().toString() else type + System.currentTimeMillis()
        }

        /**
         * @describe: 网页类型分享
         * @date: 2020/5/4
         */
        fun web(context: Context?, shareEntity: ShareEntity) {

            val wxapi = WXAPIFactory.createWXAPI(context, APP_KEY)

            //初始化一个WXWebpageObject，填写url
            val webpage = WXWebpageObject()
            webpage.webpageUrl = shareEntity.url

//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
            //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
            val msg = WXMediaMessage(webpage)
            msg.title = shareEntity.title
            //内容不能是带有html标签的文本，只能是普通文本
            msg.description = shareEntity.content
            msg.thumbData = WxUtil.bmpToByteArray(shareEntity.showBitmap, false)

            //构造一个Req
            val req = SendMessageToWX.Req()
            req.transaction = buildTransaction("webpage")
            req.message = msg
            req.scene = shareEntity.scene

            //调用api接口，发送数据到微信
            wxapi.sendReq(req)
        }


        /**
         * @describe: 分享图片
         * @date: 2020/6/23
         * @param scene 分享到朋友圈还是对话
         */
        fun shareBitmap(context: Context,scene:Int,bitmap: Bitmap){
            val wxapi = WXAPIFactory.createWXAPI(context, APP_KEY)

            //初始化 WXImageObject 和 WXMediaMessage 对象
            val imgObj = WXImageObject(bitmap)
            val msg = WXMediaMessage()
            msg.mediaObject = imgObj

            val size = 150
            //设置缩略图
            val thumbBmp =
                Bitmap.createScaledBitmap(bitmap, size, size, true)
            msg.thumbData = WxUtil.bmpToByteArray(thumbBmp, true)

            //构造一个Req
            val req = SendMessageToWX.Req()
            req.transaction = buildTransaction("img")
            req.message = msg
            req.scene = scene
//调用api接口，发送数据到微信
            wxapi.sendReq(req)
        }
    }

}