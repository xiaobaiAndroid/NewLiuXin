package module.publish.entrance

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.bzf.module_imageeditor.PictureEditActivity
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import module.common.base.BaseActivity
import module.common.picture.GlideEngine
import module.common.type.ProtocolType
import module.common.utils.ARouterHelper
import module.common.utils.StatusBarUtils
import module.publish.protocol.ProtocolActivity
import module.publish.databinding.LiveActivityPublishEntranceBinding

/*
* @describe: 发布动态入口
* @author: bzf
* @date: 2023/3/9
*/
@Route(path = ARouterHelper.PUBLISH_ENTRANCE)
class
PublishEntranceActivity : BaseActivity<LiveActivityPublishEntranceBinding, PublishEntranceViewModel>() {

    override fun createViewModel(): PublishEntranceViewModel {
        return viewModels<PublishEntranceViewModel>().value
    }

    override fun getBindingView(): LiveActivityPublishEntranceBinding {
        return LiveActivityPublishEntranceBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this,binding.titleTV)


        binding.publishVideoTV.setOnClickListener {
            //发视频前需要检查用户是否实名认证，没有弹窗提示。
//            if(certificationInfo==null || certificationInfo?.idCard.isNullOrEmpty()){
//                showCertificationDialog()
//            }else{
//                popupView = XPopup.Builder(this)
//                    .hasShadowBg(true)
//                    .asBottomList("", arrayOf("录制", "相册选择", "取消"),
//                        object : OnSelectListener {
//                            override fun onSelect(position: Int, text: String?) {
//                                if (position == 0) {
//                                    ARouterHelper.openPath(this@PublishHomeActivity, ARouterHelper.VIDEO_RECORD)
//                                } else if (position == 1) {
//                                    PictureSelectorHelper.openPicture(this@PublishHomeActivity,1, PictureMimeType.ofVideo(),this@PublishHomeActivity)
//                                }
//                            }
//                        })
//                    .show()
//            }
        }


        binding.publishImgTxtTV.setOnClickListener {
            PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine.createGlideEngine())
                .forResult(object : OnResultCallbackListener<LocalMedia?> {
                    override fun onResult(result: ArrayList<LocalMedia?>?) {
                        result?.let {
                            val list = java.util.ArrayList<String>()
                            for (localMedia in it){
                                localMedia?.let { media->
                                    list.add(media.path)
                                }

                            }
                            val intent = Intent(this@PublishEntranceActivity, PictureEditActivity::class.java)
                            intent.putStringArrayListExtra("paths",list)
                            startActivity(intent)
                        }
                    }
                    override fun onCancel() {}
                })
        }

        binding.protocolTV.setOnClickListener {
            val intent = Intent(this, ProtocolActivity::class.java)
            intent.putExtra(ARouterHelper.Key.PROTOCOL_TYPE, ProtocolType.PUBLISH.value)
            startActivity(intent)
        }

        binding.closeIV.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

}