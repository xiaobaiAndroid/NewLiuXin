package lib.share

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.TextView
import com.lxj.xpopup.core.BottomPopupView
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import java.io.File


/**
 * @describe: 分享的View
 * @date: 2020/3/9
 * @author: baizhengfu
 */
class ShareView(context: Context, val shareEntity: ShareEntity): BottomPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.share_layout_share
    }

    override fun onCreate() {
        super.onCreate()

        findViewById<TextView>(R.id.chatTV).setOnClickListener {
            shareEntity.scene = SendMessageToWX.Req.WXSceneSession
            share()
            dismiss()
        }

        findViewById<TextView>(R.id.friendTV).setOnClickListener {
            shareEntity.scene = SendMessageToWX.Req.WXSceneTimeline
            share()
            dismiss()
        }
    }

    private fun share() {
        if(shareEntity.contentType==ShareEntity.Type.RES_PICTURE){
            val bitmap = BitmapFactory.decodeResource(context.resources, shareEntity.imgResId)
            ShareHelper.shareBitmap(context, shareEntity.scene,bitmap)
        }else{

            ImageLoadHelper.downloadOnly(context,shareEntity.showBitmapUrl, object: ImageLoadHelper.DownloadListener{
                override fun fail(message: String?) {
                    ToastUtils.setMessage(context,message)
                }

                override fun success(file: File?) {
                    val bitmap = BitmapHelper.getBitmapFromFile(file?.absolutePath, 100, 100)
                    shareEntity.showBitmap = bitmap
                    if(shareEntity.contentType==ShareEntity.Type.VIDEO){
                        ShareHelper.video(context, shareEntity)
                    }else if(shareEntity.contentType==ShareEntity.Type.WEB){
                        ShareHelper.web(context, shareEntity)
                    }
                }

            })
        }
    }

}