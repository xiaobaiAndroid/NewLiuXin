package module.goods.search.result.filtrate

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.lxj.xpopup.core.DrawerPopupView
import module.common.utils.DensityUtil
import module.goods.R
import module.goods.databinding.GoodsPopupSearchFilterBinding

/**
 * @describe: 筛选窗口
 * @date: 2020/4/30
 * @author: Mr Bai
 */
class FiltrateView(context: Context,val keyword: String): DrawerPopupView(context){


    override fun getImplLayoutId(): Int {
        return R.layout.goods_popup_search_filter
    }

    override fun onCreate() {
        super.onCreate()

        val bundle = Bundle()
        bundle.putString("keyword",keyword)
        (context as FragmentActivity).supportFragmentManager.apply {
            commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView, FiltrateFragment::class.java,bundle)

            }
        }

    }

    override fun getPopupWidth(): Int {
        return DensityUtil.getScreenWidth(context as Activity) / 2
    }



}