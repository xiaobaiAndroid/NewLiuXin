package module.common.type

/**
 * @describe: 订单类型
 * @date: 2020/5/31
 * @author: Mr Bai
 */
enum class OrderType(val value:Int) {

    /*正常*/
    NORMAL(1),
    /*拼团*/
    GROUP(2),
    /*旅游*/
    TRAVEL(4)
}