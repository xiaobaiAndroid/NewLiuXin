package module.gift.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import module.common.base.BaseFragment
import module.common.data.entity.GiftCategory
import module.common.event.MessageEvent
import module.common.utils.DensityUtil
import module.common.view.GridSpaceDecoration
import module.gift.SelectedGiftMsgEntity
import module.gift.databinding.GiftFramentListBinding
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/3/22
 */
class GiftListFragment: BaseFragment<GiftFramentListBinding,GiftListViewModel>() {

    private val mAdapter = GiftAdapter(mutableListOf())

    private var lastSelectedPos = -1

    private var fragmentPosition = 0

    override fun createViewModel(): GiftListViewModel {
        return viewModels<GiftListViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): GiftFramentListBinding {
        return GiftFramentListBinding.inflate(layoutInflater,container,false)
    }

    override fun initData() {
        val giftCategory = requireArguments().getParcelable<GiftCategory?>("giftCategory")
        fragmentPosition = requireArguments().getInt("position", 0)
        viewModel.getData(giftCategory?.giftTypeId ?: 0)
    }

    override fun initView() {
        binding.contentRV.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.contentRV.adapter = mAdapter

        val gridSpaceDecoration =
            GridSpaceDecoration(mAdapter, DensityUtil.dip2px(requireContext(), 10f), 4)
        gridSpaceDecoration.setLeftDrawHalf(true)
        gridSpaceDecoration.setRightDrawHalf(true)

        binding.contentRV.addItemDecoration(gridSpaceDecoration)

        mAdapter.setOnItemClickListener { adapter, view, position ->
            if(lastSelectedPos != position){
                val gift = mAdapter.getItem(position)
                gift.isSelected = true
                mAdapter.notifyItemChanged(position)

                if(lastSelectedPos != -1){
                    val item = mAdapter.getItem(lastSelectedPos)
                    item.isSelected = false
                    mAdapter.notifyItemChanged(lastSelectedPos)
                }

                lastSelectedPos = position

                val messageEvent = MessageEvent(MessageEvent.Type.RESET_SELECT_GIFT)
                val msgEntity = SelectedGiftMsgEntity(fragmentPosition,gift)
                messageEvent.obj = msgEntity
                EventBus.getDefault().post(messageEvent)
            }

        }

        viewModel.giftsResultLD.observe(this){
            mAdapter.setList(it)
        }
    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if(event?.type === MessageEvent.Type.RESET_SELECT_GIFT){
            val entity = event.obj as SelectedGiftMsgEntity
            if(entity.fragmentPosition != fragmentPosition){
                resetSelectedGiftStatus()
            }
        }

    }

    private fun resetSelectedGiftStatus() {
        if(lastSelectedPos != -1){
            val item = mAdapter.getItem(lastSelectedPos)
            item.isSelected = false
            mAdapter.notifyItemChanged(lastSelectedPos)
            lastSelectedPos = -1
        }
    }
}