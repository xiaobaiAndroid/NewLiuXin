package module.address.city

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.promeg.pinyinhelper.Pinyin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseViewModel
import module.common.data.DataResult
import module.common.data.entity.City
import module.common.data.respository.address.AddressRepository
import module.map.entity.MapLocation
import java.util.*

/**
 *@author: baizf
 *@date: 2023/3/26
 */

class ChooseCityVModel: BaseViewModel(){

     val gpsCityLD: MutableLiveData<CitySuspension?> by lazy {
        MutableLiveData<CitySuspension?>()
    }
    val citySuspensionLD: MutableLiveData<MutableList<CitySuspension>?> by lazy {
        MutableLiveData<MutableList<CitySuspension>?>()
    }
    val searchListLD: MutableLiveData<MutableList<CitySuspension>?> by lazy {
        MutableLiveData<MutableList<CitySuspension>?>()
    }

    fun searchCityByKey(list: MutableList<CitySuspension>, key: String) = viewModelScope.launch(Dispatchers.Default){
        val searchList: MutableList<CitySuspension> = mutableListOf()
        for (i in list.indices) {
            val citySuspension: CitySuspension = list[i]
            citySuspension.city?.let {
                if (citySuspension.city.contains(key)) {
                    searchList.add(citySuspension)
                }
            }
        }
        withContext(Dispatchers.Main){
            searchListLD.value = searchList
        }
    }

    fun getCityList() = viewModelScope.launch(Dispatchers.IO){
        val citySuspensions: MutableList<CitySuspension> = ArrayList()
        val cityList: MutableList<City> = ArrayList<City>()
        val provinceDR: DataResult<MutableList<City>?> = AddressRepository.instance.getProvinces(mContext)
        provinceDR.t?.let {provinces->
            for (province in provinces) {
                val cityDR: DataResult<MutableList<City>?> = AddressRepository.instance.getCities(mContext,province.code.toString())
                cityDR.t?.let { cities->
                    cityList.addAll(cities)
                }
            }
        }

        for (city in cityList) {
            val citySuspension = CitySuspension()
            citySuspension.province_id = city.parentCode
            citySuspension.city_id = city.code
            citySuspension.city = city.name
            citySuspension.baseIndexPinyin = Pinyin.toPinyin(city.name, "")
            val letter = citySuspension.baseIndexPinyin.substring(0, 1).uppercase(Locale.ROOT)
            if (letter.matches(Regex("[A-Z]"))) {
                citySuspension.baseIndexTag = letter
            } else {
                citySuspension.baseIndexTag = "#"
            }
            citySuspensions.add(citySuspension)
        }

        withContext(Dispatchers.Main){
            citySuspensionLD.value = citySuspensions
        }
    }

    fun findGpsCity(list: MutableList<CitySuspension>, location: MapLocation?)  = viewModelScope.launch(Dispatchers.Default){
        var gpsCity: CitySuspension? = CitySuspension()
        val city: String = location?.city ?: ""
        for (cs in list) {
            if (cs.city.contains(city)) {
                gpsCity = cs
                break
            }
        }
        withContext(Dispatchers.Main){
            gpsCityLD.value = gpsCity
        }
    }

}