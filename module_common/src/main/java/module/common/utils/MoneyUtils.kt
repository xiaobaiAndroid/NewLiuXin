package module.common.utils

import java.math.BigDecimal

/**
 * @describe: 金钱工具类
 * @date: 2020/6/27
 * @author: Mr Bai
 */
object MoneyUtils {


        /**
         * @describe: 转换成显示的价格，保留两位小数
         * @date: 2020/6/27
         */
        fun convertShowPrice(price:String):Double{
            val value = BigDecimal(price).divide(BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
            //String.format("%.2f",value)
            return value
        }

}