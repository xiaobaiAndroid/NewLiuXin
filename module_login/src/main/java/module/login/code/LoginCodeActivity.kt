package module.login.code

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseActivity
import module.common.data.DataResult
import module.common.data.entity.UserInfo
import module.common.event.MessageEvent
import module.common.type.ProtocolType
import module.common.utils.ARouterHelper
import module.common.utils.KeyBoardUtils
import module.common.utils.StatusBarUtils
import module.common.utils.ToastUtils
import module.login.R
import module.login.databinding.LoginActivityLoginCodeBinding
import org.greenrobot.eventbus.EventBus


/**
 * @describe: 验证码登录
 * @date: 2020/5/8
 * @author: Mr Bai
 */

class LoginCodeActivity : BaseActivity<LoginActivityLoginCodeBinding, LoginCodeViewModel>(){

    var vcode:String?=null

    var loadingV: BasePopupView? = null

    private val countDownTimer: CountDownTimer by lazy {
          object: CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                val second = millisUntilFinished / 1000
                binding.getCodeTV.text = second.toString()
            }

            override fun onFinish() {
                binding.getCodeTV.setTextColor(resources.getColor(module.common.R.color.cl_333333))
                binding.getCodeTV.text = resources.getString(R.string.login_get_verification_code)
            }
        }

    }
    

    override fun initView(savedInstanceState: Bundle?) {

        StatusBarUtils.setMarginStatusBarHeight(this,binding.closeIV)
        StatusBarUtils.setMarginStatusBarHeight(this,binding.pswLoginTV)
        binding.closeIV.setOnClickListener {
            onBackPressed()
        }
        binding.pswLoginTV.setOnClickListener {
            onBackPressed()
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

        binding.loginTV.setOnClickListener {
            login()
        }

        binding.getCodeTV.setOnClickListener {
            getVerificationCode()
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

    private fun login() {
        val phone = binding.layoutInputPhone.phoneET.text.toString().trim()
        val code = binding.codeET.text.toString().trim()

        if(phone.isEmpty()){
            ToastUtils.setMessage(this,resources.getString(R.string.login_input_phone))
        }else if(code.isEmpty()){
            ToastUtils.setMessage(this,resources.getString(R.string.login_input_verification_code))
        }else if(code!=vcode){
            ToastUtils.setMessage(this,resources.getString(R.string.login_verification_code_error))
        }else{
            KeyBoardUtils.closeKeybord(binding.layoutInputPhone.phoneET,this)
            showLoadingUI()
            lifecycleScope.launch(Dispatchers.IO){
               val dataResult: DataResult<UserInfo>  = viewModel.login(applicationContext,phone, code)
                withContext(Dispatchers.Main){
                    hideLoadingUI()
                    if(dataResult.status == DataResult.SUCCESS){
                        val messageEvent = MessageEvent(MessageEvent.Type.LOGIN_SUCCESS)
                        EventBus.getDefault().post(messageEvent)
                        ARouterHelper.openPath(this@LoginCodeActivity, ARouterHelper.MAIN)
                        onBackPressed()
                    }else{
                        ToastUtils.setMessage(this@LoginCodeActivity,dataResult.message)
                    }
                }
            }
        }
    }

    private fun hideLoadingUI() {
        loadingV?.dismiss()
        loadingV = null
    }


    override fun initData(savedInstanceState: Bundle?) {

    }

    private fun showLoadingUI() {
        loadingV = XPopup.Builder(this)
            .dismissOnTouchOutside(false)
            .dismissOnBackPressed(false)
            .asLoading()
            .show()
    }


    private fun getVerificationCode() {
        val phone = binding.layoutInputPhone.phoneET.text.toString().trim()
        if(phone.isEmpty()){
            ToastUtils.setMessage(this,resources.getString(R.string.login_input_phone))
        }else{
            startCountDown()
            lifecycleScope.launch(Dispatchers.IO) {
                val dataResult =  viewModel.sendVerificationCode(phone)
                withContext(Dispatchers.Main){
                    if(dataResult.status == DataResult.SUCCESS){
                        ToastUtils.setMessage(this@LoginCodeActivity,resources.getString(R.string.login_send_code_success))
                        vcode = dataResult.t?.data?.vcode
                    }else{
                        ToastUtils.setMessage(this@LoginCodeActivity,dataResult.message)
                    }
                }
            }

        }
    }

    private fun startCountDown() {
        binding.getCodeTV.isEnabled = false
        binding.getCodeTV.setTextColor(resources.getColor(module.common.R.color.cl_e53935))
        countDownTimer.start()
    }




    override fun createViewModel(): LoginCodeViewModel {
        return viewModels<LoginCodeViewModel>().value
    }

    override fun getBindingView(): LoginActivityLoginCodeBinding {
        return LoginActivityLoginCodeBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        loadingV?.dismiss()
        countDownTimer.cancel()
        super.onDestroy()
    }

    override fun onBackPressed() {
        KeyBoardUtils.closeKeybord(binding.codeET,this)
        val messageEvent = MessageEvent(MessageEvent.Type.UPDATE_USERINFO)
        EventBus.getDefault().post(messageEvent)
        super.onBackPressed()

    }


}
