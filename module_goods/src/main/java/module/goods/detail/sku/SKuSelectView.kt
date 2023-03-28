package module.goods.detail.sku

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.lxj.xpopup.core.BottomPopupView
import module.common.data.entity.Goods
import module.common.utils.DensityUtil
import module.goods.R

/**
 * @describe: sku选择的View
 * @date: 2020/5/3
 * @author: Mr Bai
 */
class SKuSelectView(context: Context,val goods: Goods):BottomPopupView(context) {


    override fun getImplLayoutId(): Int {
        return R.layout.goods_popup_sku_select
    }

    override fun onCreate() {
        super.onCreate()
        val bundle = Bundle()
        bundle.putParcelable("goods",goods)

        (context as FragmentActivity).supportFragmentManager.apply {
            commit {
                setReorderingAllowed(true)
                replace(R.id.contentFCV,SkuSelectFragment::class.java,bundle)

            }
        }
    }


    override fun getMaxHeight(): Int {
        val screenHeight = DensityUtil.getScreenHeight(context as Activity)
        val maxHeight = screenHeight.times(3f).div(4f).toInt()
        return maxHeight
    }
}