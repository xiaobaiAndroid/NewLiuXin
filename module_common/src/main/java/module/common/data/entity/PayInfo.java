package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 支付信息
 * @date: 2020/5/30
 * @author: Mr Bai
 */
public class PayInfo implements Parcelable {

   private String string;
   private String orderTotal;
   private String orderTime;

   /*0未付款,1已付款*/
   private int payStatus;

    private Status res;

    public PayInfo() {
    }

    protected PayInfo(Parcel in) {
        string = in.readString();
        orderTotal = in.readString();
        orderTime = in.readString();
        payStatus = in.readInt();
        res = in.readParcelable(Status.class.getClassLoader());
    }

    public static final Creator<PayInfo> CREATOR = new Creator<PayInfo>() {
        @Override
        public PayInfo createFromParcel(Parcel in) {
            return new PayInfo(in);
        }

        @Override
        public PayInfo[] newArray(int size) {
            return new PayInfo[size];
        }
    };

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
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

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public Status getRes() {
        return res;
    }

    public void setRes(Status res) {
        this.res = res;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(string);
        dest.writeString(orderTotal);
        dest.writeString(orderTime);
        dest.writeInt(payStatus);
        dest.writeParcelable(res, flags);
    }

    public static class Status implements Parcelable{


        /*1为支付成功或者是支付宝签名*/
        private String code;

        protected Status(Parcel in) {
            code = in.readString();
        }

        public static final Creator<Status> CREATOR = new Creator<Status>() {
            @Override
            public Status createFromParcel(Parcel in) {
                return new Status(in);
            }

            @Override
            public Status[] newArray(int size) {
                return new Status[size];
            }
        };

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(code);
        }
    }
}
