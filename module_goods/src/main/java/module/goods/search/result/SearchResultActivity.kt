package module.goods.search.result

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter.Companion.EMPTY_VIEW
import com.chad.library.adapter.base.BaseQuickAdapter.Companion.FOOTER_VIEW
import com.chad.library.adapter.base.BaseQuickAdapter.Companion.LOAD_MORE_VIEW
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupPosition
import com.scwang.smart.refresh.header.ClassicsHeader
import module.common.base.CommonListActivityBase
import module.common.data.DataResult
import module.common.event.MessageEvent
import module.common.utils.*
import module.common.view.GridSpaceDecoration
import module.common.view.LinearDividerDecoration
import module.goods.R
import module.goods.databinding.GoodsActivitySearchResultBinding
import module.goods.search.result.brand.BrandView
import module.goods.search.result.filtrate.FiltrateView

/*
* @describe:
* @author: bzf
* @date: 2023/4/2
*/
class SearchResultActivity :
    CommonListActivityBase<GoodsActivitySearchResultBinding, SearchResultVModel>() {


    val resultAdapter = ResultAdapter(ArrayList<ResultMultiEntity>())

    private lateinit var shareVModel:SearchResultShareVModel

    var keyWord: String? = null
    var cateId: String? = null

    private var  brandPopupView: BasePopupView?=null

    override fun createViewModel(): SearchResultVModel {
        shareVModel = viewModels<SearchResultShareVModel>().value
        return viewModels<SearchResultVModel>().value
    }

    override fun getBindingView(): GoodsActivitySearchResultBinding {
        return GoodsActivitySearchResultBinding.inflate(layoutInflater)
    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if (MessageEvent.Type.SELCTC_BRANDS === event?.type) {
            val ids = event.obj as MutableList<String>
            viewModel.setPramsBrandIds(ids)
            viewModel.resetCurrentPage()
            viewModel.search(keyWord, cateId)
        }
    }



    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this, binding.backIV)

        binding.smartRefreshLayout.setRefreshHeader(ClassicsHeader(this))
        binding.smartRefreshLayout.setOnRefreshListener {
            viewModel.resetCurrentPage()
            viewModel.search(keyWord, cateId)
        }

        val pictureSize = DensityUtil.getScreenWidth(this)
            .minus(resources.getDimension(module.common.R.dimen.dp_8).times(3)).div(2).toInt()
        resultAdapter.setPictureSize(pictureSize)

        binding.contentRV.adapter = resultAdapter
        setTwoRowLayout()

        resultAdapter.setEmptyView(getLoadingView())

        initListener()


        setObserver()
    }

    private fun setObserver() {
        viewModel.searchResultLD.observe(this) { dataResult ->
            val list = dataResult.t
            cancelLoadingAnimation()
            if (dataResult.status == DataResult.SUCCESS) {
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.smartRefreshLayout.isRefreshing)
                        binding.smartRefreshLayout.finishRefresh()
                    resultAdapter.setList(list)
                    if (resultAdapter.data.isEmpty()) {
                        resultAdapter.setEmptyView(getEmptyView())
                    }
                } else {
                    list?.let { resultAdapter.addData(it) }
                }
                list?.let {
                    if (list.size > viewModel.getPageSize()) {
                        resultAdapter.loadMoreModule.loadMoreComplete()
                    } else {
                        resultAdapter.loadMoreModule.loadMoreEnd()
                    }
                } ?: resultAdapter.loadMoreModule.loadMoreEnd()
                viewModel.setNextPage()
            } else {
                ToastUtils.setMessage(applicationContext, dataResult.message)
                if (viewModel.getCurrentPage() == 1) {
                    if (binding.smartRefreshLayout.isRefreshing)
                        binding.smartRefreshLayout.finishRefresh()

                    resultAdapter.setEmptyView(getFailView())
                } else {
                    resultAdapter.loadMoreModule.loadMoreFail()
                }

            }
        }

        viewModel.cutLayoutLD.observe(this) {
            resultAdapter.setList(it)
        }

        shareVModel.selectBrandsLD.observe(this){
            brandPopupView?.dismiss()
            viewModel.setPramsBrandIds(it)

            viewModel.resetCurrentPage()
            startLoadingAnimation()
            viewModel.search(keyWord,cateId)
            resultAdapter.setList(mutableListOf())
        }
    }

    private fun initListener() {
        binding.backIV.setOnClickListener {
            onBackPressed()
        }

        val synthesizeSortView =
            SynthesizeSortView(this, object : SynthesizeSortView.OnClickListener {
                override fun sortSynthesize() {
                    binding.arrowIV.rotation = 0f
                    binding.synthesizeTV.text = resources.getString(R.string.goods_synthesize)
                    viewModel.setParamsSynthesize()
                    viewModel.resetCurrentPage()
                    startLoadingAnimation()
                    viewModel.search(keyWord, cateId)
                    resultAdapter.setList(mutableListOf())
                }

                override fun sortPriceDesc() {
                    binding.arrowIV.rotation = 0f
                    binding.synthesizeTV.text = resources.getString(R.string.goods_sort_price_desc)
                    viewModel.setParamsPriceDesc()
                    viewModel.resetCurrentPage()
                    startLoadingAnimation()
                    viewModel.search(keyWord, cateId)
                    resultAdapter.setList(mutableListOf())
                }

                override fun sortPriceAsc() {
                    binding.arrowIV.rotation = 0f
                    binding.synthesizeTV.text = resources.getString(R.string.goods_sort_price_asc)
                    viewModel.setParamsPriceAsc()
                    viewModel.resetCurrentPage()
                    startLoadingAnimation()
                    viewModel.search(keyWord, cateId)
                    resultAdapter.setList(mutableListOf())
                }

            })

        //综合
        binding.synthesizeCL.setOnClickListener {
            binding.arrowIV.rotation = 180f
            XPopup.Builder(this)
                .hasShadowBg(false)
                .atView(binding.tabCL)
                .asCustom(synthesizeSortView)
                .show()
        }

        //销量
        binding.salesTV.setOnClickListener {
            viewModel.setParamsSales()
            viewModel.resetCurrentPage()
            startLoadingAnimation()
            viewModel.search(keyWord, cateId)
            resultAdapter.setList(mutableListOf())
        }

        //品牌
        binding.brankTV.setOnClickListener {
            keyWord?.let { key ->
                brandPopupView  = XPopup.Builder(this)
                    .isViewMode(true)
                    .hasShadowBg(false)
                    .atView(binding.lineV)
                    .asCustom(BrandView(this, key))
                    .show()
            }
        }

        binding.filterTV.setOnClickListener {
            keyWord?.let { key ->
                XPopup.Builder(this)
                    .isViewMode(true)
                    .hasShadowBg(true)
                    .popupPosition(PopupPosition.Right)//右边
                    .asCustom(FiltrateView(this, key))
                    .show()
            }

        }

        binding.cutShowIV.setOnClickListener {
            viewModel.isSingle = !viewModel.isSingle
            if (viewModel.isSingle) {
                setSingleRowLayout()
                binding.cutShowIV.setImageResource(R.drawable.goods_ic_single_show)
            } else {
                setTwoRowLayout()
                binding.cutShowIV.setImageResource(R.drawable.goods_ic_two_show)
            }
            viewModel.cutLayout(resultAdapter.data)
        }

        binding.searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val content = s.toString()
                if (content.isEmpty()) {
                    binding.clearIV.visibility = View.GONE
                } else {
                    if (!binding.clearIV.isShown) {
                        binding.clearIV.visibility = View.VISIBLE
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.searchET.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) { // 先隐藏键盘
                    val manager: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    manager.hideSoftInputFromWindow(
                        this@SearchResultActivity
                            .currentFocus!!.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )

                    KeyBoardUtils.closeKeybord(binding.searchET, this@SearchResultActivity)
                    val content = binding.searchET.text.toString().trim()
                    if (!content.isNullOrEmpty()) {
                        keyWord = content
                        viewModel.setParamsSynthesize()
                        viewModel.resetCurrentPage()
                        viewModel.search(content, cateId)
                    } else {
                        ToastUtils.setMessage(
                            this@SearchResultActivity,
                            resources.getString(R.string.goods_input_search_content)
                        )
                    }
                    return true
                }
                return false
            }

        })

        binding.clearIV.setOnClickListener {
            binding.searchET.setText("")
        }

        resultAdapter.loadMoreModule.setOnLoadMoreListener {
            viewModel.search(keyWord, cateId)
        }

        resultAdapter.setOnItemClickListener { adapter, view, position ->
            val item = resultAdapter.getItem(position)
            val goods = item.goods
            ActivityLauncher.launchGoodsDetail(this, goods?.goodsId, goods?.actId)
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        keyWord = intent.getStringExtra("keyword")
        cateId = intent.getStringExtra("cateId")
        if (!cateId.isNullOrEmpty()) {
            keyWord = ""
        }
        if (!keyWord.isNullOrEmpty()) {
            binding.searchET.setText(keyWord)
            binding.searchET.setSelection(keyWord!!.length)
            viewModel.setParamsPriceAsc()
        }
        viewModel.search(keyWord, cateId)
    }

    /**
     * @describe: 设置单行布局
     * @date: 2020/3/6
     */
    private fun setSingleRowLayout() {
        binding.contentRV.layoutManager = LinearLayoutManager(this)
        val decoration = LinearDividerDecoration<ColorDrawable>(
            resultAdapter,
            ColorDrawable(Color.parseColor("#eeeeee")),
            resources.getDimension(module.common.R.dimen.dp_1).toInt()
        )
        if (binding.contentRV.itemDecorationCount > 0) {
            binding.contentRV.removeItemDecorationAt(0)
        }
        binding.contentRV.addItemDecoration(decoration)
    }

    /**
     * @describe: 设置两行布局
     * @date: 2020/3/6
     */
    private fun setTwoRowLayout() {
        val spanCount = 2
        val gridLayoutManager = GridLayoutManager(this, spanCount)

        binding.contentRV.layoutManager = gridLayoutManager
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (resultAdapter.getItemViewType(position) == EMPTY_VIEW || resultAdapter.getItemViewType(position) == LOAD_MORE_VIEW || resultAdapter.getItemViewType(position) == FOOTER_VIEW) {
                    return 2
                } else {
                    1
                }
            }

        }

        val decoration = GridSpaceDecoration(
            resultAdapter,
            resources.getDimension(module.common.R.dimen.dp_8).toInt(),
            spanCount
        )
        decoration.setMargin(resources.getDimension(module.common.R.dimen.dp_8).toInt())
        decoration.setDrawHeader(true)
        if (binding.contentRV.itemDecorationCount > 0) {
            binding.contentRV.removeItemDecorationAt(0)
        }
        binding.contentRV.addItemDecoration(decoration, 0)
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}