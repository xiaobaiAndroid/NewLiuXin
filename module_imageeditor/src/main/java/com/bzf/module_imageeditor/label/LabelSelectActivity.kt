package com.bzf.module_imageeditor.label

import android.os.Bundle
import androidx.activity.viewModels
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.databinding.ImgActivityLabelSelectBinding
import com.google.android.material.tabs.TabLayoutMediator
import module.common.base.BaseActivity
import module.common.utils.ImmersionBarUtils

class LabelSelectActivity : BaseActivity<ImgActivityLabelSelectBinding,LabelSelectViewModel>() {


    override fun createViewModel(): LabelSelectViewModel {
        return viewModels<LabelSelectViewModel>().value
    }

    override fun getBindingView(): ImgActivityLabelSelectBinding {
        return ImgActivityLabelSelectBinding.inflate(layoutInflater)
    }

    override fun initStatusBar() {
        ImmersionBarUtils.init(this, module.common.R.color.cl_000000,true)
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.actionBarV.setData(this,resources.getString(R.string.img_label))
        binding.actionBarV.setBackgroundColor(resources.getColor(android.R.color.transparent))
        binding.actionBarV.setBackImageColor(resources.getColor(R.color.cl_ffffff))
        binding.actionBarV.setTitleColor(resources.getColor(R.color.cl_ffffff))

        val tabNameArr = arrayOf(resources.getString(R.string.img_label_location),resources.getString(R.string.img_label_user))


        val fragmentAdapter = LabelSelectFragmentAdapter(this)
        binding.contentVP.adapter = fragmentAdapter

        TabLayoutMediator(binding.tabLayout,binding.contentVP){ tab, position->
                tab.text = tabNameArr[position]
        }.attach()

    }

    override fun initData(savedInstanceState: Bundle?) {

    }

}