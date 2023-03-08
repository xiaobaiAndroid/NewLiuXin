package module.common.data.request;

/**
 * @describe: 赠送礼物的请求实体
 * @date: 2020/3/8
 * @author: Mr Bai
 */
public class GiveGiftReq {

    /*发送礼物用户id*/
    private String fromUId;

    /*接收礼物用户id*/
    private String toUId;

    private String giftId;
    private String giftNum;

    public String getFromUId() {
        return fromUId;
    }

    public void setFromUId(String fromUId) {
        this.fromUId = fromUId;
    }

    public String getToUId() {
        return toUId;
    }

    public void setToUId(String toUId) {
        this.toUId = toUId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(String giftNum) {
        this.giftNum = giftNum;
    }
}
