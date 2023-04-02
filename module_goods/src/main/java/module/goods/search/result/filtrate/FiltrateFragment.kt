package module.goods.search.result.filtrate

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import module.common.base.BaseFragment
import module.common.utils.DensityUtil
import module.common.utils.StatusBarUtils
import module.common.view.LinearSpaceDecoration
import module.goods.R
import module.goods.databinding.GoodsFragmentSearchFilterBinding

/**
 *@author: baizf
 *@date: 2023/4/2
 */
class FiltrateFragment: BaseFragment<GoodsFragmentSearchFilterBinding, FiltrateVModel>() {

    val filtrateAdapter = FiltrateAdapter(mutableListOf<FiltrateEntity>())

    var minPrice:String?=null
    var maxPrice:String?=null

    var priceLeftET:EditText?=null
    var priceRightET:EditText?=null

    override fun createViewModel(): FiltrateVModel {
        return viewModels<FiltrateVModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): GoodsFragmentSearchFilterBinding {
        return GoodsFragmentSearchFilterBinding.inflate(layoutInflater)
    }


    override fun initView() {
        binding.filtrateRV.layoutManager = LinearLayoutManager(context)
        binding.filtrateRV.adapter = filtrateAdapter
        val linearSpaceDecoration = LinearSpaceDecoration(filtrateAdapter, DensityUtil.dip2px(requireContext(), 8f))
        binding.filtrateRV.addItemDecoration(linearSpaceDecoration)
        binding.filtrateRV.setItemViewCacheSize(500)

        val simpleItemAnimator = binding.filtrateRV.itemAnimator as SimpleItemAnimator
        simpleItemAnimator.supportsChangeAnimations = false

        filtrateAdapter.addHeaderView(getHeaderView())

        filtrateAdapter.addChildClickViewIds(R.id.titleTV)
        filtrateAdapter.setOnItemChildClickListener { adapter, view, position ->
            if(view.id== R.id.titleTV){
                val item = filtrateAdapter.getItem(position)
                item.isUnfold = !item.isUnfold
                filtrateAdapter.notifyItemChanged(position+filtrateAdapter.headerLayoutCount)
            }

        }

        binding.resetTV.setOnClickListener {
//            reset()
        }
        binding.confirmTV.setOnClickListener {
            viewModel.getSelectedFiltrateSkus(filtrateAdapter.data)
        }

        setObserver()
    }

    private fun setObserver() {
        viewModel.skuFiltrateResultLD.observe(this){
            filtrateAdapter.setList(it)
        }
        viewModel.skuIdsLD.observe(this){skuIds->
            if(!skuIds.isNullOrEmpty() || !minPrice.isNullOrEmpty() || !maxPrice.isNullOrEmpty()){
//            listener.selectedSkuId(skuIds,minPrice,maxPrice)
            }

        }
    }

    private fun getHeaderView(): View {
        val headerView = LayoutInflater.from(context).inflate(R.layout.goods_header_filtrate, null)
        val priceSectionTV = headerView.findViewById<TextView>(R.id.priceSectionTV)
        val layoutParams = priceSectionTV.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.topMargin = StatusBarUtils.getStatusHeight(context as Activity)
        priceSectionTV.layoutParams = layoutParams

        priceLeftET = headerView.findViewById<EditText>(R.id.priceLeftET)
        priceLeftET?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                minPrice = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        priceRightET = headerView.findViewById<EditText>(R.id.priceRightET)
        priceRightET?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                maxPrice = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        return headerView
    }

    fun reset(){
        priceLeftET?.setText("")
        priceRightET?.setText("")
        viewModel.resetData(filtrateAdapter.data)
        filtrateAdapter.notifyDataSetChanged()
    }


    override fun initData() {
        val keyword = requireArguments().getString("keyword")
        viewModel.getSkuFiltrateData(keyword)
    }
}