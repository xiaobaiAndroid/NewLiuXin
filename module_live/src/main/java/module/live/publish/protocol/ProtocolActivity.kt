package module.live.publish.protocol

import android.media.MediaFormat
import android.media.MediaMetadata
import android.os.Bundle
import android.webkit.WebSettings
import androidx.activity.viewModels
import module.common.base.BaseActivity
import module.common.utils.ARouterHelper
import module.live.databinding.LiveActivityProtocolBinding

class ProtocolActivity : BaseActivity<LiveActivityProtocolBinding,ProtocolViewModel>() {

    var type = 0

    override fun createViewModel(): ProtocolViewModel {
        return viewModels<ProtocolViewModel>().value
    }

    override fun getBindingView(): LiveActivityProtocolBinding {
        return LiveActivityProtocolBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {

        val webSettings: WebSettings = binding.contentWV.settings
        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true; //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true; // 缩放至屏幕的大小
        webSettings.textZoom = 280

        viewModel.protocolLiveData.observe(this){
            it.title?.let {title->
                binding.actionBarView.setTitle(title)
            }
            binding.contentWV.loadData(it.content, "text/html", "UTF-8")
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        type = intent.getIntExtra(ARouterHelper.Key.PROTOCOL_TYPE, 0)
        binding.actionBarView.setData(this, "")

        viewModel.getProtocolData(type)
    }

}