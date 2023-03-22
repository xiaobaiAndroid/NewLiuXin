package module.common.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @describe: 礼物的分类
 * @date: 2020/3/8
 * @author: Mr Bai
 */
@Parcelize
class GiftCategory: Parcelable {

    var id: String? = null
    var giftTypeId = 0
    var giftTypeName: String? = null
    var giftName: Any? = null
    var giftPrice: Any? = null
    var giftUrl: String? = null
    var giftType: Any? = null
    var giftSvgaUrl: Any? = null
    var state: Any? = null
    var displayOrder: Any? = null
    var createBy: Any? = null
    var createTime: Any? = null
    var updateBy: Any? = null
    var updateTime: Any? = null
}