package com.bzf.module_imageeditor.attachment.label.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.databinding.ImgFragmentLabelSelectBinding
import com.bzf.module_imageeditor.label.select.LabelEntity
import com.bzf.module_imageeditor.label.select.LabelType
import com.bzf.module_imageeditor.view.LinearSpaceDecoration
import module.common.base.BaseFragment
import module.common.event.MessageEvent
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/3/14
 */
class LabelUserSelectFragment: BaseFragment<ImgFragmentLabelSelectBinding, LabelUserSelectViewModel>() {

    private val mAdapter = LableUserAdapter()

    private var mPosition = 0

    override fun createViewModel(): LabelUserSelectViewModel {
        return viewModels<LabelUserSelectViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ImgFragmentLabelSelectBinding {
        isAlone = true
        return ImgFragmentLabelSelectBinding.inflate(inflater,container,false)
    }

    override fun initData() {
        mPosition = arguments?.getInt("position", 0) ?: 0

        viewModel.friendsLiveData.observe(this){
            mAdapter.setList(it)
        }
        viewModel.getData()
    }


    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        event?.let {
            if (MessageEvent.Type.UPDATE_USERINFO === it.type) {
                viewModel.getData()
            }
        }
    }



    override fun initView() {

        binding.contentRV.layoutManager = LinearLayoutManager(requireContext())
        binding.contentRV.adapter = mAdapter

        val padding = resources.getDimension(R.dimen.dp_16).toInt()
        val spaceDecoration = LinearSpaceDecoration(
            mAdapter,
            padding,
            padding,padding
        )

        spaceDecoration.setDrawHeader(true)

        binding.contentRV.addItemDecoration(spaceDecoration)

        mAdapter.setOnItemClickListener { adapter, view, position ->
            val item = mAdapter.getItem(position)
            val labelEntity = LabelEntity(item.extend.nickName, LabelType.USER)
            labelEntity.position = mPosition
            val messageEvent = MessageEvent(MessageEvent.Type.SELECT_LABEL)
            messageEvent.obj = labelEntity
            EventBus.getDefault().post(messageEvent)
            activity?.onBackPressed()
        }
    }


}