package module.gift

import module.common.data.entity.Gift

/**
 *@author: baizf
 *@date: 2023/3/22
 */
data class SelectedGiftMsgEntity(val fragmentPosition: Int, val gift: Gift) {
}