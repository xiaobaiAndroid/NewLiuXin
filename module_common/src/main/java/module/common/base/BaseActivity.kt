package module.common.base

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import module.common.data.entity.UserInfo
import module.common.event.MessageEvent
import module.common.utils.ImmersionBarUtils
import module.common.utils.LogUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity<T : ViewBinding, V: ViewModel> : AppCompatActivity() {
    protected var isLogin = false

    /*Activity是否初始化*/
    private var activityInit = false
    protected lateinit var  binding: T
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBindingView()
        setContentView(binding.root)
        viewModel = createViewModel()

        initStatusBar()
        initView(savedInstanceState)
        if (needEventBus()) {
            EventBus.getDefault().register(this)
        }
        initData(savedInstanceState)
    }

    abstract fun createViewModel(): V

    abstract fun getBindingView(): T

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!activityInit) {
            activityInit = true
            activityStartVisible()
        }
    }

    /**
     * @describe: Activity是完全对用户可见的(只是可见，还一片黑呼呼的，有待draw..)
     * @date: 2020/2/7
     */
    protected fun activityStartVisible() {
        LogUtils.i("activityStartVisible-----")
    }


    protected open fun initStatusBar() {
        ImmersionBarUtils.buildBarDark(this)
    }

    /**
     * @describe: 是否需要EventBus
     * @date: 2019/9/4
     */
    fun needEventBus(): Boolean {
        return true
    }

    override fun onDestroy() {
        if (needEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        if (MessageEvent.MessageType.UPDATE_USERINFO == event.type) {
            val userInfo = event.obj as UserInfo
//            isLogin = userInfo.isLogin != UserInfo.LoginStatus.LOGOUT
        } else if (MessageEvent.MessageType.EXIT_APP == event.type) {
            finish()
        }
        disposeMessageEvent(event)
    }

   open fun disposeMessageEvent(event: MessageEvent?) {}

    /**
     * 当按钮设备的返回键，关闭Activity
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        //按下的如果是BACK，同时没有重复
        if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }





    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun initData(savedInstanceState: Bundle?)
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isLogin", isLogin)
        outState.putBoolean("activityInit", activityInit)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            isLogin = savedInstanceState.getBoolean("isLogin")
            activityInit = savedInstanceState.getBoolean("activityInit")
        }
    }

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}