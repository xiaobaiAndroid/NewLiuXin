package module.common.type

/**
 * @describe: 支付类型
 * @date: 2020/5/30
 * @author: Mr Bai
 */
enum class PayType(val value:Int) {

    ALIPAY(4),
    WECHAT(3),
    /*金铢*/
    VIRTUAL(6),
    /*余额*/
    BALANCE(5)

}