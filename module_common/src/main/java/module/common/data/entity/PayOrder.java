package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @describe: 支付订单实体
 * @date: 2020/5/30
 * @author: Mr Bai
 */
public class PayOrder implements Parcelable {


    /**
     * mainId : 416683016564649984
     * orderIds : ["416683016564649984"]
     * orderTotal : 2000
     * orderTime : 2020-05-30 08:37:42
     * useTime : null
     * endTime : null
     * payType : null
     */

    private String mainId;
    private String orderTotal;
    private String orderTime;
    private String useTime;
    private String endTime;
    private String payType;
    private List<String> orderIds;

    public PayOrder() {
    }

    protected PayOrder(Parcel in) {
        mainId = in.readString();
        orderTotal = in.readString();
        orderTime = in.readString();
        useTime = in.readString();
        endTime = in.readString();
        payType = in.readString();
        orderIds = in.createStringArrayList();
    }

    public static final Creator<PayOrder> CREATOR = new Creator<PayOrder>() {
        @Override
        public PayOrder createFromParcel(Parcel in) {
            return new PayOrder(in);
        }

        @Override
        public PayOrder[] newArray(int size) {
            return new PayOrder[size];
        }
    };

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mainId);
        dest.writeString(orderTotal);
        dest.writeString(orderTime);
        dest.writeString(useTime);
        dest.writeString(endTime);
        dest.writeString(payType);
        dest.writeStringList(orderIds);
    }
}
