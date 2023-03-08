package module.common.data.response;

import module.common.data.api.BaseResp;

import java.io.Serializable;

/**
 * @describe: 发送红包的响应实体
 * @date: 2020/3/27
 * @author: Mr Bai
 */
public class SendRedPacketResp extends BaseResp<SendRedPacketResp.Data> {

    public static class Data implements Serializable {
        /*返回状态0-余额不足 1-成功*/
        private String status;

        /*	红包订单id*/
        private String orderInfoId;

        /*总金额*/
        private String totalPrice;

        /*描述*/
        private String describe;

        /*是否已领取*/
        private boolean isGet = false;


        public boolean isGet() {
            return isGet;
        }

        public void setGet(boolean get) {
            isGet = get;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderInfoId() {
            return orderInfoId;
        }

        public void setOrderInfoId(String orderInfoId) {
            this.orderInfoId = orderInfoId;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}
