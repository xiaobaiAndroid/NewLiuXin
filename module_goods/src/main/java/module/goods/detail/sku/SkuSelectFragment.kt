package module.goods.detail.sku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import module.common.base.BaseFragment
import module.common.data.entity.Goods
import module.common.data.entity.GoodsDetailSku
import module.common.data.entity.GoodsSkuAttribute
import module.common.data.entity.GoodsSkuAttributeValue
import module.common.utils.ImageLoadHelper
import module.common.utils.MoneyUtils
import module.common.utils.ToastUtils
import module.goods.R
import module.goods.databinding.GoodsFragmentSkuSelectBinding
import module.goods.detail.DetailActFraPort
import module.goods.detail.SettleParams
import java.math.BigDecimal

/**
 *@author: baizf
 *@date: 2023/3/28
 */
class SkuSelectFragment:BaseFragment<GoodsFragmentSkuSelectBinding, SkuSelectVModel>() {

    var buyNumber = 1

    var mCurrentGoodsSku: GoodsDetailSku?=null

    var goods: Goods?=null

    var skuAdapter = SkuAdapter(goods?.attrList)

    var selectedSku: GoodsDetailSku? = null

    /*颜色的位置*/
    val colorPosition = 1

    val selectSkuSB = StringBuilder()

    var currentOperationSku: GoodsSkuAttribute?=null

    lateinit var mActFraPort: DetailActFraPort


    val selectedSkuValues = mutableListOf<GoodsSkuAttributeValue>()

    override fun createViewModel(): SkuSelectVModel {
        mActFraPort = activityViewModels<DetailActFraPort>().value
        return viewModels<SkuSelectVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): GoodsFragmentSkuSelectBinding {
        return GoodsFragmentSkuSelectBinding.inflate(layoutInflater,container,false)
    }

    override fun initData() {
        goods = requireArguments().getParcelable<Goods>("goods")

        goods ?: return

        val defaultPosition  =0

        val defaultAttributeValue = goods?.attrList?.get(defaultPosition)?.items?.get(defaultPosition)
        defaultAttributeValue?.isEnable = true
        defaultAttributeValue?.isSelected = true
        selectedSkuValues.add(defaultPosition, defaultAttributeValue!!)

        mCurrentGoodsSku = goods?.goodsSkuList?.get(defaultPosition)

        if(goods?.attrList?.size!!>defaultPosition+1){
            currentOperationSku = goods?.attrList?.get(defaultPosition+1)
        }

        goods?.let {
            viewModel.filtrateSkuOptions(defaultAttributeValue,defaultPosition,it)
        }

        val sellPrice: Double = BigDecimal(goods!!.salePrice).divide(BigDecimal(100.00)).toDouble()

        ImageLoadHelper.load(binding.goodsPictureIV, goods?.goodsUrl)
        binding.priceTV.text = "￥$sellPrice"
        binding.selectedSkuTV.text = context?.getString(R.string.goods_please_select)

        context?.getString(R.string.goods_format_inventory)?.let {
            binding.inventoryTV.text = String.format(it, mCurrentGoodsSku?.stock)
        }
    }

    override fun initView() {
        binding.skuRV.layoutManager = LinearLayoutManager(context)

        binding.skuRV.adapter = skuAdapter

        setListener()
        setObserver()
    }

    private fun setObserver() {
        viewModel.filtrateSkuOptionsLD.observe(this){
            skuAdapter.notifyDataSetChanged()
        }
        viewModel.colorPictureUrlLD.observe(this){colorPictureUrl->
            if(colorPictureUrl.isNullOrEmpty()){
                ImageLoadHelper.load(binding.goodsPictureIV, goods?.goodsUrl)
            }else{
                ImageLoadHelper.load(binding.goodsPictureIV, colorPictureUrl)
            }
        }
        viewModel.goodsDetailSkuLD.observe(this){sku->
            selectedSku = sku

            binding.priceTV.text = requireContext().resources.getString(R.string.goods_money_tag) + MoneyUtils.convertShowPrice(selectedSku!!.salePrice.toString())

            val inventoryFormat = requireContext().getString(R.string.goods_format_inventory)
            binding.inventoryTV.text = String.format(inventoryFormat, selectedSku?.stock)
        }
    }

    private fun setListener() {
        skuAdapter.setmListener(object : SkuAdapter.SkuListener {
            val striMap = HashMap<Int, String>()

            override fun onClick(value: GoodsSkuAttributeValue?, position: Int) {
                striMap.put(position, value?.skuAttrItemName!!)
                val iterator = striMap.iterator()
                selectSkuSB.clear()
                while (iterator.hasNext()) {
                    selectSkuSB.append(iterator.next().value).append(" ")
                }
                binding.selectedSkuTV.text = "已选 " + selectSkuSB.toString()

                if (position == colorPosition) {
                    viewModel.getColorPictureById(value?.skuAttrItemId, goods?.colorImages)
                }
                if (position == (skuAdapter.data.size - 1)) {
                    viewModel.getSelectedSku(value?.skuAttrItemId, goods?.goodsSkuList, position)
                } else {
                    currentOperationSku = skuAdapter.getItem(position + 1)
                }
                goods?.let {
                    viewModel.filtrateSkuOptions(value, position, it)
                }
                selectedSkuValues.add(position, value)
            }
        })

        binding.confirmTV.setOnClickListener {
            selectedSku?.let {
                mActFraPort.settleParamsLD.value = SettleParams(selectedSkuValues,selectedSku,binding.numberTV.text.toString().toInt(),selectSkuSB.toString())
            } ?: kotlin.run {
                ToastUtils.setMessage(context,requireContext().resources.getString(R.string.goods_please_select)+currentOperationSku?.skuAttrName)
            }
        }
        binding.addTV.setOnClickListener {
            if (mCurrentGoodsSku != null) {
                if (buyNumber < mCurrentGoodsSku!!.stock) {
                    buyNumber = buyNumber.inc()
                    binding.numberTV.text = buyNumber.toString()
                }
            }
        }
        binding.minusTV.setOnClickListener {
            if (buyNumber > 1) {
                buyNumber = buyNumber.dec()
                binding.numberTV.text = buyNumber.toString()
            }
        }
        binding.closeIV.setOnClickListener {
            mActFraPort.closeSkuViewLD.value = true
        }

        binding.addCartTV.setOnClickListener {
            selectedSku?.let {
                mActFraPort.settleParamsLD.value = SettleParams(selectedSkuValues,selectedSku,binding.numberTV.text.toString().toInt(),selectSkuSB.toString())
            } ?: kotlin.run {
                ToastUtils.setMessage(context,requireContext().resources.getString(R.string.goods_please_select)+currentOperationSku?.skuAttrName)
            }
        }
    }
}