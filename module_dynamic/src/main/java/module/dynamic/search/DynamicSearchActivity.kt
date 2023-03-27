package module.dynamic.search

import android.content.Intent
import android.graphics.drawable.GradientDrawable
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
import module.common.data.entity.HistorySearch
import module.common.utils.*
import module.dynamic.R
import module.dynamic.databinding.DynamicActivityDynamicSearchBinding
import module.dynamic.search.result.SearchResultActivity

/*
动态搜索
* @describe:
* @author: bzf
* @date: 2023/3/26
*/
class DynamicSearchActivity : BaseActivity<DynamicActivityDynamicSearchBinding, DynamicSearchVModel>() {

    val historyAdapter = HistoryAdapter(mutableListOf())
    lateinit var tfl: TagFlowLayout<HotSearchEntity>

    override fun createViewModel(): DynamicSearchVModel {
        return viewModels<DynamicSearchVModel>().value
    }

    override fun getBindingView(): DynamicActivityDynamicSearchBinding {
        return DynamicActivityDynamicSearchBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this,binding.searchET)

        binding.contentRV.layoutManager = LinearLayoutManager(this)
        binding.contentRV.adapter = historyAdapter
        historyAdapter.setHeaderView(getHeaderView())

        tfl.adapter = mTagAdapter

        initListenter()
        setObserver()
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

    private val mTagAdapter = object:TagAdapter<HotSearchEntity>(mutableListOf()){
        override fun getView(parent: FlowLayout?, position: Int, entity: HotSearchEntity): View? {
            val tagTV = LayoutInflater.from(this@DynamicSearchActivity).inflate(R.layout.clique_item_hot_search, null) as TextView
            tagTV.text = entity.title
            val gradientDrawable = GradientDrawable()
            gradientDrawable.cornerRadius = DensityUtil.dip2pxFloat(applicationContext,6f)
            gradientDrawable.setColor(entity.color)
            tagTV.background  =gradientDrawable
            return tagTV
        }

    }

    private fun DynamicSearchActivity.initListenter() {
        binding.cancelTV.setOnClickListener {
            onBackPressed()
        }

        binding.clearIV.setOnClickListener {
            binding.searchET.setText("")
        }

        binding.searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val content = s.toString()
                if (content.isNullOrEmpty()) {
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
                        this@DynamicSearchActivity
                            .currentFocus!!.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                    KeyBoardUtils.closeKeybord(binding.searchET, this@DynamicSearchActivity)
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
            historyAdapter.removeAt(position)
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

    override fun initData(savedInstanceState: Bundle?) {
        viewModel.getHotSearch()
        viewModel.getHistories()
    }


    private fun search(keyword: String?) {
        if (keyword.isNullOrEmpty()) {
            ToastUtils.setMessage(this, resources.getString(R.string.clique_input_key))
        } else {
            viewModel.saveSearchHistory(keyword)
            val intent = Intent(this, SearchResultActivity::class.java)
            intent.putExtra("keyword",keyword)
            startActivity(intent)
        }
    }

    private fun getHeaderView(): View {
        val view = LayoutInflater.from(this).inflate(R.layout.dynamic_header_history, null)
        tfl = view.findViewById<TagFlowLayout<HotSearchEntity>>(R.id.tfl)
        tfl.setMaxSelectCount(1)
        return view
    }

    private fun getFooterView(): View {
        val view = layoutInflater.inflate(R.layout.dynamic_footer_search, null)
        view.findViewById<TextView>(R.id.clearTV).setOnClickListener {
            viewModel.clearHistories()
        }
        return view
    }
}