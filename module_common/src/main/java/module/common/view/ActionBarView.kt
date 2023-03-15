package module.common.view

import android.app.Activity
import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import module.common.R
import module.common.databinding.LayoutActionbarBinding
import module.common.utils.StatusBarUtils
import module.common.utils.StringUtils

/**
 * @describe: 标题栏
 * @date: 2020/2/5
 * @author: baizhengfu
 */
class ActionBarView(context: Context,attrs:AttributeSet) : FrameLayout(context,attrs){

    val binding: LayoutActionbarBinding
    init {
        binding = LayoutActionbarBinding.inflate(LayoutInflater.from(context), this)
//        backIB.setColorFilter(resources.getColor(R.color.cl_cccccc), PorterDuff.Mode.SRC_IN)
        setBackgroundColor(resources.getColor(R.color.cl_ffffff))
    }


    public fun <T:Activity> setData(activity: T,title:String){
        binding.titleTV.text = StringUtils.packNull(title)
        binding.backIB.setOnClickListener {
            activity.onBackPressed()
        }
//        StatusBarUtils.setMarginStatusBarHeight(activity,binding.toolbarCL)
    }

    /**
     * @describe: 设置返回的键的颜色
     * @date: 2020/3/14
     */
    public  fun setBackImageColor(color: Int) {
        binding.backIB.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }

    /**
     * @describe: 是否隐藏标题
     * @date: 2020/3/14
     */
    fun hideTitleView(hide: Boolean) {
        if(hide){
            binding.titleTV.visibility = View.GONE
        }else{
            binding.titleTV.visibility = View.VISIBLE
        }
    }

    fun setTitleColor(color: Int) {
        binding.titleTV.setTextColor(color)
    }

    fun setTitle(title: String?) {
        binding.titleTV.text = StringUtils.packNull(title)
    }



    fun hideImageBack(isHide:Boolean){
        if(isHide){
            binding.backIB.visibility = View.GONE
        }else{
            binding.backIB.visibility = View.VISIBLE
        }
    }
}