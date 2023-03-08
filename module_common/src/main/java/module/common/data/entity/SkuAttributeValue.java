package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class SkuAttributeValue implements Parcelable {


    /**
     * id : 94
     * skuAttrId : 9
     * frontName : 70A=32A单件
     * backName : 70A=32A单件
     * sort : 1
     * createBy : 13800138001
     * createTime : 2020-03-28 14:37:33
     * updateBy : 13800138001
     * updateTime : 2020-04-26 15:55:39
     */

    private String id;
    private String skuAttrId;
    private String frontName;
    private String backName;
    private int sort;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    private String skuAttrItemId;
    private String skuAttrItemName;

    private boolean isSelect;

    public SkuAttributeValue() {
    }

    protected SkuAttributeValue(Parcel in) {
        id = in.readString();
        skuAttrId = in.readString();
        frontName = in.readString();
        backName = in.readString();
        sort = in.readInt();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
        skuAttrItemId = in.readString();
        skuAttrItemName = in.readString();
        isSelect = in.readByte() != 0;
    }

    public static final Creator<SkuAttributeValue> CREATOR = new Creator<SkuAttributeValue>() {
        @Override
        public SkuAttributeValue createFromParcel(Parcel in) {
            return new SkuAttributeValue(in);
        }

        @Override
        public SkuAttributeValue[] newArray(int size) {
            return new SkuAttributeValue[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuAttrId() {
        return skuAttrId;
    }

    public void setSkuAttrId(String skuAttrId) {
        this.skuAttrId = skuAttrId;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    public String getBackName() {
        return backName;
    }

    public void setBackName(String backName) {
        this.backName = backName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(skuAttrId);
        dest.writeString(frontName);
        dest.writeString(backName);
        dest.writeInt(sort);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
        dest.writeString(skuAttrItemId);
        dest.writeString(skuAttrItemName);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }
}
