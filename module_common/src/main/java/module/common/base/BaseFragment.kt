package module.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import module.common.event.MessageEvent
import module.common.utils.LogUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment<T : ViewBinding, V : BaseViewModel> : Fragment() {
    private var isLoaded = false

    protected lateinit var binding: T
    protected lateinit var viewModel: V

    protected var shareVModel: ShareLazyViewModelBase? = null

    private var mPagePosition = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(layoutInflater, container)

        mPagePosition = requireArguments().getInt("pagePosition", 0)

        shareVModel = createShareViewModel()
        viewModel = createViewModel()
        viewModel.mContext = requireContext().applicationContext

        if (needEventBus()) {
            EventBus.getDefault().register(this)
        }
        return binding.root
    }

    protected open fun createShareViewModel(): ShareLazyViewModelBase? {
        return null
    }



    protected abstract fun createViewModel(): V

    protected abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    /**
     * @describe: 是否需要EventBus
     * @date: 2019/9/4
     */
    fun needEventBus(): Boolean {
        return true
    }


    override fun onStart() {
        super.onStart()
        LogUtils.printI("lifecycle----start $this")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.printI("lifecycle----onStop $this")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.printI("lifecycle----onResume $this")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.printI("lifecycle----onPause $this")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.apply {
            mPagePosition = getInt("mPagePosition")
        }
        viewModel.getAccountInfo()

        shareVModel?.let {
            it.positionLD.observe(viewLifecycleOwner) {
                if (it == mPagePosition) {
                    if (!isLoaded) {
                        LogUtils.printI("fragment= ${this.javaClass.name}   currentPosition=$it, mPagePosition=$mPagePosition")
                        isLoaded = true
                        initView()
                        initData()
                    }
                }
            }
        } ?: kotlin.run {
            initView()
            initData()
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        if (MessageEvent.Type.UPDATE_USERINFO === event.type) {
            viewModel.getAccountInfo()
        }
        disposeMessageEvent(event)
    }

    open fun disposeMessageEvent(event: MessageEvent?) {}

    protected abstract fun initData()
    override fun onDestroyView() {
        if (needEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        LogUtils.printI("lifecycle----onViewCreated $this")
        super.onDestroyView()
    }

    protected abstract fun initView()
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("mPagePosition", mPagePosition)
        super.onSaveInstanceState(outState)
    }
}