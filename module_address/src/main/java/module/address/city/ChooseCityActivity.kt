package module.address.city

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.promeg.pinyinhelper.Pinyin
import com.github.promeg.tinypinyin.lexicons.java.cncity.CnCityDict
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.address.R
import module.address.databinding.AddrActivityChooseCityBinding
import module.common.base.CommonListActivityBase
import module.common.event.MessageEvent
import module.common.utils.DensityUtil
import module.common.utils.KeyBoardUtils
import module.common.utils.StatusBarUtils
import module.common.utils.ToastUtils
import module.common.view.LinearDividerDecoration
import module.map.LocationBroker
import module.map.LocationHelper
import org.greenrobot.eventbus.EventBus

/*
* @describe:
* @author: bzf
* @date: 2023/3/26
*/
class ChooseCityActivity : CommonListActivityBase<AddrActivityChooseCityBinding, ChooseCityVModel>() {

    private var cityAdapter = CityAdapter(mutableListOf())

    private var hostCityAdapter = HostCityAdapter(ArrayList<CitySuspension>())
    private var suspensionDecoration: SuspensionDecoration? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var gpsCitySuspension: CitySuspension? = null
    private var searchResultAdapter = CityAdapter(mutableListOf())

    private lateinit var gpsCityTV:TextView



    private val permissionLaunch = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ map->
        val set = map.entries
        var granted = true
        set.forEach {
            granted = it.value
        }

        if (!granted) {
            ToastUtils.setMessage(
                applicationContext,
                resources.getString(R.string.addr_user_disable_location_permisison)
            )
        }
    }

    override fun createViewModel(): ChooseCityVModel {
        return viewModels<ChooseCityVModel>().value
    }

    override fun getBindingView(): AddrActivityChooseCityBinding {
        return AddrActivityChooseCityBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        LocationHelper.agreePrivacy()

        // 添加中文城市词典
        val with = Pinyin.newConfig().with(CnCityDict.getInstance())
        Pinyin.init(with)


        binding.actionBarView.setData(this, "")

        StatusBarUtils.setMarginStatusBarHeight(this,binding.searchRL)

        binding.searchCityET.isCursorVisible = false
        linearLayoutManager = LinearLayoutManager(applicationContext)
        binding.cityRV.layoutManager = linearLayoutManager
        binding.cityRV.adapter = cityAdapter

        cityAdapter.setEmptyView(getLoadingView())

        val view = layoutInflater.inflate(R.layout.map_header_gps_city,null)
        gpsCityTV = view.findViewById<TextView>(R.id.gpsCityTV)
        gpsCityTV.setOnClickListener {
            viewModel.gpsCityLD.value?.let {
                sendChooseCity(it)
            } ?: ToastUtils.setMessage(this,"未获取到定位的城市")
        }
        cityAdapter.setHeaderView(view)


        //设置间隔
        val spaceDecoration = LinearDividerDecoration<ColorDrawable>(cityAdapter
            , ColorDrawable(resources.getColor(module.common.R.color.cl_f1f1f1))
            , DensityUtil.dip2px(this, 1f))
        binding.cityRV.addItemDecoration(spaceDecoration)
        //设置悬浮导航栏
        suspensionDecoration = SuspensionDecoration(this, cityAdapter.data)
        suspensionDecoration!!.headerViewCount = cityAdapter.headerLayoutCount
        suspensionDecoration!!.setColorTitleBg(resources.getColor(R.color.map_cl_f5f5f5))
        suspensionDecoration!!.setTitleFontSize(DensityUtil.sp2px(applicationContext, 14f))
        suspensionDecoration!!.setColorTitleFont(resources.getColor(R.color.map_cl_999999))
        binding.cityRV.addItemDecoration(suspensionDecoration!!)

        binding.indexBar.setNeedRealIndex(true) //设置需要真实的索引
            .setmLayoutManager(linearLayoutManager) //设置RecyclerView的LayoutManager
            .setmPressedShowTextView(binding.letterTV).headerViewCount = cityAdapter.headerLayoutCount

        initSearchResultRV()
        initListener()
        checkPermission()

        setObserver()
    }

    private fun setObserver() {
        viewModel.gpsCityLD.observe(this) {
            gpsCityTV.text = it?.city
        }

        viewModel.citySuspensionLD.observe(this) {
            it?.let { citySuspensiions ->
                if (citySuspensiions.isEmpty()) {
                    cityAdapter.setEmptyView(getEmptyView())
                } else {
                    cityAdapter.setList(citySuspensiions)
                    binding.indexBar.setmSourceDatas(citySuspensiions).invalidate()
                    cityAdapter.setList(citySuspensiions)
                    suspensionDecoration!!.setmDatas(citySuspensiions)
                }

                cancelLoadingAnimation()
                startLocation()
            }
        }

        viewModel.searchListLD.observe(this) {
            searchResultAdapter.setList(it)
        }
    }


    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {

            } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                ToastUtils.setMessage(
                    this,
                    resources.getString(R.string.addr_user_disable_location_permisison)
                )
            } else {
                permissionLaunch.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }

        }
    }

    private fun startLocation() {
        val locationBroker = LocationBroker()
        lifecycleScope.launch(Dispatchers.IO) {
            val mapLocation = locationBroker.getLocationData(this@ChooseCityActivity,false)
            withContext(Dispatchers.Main){
                viewModel.findGpsCity(cityAdapter.data,mapLocation)
            }
        }

    }

    override fun initData(savedInstanceState: Bundle?) {
        gpsCitySuspension = DefaultCity()
        viewModel.getCityList()
    }


    private fun initListener() {
        binding.searchCityET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val key = s.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(key)) {
                    binding.clearKeyIB.visibility = View.VISIBLE
                    binding.searchFL.visibility = View.VISIBLE
                    viewModel.searchCityByKey(cityAdapter.data, key)
                } else {
                    binding.clearKeyIB.visibility = View.GONE
                    binding.searchFL.visibility = View.GONE
                }
            }
        })
        binding.clearKeyIB.setOnClickListener(View.OnClickListener {
            binding.searchCityET.setText("")
            KeyBoardUtils.closeKeybord(binding.searchCityET, this@ChooseCityActivity)
        })
        binding.searchCityET.setOnClickListener { binding.searchCityET.setCursorVisible(true) }

        hostCityAdapter.setOnItemClickListener { adapter, view, position ->
            val citySuspension = hostCityAdapter.data[position]
            sendChooseCity(citySuspension)
        }
        cityAdapter.setOnItemClickListener { adapter, view, position ->
            val citySuspension = cityAdapter!!.data[position]
            sendChooseCity(citySuspension)
        }
//        gpsCityTV!!.setOnClickListener { saveChooseCity(gpsCitySuspension) }
        searchResultAdapter.setOnItemClickListener { adapter, view, position ->
            val citySuspension = searchResultAdapter!!.data[position]
            sendChooseCity(citySuspension)
        }

        binding.searchCityET.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val key = binding.searchCityET.text.toString().trim()
                    viewModel.searchCityByKey(cityAdapter.data,key)
                    KeyBoardUtils.closeKeybord(binding.searchCityET, this@ChooseCityActivity)
                    return true
                } else {
                    return false
                }
            }})
    }


    /**
     * @describe 描述:发送选择的城市
     * @date:  2017/12/28
     */
    private fun sendChooseCity(citySuspension: CitySuspension?) {
        if (citySuspension != null) {
            val messageEvent = MessageEvent(MessageEvent.Type.SELECTED_CITY)
            messageEvent.obj  = citySuspension?.city_id.toString()
            EventBus.getDefault().post(messageEvent)
            onBackPressed()
        }
    }


    override fun onDestroy() {
        permissionLaunch.unregister()
        super.onDestroy()
    }

    /**
     * @describe 描述:初始化搜索结果列表
     * @author: Baizhengfu
     * @date:  2017/12/28
     */
    private fun initSearchResultRV() {
        binding.searchFL.visibility = View.GONE
        binding.clearKeyIB.visibility = View.GONE
        binding.searchResultRV.layoutManager = LinearLayoutManager(applicationContext)
        searchResultAdapter = CityAdapter(ArrayList())
        binding.searchResultRV.adapter = searchResultAdapter
        //设置间隔
        val spaceDecoration = LinearDividerDecoration<ColorDrawable>(cityAdapter
            , ColorDrawable(resources.getColor(module.common.R.color.cl_f1f1f1))
            , DensityUtil.dip2px(this, 1f))
        binding.searchResultRV.addItemDecoration(spaceDecoration)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        try {
            KeyBoardUtils.closeKeybord(binding.searchCityET, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}