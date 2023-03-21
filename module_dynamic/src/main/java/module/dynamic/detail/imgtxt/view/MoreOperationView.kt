package module.dynamic.detail.imgtxt.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.enums.PopupAnimation
import lib.share.ShareEntity
import lib.share.ShareView
import module.common.data.entity.Dynamic
import module.dynamic.R
import module.dynamic.databinding.DynamicPopupMoreOperationBinding

/**
 * @describe: 更对操作的View
 * @date: 2020/3/8
 * @author: baizhengfu
 */
class MoreOperationView(context: Context, val dynamic: Dynamic?, val imageView: ImageView, val shareEntity: ShareEntity) : BottomPopupView(context){


    override fun getImplLayoutId(): Int {
        return R.layout.dynamic_popup_more_operation
    }

    override fun onCreate() {
        super.onCreate()
        val binding = DynamicPopupMoreOperationBinding.bind(popupImplView)
        binding.deleteTV.visibility = View.GONE
        binding.noSelfOperationLL.visibility = View.GONE

        binding.shareTV.setOnClickListener {
            val shareView = ShareView(context,shareEntity)
            XPopup.Builder(context)
                    .hasShadowBg(false)
                    .enableDrag(false)
                    .popupAnimation(PopupAnimation.TranslateFromBottom)
                    .asCustom(shareView)
                    .show()
            dismiss()
        }

        /*举报*/
        binding.reportTV.setOnClickListener {
            dismiss()
        }

        binding.deleteTV.setOnClickListener {

        }

        /*屏蔽*/
        binding.blacklistTV.setOnClickListener {

        }

        binding.cancelTV.setOnClickListener {
            dismiss()
        }
        initData()
    }

    private fun initData() {

    }


}