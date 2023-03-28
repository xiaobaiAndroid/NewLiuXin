package module.goods.category.home.list

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.youth.banner.indicator.CircleIndicator
import module.common.data.entity.Banner
import module.common.data.entity.GoodsCategory
import module.common.utils.ARouterHelper
import module.common.utils.DensityUtil
import module.common.utils.ImageLoadHelper
import module.common.utils.NumberUtils
import module.goods.databinding.GoodsHeaderCategoryBinding

class IndexHeaderView(context: Context, val lifecycleOwner: LifecycleOwner):FrameLayout(context) {

    val binding = GoodsHeaderCategoryBinding.inflate(LayoutInflater.from(context), this, true)

    private val mCategoryAdapter = IndexCategoryAdapter(mutableListOf())

    private val mBannerAdapter = GoodsListBannerAdapter(mutableListOf())

    fun setBannerData(banners: MutableList<Banner>?) {
        banners ?: return
        mBannerAdapter.setDatas(banners)

    }

    fun setCategories(list: MutableList<GoodsCategory>?) {
        mCategoryAdapter.setList(list)
    }

    init {
        binding.categoryRV.layoutManager = GridLayoutManager(context,4)
        binding.categoryRV.adapter = mCategoryAdapter


        binding.bannerV.setAdapter(mBannerAdapter)
            .addBannerLifecycleObserver(lifecycleOwner).indicator = CircleIndicator(context)


        binding.bannerV.setOnBannerListener { data, position ->
            val banner = data as Banner
            val goodsTag = "liuxin://yiguosoft.com/goods?goodsId="
            if(!TextUtils.isEmpty(banner.url)){
                if(NumberUtils.isNumber(banner.url)){
                    ARouterHelper.toGoodsDetail(context,banner?.url,"")
                }else if("liuixn_Attract".equals(banner?.url)){//显示招商广告
                    val arrayList = ArrayList<String>()
                    arrayList.add(ImageLoadHelper.ASSET+"attr_inves.jpg")
//                    LookPictureActivity.launch(context as Activity,position,arrayList,bannerV)
                }else if(banner?.url!!.contains(goodsTag)){
                    val goodsId = banner.url.substring(goodsTag.length)
//                    ARouterHelper.toGoodsDetail(context,goodsId,"")
                }
            }
        }

    }

}