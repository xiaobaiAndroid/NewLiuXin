package module.gift

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.lxj.xpopup.core.BottomPopupView


/**
 * @describe: 礼物列表窗口
 * @date: 2020/3/7
 * @author: baizhengfu
 */
class GiftHomeView(context: Context, val dynamicUserId: String?) : BottomPopupView(context) {



    override fun getImplLayoutId(): Int {
        return R.layout.gift_list_view
    }

    override fun onCreate() {
        super.onCreate()

        val bundle = Bundle()
        bundle.putString("dynamicUserId",dynamicUserId)

        (context as FragmentActivity).supportFragmentManager.apply {
            commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView,GiftHomeFragment::class.java,bundle)
            }
        }


    }

}