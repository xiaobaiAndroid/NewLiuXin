package module.publish.category

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.core.BottomPopupView
import module.common.data.entity.CliqueCategory
import module.common.event.MessageEvent
import module.common.view.LinearDividerDecoration
import module.publish.R
import module.publish.databinding.LivePublishPopupCategoriesBinding
import org.greenrobot.eventbus.EventBus

/**
 * @describe: 分类列表的view
 * @date: 2020/3/13
 * @author: baizhengfu
 */
class CategoriseView(context: Context, private val categorises: MutableList<CliqueCategory>) :
    BottomPopupView(context) {


    override fun getImplLayoutId(): Int {
        return R.layout.live_publish_popup_categories
    }

    override fun onCreate() {
        super.onCreate()
        val binding = LivePublishPopupCategoriesBinding.bind(this)

        binding.contentRV.layoutManager = LinearLayoutManager(context)
        val categoriseAdapter = CategoriseAdapter(categorises)
        binding.contentRV.adapter = categoriseAdapter
        val decoration = LinearDividerDecoration<ColorDrawable>(
            categoriseAdapter,
            ColorDrawable(context.resources.getColor(module.common.R.color.cl_eeeeee)),
            context.resources.getDimension(module.common.R.dimen.dp_1).toInt()
        )
        binding.contentRV.addItemDecoration(decoration)
        categoriseAdapter.setOnItemClickListener { _, _, position ->
            val category = categoriseAdapter.getItem(position)
            val messageEvent = MessageEvent(MessageEvent.Type.PUBLISH_SELECT_CATEGORY)
            messageEvent.obj = category
            EventBus.getDefault().post(messageEvent)
            dismiss()
        }
    }
}