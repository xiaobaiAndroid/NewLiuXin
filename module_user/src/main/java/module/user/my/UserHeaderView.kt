package module.user.my

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.lxj.xpopup.XPopup
import lib.share.ShareEntity
import lib.share.ShareView
import module.common.data.entity.Money
import module.common.data.entity.UserInfo
import module.common.type.SexType
import module.common.utils.*
import module.user.R
import module.user.databinding.UserHeaderUserInfoBinding

class UserHeaderView(context: Context, attributeSet: AttributeSet): FrameLayout(context, attributeSet) {

    var earnings: Money? =null
    var liuMoney: Money? =null
    private val binding = UserHeaderUserInfoBinding.inflate(LayoutInflater.from(context),this,true)

    init {

        val liuchatIdFormat = resources.getString(R.string.user_format_liuchat_id)
        binding.liuchatIdTV.text = String.format(liuchatIdFormat, "")
        binding.lxMoneyCountTV.text = "0.00"

        val addressFormat = resources.getString(R.string.user_format_address)
        binding.addressTV.text = String.format(addressFormat,"")

        binding.earningsTV.text = "0.00"

        binding.earningsCL.setOnClickListener {
            ARouterHelper.toBillList(context,earnings?.id,resources.getString(R.string.user_earnings_detail),true)
        }

        binding.lxMoneyCountCL.setOnClickListener {
            ARouterHelper.toBillList(context,liuMoney?.id,resources.getString(R.string.user_bill_detail),false)
        }

        binding.shareIV.setOnClickListener {
            val shareEntity = ShareEntity()
            shareEntity.contentType = ShareEntity.Type.RES_PICTURE
            shareEntity.imgResId = lib.share.R.drawable.share_ic_share
            XPopup.Builder(context)
                    .asCustom(ShareView(context,shareEntity))
                    .show()
        }

    }


    fun setData(userInfo: UserInfo){
        updateBackground(userInfo.photo)

        ImageLoadHelper.loadCircle(binding.avatarIV, userInfo.avatar, module.common.R.drawable.ic_default_avatar)
        val liuchatIdFormat = resources.getString(R.string.user_format_liuchat_id)
        binding.liuchatIdTV.text = String.format(liuchatIdFormat, userInfo.mobile)
        binding.nickNameTV.text = StringUtils.packNull(userInfo.nickName)
        if(userInfo.sex == SexType.MAN.value){
            binding.sexIV.setImageResource(module.common.R.drawable.ic_man)
            binding.sexIV.rotation = 0f
        }else{
            binding.sexIV.setImageResource(module.common.R.drawable.ic_woman)
            binding.sexIV.rotation = 45f
        }
        val addressFormat = resources.getString(R.string.user_format_address)
        binding.addressTV.text = String.format(addressFormat, StringUtils.packNull(userInfo.provinceName)+ StringUtils.packNull(userInfo.cityName))
    }

    fun setLiuMoneyData(money: Money?) {
        if(money!=null){
            this.liuMoney = money

            val tenThousand = "10000"
            if(ArithmeticUtils.compare(money.money,tenThousand)){
                binding.lxMoneyCountTV.text = ArithmeticUtils.div(money.money,tenThousand,2) + resources.getString(R.string.user_tag_ten_thousand)+"+"
            }else{
                binding.lxMoneyCountTV.text = money.money
            }
        }
    }

    fun setupEarningsData(money: Money?) {
        if(money!=null){
            this.earnings =money
            val tenThousand = "10000"
            if(ArithmeticUtils.compare(money.money,tenThousand)){
                binding.earningsTV.text = ArithmeticUtils.div(money.money,tenThousand,2) + resources.getString(R.string.user_tag_ten_thousand)+"+"
            }else{
                binding.earningsTV.text = MoneyUtils.convertShowPrice(money.money).toString()
            }
        }
    }

    fun updateBackground(newUrl: String?) {
        ImageLoadHelper.load(binding.userbgIV,newUrl,R.drawable.user_bg_my_header)
    }
}