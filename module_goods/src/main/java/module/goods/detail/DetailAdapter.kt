package module.goods.detail

import android.app.Activity
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.request.target.ImageViewTarget
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener
import module.common.data.entity.Goods
import module.common.data.entity.GoodsDetailImage
import module.common.data.entity.Shop
import module.common.utils.DensityUtil.getScreenWidth
import module.common.utils.IconUtils.setColor
import module.common.utils.ImageLoadHelper
import module.common.utils.ImageLoadHelper.loadCircle
import module.common.utils.StringUtils.packNull
import module.goods.R
import java.math.BigDecimal

class DetailAdapter(data: MutableList<DetailMultiEntity>?) :
    BaseMultiItemQuickAdapter<DetailMultiEntity, BaseViewHolder>(data), LoadMoreModule {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    init {
        addItemType(DetailMultiEntity.Type.BANNER.ordinal, R.layout.goods_item_multi_detail_banner)
        addItemType(DetailMultiEntity.Type.SKU.ordinal, R.layout.goods_item_multi_detail_sku)
        addItemType(DetailMultiEntity.Type.SHOP.ordinal, R.layout.goods_item_multi_detail_shop)
        addItemType(DetailMultiEntity.Type.DETAIL.ordinal, R.layout.goods_item_multi_goods_detail)
    }

    protected override fun convert(holder: BaseViewHolder, item: DetailMultiEntity) {
        val goods = item.goods
        if (item.itemType == DetailMultiEntity.Type.BANNER.ordinal) {
            setBannerData(holder, goods,item.banners)
        } else if (item.itemType == DetailMultiEntity.Type.SKU.ordinal) {
            val skuArrowIV = holder.getView<ImageView>(R.id.skuArrowIV)
            setColor(skuArrowIV, context.resources.getColor(module.common.R.color.cl_707070))
            val specificationArrowTV = holder.getView<ImageView>(R.id.specificationArrowTV)
            setColor(
                specificationArrowTV,
                context.resources.getColor(module.common.R.color.cl_707070)
            )
            if (TextUtils.isEmpty(item.slectedSkuContent)) {
                holder.setText(
                    R.id.skuTV,
                    context.resources.getString(R.string.goods_please_select)
                )
            } else {
                holder.setText(R.id.skuTV, item.slectedSkuContent)
            }
        } else if (item.itemType == DetailMultiEntity.Type.SHOP.ordinal) {
            setShopData(holder, item.shop)
        } else {
            val detailImage = item.detailImage
            if (detailImage != null) {
                val detailIV = holder.getView<ImageView>(R.id.detailIV)
                val loadingPB = holder.getView<ProgressBar>(R.id.loadingPB)
                loadingPB.visibility = View.VISIBLE
                ImageLoadHelper.loadFitCenterListener(
                    detailIV.context,
                    detailImage.url,
                    object : ImageViewTarget<Drawable>(detailIV) {
                        override fun setResource(resource: Drawable?) {
                            if (resource != null) {
                                val intrinsicWidth = resource.intrinsicWidth
                                val intrinsicHeight = resource.intrinsicHeight
                                val screenWidth = getScreenWidth(
                                    (context as Activity)
                                )
                                val ratio = screenWidth * 1.0f / intrinsicWidth
                                val layoutParams = detailIV.layoutParams
                                layoutParams.width = screenWidth
                                layoutParams.height = (intrinsicHeight * ratio).toInt()
                                detailIV.layoutParams = layoutParams
                                detailIV.scaleType = ImageView.ScaleType.FIT_CENTER
                                detailIV.setImageDrawable(resource)
                                loadingPB.visibility = View.GONE
                            }
                        }
                    },
                    module.common.R.drawable.ic_default_photo
                )
            }
        }
    }

    private fun setShopData(helper: BaseViewHolder, shop: Shop?) {
        val format = context.resources.getString(R.string.goods_format_shop_sell_number)
        if (shop != null) {
            val shopPictureIV = helper.getView<ImageView>(R.id.shopPictureIV)
            loadCircle(shopPictureIV, shop.storeLogo)
            helper.setText(R.id.shopNameTV, packNull(shop.storeName))
                .setGone(R.id.sellNumberTV, false)
                .setText(R.id.sellNumberTV, String.format(format, shop.goodsCount.toString()))
        } else {
            helper.setGone(R.id.sellNumberTV, true)
        }
    }

    private fun setBannerData(
        helper: BaseViewHolder,
        goods: Goods?,
        banners: MutableList<GoodsDetailImage>?
    ) {

        goods ?: kotlin.run {
            helper.setGone(R.id.shareMoneyTV, true)
            return
        }

        val bannerV = helper.getView<Banner<GoodsDetailImage, GoodsDetailBannerAdapter>>(R.id.bannerV)
        val screenWidth = getScreenWidth((context as Activity))
        val layoutParams = bannerV.layoutParams
        layoutParams.width = screenWidth
        layoutParams.height = screenWidth
        bannerV.layoutParams = layoutParams
        if (bannerV.adapter == null) {
            banners?.let {
                bannerV.setAdapter(GoodsDetailBannerAdapter(it))
            }
        } else {
            val bannerAdapter = bannerV.adapter as GoodsDetailBannerAdapter
            bannerAdapter.setDatas(banners)
        }
        bannerV.setOnBannerListener(
            OnBannerListener { data: GoodsDetailImage?, position: Int ->
                val goodsImages = goods.goodsImages
                val arrayList = ArrayList<String>()
                for (goodsDetailImage in goodsImages) {
                    if (goodsDetailImage != null) {
                        arrayList.add(goodsDetailImage.url)
                    }
                }
            }
        )
        val shareMoney = BigDecimal(goods.income1).divide(BigDecimal(100.00)).toDouble()
        val sellPrice = BigDecimal(goods.salePrice).divide(BigDecimal(100.00)).toDouble()
        helper.setText(R.id.goodsNameTV, packNull(goods.goodsTitle))
            .setText(R.id.shareMoneyTV, "分享红利￥" + String.format("%.2f", shareMoney))
            .setGone(R.id.shareMoneyTV, true)
            .setText(R.id.priceTV, "￥" + String.format("%.2f", sellPrice))
    }
}