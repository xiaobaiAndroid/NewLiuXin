package module.dynamic.view.category

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.lxj.xpopup.impl.PartShadowPopupView
import module.dynamic.R
import module.dynamic.databinding.DynamicPopupCategorySelectBinding

/**
 * @describe: 分类选择弹窗
 * @date: 2020/3/16
 * @author: baizhengfu
 */
class CategorySelectView(context: Context, val catgories: MutableList<CategoryMultiItem>?, var currentPosition: Int): PartShadowPopupView(context) {

    val categorySelectAdapter = CategorySelectAdapter(catgories)

    override fun getImplLayoutId(): Int {
        return R.layout.dynamic_popup_category_select
    }


    override fun onCreate() {
        super.onCreate()
        val binding = DynamicPopupCategorySelectBinding.bind(popupImplView)

        binding.contentRV.layoutManager = GridLayoutManager(context,3)
        binding.contentRV.adapter = categorySelectAdapter
//        mPresenter.convertData(catgories,currentPosition)
//
//        categorySelectAdapter.setOnItemClickListener { _, _, position ->
//            val messageEvent = MessageEvent(MessageEvent.Type.DYNAMIC_SELECT_CATEGORY)
//            messageEvent.obj = position
//            EventBus.getDefault().post(messageEvent)
//            dismiss()
//        }
    }


}