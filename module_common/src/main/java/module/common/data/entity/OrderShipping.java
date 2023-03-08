package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 订单的配送信息
 * @date: 2020/5/31
 * @author: Mr Bai
 */
public class OrderShipping implements Parcelable {

    /*收货地址id*/
    private String shippingId;
    /*收货人*/
    private String consignee;
    /*联系方式*/
    private String contactsWay;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String countyCode;
    private String countyName;
    private String streetCode;
    private String streetName;
    private String fullAddress;

    /*邮编*/
    private String zipCode;

    /*配送状态，配送状态，0未知，1待发货,2已发货,3已收货,4退货*/
    private String shippingStatus;
    /*快递单号*/
    private String expressNo;
    /*快递公司*/
    private String expressCompany;

    /*发货时间*/
    private String shippingTime;
    /*收货时间*/
    private String receiveTime;

    protected OrderShipping(Parcel in) {
        shippingId = in.readString();
        consignee = in.readString();
        contactsWay = in.readString();
        provinceCode = in.readString();
        provinceName = in.readString();
        cityCode = in.readString();
        cityName = in.readString();
        countyCode = in.readString();
        countyName = in.readString();
        streetCode = in.readString();
        streetName = in.readString();
        fullAddress = in.readString();
        zipCode = in.readString();
        shippingStatus = in.readString();
        expressNo = in.readString();
        expressCompany = in.readString();
        shippingTime = in.readString();
        receiveTime = in.readString();
    }

    public static final Creator<OrderShipping> CREATOR = new Creator<OrderShipping>() {
        @Override
        public OrderShipping createFromParcel(Parcel in) {
            return new OrderShipping(in);
        }

        @Override
        public OrderShipping[] newArray(int size) {
            return new OrderShipping[size];
        }
    };

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
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

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shippingId);
        dest.writeString(consignee);
        dest.writeString(contactsWay);
        dest.writeString(provinceCode);
        dest.writeString(provinceName);
        dest.writeString(cityCode);
        dest.writeString(cityName);
        dest.writeString(countyCode);
        dest.writeString(countyName);
        dest.writeString(streetCode);
        dest.writeString(streetName);
        dest.writeString(fullAddress);
        dest.writeString(zipCode);
        dest.writeString(shippingStatus);
        dest.writeString(expressNo);
        dest.writeString(expressCompany);
        dest.writeString(shippingTime);
        dest.writeString(receiveTime);
    }
}
