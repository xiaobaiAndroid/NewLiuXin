package module.goods.search.result

import android.content.Context
import com.lxj.xpopup.impl.PartShadowPopupView
import module.goods.R
import module.goods.databinding.GoodsPopupSynthesizeSortBinding


/**
 * @describe: 综合排序
 * @date: 2020/4/29
 * @author: Mr Bai
 */
class SynthesizeSortView(context: Context,val listener: OnClickListener): PartShadowPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.goods_popup_synthesize_sort
    }

    override fun onCreate() {
        super.onCreate()
        val binding = GoodsPopupSynthesizeSortBinding.bind(popupImplView)

        binding.synthesizeTV.setOnClickListener {
            dismiss()
            listener.sortSynthesize()
        }

        binding.priceHighLowTV.setOnClickListener {
            dismiss()
            listener.sortPriceDesc()
        }

        binding.priceLowHighTV.setOnClickListener {
            dismiss()
            listener?.sortPriceAsc()
        }
    }

    interface OnClickListener{

        /*综合排序*/
        fun sortSynthesize()

        /*价格降序*/
        fun sortPriceDesc()

        /*价格升序*/
        fun sortPriceAsc()
    }

}