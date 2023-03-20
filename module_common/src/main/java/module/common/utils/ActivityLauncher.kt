package module.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ComponentActivity
import module.common.data.entity.Dynamic

/**
 *@author: baizf
 *@date: 2023/3/20
 */
object ActivityLauncher {

    fun launchImgTxtDetail(context: Context, dynamic: Dynamic, typeId: String?){
        val bundle = Bundle()
        bundle.putParcelable("dynamic", dynamic)
        bundle.putString("categoryId", typeId)
        val intent = Intent("dynamic.detail.imgtxt.ImgTxtDetailActivity")
        intent.data = Uri.parse("liuxin://com.yiguo.shop:8888/ImgTxtDetailActivity")
        intent.putExtras(bundle)
        if(context is Activity){
            context.startActivity(intent)
        }else{
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }


    }

}