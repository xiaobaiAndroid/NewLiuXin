package module.login.password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseActivity
import module.common.data.DataResult
import module.common.event.MessageEvent
import module.common.type.ProtocolType
import module.common.utils.ARouterHelper
import module.common.utils.KeyBoardUtils
import module.common.utils.StatusBarUtils
import module.common.utils.ToastUtils
import module.login.R
import module.login.code.LoginCodeActivity
import module.login.code.LoginCodeViewModel
import module.login.databinding.LoginActivityLoginBinding
import module.login.databinding.LoginActivityLoginCodeBinding
import org.greenrobot.eventbus.EventBus

@Route(path = ARouterHelper.LOGIN_PSW)
class LoginPswActivity : BaseActivity<LoginActivityLoginBinding, LoginPswViewModel>(){

    var loadingV: BasePopupView? = null

    override fun createViewModel(): LoginPswViewModel {
        return viewModels<LoginPswViewModel>().value
    }

    override fun getBindingView(): LoginActivityLoginBinding {
        return LoginActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this,binding.closeIV)
        binding.closeIV.setOnClickListener {
            supportFinishAfterTransition()
        }

        binding.layoutInputPhone.phoneET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val content = s.toString()
                if (content.isEmpty()){
                    binding.layoutInputPhone.clearPhoneIV.visibility = View.GONE
                }else{
                    if(!binding.layoutInputPhone.clearPhoneIV.isShown){
                        binding.layoutInputPhone.clearPhoneIV.visibility = View.VISIBLE
                    }
                }
            }

        })

        binding.layoutInputPhone.clearPhoneIV.setOnClickListener {
            binding.layoutInputPhone.phoneET.setText("")
        }

        binding.forgetPswTV.setOnClickListener {
            ARouterHelper.openPath(this, ARouterHelper.SET_PSW)
        }
        binding.pswLoginTV.setOnClickListener {
            startActivity(Intent(this, LoginCodeActivity::class.java))
        }
        binding.registerTV.setOnClickListener {
            ARouterHelper.openPath(this, ARouterHelper.REGISTER)
        }

        binding.loginTV.setOnClickListener {
            val phone = binding.layoutInputPhone.phoneET.text.toString().trim()
            val psw = binding.pswET.text.toString().trim()
            if(phone.isEmpty()){
                ToastUtils.setMessage(this,resources.getString(R.string.login_input_phone))
            }else if(psw.isEmpty()){
                ToastUtils.setMessage(this,resources.getString(R.string.login_input_psw))
            }else{
                KeyBoardUtils.closeKeybord(binding.layoutInputPhone.phoneET,this)

                showLoadingUI()
                lifecycleScope.launch(Dispatchers.IO){
                    val dataResult = viewModel.login(applicationContext, phone, psw)
                    withContext(Dispatchers.Main){
                        hideLoadingUI()
                        if(dataResult.status == DataResult.SUCCESS){
                            val messageEvent = MessageEvent(MessageEvent.Type.UPDATE_USERINFO)
                            messageEvent.obj = dataResult.t
                            EventBus.getDefault().post(messageEvent)
                            ARouterHelper.openPath(this@LoginPswActivity, ARouterHelper.MAIN)
                        }else{
                            ToastUtils.setMessage(this@LoginPswActivity,dataResult.message)
                        }
                    }
                }
            }
        }

        binding.userProtocolTV.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(ARouterHelper.Key.PROTOCOL_TYPE, ProtocolType.REGISTER.value)
            ARouterHelper.openPath(this, ARouterHelper.RECHARGE_PROTOCOL,bundle)
        }
        binding.privacyPolicyTV.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(ARouterHelper.Key.PROTOCOL_TYPE, ProtocolType.PRIVACY.value)
            ARouterHelper.openPath(this, ARouterHelper.RECHARGE_PROTOCOL,bundle)
        }
    }

    private fun hideLoadingUI() {
        loadingV?.dismiss()
        loadingV = null
    }

    override fun onDestroy() {
        loadingV?.dismiss()
        super.onDestroy()
    }


    private fun showLoadingUI() {
        loadingV = XPopup.Builder(this)
            .dismissOnTouchOutside(false)
            .dismissOnBackPressed(false)
            .asLoading()
            .show()
    }

    override fun onBackPressed() {
        KeyBoardUtils.closeKeybord(binding.layoutInputPhone.phoneET,this)
        super.onBackPressed()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }
}