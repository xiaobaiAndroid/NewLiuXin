package module.common.base

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import module.common.event.MessageEvent
import module.common.utils.ImmersionBarUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity<T : ViewBinding, V: BaseViewModel> : AppCompatActivity() {

    protected lateinit var  binding: T
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBindingView()
        setContentView(binding.root)
        viewModel = createViewModel()
        viewModel.mContext = applicationContext

        initStatusBar()
        initView(savedInstanceState)
        if (needEventBus()) {
            EventBus.getDefault().register(this)
        }

        viewModel.getAccountInfo()
        initData(savedInstanceState)
    }

    abstract fun createViewModel(): V

    abstract fun getBindingView(): T


    protected open fun initStatusBar() {
        ImmersionBarUtils.init(this)
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
        if (MessageEvent.Type.UPDATE_USERINFO === event.type) {
            viewModel.getAccountInfo()
        } else if (MessageEvent.Type.EXIT_APP === event.type) {
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

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}