package module.common.data.entity

import com.contrarywind.interfaces.IPickerViewData

/**
 * @describe: 城市
 * @date: 2020/4/5
 * @author: Mr Bai
 */
class City : IPickerViewData {
    /**
     * id : 122377
     * code : 440000
     * parentCode : 0
     * name : 广东省
     */
    var id: String? = null
    var code = 0
    var parentCode = 0
    var cityId: String? = null
    var name: String? = null

    override fun getPickerViewText(): String {
        return name!!
    }
}