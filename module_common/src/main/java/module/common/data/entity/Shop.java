package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 店铺
 * @date: 2020/5/3
 * @author: Mr Bai
 */
public class Shop implements Parcelable {


    /**
     * storeId : 351886575548116992
     * storeName : 褐马内衣
     * storeLogo : https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/goods/2019/12/%E8%A4%90.jpg
     * goodsCount : 141
     */

    private String storeId;
    private String storeName;
    private String storeLogo;
    private int goodsCount;

    protected Shop(Parcel in) {
        storeId = in.readString();
        storeName = in.readString();
        storeLogo = in.readString();
        goodsCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storeId);
        dest.writeString(storeName);
        dest.writeString(storeLogo);
        dest.writeInt(goodsCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}
