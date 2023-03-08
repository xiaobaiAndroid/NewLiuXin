package com.yiguo.shop

import com.flyco.tablayout.listener.CustomTabEntity

class MainEntity(val unselectIcon:Int, val selectIcon:Int,  val title:String):CustomTabEntity {

    override fun getTabUnselectedIcon() = unselectIcon

    override fun getTabSelectedIcon()= selectIcon

    override fun getTabTitle()=title
}