package com.bzf.module_imageeditor.label

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.databinding.ImgFragmentLabelSelectBinding
import com.bzf.module_imageeditor.view.LinearSpaceDecoration
import module.common.base.BaseFragment
import module.common.event.MessageEvent
import module.common.utils.DensityUtil

/**
 *@author: baizf
 *@date: 2023/3/14
 */
class LabelUserSelectFragment: BaseFragment<ImgFragmentLabelSelectBinding, LabelUserSelectViewModel>() {

    private val mAdapter = LableUserAdapter()

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
    }


}