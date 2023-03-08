package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 商品的sku属性值
 * @date: 2020/5/2
 * @author: Mr Bai
 */
public class GoodsSkuAttributeValue implements Parcelable {


    /**
     * skuAttrId : 1
     * skuAttrItemId : 1
     * skuAttrItemName : 零售
     */

    private String skuAttrId;
    private String skuAttrItemId;
    private String skuAttrItemName;

    /*是否是可选状态*/
    private boolean enable;

    /*是否选中状态*/
    private boolean isSelected;

    protected GoodsSkuAttributeValue(Parcel in) {
        skuAttrId = in.readString();
        skuAttrItemId = in.readString();
        skuAttrItemName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(skuAttrId);
        dest.writeString(skuAttrItemId);
        dest.writeString(skuAttrItemName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GoodsSkuAttributeValue> CREATOR = new Creator<GoodsSkuAttributeValue>() {
        @Override
        public GoodsSkuAttributeValue createFromParcel(Parcel in) {
            return new GoodsSkuAttributeValue(in);
        }

        @Override
        public GoodsSkuAttributeValue[] newArray(int size) {
            return new GoodsSkuAttributeValue[size];
        }
    };

    public String getSkuAttrId() {
        return skuAttrId;
    }

    public void setSkuAttrId(String skuAttrId) {
        this.skuAttrId = skuAttrId;
    }

    public String getSkuAttrItemId() {
        return skuAttrItemId;
    }

    public void setSkuAttrItemId(String skuAttrItemId) {
        this.skuAttrItemId = skuAttrItemId;
    }

    public String getSkuAttrItemName() {
        return skuAttrItemName;
    }

    public void setSkuAttrItemName(String skuAttrItemName) {
        this.skuAttrItemName = skuAttrItemName;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
