package module.comment.input

import android.content.Context
import com.lxj.xpopup.core.BottomPopupView
import module.comment.R
import module.comment.databinding.CommentLayoutInputBinding
import module.common.event.MessageEvent
import module.common.utils.KeyBoardUtils
import module.common.utils.ToastUtils
import org.greenrobot.eventbus.EventBus

/**
 * @describe: 输入框布局
 * @date: 2020/3/10
 * @author: baizhengfu
 */
class InputContentView(context: Context) :BottomPopupView(context){

    override fun getImplLayoutId(): Int {
        return R.layout.comment_layout_input
    }

    override fun onCreate() {
        super.onCreate()
        val binding = CommentLayoutInputBinding.bind(popupImplView)

        binding.sendTV.setOnClickListener {
            val content = binding.inputET.text.toString().trim()
            if(content.isEmpty()){
                ToastUtils.setMessage(context,resources.getString(R.string.comment_input_content))
            }else{
                binding.inputET.setText("")
                KeyBoardUtils.closeKeybord(binding.inputET,context)

                val messageEvent = MessageEvent(MessageEvent.Type.SEND_COMMENT)
                messageEvent.obj = content
                EventBus.getDefault().post(messageEvent)
                dismiss()
            }
        }
    }

    override fun onDismiss() {
        super.onDismiss()

    }

}