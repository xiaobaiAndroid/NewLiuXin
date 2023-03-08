package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class GoodsDetailSku implements Parcelable {


    /**
     * id : 405958425609383936
     * goodsId : 405958424497893376
     * skuCode : 1,103,94
     * preCount : 1
     * costPrice : 510
     * salePrice : 1200
     * income1 : 200
     * income2 : 100
     * income3 : 0
     * stock : 999
     * delFlag : 0
     * createBy : 13688879888
     * createTime : 2020-04-30 18:22:01
     * updateBy : 13688879888
     * updateTime : 2020-04-30 18:22:01
     */

    private String id;
    private String goodsId;
    /*规格编码*/
    private String skuCode;

    /*每次购买数量，默认为1，主要为一手必填*/
    private int preCount;
    /*成本价（分）*/
    private int costPrice;
    /*销售价（分）*/
    private int salePrice;
    /*收益1（分）*/
    private int income1;
    /*收益2（分）*/
    private int income2;
    /*收益3（分）*/
    private int income3;
    /*库存*/
    private int stock;
    private int delFlag;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    public GoodsDetailSku() {
    }

    protected GoodsDetailSku(Parcel in) {
        id = in.readString();
        goodsId = in.readString();
        skuCode = in.readString();
        preCount = in.readInt();
        costPrice = in.readInt();
        salePrice = in.readInt();
        income1 = in.readInt();
        income2 = in.readInt();
        income3 = in.readInt();
        stock = in.readInt();
        delFlag = in.readInt();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(goodsId);
        dest.writeString(skuCode);
        dest.writeInt(preCount);
        dest.writeInt(costPrice);
        dest.writeInt(salePrice);
        dest.writeInt(income1);
        dest.writeInt(income2);
        dest.writeInt(income3);
        dest.writeInt(stock);
        dest.writeInt(delFlag);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GoodsDetailSku> CREATOR = new Creator<GoodsDetailSku>() {
        @Override
        public GoodsDetailSku createFromParcel(Parcel in) {
            return new GoodsDetailSku(in);
        }

        @Override
        public GoodsDetailSku[] newArray(int size) {
            return new GoodsDetailSku[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public int getPreCount() {
        return preCount;
    }

    public void setPreCount(int preCount) {
        this.preCount = preCount;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getIncome1() {
        return income1;
    }

    public void setIncome1(int income1) {
        this.income1 = income1;
    }

    public int getIncome2() {
        return income2;
    }

    public void setIncome2(int income2) {
        this.income2 = income2;
    }

    public int getIncome3() {
        return income3;
    }

    public void setIncome3(int income3) {
        this.income3 = income3;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
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
}
