package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 地址
 * @date: 2020/5/17
 * @author: Mr Bai
 */
public class Address implements Parcelable {


    /**
     * id : 396570135026348032
     * userId : 384489609499127808
     * consignee : 白菜
     * contactsWay : 18664868453
     * provinceCode : 440000
     * provinceName : 广东省
     * cityCode : 440700
     * cityName : 江门市
     * countyCode : 440704
     * countyName : 江海区
     * streetCode : null
     * streetName : null
     * fullAddress : 测试
     * zipCode : null
     * checkStatus : 1
     * remark : null
     * createBy : 18664868453
     * createTime : 2020-04-04 20:36:18
     * updateBy : 18664868453
     * updateTime : 2020-04-04 20:36:18
     */

    private String id;
    private String userId;
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
    /*详细地址*/
    private String fullAddress;
    /*邮编*/
    private String zipCode;

    /*是否默认地址*/
    private int checkStatus;

    /*备注*/
    private String remark;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    public Address() {
    }

    protected Address(Parcel in) {
        id = in.readString();
        userId = in.readString();
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
        checkStatus = in.readInt();
        remark = in.readString();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
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

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        dest.writeString(userId);
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
        dest.writeInt(checkStatus);
        dest.writeString(remark);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
    }
}
