package module.gift

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import module.common.data.entity.Gift
import java.io.Serializable

/**
 * @describe: 赠送礼物
 * @date: 2020/3/8
 * @author: Mr Bai
 */
@Parcelize
data class EGiveGift(
    val selectedNumber: Int = 1,
    val selectGift: Gift) : Parcelable

