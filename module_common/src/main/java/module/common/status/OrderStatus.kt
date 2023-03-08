package module.common.status

/**
 * @describe: 订单状态 不传为全部，1待付款，2待发货，3待收货，4待评价，5已完成，8交易关闭，9已取消
 * @date: 2020/5/31
 * @author: Mr Bai
 */
enum class OrderStatus(val value:Int) {
    ALL(0),
    /*待付款*/
    WAIT_PAY(1),
    /*待发货*/
    WAIT_DELIVER(2),
    /*待收货*/
    WAIT_RECEIVE(3),
    /*待评价*/
    WAIT_APPRAISE(4),
    /*已完成*/
    FINIFSH(5),

//    /*待结算*/
//    WAIT_SETTLE(6),
//
//    /*已结算*/
//    YET_SETTLE(7),

    /*交易关闭*/
    CLOSE(8),
    CANCEL(9)
}