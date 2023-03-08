package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 浏币
 * @date: 2020/3/27
 * @author: Mr Bai
 */
public class Money implements Parcelable {

    /*账户id*/
    private String id;

    /*金额*/
    private String money;

    /*冻结金额*/
    private String freezeMoney;

    public Money() {
    }

    protected Money(Parcel in) {
        id = in.readString();
        money = in.readString();
        freezeMoney = in.readString();
    }

    public static final Creator<Money> CREATOR = new Creator<Money>() {
        @Override
        public Money createFromParcel(Parcel in) {
            return new Money(in);
        }

        @Override
        public Money[] newArray(int size) {
            return new Money[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(String freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(money);
        dest.writeString(freezeMoney);
    }
}
