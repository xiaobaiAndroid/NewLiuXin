package com.yiguo.shop

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.yiguo.shop.databinding.ItemMainTabBinding

/**
 *@author: baizf
 *@date: 2023/3/29
 */
class MainTabView(context: Context, val entity: MainEntity): FrameLayout(context){

    val binding = ItemMainTabBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.nameTV.text = entity.title
        binding.tabIV.setImageResource(entity.unselectIcon)
    }

    fun setTabSelectedStatus(isSelected: Boolean){
        if (isSelected){
            binding.tabIV.setImageResource(entity.selectIcon)
            binding.nameTV.setTextColor(resources.getColor(module.common.R.color.cl_13b5b1))
        }else{
            binding.tabIV.setImageResource(entity.unselectIcon)
            binding.nameTV.setTextColor(resources.getColor(module.common.R.color.cl_000000))
        }
    }
}