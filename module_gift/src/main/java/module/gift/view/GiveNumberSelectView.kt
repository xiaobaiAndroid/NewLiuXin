package module.gift.view

/**
 *@author: baizf
 *@date: 2023/3/22
 */
import android.content.Context
import module.common.utils.ToastUtils
import com.lxj.xpopup.core.BottomPopupView
import module.gift.R
import module.gift.databinding.GiftPopupGiftNumberSelectBinding
import java.lang.StringBuilder

/**
 * @describe: 赠送数量选择的view
 * @date: 2020/3/9
 * @author: baizhengfu
 */
class GiveNumberSelectView(context: Context, val selectedCallback: (Int)->Unit):BottomPopupView(context) {

    var sb = StringBuilder()

    override fun getImplLayoutId(): Int {
        return R.layout.gift_popup_gift_number_select
    }

    override fun onCreate() {
        super.onCreate()

        val binding = GiftPopupGiftNumberSelectBinding.bind(popupImplView)

        binding.giveTV.setOnClickListener {
            val number = binding.numberTV.text.toString()
            if(number.isEmpty()){
                ToastUtils.setMessage(context,context.getString(R.string.gift_input_gift_number))
            }else{
                selectedCallback.invoke(number.toInt())
                dismiss()
            }
        }

        binding.oneTV.setOnClickListener {
            sb.append(1)
            binding.numberTV.text = sb.toString()
        }

        binding.twoTV.setOnClickListener {
            sb.append(2)
            binding.numberTV.text = sb.toString()
        }

        binding.threeTV.setOnClickListener {
            sb.append(3)
            binding.numberTV.text = sb.toString()
        }

        binding.fourTV.setOnClickListener {
            sb.append(4)
            binding.numberTV.text = sb.toString()
        }

        binding.fiveTV.setOnClickListener {
            sb.append(5)
            binding.numberTV.text = sb.toString()
        }

        binding.sixTV.setOnClickListener {
            sb.append(6)
            binding.numberTV.text = sb.toString()
        }

        binding.sevenTV.setOnClickListener {
            sb.append(7)
            binding.numberTV.text = sb.toString()
        }

        binding.eightTV.setOnClickListener {
            sb.append(8)
            binding.numberTV.text = sb.toString()
        }
        binding.nineTV.setOnClickListener {
            sb.append(9)
            binding.numberTV.text = sb.toString()
        }

        binding.zeroTV.setOnClickListener {
            sb.append(0)
            binding.numberTV.text = sb.toString()
        }

        binding.cancelTV.setOnClickListener {
            dismiss()
        }

        binding.deleteTV.setOnClickListener {
            if(sb.isNotEmpty()){
                sb = sb.delete(sb.length-1,sb.length)
                binding.numberTV.text = sb.toString()
            }

        }
    }

}