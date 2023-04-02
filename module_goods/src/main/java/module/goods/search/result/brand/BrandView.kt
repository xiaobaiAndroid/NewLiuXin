package module.goods.search.result.brand

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.lxj.xpopup.impl.PartShadowPopupView
import module.common.utils.DensityUtil
import module.goods.R

/**
 *@author: baizf
 *@date: 2023/4/2
 */
class BrandView(context: Context, val keyword: String): PartShadowPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.goods_popup_search_brand
    }

    override fun onCreate() {
        super.onCreate()

        val bundle = Bundle()
        bundle.putString("keyword",keyword)
        (context as FragmentActivity).supportFragmentManager.apply {
            commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView, BrandFragment::class.java,bundle)

            }
        }

    }

    override fun getMaxHeight(): Int {
        return DensityUtil.getScreenHeight(context as Activity) / 3
    }

}