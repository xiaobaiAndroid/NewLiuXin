package com.yiguo.shop

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yiguo.shop.databinding.ActivityMainBinding
import module.common.base.BaseActivity
import module.common.event.MessageEvent
import module.common.utils.ARouterHelper
import module.common.utils.LogUtils

@Route(path = ARouterHelper.MAIN)
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    val defaultPosition = 2

    override fun createViewModel(): MainViewModel {
        return viewModels<MainViewModel>().value
    }

    override fun getBindingView() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {

        val titles = resources.getStringArray(R.array.main_app_home)
        val arr = arrayListOf<CustomTabEntity>(MainEntity(R.drawable.ic_home_tab_im_select, R.drawable.ic_home_tab_im_select, titles.get(0))
            ,MainEntity(R.drawable.ic_home_tab_clique, R.drawable.ic_home_tab_clique_select, titles[1])
            ,MainEntity(R.drawable.ic_home_tab_goods, R.drawable.ic_home_tab_goods_select, titles[2])
            ,MainEntity(R.drawable.ic_home_tab_nearby, R.drawable.ic_home_tab_nearby_select, titles[3])
            ,MainEntity(R.drawable.ic_home_tab_my, R.drawable.ic_home_tab_my_select, titles[4])
        )
        binding.ctl.setTabData(arr)


        binding.ctl.setOnTabSelectListener(object: OnTabSelectListener {

            var lastPosition = defaultPosition

            override fun onTabSelect(position: Int) {
                if(isLogin){
                    binding.viewPager.setCurrentItem(position,false)
                    lastPosition = position
                }else{
                    if (position==0 || position==4){
                        binding.ctl.currentTab = lastPosition
                        ARouterHelper.openBottomToTop(this@MainActivity, ARouterHelper.LOGIN_PSW)
                    }else{
                        binding.viewPager.setCurrentItem(position,false)
                        lastPosition = position
                    }

                }
            }

            override fun onTabReselect(position: Int) {
                LogUtils.i("onTabReselect---position="+position)
            }

        })

        binding.viewPager.adapter = MainAdapter(supportFragmentManager, titles.size)
        binding.viewPager.offscreenPageLimit= titles.size

        binding.ctl.currentTab = defaultPosition
        binding.viewPager.setCurrentItem(defaultPosition,false)
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if(event?.type == MessageEvent.Type.MAIN_GOODS_PAGE){
            binding.ctl.currentTab = defaultPosition
            binding.viewPager.setCurrentItem(defaultPosition,false)
        }
    }

}