package module.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import module.common.base.BaseFragment
import module.common.data.entity.Gift
import module.common.event.MessageEvent
import module.common.utils.ARouterHelper
import module.common.utils.ToastUtils
import module.gift.databinding.GiftFramentHomeBinding
import module.gift.view.GiveNumberSelectView
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/3/22
 */
class GiftHomeFragment : BaseFragment<GiftFramentHomeBinding, GiftHomeViewModel>() {


    /*选中的数量*/
    var mSelectedNumber = 1


    private var mSelectGift: Gift? = null
    private var mSelectNumber: Int = 1
    private var mDynamicUserId: String? = null

    private val titles: Array<String> by lazy {
        resources.getStringArray(R.array.gift_number)
    }

    override fun createViewModel(): GiftHomeViewModel {
        return viewModels<GiftHomeViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): GiftFramentHomeBinding {
        return GiftFramentHomeBinding.inflate(layoutInflater, container, false)
    }

    override fun initData() {
        mDynamicUserId = requireArguments().getString("dynamicUserId")

        viewModel.getAccountBalance()
        viewModel.getGiftCategory()
        for (index in titles.indices) {
            val title = titles[index]
            val tab = binding.numberCTL.newTab()
            tab.text = title
            if (index == 0) {
                binding.numberCTL.addTab(tab, 0, true)
            } else {
                binding.numberCTL.addTab(tab)
            }

        }

    }

    override fun initView() {
        initListener()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.categoriesResultLD.observe(this) {
            binding.giftVP.adapter = GiftPagerAdapter(requireActivity(), it)
            TabLayoutMediator(binding.giftTL, binding.giftVP) { tab, position ->
                tab.text = it[position].giftTypeName
            }.attach()
        }

        viewModel.balanceResultLD.observe(this) {
            val value = it.money.toInt()

            val balance: String = if (value >= 10000) {

                val intValue = value / 10000
                val decimalValue = value % 10000

                val money = "$intValue.$decimalValue"
                String.format(
                    resources.getString(R.string.gift_format_tenthousand_balance),
                    money
                )
            } else {
                val money: String = it.money
                String.format(resources.getString(R.string.gift_format_balance), money)
            }

            binding.balanceTV.text = balance
        }

    }

    private fun initListener() {

        binding.giveTV.setOnClickListener {
            if (mSelectGift == null) {
                ToastUtils.setMessage(context, resources.getString(R.string.gift_select_send_gift))
            } else if (mSelectNumber < 0) {
                ToastUtils.setMessage(context, resources.getString(R.string.gift_input_gift_number))
            } else {
                startGiveGift()
            }
        }

        binding.rechargeTV.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(ARouterHelper.Key.MONEY, viewModel.balanceResultLD.value)
            ARouterHelper.openPath(context, ARouterHelper.RECHARGE, bundle)

        }
        binding.numberCTL.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                disposeTabSelected(tab)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                disposeTabSelected(tab)
            }

        })
    }

    private fun startGiveGift() {
        mSelectGift?.let {
            val eGiveGift = EGiveGift(mSelectedNumber ?: 1,it)

            val messageEvent = MessageEvent(MessageEvent.Type.PLAY_VIDEO_SVGA)
            messageEvent.obj = eGiveGift
            EventBus.getDefault().post(messageEvent)
        }

    }

    private fun disposeTabSelected(tab: TabLayout.Tab?) {
        tab?.position?.let { position ->
            if (position == (titles.size - 1)) {
                if (mSelectGift == null) {
                    ToastUtils.setMessage(
                        context,
                        resources.getString(R.string.gift_select_send_gift)
                    )
                } else {
                    showInputGiftNumberView()
                }
            } else {
                mSelectedNumber = titles[position].toInt()
            }
        }
    }

    private fun showInputGiftNumberView() {
        XPopup.Builder(requireActivity())
            .isViewMode(true)
            .hasStatusBar(false)
            .customHostLifecycle(lifecycle)
            .popupAnimation(PopupAnimation.TranslateFromBottom)
            .asCustom(GiveNumberSelectView(requireActivity()) { number ->
                mSelectedNumber = number
                startGiveGift()
            })
            .show()
    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if (event?.type === MessageEvent.Type.RESET_SELECT_GIFT) {
            val entity = event.obj as SelectedGiftMsgEntity
            mSelectGift = entity.gift
        }

    }
}