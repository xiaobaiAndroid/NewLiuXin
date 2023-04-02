package module.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import module.common.data.entity.Dynamic
import java.util.ArrayList

/**
 *@author: baizf
 *@date: 2023/3/20
 */
object ActivityLauncher {

    fun launchImgTxtDetail(context: Context, dynamic: Dynamic, typeId: String?) {
        val bundle = Bundle()
        bundle.putParcelable("dynamic", dynamic)
        bundle.putString("categoryId", typeId)
        val intent = Intent("dynamic.detail.imgtxt.ImgTxtDetailActivity")
        intent.data = Uri.parse("liuxin://com.yiguo.shop:8888/ImgTxtDetailActivity")
        intent.putExtras(bundle)
        if (context is Activity) {
            context.startActivity(intent)
        } else {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }


    }

    fun launchChooseCity(context: Context){
        val intent = Intent("address.city.ChooseCityActivity")
        intent.data = Uri.parse("liuxin://com.yiguo.shop:8888/ChooseCityActivity")
        if (context is Activity) {
            context.startActivity(intent)
        } else {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun launchVideoDetail(
        context: Context,
        dynamics: ArrayList<Dynamic>?,
        currentPage: Int,
        position: Int,
        typeId: String?,
        cityCode: String?,
        loadMore: Boolean = true
    ) {

        val bundle = Bundle()
        bundle.putInt("pageNumber", currentPage)
        bundle.putInt("playPosition", position)
        bundle.putString("typeId", typeId)
        bundle.putString("cityCode", cityCode)
        bundle.putBoolean("isLoadMore", loadMore)
        bundle.putParcelableArrayList("dynamics", dynamics)
        val intent = Intent("dynamic.detail.video.VideoDetailActivity")
        intent.data = Uri.parse("liuxin://com.yiguo.shop:8888/VideoDetailActivity")
        intent.putExtras(bundle)
        if (context is Activity) {
            context.startActivity(intent)
        } else {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun launchGoodsDetail(context: Context, goodsId: String?, actId: String?) {
        val intent = Intent("goods.detail.GoodsDetailActivity")
        intent.data = Uri.parse("liuxin://com.yiguo.shop:8888/GoodsDetailActivity")
        val bundle = Bundle()
        bundle.putString("goodsId", goodsId)
        bundle.putString("actId", actId)
        intent.putExtras(bundle)
        if (context is Activity) {
            context.startActivity(intent)
        } else {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun launchGoodsSearch(context: Context) {
        val intent = Intent("goods.search.GoodsSearchActivity")
        intent.data = Uri.parse("liuxin://com.yiguo.shop:8888/GoodsSearchActivity")
        if (context is Activity) {
            context.startActivity(intent)
        } else {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun launchGoodsSearchResult(
        context: Context,
        keyword: String,
        cateId: String?
    ) {
        val intent = Intent("goods.search.result.SearchResultActivity")
        intent.data = Uri.parse("liuxin://com.yiguo.shop:8888/SearchResultActivity")

        val bundle = Bundle()
        bundle.putString("keyword", keyword)
        bundle.putString("cateId", cateId)
        intent.putExtras(bundle)
        if (context is Activity) {
            context.startActivity(intent)
        } else {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

}