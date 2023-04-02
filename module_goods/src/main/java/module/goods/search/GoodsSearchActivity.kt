package module.goods.search

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import lib.flowlayout.FlowLayout
import lib.flowlayout.TagAdapter
import lib.flowlayout.TagFlowLayout
import module.common.base.BaseActivity
import module.common.base.CommonListActivityBase
import module.common.data.entity.HistorySearch
import module.common.utils.*
import module.goods.R
import module.goods.databinding.GoodsActivityGoodsSearchBinding

/*
* @describe:
* @author: bzf
* @date: 2023/4/2
*/
class GoodsSearchActivity : BaseActivity<GoodsActivityGoodsSearchBinding,GoodsSearchVModel>() {

    val historyAdapter = HistoryAdapter(mutableListOf<HistorySearch>())
    lateinit var tfl: TagFlowLayout<HotSearchEntity>


    private val mTagAdapter: TagAdapter<HotSearchEntity> by lazy {
        HistoryTagAdapter(this,mutableListOf<HotSearchEntity>())
    }

    override fun createViewModel(): GoodsSearchVModel {
        return viewModels<GoodsSearchVModel>().value
    }

    override fun getBindingView(): GoodsActivityGoodsSearchBinding {
        return GoodsActivityGoodsSearchBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this, binding.actionCL)

        binding.contentRV.layoutManager = LinearLayoutManager(this)
        binding.contentRV.adapter = historyAdapter
        historyAdapter.setHeaderView(getHeaderView())

        tfl.adapter = mTagAdapter

        initListener()
        setObserver()
    }

    private fun GoodsSearchActivity.initListener() {
        binding.cancelTV.setOnClickListener {
            onBackPressed()
        }

        binding.clearIV.setOnClickListener {
            binding.searchET.setText("")
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
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    manager.hideSoftInputFromWindow(
                        this@GoodsSearchActivity
                            .currentFocus!!.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )

                    KeyBoardUtils.closeKeybord(binding.searchET, this@GoodsSearchActivity)
                    val content = binding.searchET.text.toString().trim()
                    search(content)
                    return true
                }
                return false
            }

        })

        historyAdapter.addChildClickViewIds(R.id.deleteIV)
        historyAdapter.setOnItemChildClickListener { adapter, view, position ->
            val historySearch = historyAdapter.getItem(position)
            historyAdapter.remove(position)
            viewModel.deleteHistory(historySearch)
        }

        historyAdapter.setOnItemClickListener { adapter, view, position ->
            val historySearch = historyAdapter.getItem(position)
            search(historySearch.keyWord)
        }

        tfl.setOnTagClickListener(object :TagFlowLayout.OnTagClickListener{
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                val searchEntity = mTagAdapter.mTagDatas?.get(position)
                search(searchEntity?.title)
                return true
            }
        })
    }


    private fun setObserver() {
        viewModel.hostListLD.observe(this){ list->
            tfl.setList(list)
        }

        viewModel.historyListLD.observe(this){ list->
            historyAdapter.data.clear()
            historyAdapter.addData(list)
            if(historyAdapter.data.isNotEmpty()){
                historyAdapter.setFooterView(getFooterView())
            }else{
                historyAdapter.removeAllFooterView()
            }
        }
    }


    override fun initData(savedInstanceState: Bundle?) {
        viewModel.getHotSearch()
        viewModel.getHistories()
    }

    private fun search(keyword: String?) {
        if (keyword.isNullOrEmpty()) {
            ToastUtils.setMessage(this, resources.getString(R.string.goods_input_key))
        } else {
            viewModel.saveSearchHistory(keyword)
            ActivityLauncher.launchGoodsSearchResult(this,keyword, null)
        }
    }

    private fun getHeaderView(): View {
        val view = LayoutInflater.from(this).inflate(R.layout.goods_header_history, null)
        tfl = view.findViewById<TagFlowLayout<HotSearchEntity>>(R.id.tfl)
        tfl.setMaxSelectCount(1)
        return view
    }

    private fun getFooterView(): View {
        val view = layoutInflater.inflate(R.layout.goods_footer_search, null)
        view.findViewById<TextView>(R.id.clearTV).setOnClickListener {
            viewModel.clearHistories()
        }
        return view
    }

}