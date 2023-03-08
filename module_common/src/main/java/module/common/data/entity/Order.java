package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @describe: 订单
 * @date: 2020/5/31
 * @author: Mr Bai
 */
public class Order implements Parcelable {


    /**
     * id : 416893398809980928
     * mainId : 416893398809980928
     * orderType : 4
     * groupId : 0
     * userId : 415083361825337345
     * goodsTotal : 2000
     * shippingFee : 0
     * orderTotal : 2000
     * storeId : 412923096442744832
     * storeType : 2
     * storeName : 大漠商旅
     * orderStatus : 1
     * orderSource : APP
     * orderRemark : null
     * orderTime : 2020-05-30 22:33:41
     * payWay : 6
     * payStatus : 1
     * payNo : null
     * payTime : null
     * expressWay : 1
     * shippingStatus : 0
     * shippingTime : null
     * receiveTime : null
     * useTime : null
     * endTime : null
     * useMan : 测试一
     * linkPhone :
     * createBy : auto
     * createTime : 2020-05-30 22:33:41
     * updateBy : auto
     * updateTime : 2020-05-30 22:33:50
     * group : null
     * orderGoodsList : [{"id":"416893398826758144","orderId":"416893398809980928","goodsId":"412927547794665472","goodsUrl":"https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/goods/2020/5/3334027850709840s44.jpg.png","goodsTitle":"水上乐园","goodsSkuId":"412927547844997120","goodsSkuCode":"122","goodsSkuName":"儿童","actId":"0","storeId":"412923096442744832","buyCount":1,"sumBuyCount":null,"preCount":1,"costPrice":1000,"salePrice":2000,"income1":0,"income2":0,"income3":0,"total":2000,"backCount":null,"createBy":"auto","createTime":"2020-05-30 22:33:41","updateBy":"auto","updateTime":"2020-05-30 22:33:41"}]
     * orderShipping : null
     */

    private String id;
    private String mainId;
    private int orderType;
    private String groupId;
    private String userId;
    private int goodsTotal;
    private int shippingFee;
    private int orderTotal;
    private String storeId;
    private int storeType;
    private String storeName;
    private int orderStatus;
    private String orderSource;
    private String orderRemark;
    private String orderTime;
    private int payWay;
    private int payStatus;

    /*支付号*/
    private String payNo;
    private String payTime;
    private int expressWay;
    private int shippingStatus;
    private String shippingTime;
    private String receiveTime;
    private String useTime;
    private String endTime;
    private String useMan;
    private String linkPhone;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private Object group;

    /*配送信息*/
    private OrderShipping orderShipping;
    private List<OrderGoods> orderGoodsList;

    protected Order(Parcel in) {
        id = in.readString();
        mainId = in.readString();
        orderType = in.readInt();
        groupId = in.readString();
        userId = in.readString();
        goodsTotal = in.readInt();
        shippingFee = in.readInt();
        orderTotal = in.readInt();
        storeId = in.readString();
        storeType = in.readInt();
        storeName = in.readString();
        orderStatus = in.readInt();
        orderSource = in.readString();
        orderRemark = in.readString();
        orderTime = in.readString();
        payWay = in.readInt();
        payStatus = in.readInt();
        payNo = in.readString();
        payTime = in.readString();
        expressWay = in.readInt();
        shippingStatus = in.readInt();
        shippingTime = in.readString();
        receiveTime = in.readString();
        useTime = in.readString();
        endTime = in.readString();
        useMan = in.readString();
        linkPhone = in.readString();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
        orderShipping = in.readParcelable(OrderShipping.class.getClassLoader());
        orderGoodsList = in.createTypedArrayList(OrderGoods.CREATOR);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(int goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public int getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(int shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getExpressWay() {
        return expressWay;
    }

    public void setExpressWay(int expressWay) {
        this.expressWay = expressWay;
    }

    public int getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(int shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
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

    public String getUseMan() {
        return useMan;
    }

    public void setUseMan(String useMan) {
        this.useMan = useMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
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

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(mainId);
        dest.writeInt(orderType);
        dest.writeString(groupId);
        dest.writeString(userId);
        dest.writeInt(goodsTotal);
        dest.writeInt(shippingFee);
        dest.writeInt(orderTotal);
        dest.writeString(storeId);
        dest.writeInt(storeType);
        dest.writeString(storeName);
        dest.writeInt(orderStatus);
        dest.writeString(orderSource);
        dest.writeString(orderRemark);
        dest.writeString(orderTime);
        dest.writeInt(payWay);
        dest.writeInt(payStatus);
        dest.writeString(payNo);
        dest.writeString(payTime);
        dest.writeInt(expressWay);
        dest.writeInt(shippingStatus);
        dest.writeString(shippingTime);
        dest.writeString(receiveTime);
        dest.writeString(useTime);
        dest.writeString(endTime);
        dest.writeString(useMan);
        dest.writeString(linkPhone);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
        dest.writeParcelable(orderShipping, flags);
        dest.writeTypedList(orderGoodsList);
    }
}
