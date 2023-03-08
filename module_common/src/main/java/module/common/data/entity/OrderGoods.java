package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 订单的商品
 * @date: 2020/5/31
 * @author: Mr Bai
 */
public class OrderGoods implements Parcelable {

    private String id;
    private String orderId;
    private String goodsId;
    private String goodsUrl;
    private String goodsTitle;
    private String goodsSkuId;
    private String goodsSkuCode;
    private String goodsSkuName;
    private String actId;
    private String storeId;
    private int buyCount;
    private int sumBuyCount;
    private int preCount;
    private int costPrice;
    private int salePrice;
    private int income1;
    private int income2;
    private int income3;
    private int total;
    private int backCount;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    protected OrderGoods(Parcel in) {
        id = in.readString();
        orderId = in.readString();
        goodsId = in.readString();
        goodsUrl = in.readString();
        goodsTitle = in.readString();
        goodsSkuId = in.readString();
        goodsSkuCode = in.readString();
        goodsSkuName = in.readString();
        actId = in.readString();
        storeId = in.readString();
        buyCount = in.readInt();
        sumBuyCount = in.readInt();
        preCount = in.readInt();
        costPrice = in.readInt();
        salePrice = in.readInt();
        income1 = in.readInt();
        income2 = in.readInt();
        income3 = in.readInt();
        total = in.readInt();
        backCount = in.readInt();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
    }

    public static final Creator<OrderGoods> CREATOR = new Creator<OrderGoods>() {
        @Override
        public OrderGoods createFromParcel(Parcel in) {
            return new OrderGoods(in);
        }

        @Override
        public OrderGoods[] newArray(int size) {
            return new OrderGoods[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(String goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    public String getGoodsSkuCode() {
        return goodsSkuCode;
    }

    public void setGoodsSkuCode(String goodsSkuCode) {
        this.goodsSkuCode = goodsSkuCode;
    }

    public String getGoodsSkuName() {
        return goodsSkuName;
    }

    public void setGoodsSkuName(String goodsSkuName) {
        this.goodsSkuName = goodsSkuName;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public int getSumBuyCount() {
        return sumBuyCount;
    }

    public void setSumBuyCount(int sumBuyCount) {
        this.sumBuyCount = sumBuyCount;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBackCount() {
        return backCount;
    }

    public void setBackCount(int backCount) {
        this.backCount = backCount;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(orderId);
        dest.writeString(goodsId);
        dest.writeString(goodsUrl);
        dest.writeString(goodsTitle);
        dest.writeString(goodsSkuId);
        dest.writeString(goodsSkuCode);
        dest.writeString(goodsSkuName);
        dest.writeString(actId);
        dest.writeString(storeId);
        dest.writeInt(buyCount);
        dest.writeInt(sumBuyCount);
        dest.writeInt(preCount);
        dest.writeInt(costPrice);
        dest.writeInt(salePrice);
        dest.writeInt(income1);
        dest.writeInt(income2);
        dest.writeInt(income3);
        dest.writeInt(total);
        dest.writeInt(backCount);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
    }
}
