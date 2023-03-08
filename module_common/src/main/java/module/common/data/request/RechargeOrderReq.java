package module.common.data.request;

public class RechargeOrderReq {

    /*充值id*/
    private String topUpId;

//    0未知，1微信公众号，2微信H5，3微信App，4支付宝，5浏币 ,7-苹果
    private String payWay;


    public String getTopUpId() {
        return topUpId;
    }

    public void setTopUpId(String topUpId) {
        this.topUpId = topUpId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public enum PayWay{
        OTHER,
        WEICHAT_PUBLIC,
        WECHAT_H5,
        WECHAT,
        ALIPAY,
        LIUCHAT_MONEY
    }
}
