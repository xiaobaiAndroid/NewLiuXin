package com.yiguo.shop

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import module.common.utils.DeviceUtils
import module.common.utils.LogUtils
import java.util.*


/**
 *@author: baizf
 *@date: 2023/3/5
 */
class MyApp: Application() {


    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initIM()


//        DoKit.Builder(this)
//            .productId("559111ec6e3a19c543fa91a4011e6330")
//            .build()

        initBugly()

//        OkGoHelper.getInstance().init(this)
//        LitePal.initialize(this)
        if (LogUtils.isIsOpen) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog() // 打印日志
            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        // 尽可能早,推荐在Application中初始化
        ARouter.init(this)
//        EmojiHelper.init(this)
    }

    private fun initBugly() {
        val strategy = UserStrategy(applicationContext)



        // 通过UserStrategy设置
//        strategy.deviceID = UUID.randomUUID().toString()
        strategy.deviceModel = DeviceUtils.getDeviceModel()


        //请务必确保您已经将Bugly SDK升级到满足监管新规的最新版本
        //请务必在用户授权《隐私政策》后再初始化Bugly SDK
        CrashReport.initCrashReport(applicationContext, "36dec04860", false,strategy)
        //        CrashReport.setIsDevelopmentDevice(applicationContext, true)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    private fun initIM() {
//        val userInfo = UserRepository.instance.userInfo
//        ImSDK.init(this, LoginInfo(userInfo.userId,userInfo.userId))
//        ImSDK.setSessionListener(object : SessionEventListener {
//            override fun onAvatarClicked(context: Context, message: IMMessage) {
////				Router.gotoUserCard(context, message.getFromAccount());
//            }
//
//            override fun onAvatarLongClicked(context: Context, message: IMMessage) {}
//            override fun onAckMsgClicked(context: Context, message: IMMessage) {}
//        })
//        ImSDK.setCallback(object : ImSDK.Callback {
//            override fun gotoSingleChatSetting(context: Context, view: View, userId: String) {
////				Router.gotoSingleChatSet(context, userId);
//            }
//
//            override fun gotoTeamChatSetting(context: Context, view: View, userId: String) {}
//        })
//        ImSDK.setOnlineStateContentProvider(object : OnlineStateContentProvider {
//            override fun getSimpleDisplay(account: String): String {
//                val userInfo = NIMClient.getService(UserService::class.java).getUserInfo(account)
//                return if (userInfo != null && userInfo.extensionMap != null) {
//                    Dict.getUserOnlineState(userInfo.extensionMap["onLineState"])
//                } else "[离线]"
//            }
//
//            override fun getDetailDisplay(account: String): String {
//                return getSimpleDisplay(account)
//            }
//        })
    }

}