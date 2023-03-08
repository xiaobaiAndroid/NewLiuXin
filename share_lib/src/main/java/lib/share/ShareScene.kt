package lib.share

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX


/**
 * @describe: 分享方式
 * @date: 2020/6/23
 * @author: Mr Bai
 */
class ShareScene {
    companion object{

        /*会话*/
        val CHAT = SendMessageToWX.Req.WXSceneSession

        /*朋友圈*/
        val FRIEND = SendMessageToWX.Req.WXSceneTimeline
    }


}