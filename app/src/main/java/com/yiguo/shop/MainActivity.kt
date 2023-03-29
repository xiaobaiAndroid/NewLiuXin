package com.yiguo.shop

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yiguo.shop.databinding.ActivityMainBinding
import lib.share.WxShareBroker
import module.common.base.BaseActivity
import module.common.event.MessageEvent
import module.common.utils.ARouterHelper
import module.common.utils.LogUtils
import module.common.utils.StatusBarUtils
import java.util.concurrent.atomic.AtomicReference

@Route(path = ARouterHelper.MAIN)
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    val defaultPosition = 2

    override fun createViewModel(): MainViewModel {
        return viewModels<MainViewModel>().value
    }

    companion object{
        val wxShareBroker: WxShareBroker by lazy {
            WxShareBroker.instance
        }
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
                LogUtils.printI("onTabReselect---position="+position)
            }

        })

        binding.viewPager.adapter = MainAdapter(supportFragmentManager, titles.size)
        binding.viewPager.offscreenPageLimit= titles.size

        binding.ctl.currentTab = defaultPosition
        binding.viewPager.setCurrentItem(defaultPosition,false)

        getStatusBarHeight()

    }

    /*
    * @describe: 获取状态栏高度的最佳方式
    * @date: 2023/3/29
    */
    private fun getStatusBarHeight() {
        binding.root.setOnApplyWindowInsetsListener(object : View.OnApplyWindowInsetsListener {

            var statusBarSize: Int = 0

            override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets {

                if (statusBarSize > 0) {
                    binding.root.setOnApplyWindowInsetsListener(null)
                    return insets
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val inset = insets.getInsets(WindowInsets.Type.statusBars())
                    statusBarSize = inset.top
                } else {
                    statusBarSize = insets.systemWindowInsetTop
                }
                viewModel.saveStatusHeight(statusBarSize)
                return insets
            }

        })
    }

    override fun initData(savedInstanceState: Bundle?) {
        wxShareBroker.init(this)

    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if(event?.type == MessageEvent.Type.MAIN_GOODS_PAGE){
            binding.ctl.currentTab = defaultPosition
            binding.viewPager.setCurrentItem(defaultPosition,false)
        }
    }

    override fun onDestroy() {
        wxShareBroker.destroy()
        super.onDestroy()
    }

}