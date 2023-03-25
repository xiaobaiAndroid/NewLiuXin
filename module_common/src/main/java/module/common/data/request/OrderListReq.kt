package module.common.data.request

import module.common.base.BaseListReq
import module.common.type.OrderType

/**
 * @describe: 订单列表请求实体
 * @date: 2020/5/31
 * @author: Mr Bai
 */
class OrderListReq(queryObj: QueryObj?) : BaseListReq<OrderListReq.QueryObj?>(queryObj) {
    class QueryObj {
        var userId: String? = null
        var orderType = OrderType.NORMAL.value
        var orderStatus: String? = null
    }
}