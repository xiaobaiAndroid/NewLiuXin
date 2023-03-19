package module.user.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseActivity
import module.common.data.DataResult
import module.common.data.entity.UserInfo
import module.common.type.ProtocolType
import module.common.utils.*
import module.user.R
import module.user.databinding.UserActivityRegisterBinding

@Route(path = ARouterHelper.REGISTER)
class RegisterActivity : BaseActivity<UserActivityRegisterBinding, RegisterViewModel>() {


    override fun createViewModel(): RegisterViewModel {
        return viewModels<RegisterViewModel>().value
    }

    override fun getBindingView(): UserActivityRegisterBinding {
        return UserActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.actionBarView.setData(this,"")

        StatusBarUtils.setMarginStatusBarHeight(this,binding.pswLoginTV)

        binding.pswLoginTV.setOnClickListener {
            onBackPressed()
        }

        binding.phoneET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val content = s.toString()
                if (content.isNullOrEmpty()){
                    binding.clearPhoneIV.visibility = View.GONE
                }else{
                    if(!binding.clearPhoneIV.isShown){
                        binding.clearPhoneIV.visibility = View.VISIBLE
                    }
                }
            }

        })

        binding.clearPhoneIV.setOnClickListener {
            binding.phoneET.setText("")
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

        binding.loginTV.setOnClickListener {
            login()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    private fun login() {
        val phone = binding.phoneET.text.toString().trim()
        val code = binding.codeET.text.toString().trim()
        val recommendPhone = binding.recommendPeopleET.text.toString().trim()
        if(phone.isEmpty()){
            ToastUtils.setMessage(this,resources.getString(R.string.user_input_phone))
        }else if(code.isEmpty()){
            ToastUtils.setMessage(this,resources.getString(R.string.user_input_verification_code))
        }else if(code!=viewModel.vcodeLiveData.value){
            ToastUtils.setMessage(this,resources.getString(R.string.user_verification_code_error))
        }else{
            lifecycleScope.launch(Dispatchers.IO){
               val dataResult: DataResult<String>  = viewModel.register(phone,code,recommendPhone)
                withContext(Dispatchers.Main){
                    if(dataResult.status == DataResult.SUCCESS){
                        ARouterHelper.openPath(this@RegisterActivity, ARouterHelper.MAIN)
                        onBackPressed()
                    }else{
                        ToastUtils.setMessage(this@RegisterActivity,dataResult.message)
                    }
                }

            }
            KeyBoardUtils.closeKeybord(binding.phoneET,this)
        }
    }

    private fun getVerificationCode() {
        val phone = binding.phoneET.text.toString().trim()
        if(phone.isNullOrEmpty()){
            ToastUtils.setMessage(this,resources.getString(R.string.user_input_phone))
        }else{
//            mCountDownUtil = CountDownUtils(getCodeTV, R.color.cl_13b5b1, R.color.cl_13b5b1)
//            mCountDownUtil?.start(59,resources.getString(R.string.user_get_verification_code))
//            mPresenter.sendVerificationCode(phone)
        }
    }
}