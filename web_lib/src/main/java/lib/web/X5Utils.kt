package com.liuchat.web

import android.content.Context
import com.tencent.smtt.sdk.QbSdk


/**
 * @describe: X5工具类
 * @date: 2020/4/21
 * @author: Mr Bai
 */
class X5Utils() {

    companion object{

         fun init(context: Context){
            //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

            //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
            val cb: QbSdk.PreInitCallback = object : QbSdk.PreInitCallback {
                override fun onCoreInitFinished() {

                }

                override fun onViewInitFinished(p0: Boolean) {

                }

            }
            //x5内核初始化接口
            QbSdk.initX5Environment(context.applicationContext, cb)
        }
    }

}