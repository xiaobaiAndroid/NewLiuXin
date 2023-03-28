package module.goods.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import module.common.base.BaseActivity
import module.common.data.entity.Goods
import module.common.data.entity.GoodsDetailSku
import module.common.data.entity.GoodsSkuAttributeValue
import module.common.data.entity.SkuAttributeValue
import module.common.data.request.CreateOrderReq
import module.common.event.MessageEvent
import module.common.type.ChatType
import module.common.type.OrderType
import module.common.utils.*
import module.goods.R
import module.goods.databinding.GoodsActivityGoodsDetailBinding
import org.greenrobot.eventbus.EventBus

/*
* @describe:
* @author: bzf
* @date: 2023/3/28
*/
class GoodsDetailActivity : BaseActivity<GoodsActivityGoodsDetailBinding,GoodsDetailVModel>() {


    val detailAdapter = DetailAdapter(mutableListOf<DetailMultiEntity>())

    var mGoods: Goods? = null

    var selectedSku: GoodsDetailSku?=null
    /*购买数量*/
    var buyNumber: Int=1

    var selectedSkuValues= mutableListOf<GoodsSkuAttributeValue>()

    override fun createViewModel(): GoodsDetailVModel {
        return viewModels<GoodsDetailVModel>().value
    }

    override fun getBindingView(): GoodsActivityGoodsDetailBinding {
       return GoodsActivityGoodsDetailBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this,binding.actionBarCL)

        hideActionBar()

        binding.contentRV.layoutManager = LinearLayoutManager(this)
        binding.contentRV.adapter = detailAdapter


        detailAdapter.addData(DetailMultiEntity(DetailMultiEntity.Type.BANNER.ordinal))
        detailAdapter.addData(DetailMultiEntity(DetailMultiEntity.Type.SKU.ordinal))
        detailAdapter.addData(DetailMultiEntity(DetailMultiEntity.Type.SHOP.ordinal))

        initListener()

        setObserver()
    }

    override fun initStatusBar() {
        ImmersionBarUtils.init(this, android.R.color.transparent,false,true)
    }

    private fun setObserver() {
        viewModel.bannersLD.observe(this){ list->
            val bannerItem = detailAdapter.getItem(0)
            bannerItem.banners = list
            bannerItem.goods = viewModel.goodsLD.value
            detailAdapter.notifyItemChanged(0)
        }

        viewModel.goodsDetailLD.observe(this){ list->
            if(list.isNotEmpty()){
                mGoods = list[0].goods
                val bannerItem = detailAdapter.getItem(0)
                bannerItem.goods = mGoods
                detailAdapter.notifyItemChanged(0)
                detailAdapter.addData(list)
            }
        }

        viewModel.shopLD.observe(this){
            it?.let {shop->
                val shopItem = detailAdapter.getItem(2)
                shopItem.shop = shop
                detailAdapter.notifyItemChanged(2)
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        var goodsId = intent.getStringExtra("goodsId")
        var actId = intent.getStringExtra("actId")
        LogUtils.printI("goodsId=$goodsId")

        viewModel.getGoodsDetail(goodsId,actId)
    }


    private fun initListener() {
        binding.backIV.setOnClickListener {
            onBackPressed()
        }

        binding.shareIV.setOnClickListener {
            showShareView()
        }

        detailAdapter.addChildClickViewIds(R.id.toShopDetailTV,R.id.skuCL,R.id.detailIV)
        detailAdapter.setOnItemChildClickListener { adapter, view, position ->
            val item = detailAdapter.getItem(position)
            if(view.id==R.id.toShopDetailTV){ //到店铺详情
                ARouterHelper.toShopDetail(this,item?.shop?.storeId)
            }else if(view.id==R.id.skuCL){//选择规格
                showSkuView(SkuOperationType.NORMAL)
            }else if(view.id==R.id.detailIV){
                val arr = ArrayList<String?>()
                if(item.detailImage!=null){
                    arr.add(item.detailImage?.url)
//                    LookPictureActivity.launch(this,0,arr,view)
                }
            }
        }

        binding.serviceCL.setOnClickListener {
            if (mGoods != null) {
                val bundle = Bundle()
                bundle.putString(ARouterHelper.Key.ID, mGoods?.adminId)
                val shopItem = detailAdapter.getItem(2)
                bundle.putString(ARouterHelper.Key.AVATAR, shopItem?.shop?.getStoreLogo())
                bundle.putString(ARouterHelper.Key.NICKNAME, mGoods?.getStoreName())
                bundle.putInt(ARouterHelper.Key.TYPE, ChatType.SERVICE.ordinal)
                ARouterHelper.openPath(this, ARouterHelper.SINGLE_CHAT, bundle)
            }
        }

        binding.collectCL.setOnClickListener {
            if(mGoods!=null){
                binding.collectCL.isEnabled = false
                viewModel.collectGoods(mGoods?.goodsId)
            }
        }

        binding.shoppingCartCL.setOnClickListener {
            ARouterHelper.openPath(this, ARouterHelper.SHOPPING_CART)
        }

        binding.addShoppingCartTV.setOnClickListener {
            mGoods?.let {goods->
                selectedSku?.let {
                    binding.addShoppingCartTV.isClickable = false
                    viewModel.addShoppingCart(goods,selectedSku?.id,buyNumber)

                } ?: kotlin.run {
                    showSkuView(SkuOperationType.ADDCART)
                }
            }
        }

        binding.buyNowTV.setOnClickListener {
            mGoods?.let {goods->
                selectedSku?.let {
                    toOrderSettlement(selectedSkuValues,selectedSku,buyNumber,mGoods)

                } ?: kotlin.run {
                    showSkuView(SkuOperationType.BUY_NOW)
                }
            }
        }

        binding.contentRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var offset: Int = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                LogUtils.printI("dy=$dy")
                offset += dy
                if (offset <= binding.actionBarCL.height) {
                    hideActionBar()
                } else {
                    showActionBar()
                }
            }
        })
    }

    private fun hideActionBar() {
        binding.actionBarFL.setBackgroundColor(Color.TRANSPARENT)
        IconUtils.setColor(binding.backIV, Color.WHITE)
        binding.backIV.setBackgroundResource(R.drawable.goods_bg_detail_button)
        IconUtils.setColor(binding.shareIV, 0)
        binding.shareIV.setBackgroundResource(R.drawable.goods_bg_detail_button)
    }


    private fun showActionBar() {
        binding.actionBarFL.setBackgroundColor(Color.WHITE)
        IconUtils.setColor(binding.backIV, Color.BLACK)
        binding.backIV.setBackgroundResource(0)
        IconUtils.setColor(binding.shareIV, Color.BLACK)
        binding.shareIV.setBackgroundResource(0)

    }


    /**
     * @describe: 到订单结算页面
     * @date: 2020/5/5
     */
    private fun toOrderSettlement(selectedSkuValues:MutableList<GoodsSkuAttributeValue>, selectedSku: GoodsDetailSku?, buyNumber: Int, mGoods: Goods?) {
        val createOrderReq = CreateOrderReq()
        createOrderReq.orderType = OrderType.NORMAL.value.toString()

        val storeCart = CreateOrderReq.StoreCart()
        storeCart.storeId = mGoods?.storeId
        storeCart.storeName = mGoods?.storeName
        storeCart.logo = mGoods?.merchant?.logo

        val cart = CreateOrderReq.Cart()
        cart.storeName = mGoods?.storeName
        cart.storeId = mGoods?.storeId
        cart.actId = mGoods?.actId
        cart.goodsTitle = mGoods?.goodsTitle
        cart.goodsId = mGoods?.goodsId
        cart.goodsSkuId = selectedSku?.id
        cart.preCount = selectedSku?.preCount
        cart.stock = selectedSku?.stock
        cart.income1 = selectedSku?.income1
        cart.goodsUrl = mGoods?.goodsUrl
        cart.buyCount = buyNumber
        cart.salePrice = selectedSku?.salePrice

        val skuAttrItemList = mutableListOf<SkuAttributeValue>()
        for(goodsSkuAttrValue in selectedSkuValues){
            val skuAttributeValue = SkuAttributeValue()
            skuAttributeValue.skuAttrId = goodsSkuAttrValue.skuAttrId
            skuAttributeValue.skuAttrItemId = goodsSkuAttrValue.skuAttrItemId
            skuAttributeValue.skuAttrItemName = goodsSkuAttrValue.skuAttrItemName
            skuAttrItemList.add(skuAttributeValue)
        }
        cart.skuAttrItemList =skuAttrItemList

        storeCart.cartList = mutableListOf(cart)

        createOrderReq.storeCartList = mutableListOf(storeCart)

        ARouterHelper.toOrderSettlement(this,createOrderReq)
    }

    private fun showShareView() {
//        if(mGoods!=null){
//            val stringBuilder = StringBuilder()
//            if (!mGoods?.keyword.isNullOrEmpty()) {
//                stringBuilder.append(mGoods?.keyword).append(" ")
//            }
//
//            val shareEntity = ShareEntity()
//            shareEntity.title = mGoods?.goodsTitle
//            shareEntity.content = stringBuilder.toString()
//            shareEntity.contentType = ShareEntity.Type.WEB
//            if(mGoods!!.id.isNullOrEmpty()){
//                shareEntity.url = URLUtils.GOODS_SHARE+mGoods?.goodsId
//            }else{
//                shareEntity.url = URLUtils.GOODS_SHARE+mGoods?.id
//            }
//            shareEntity.showBitmapUrl = mGoods?.goodsUrl
//            XPopup.Builder(this)
//                .hasShadowBg(true)
//                .enableDrag(false)
//                .popupAnimation(PopupAnimation.TranslateFromBottom)
//                .asCustom(ShareView(this,shareEntity))
//                .show()
//        }
    }

    /**
     * @describe: 显示Sku选择框
     * @date: 2020/5/3
     */
    private fun showSkuView(type: SkuOperationType) {
//        val sKuSelectView = SKuSelectView(this, mGoods)
//        sKuSelectView.mListener = object:SKuSelectView.SkuListener{
//
//            override fun confirm(selectedSkuValues:ArrayList<GoodsSkuAttributeValue>, selectedSku: GoodsDetailSku?, buyNumber:Int, skuContent: String?) {
//                this@GoodsDetailActivity.selectedSkuValues = selectedSkuValues
//                this@GoodsDetailActivity.selectedSku = selectedSku
//                this@GoodsDetailActivity.buyNumber = buyNumber
//                val item = detailAdapter.getItem(1)
//                item?.slectedSkuContent = skuContent
//                detailAdapter.notifyItemChanged(1)
//                if(type==SkuOperationType.BUY_NOW){
//                    toOrderSettlement(selectedSkuValues,selectedSku,buyNumber,mGoods)
//                }else if(type==SkuOperationType.ADDCART){
//
//                }
//            }
//
//            override fun addCart(selectedSkuValues: ArrayList<GoodsSkuAttributeValue>, selectedSku: GoodsDetailSku?, buyNumber: Int, toString: String?) {
//                mPresenter.addShoppingCart(mGoods,selectedSku?.id,buyNumber)
//            }
//        }
//
//        XPopup.Builder(this)
//            .hasShadowBg(true)
//            .enableDrag(false)
//            .popupAnimation(PopupAnimation.TranslateFromBottom)
//            .asCustom(sKuSelectView)
//            .show()
    }


}