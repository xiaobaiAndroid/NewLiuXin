package module.camera

import android.opengl.GLES11Ext
import android.opengl.GLES31Ext
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import module.camera.databinding.CameraActivityCameraBinding
import module.common.base.BaseActivity
import module.common.base.BaseViewModel

class CameraActivity : BaseActivity<CameraActivityCameraBinding,CameraViewModel>() {
    override fun createViewModel(): CameraViewModel {
        return viewModels<CameraViewModel>().value
    }

    override fun getBindingView(): CameraActivityCameraBinding {
        return CameraActivityCameraBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData(savedInstanceState: Bundle?) {

    }

}