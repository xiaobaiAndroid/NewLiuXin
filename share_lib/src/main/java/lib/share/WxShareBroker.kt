package lib.share

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory


/**
 *@author: baizf
 *@date: 2023/3/21
 */
class WxShareBroker() {


    private val APP_KEY = "wx2686f55c694547d9"

    // IWXAPI 是第三方app和微信通信的openApi接口
    private var api: IWXAPI? = null


    fun init(context: Context){
        if(api == null){
            api = WXAPIFactory.createWXAPI(context, APP_KEY)

            //建议动态监听微信启动广播进行注册到微信
            context.registerReceiver(object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {

                    // 将该app注册到微信
                    api!!.registerApp(APP_KEY)
                }
            }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP))
        }
    }

    fun handleIntent(intent: Intent?,handler: IWXAPIEventHandler){
        api?.handleIntent(intent, handler)
    }

    fun destroy(){
        api?.unregisterApp()
    }

    fun pay(orderId: String?) {
        //调起微信支付
    }

    fun sendReq(req: SendMessageToWX.Req) {
        api?.sendReq(req)
    }

    companion object {
        val instance: WxShareBroker by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            WxShareBroker()
        }
    }

    
}