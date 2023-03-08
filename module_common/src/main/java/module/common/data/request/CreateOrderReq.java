package module.common.data.request;

import android.os.Parcel;
import android.os.Parcelable;

import module.common.data.entity.SkuAttributeValue;

import java.util.List;

/**
 * @describe: 创建订单请求
 * @date: 2020/5/27
 * @author: Mr Bai
 */
public class CreateOrderReq implements Parcelable{

    /*收货地址id*/
    private String shippingId;

    private OrderUser userShipping;

    /*	店铺购物车列表*/
    private List<StoreCart> storeCartList;

    /*使用人*/
    private String useMan;
    /*联系手机*/
    private String linkPhone;

    /*备注*/
    private String orderRemark;

    /*旅游产品：4，普通商品1*/
    private String orderType;

    /*开始时间*/
    private String useTime;

    /*结束时间*/
    private String endTime;

    public CreateOrderReq() {
    }

    protected CreateOrderReq(Parcel in) {
        shippingId = in.readString();
        userShipping = in.readParcelable(OrderUser.class.getClassLoader());
        storeCartList = in.createTypedArrayList(StoreCart.CREATOR);
        useMan = in.readString();
        linkPhone = in.readString();
        orderRemark = in.readString();
        orderType = in.readString();
        useTime = in.readString();
        endTime = in.readString();
    }

    public static final Creator<CreateOrderReq> CREATOR = new Creator<CreateOrderReq>() {
        @Override
        public CreateOrderReq createFromParcel(Parcel in) {
            return new CreateOrderReq(in);
        }

        @Override
        public CreateOrderReq[] newArray(int size) {
            return new CreateOrderReq[size];
        }
    };

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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public OrderUser getUserShipping() {
        return userShipping;
    }

    public void setUserShipping(OrderUser userShipping) {
        this.userShipping = userShipping;
    }

    public List<StoreCart> getStoreCartList() {
        return storeCartList;
    }

    public void setStoreCartList(List<StoreCart> storeCartList) {
        this.storeCartList = storeCartList;
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

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shippingId);
        dest.writeParcelable(userShipping, flags);
        dest.writeTypedList(storeCartList);
        dest.writeString(useMan);
        dest.writeString(linkPhone);
        dest.writeString(orderRemark);
        dest.writeString(orderType);
        dest.writeString(useTime);
        dest.writeString(endTime);
    }

    /**
     * @describe: 创建订单的用户信息
     * @date: 2020/5/27
     */
    public static class OrderUser implements Parcelable{

        private String id;
        private String userId;
        private String consignee;
        private String contactsWay;
        private Integer provinceCode;
        private String provinceName;
        private Integer cityCode;
        private String cityName;
        private Integer countyCode;
        private String countyName;
        private Integer streetCode;
        private String streetName;
        private String fullAddress;
        private String zipCode;
        private Integer checkStatus = 1;
        private String remark="";

        public OrderUser() {
        }

        protected OrderUser(Parcel in) {
            id = in.readString();
            userId = in.readString();
            consignee = in.readString();
            contactsWay = in.readString();
            if (in.readByte() == 0) {
                provinceCode = null;
            } else {
                provinceCode = in.readInt();
            }
            provinceName = in.readString();
            if (in.readByte() == 0) {
                cityCode = null;
            } else {
                cityCode = in.readInt();
            }
            cityName = in.readString();
            if (in.readByte() == 0) {
                countyCode = null;
            } else {
                countyCode = in.readInt();
            }
            countyName = in.readString();
            if (in.readByte() == 0) {
                streetCode = null;
            } else {
                streetCode = in.readInt();
            }
            streetName = in.readString();
            fullAddress = in.readString();
            zipCode = in.readString();
            if (in.readByte() == 0) {
                checkStatus = null;
            } else {
                checkStatus = in.readInt();
            }
            remark = in.readString();
        }

        public static final Creator<OrderUser> CREATOR = new Creator<OrderUser>() {
            @Override
            public OrderUser createFromParcel(Parcel in) {
                return new OrderUser(in);
            }

            @Override
            public OrderUser[] newArray(int size) {
                return new OrderUser[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getContactsWay() {
            return contactsWay;
        }

        public void setContactsWay(String contactsWay) {
            this.contactsWay = contactsWay;
        }

        public Integer getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(Integer provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public Integer getCityCode() {
            return cityCode;
        }

        public void setCityCode(Integer cityCode) {
            this.cityCode = cityCode;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public Integer getCountyCode() {
            return countyCode;
        }

        public void setCountyCode(Integer countyCode) {
            this.countyCode = countyCode;
        }

        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }

        public Integer getStreetCode() {
            return streetCode;
        }

        public void setStreetCode(Integer streetCode) {
            this.streetCode = streetCode;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getFullAddress() {
            return fullAddress;
        }

        public void setFullAddress(String fullAddress) {
            this.fullAddress = fullAddress;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public Integer getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(Integer checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(userId);
            dest.writeString(consignee);
            dest.writeString(contactsWay);
            if (provinceCode == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(provinceCode);
            }
            dest.writeString(provinceName);
            if (cityCode == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(cityCode);
            }
            dest.writeString(cityName);
            if (countyCode == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(countyCode);
            }
            dest.writeString(countyName);
            if (streetCode == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(streetCode);
            }
            dest.writeString(streetName);
            dest.writeString(fullAddress);
            dest.writeString(zipCode);
            if (checkStatus == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(checkStatus);
            }
            dest.writeString(remark);
        }
    }

    /**
     * @describe: 订单的店铺信息
     * @date: 2020/5/27
     */
    public static class StoreCart implements Parcelable{

        private String storeId;
        private String storeName;
        private String logo;

        /*店铺下的购物车列表*/
        private List<Cart> cartList;

        /*	配送方式 目前只支持：快递1*/
        private Integer expressType  =1;
        private String remark;

        public StoreCart() {
        }

        protected StoreCart(Parcel in) {
            storeId = in.readString();
            storeName = in.readString();
            logo = in.readString();
            cartList = in.createTypedArrayList(Cart.CREATOR);
            if (in.readByte() == 0) {
                expressType = null;
            } else {
                expressType = in.readInt();
            }
            remark = in.readString();
        }

        public static final Creator<StoreCart> CREATOR = new Creator<StoreCart>() {
            @Override
            public StoreCart createFromParcel(Parcel in) {
                return new StoreCart(in);
            }

            @Override
            public StoreCart[] newArray(int size) {
                return new StoreCart[size];
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public List<Cart> getCartList() {
            return cartList;
        }

        public void setCartList(List<Cart> cartList) {
            this.cartList = cartList;
        }

        public Integer getExpressType() {
            return expressType;
        }

        public void setExpressType(Integer expressType) {
            this.expressType = expressType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(storeId);
            dest.writeString(storeName);
            dest.writeString(logo);
            dest.writeTypedList(cartList);
            if (expressType == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(expressType);
            }
            dest.writeString(remark);
        }
    }

    public static class Cart implements Parcelable {
        /**
         * id : 321598524628545536
         * userId : 308133608336797696
         * goodsId : 1
         * goodsCount : 1
         * activityId : 1
         * finalPrice : 80.0
         * storeId : 1111
         * storeName : 示例店
         * state : 1
         * createBy : 13544588991
         * createTime : 2019-09-10 23:26:04
         * updateBy : 13544588991
         * updateTime : 2019-09-10 23:26:04
         * activity : {"id":"1","actType":1,"actName":"1限量特价","storeId":"1111","state":1,"startTime":"2019-09-10 14:47:33","endTime":"2019-12-17 14:47:35","actualEndTime":"2019-12-10 14:47:48","createBy":"admin","createTime":"2019-09-10 14:48:05","updateBy":"admin","updateTime":"2019-09-10 14:48:18","actBookedGoods":null}
         * goods : {"id":"1","cateId":"2","keyword":"商品","goodsUrl":"https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/goods/goods1.jpg","goodsName":"示例商品","goodsTitle":"2019秋冬女装春装新款上衣连衣裙百搭打底衫女长袖","price":200,"profit":20,"sales":999,"storeId":"1111","state":1,"createBy":"admin","createTime":"2019-08-21 11:06:06","updateBy":"admin","updateTime":"2019-08-21 11:06:10","cate":null}
         */

        //购物车带参数
        private String cartId;

        //立即购买和购物车必带参数
        private String goodsId;
        private String goodsSkuId;
        private String actId;
        private Integer buyCount;

        //下单界面参数
        private String goodsUrl;
        private String goodsTitle;
        private Integer salePrice;
        private Integer income1;

        private List<SkuAttributeValue> skuAttrItemList;
        private Integer preCount;
        private Integer stock;

        private String storeId;
        private String storeName;

        private Integer state=1;

        public Cart() {
        }

        protected Cart(Parcel in) {
            cartId = in.readString();
            goodsId = in.readString();
            goodsSkuId = in.readString();
            actId = in.readString();
            if (in.readByte() == 0) {
                buyCount = null;
            } else {
                buyCount = in.readInt();
            }
            goodsUrl = in.readString();
            goodsTitle = in.readString();
            if (in.readByte() == 0) {
                salePrice = null;
            } else {
                salePrice = in.readInt();
            }
            if (in.readByte() == 0) {
                income1 = null;
            } else {
                income1 = in.readInt();
            }
            skuAttrItemList = in.createTypedArrayList(SkuAttributeValue.CREATOR);
            if (in.readByte() == 0) {
                preCount = null;
            } else {
                preCount = in.readInt();
            }
            if (in.readByte() == 0) {
                stock = null;
            } else {
                stock = in.readInt();
            }
            storeId = in.readString();
            storeName = in.readString();
            if (in.readByte() == 0) {
                state = null;
            } else {
                state = in.readInt();
            }
        }

        public static final Creator<Cart> CREATOR = new Creator<Cart>() {
            @Override
            public Cart createFromParcel(Parcel in) {
                return new Cart(in);
            }

            @Override
            public Cart[] newArray(int size) {
                return new Cart[size];
            }
        };

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsSkuId() {
            return goodsSkuId;
        }

        public void setGoodsSkuId(String goodsSkuId) {
            this.goodsSkuId = goodsSkuId;
        }

        public String getActId() {
            return actId;
        }

        public void setActId(String actId) {
            this.actId = actId;
        }

        public Integer getBuyCount() {
            return buyCount;
        }

        public void setBuyCount(Integer buyCount) {
            this.buyCount = buyCount;
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

        public Integer getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(Integer salePrice) {
            this.salePrice = salePrice;
        }

        public Integer getIncome1() {
            return income1;
        }

        public void setIncome1(Integer income1) {
            this.income1 = income1;
        }

        public List<SkuAttributeValue> getSkuAttrItemList() {
            return skuAttrItemList;
        }

        public void setSkuAttrItemList(List<SkuAttributeValue> skuAttrItemList) {
            this.skuAttrItemList = skuAttrItemList;
        }

        public Integer getPreCount() {
            return preCount;
        }

        public void setPreCount(Integer preCount) {
            this.preCount = preCount;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

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

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(cartId);
            dest.writeString(goodsId);
            dest.writeString(goodsSkuId);
            dest.writeString(actId);
            if (buyCount == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(buyCount);
            }
            dest.writeString(goodsUrl);
            dest.writeString(goodsTitle);
            if (salePrice == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(salePrice);
            }
            if (income1 == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(income1);
            }
            dest.writeTypedList(skuAttrItemList);
            if (preCount == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(preCount);
            }
            if (stock == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(stock);
            }
            dest.writeString(storeId);
            dest.writeString(storeName);
            if (state == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(state);
            }
        }
    }


}
