package module.common.data.request;

import module.common.base.BaseListReq;
import module.common.type.OrderType;

/**
 * @describe: 订单列表请求实体
 * @date: 2020/5/31
 * @author: Mr Bai
 */
public class OrderListReq extends BaseListReq<OrderListReq.QueryObj> {

    public static class QueryObj{
        private String userId;
        private int orderType = OrderType.NORMAL.getValue();
        private String orderStatus;

       public String getUserId() {
           return userId;
       }

       public void setUserId(String userId) {
           this.userId = userId;
       }

       public int getOrderType() {
           return orderType;
       }

       public void setOrderType(int orderType) {
           this.orderType = orderType;
       }

       public String getOrderStatus() {
           return orderStatus;
       }

       public void setOrderStatus(String orderStatus) {
           this.orderStatus = orderStatus;
       }
   }
}
