package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @describe: 商品的sku属性
 * @date: 2020/5/2
 * @author: Mr Bai
 */
public class GoodsSkuAttribute implements Parcelable {


    /**
     * skuAttrId : 1
     * skuAttrName : 模式
     * component : null
     * items : [{"skuAttrId":"1","skuAttrItemId":"1","skuAttrItemName":"零售"}]
     */

    private String skuAttrId;
    private String skuAttrName;
    private Object component;
    private List<GoodsSkuAttributeValue> items;

    protected GoodsSkuAttribute(Parcel in) {
        skuAttrId = in.readString();
        skuAttrName = in.readString();
        items = in.createTypedArrayList(GoodsSkuAttributeValue.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(skuAttrId);
        dest.writeString(skuAttrName);
        dest.writeTypedList(items);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GoodsSkuAttribute> CREATOR = new Creator<GoodsSkuAttribute>() {
        @Override
        public GoodsSkuAttribute createFromParcel(Parcel in) {
            return new GoodsSkuAttribute(in);
        }

        @Override
        public GoodsSkuAttribute[] newArray(int size) {
            return new GoodsSkuAttribute[size];
        }
    };

    public String getSkuAttrId() {
        return skuAttrId;
    }

    public void setSkuAttrId(String skuAttrId) {
        this.skuAttrId = skuAttrId;
    }

    public String getSkuAttrName() {
        return skuAttrName;
    }

    public void setSkuAttrName(String skuAttrName) {
        this.skuAttrName = skuAttrName;
    }

    public Object getComponent() {
        return component;
    }

    public void setComponent(Object component) {
        this.component = component;
    }

    public List<GoodsSkuAttributeValue> getItems() {
        return items;
    }

    public void setItems(List<GoodsSkuAttributeValue> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * skuAttrId : 1
         * skuAttrItemId : 1
         * skuAttrItemName : 零售
         */

        private String skuAttrId;
        private String skuAttrItemId;
        private String skuAttrItemName;

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
    }
}
