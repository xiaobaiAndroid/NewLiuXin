package module.common.data.request;

/**
 * @describe:支付请求
 * @date: 2020/4/5
 * @author: Mr Bai
 */
public class PayReq {

//    主单ID
    private String mainId;

    /*苹果内购支付号*/
    private String idoorId = "888";

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getIdoorId() {
        return idoorId;
    }

    public void setIdoorId(String idoorId) {
        this.idoorId = idoorId;
    }

    public enum PayType{
        OTHER,
        ALIPAY,
        WECHAT
    }
}
