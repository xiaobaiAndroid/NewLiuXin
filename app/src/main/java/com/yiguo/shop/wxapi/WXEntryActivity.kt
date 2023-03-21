package com.yiguo.shop.wxapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.yiguo.shop.MainActivity
import lib.share.WxShareBroker
import module.common.utils.GsonUtils.toJson
import module.common.utils.LogUtils

class WXEntryActivity : AppCompatActivity(), IWXAPIEventHandler {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.wxShareBroker.init(this)
        MainActivity.wxShareBroker.handleIntent(intent,this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        MainActivity.wxShareBroker.handleIntent(intent,this)
    }

    override fun onReq(baseReq: BaseReq?) {
        LogUtils.printI("onReq----baseReq=" + toJson(baseReq))
    }

    override fun onResp(baseResp: BaseResp?) {
        LogUtils.printI("onResp----baseResp=" + toJson(baseResp))
        onBackPressed()
    }
}