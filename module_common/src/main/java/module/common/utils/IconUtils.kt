package module.common.utils

import android.graphics.PorterDuff
import android.widget.ImageView

/**
 * @describe: 图标工具类
 * @date: 2020/3/18
 * @author: baizhengfu
 */
object IconUtils {


    /**
     * @describe: 设置icon的颜色
     * @date: 2020/3/18
     * @param color zhi为0表示icon原来的颜色
     */
    fun setColor(imageView: ImageView, color: Int) {
        if (color == 0) {
            imageView.setColorFilter(0)

        } else {
            imageView.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }

    }

}