package com.yiguo.shop

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.yiguo.shop.databinding.ActivityMainBinding
import lib.share.WxShareBroker
import module.common.base.BaseActivity
import module.common.event.MessageEvent
import module.common.utils.ARouterHelper
import module.common.viewmodel.MainTabShareVModel

@Route(path = ARouterHelper.MAIN)
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    val defaultPosition = 2

    private lateinit var  shareVModel: MainTabShareVModel

    override fun createViewModel(): MainViewModel {
        shareVModel = viewModels<MainTabShareVModel>().value
        return viewModels<MainViewModel>().value
    }

    companion object {
        val wxShareBroker: WxShareBroker by lazy {
            WxShareBroker.instance
        }
    }

    override fun getBindingView() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {

        val titles = resources.getStringArray(R.array.main_app_home)

        val arr = arrayListOf<MainEntity>(
            MainEntity(
                R.drawable.ic_home_tab_im_select,
                R.drawable.ic_home_tab_im_select,
                titles.get(0)
            ),
            MainEntity(
                R.drawable.ic_home_tab_clique,
                R.drawable.ic_home_tab_clique_select,
                titles[1]
            ),
            MainEntity(
                R.drawable.ic_home_tab_goods,
                R.drawable.ic_home_tab_goods_select,
                titles[2]
            ),
            MainEntity(
                R.drawable.ic_home_tab_nearby,
                R.drawable.ic_home_tab_nearby_select,
                titles[3]
            ),
            MainEntity(R.drawable.ic_home_tab_my, R.drawable.ic_home_tab_my_select, titles[4])
        )


        val mainAdapter = MainAdapter(this, titles.size)


        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = mainAdapter
        binding.viewPager.offscreenPageLimit = titles.size
        binding.viewPager.setCurrentItem(defaultPosition,false)

        TabLayoutMediator(binding.tabLayout, binding.viewPager, false, false) { tab, position ->
            tab.customView = MainTabView(this, arr[position])
            if(position == defaultPosition){
                val mainTabView = tab.customView as MainTabView
                shareVModel.positionLD.value = position
                mainTabView.setTabSelectedStatus(true)
            }
        }.attach()



        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val mainTabView = tab!!.customView as MainTabView
                if (viewModel.isLogin()) {
                    shareVModel.positionLD.value = tab!!.position
                    mainTabView.setTabSelectedStatus(true)
                } else {
                    if (tab!!.position == 0 || tab.position == 4) {
                        ARouterHelper.openBottomToTop(this@MainActivity, ARouterHelper.LOGIN_PSW)
                    } else {
                        shareVModel.positionLD.value = tab!!.position
                        mainTabView.setTabSelectedStatus(true)
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val mainTabView = tab!!.customView as MainTabView
                mainTabView.setTabSelectedStatus(false)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

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
        if (event?.type == MessageEvent.Type.MAIN_GOODS_PAGE) {
            binding.viewPager.setCurrentItem(defaultPosition, false)
        }
    }

    override fun onDestroy() {
        wxShareBroker.destroy()
        super.onDestroy()
    }

}