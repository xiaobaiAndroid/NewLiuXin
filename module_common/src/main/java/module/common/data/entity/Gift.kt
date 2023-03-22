package module.common.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @describe: 礼物
 * @date: 2020/3/8
 * @author: Mr Bai
 */
@Parcelize
class Gift : Parcelable {
    var id: String? = null
    var giftName: String? = null
    var giftUrl: String? = null
    var giftTypeId: String? = null
    var giftPrice: String? = null

    /*礼物类型 1-svga 2-gif 3-png*/
    var giftType: String? = null

    /*giftSvgaUrl动画url*/
    var giftSvgaUrl: String? = null

    /*排序*/
    var displayOrder: String? = null

    /*是否选中*/
    var isSelected = false

    /*礼物类型 1-svga 2-gif 3-png*/
    object Type {
        const val SVGA = "1"
        const val GIF = "2"
        const val PNG = "3"
    }
}