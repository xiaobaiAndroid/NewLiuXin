package module.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import module.common.data.entity.UserInfo
import module.common.event.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment<T : ViewBinding,V: BaseViewModel> : Fragment() {
    protected var isViewInitiated = false
    protected var isVisibleToUser = false
    protected var isDataInitiated = false
    protected var isLogin = false
    protected var mArguments: Bundle? = null

    protected lateinit var binding: T
    protected lateinit var viewModel: V

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isAlone) {
            this.isVisibleToUser = isVisibleToUser
            prepareFetchData()
        }
    }


    /*是否一个Activity只有一个Fragment,那就不需要延迟加载*/
    protected var isAlone: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(layoutInflater,container)
        viewModel = createViewModel()
        viewModel.mContext = requireContext().applicationContext
        mArguments = arguments
        if (needEventBus()) {
            EventBus.getDefault().register(this)
        }
        return binding.root
    }

    protected abstract fun createViewModel(): V

    protected abstract fun getViewBinding(inflater: LayoutInflater,
    container: ViewGroup?): T

    /**
     * @describe: 是否需要EventBus
     * @date: 2019/9/4
     */
    fun needEventBus(): Boolean {
        return true
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            isLogin = savedInstanceState.getBoolean("isLogin")
        }

        initView()
        initData()
//        if (isAlone) {
//            initView()
//            initData()
//        } else {
//            isViewInitiated = true
//            prepareFetchData()
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        if (MessageEvent.Type.UPDATE_USERINFO === event.type) {
            val userInfo = event.obj as UserInfo
            isLogin = userInfo.isLogin != UserInfo.LoginStatus.LOGOUT
        }
        disposeMessageEvent(event)
    }

    open fun disposeMessageEvent(event: MessageEvent?) {}
    @JvmOverloads
    fun prepareFetchData(forceUpdate: Boolean = false): Boolean {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            initView()
            initData()
            isDataInitiated = true
            return true
        }
        return false
    }

    protected abstract fun initData()
    override fun onDestroyView() {
        if (needEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroyView()
    }

    protected abstract fun initView()
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isLogin", isLogin)
        super.onSaveInstanceState(outState)
    }
}