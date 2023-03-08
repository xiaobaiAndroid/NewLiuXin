package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 商家
 * @date: 2020/5/23
 * @author: Mr Bai
 */
public class Merchant implements Parcelable {

    /**
     * id : 337743386738307072
     * merchantType : 1
     * storeId : 412923096442744832
     * merchantName : 自营商户
     * registeredAddress : 广州市
     * operationalAddress : 城市广场
     * mainMarket : null
     * totalAnnualRevenue : null
     * mainBusiness : 生产、销售手机、代理手机
     * moreBusiness : 苹果、华为、panda
     * registeredTime : 2016
     * employeeNum : 18
     * webAddress : www.ewei.club
     * companyDetail : 公司是手机生产商家，代理销售其他品牌资质
     * legalPerson : 潘允凌
     * workArea : 1000M
     * manager : 谢先生
     * phone : 020-12345678
     * mobile : 13512345678
     * email : 253532221@qq.com
     * countryCode : null
     * countryName : null
     * provinceCode : 440000
     * provinceName : 广东省
     * cityCode : 440100
     * cityName : 广州市
     * countyCode : 440106
     * countyName : 天河区
     * fullAddress : 黄埔大道
     * zipCode : 525255
     * state : 1
     * factorySize : 1000平方
     * factoryAddress : 深圳市宝安区
     * productionEquipment : null
     * productionLines : 2条
     * contractManufacturing : 标准合同
     * annualOutputValue : 100万美金
     * sort : 4
     * grade : merchant_grade_gold
     * createBy : 13544588991
     * createTime : 2019-10-25 12:39:49
     * updateBy : 13800138001
     * updateTime : 2020-04-07 17:37:25
     * logo : https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/goods/2020/5/13989553117110522logo.jpg
     * isLike : null
     * goodsList : null
     * merchantImages : null
     * merchantVideos : null
     * merchantCertification : null
     */

    private String id;
    private int merchantType;
    private String storeId;
    private String merchantName;
    private String registeredAddress;
    private String operationalAddress;
    private Object mainMarket;
    private Object totalAnnualRevenue;
    private String mainBusiness;
    private String moreBusiness;
    private String registeredTime;
    private int employeeNum;
    private String webAddress;
    private String companyDetail;
    private String legalPerson;
    private String workArea;
    private String manager;
    private String phone;
    private String mobile;
    private String email;
    private int countryCode;
    private String countryName;
    private int provinceCode;
    private String provinceName;
    private int cityCode;
    private String cityName;
    private int countyCode;
    private String countyName;
    private String fullAddress;
    private String zipCode;
    private int state;
    private String factorySize;
    private String factoryAddress;
    private Object productionEquipment;
    private String productionLines;
    private String contractManufacturing;
    private String annualOutputValue;
    private int sort;
    private String grade;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String logo;
    private Object isLike;
    private Object goodsList;
    private Object merchantImages;
    private Object merchantVideos;
    private Object merchantCertification;

    //营业执照
    private String registeredPic;
    private String merchantTypeTitle;

    public Merchant() {
    }


    protected Merchant(Parcel in) {
        id = in.readString();
        merchantType = in.readInt();
        storeId = in.readString();
        merchantName = in.readString();
        registeredAddress = in.readString();
        operationalAddress = in.readString();
        mainBusiness = in.readString();
        moreBusiness = in.readString();
        registeredTime = in.readString();
        employeeNum = in.readInt();
        webAddress = in.readString();
        companyDetail = in.readString();
        legalPerson = in.readString();
        workArea = in.readString();
        manager = in.readString();
        phone = in.readString();
        mobile = in.readString();
        email = in.readString();
        countryCode = in.readInt();
        countryName = in.readString();
        provinceCode = in.readInt();
        provinceName = in.readString();
        cityCode = in.readInt();
        cityName = in.readString();
        countyCode = in.readInt();
        countyName = in.readString();
        fullAddress = in.readString();
        zipCode = in.readString();
        state = in.readInt();
        factorySize = in.readString();
        factoryAddress = in.readString();
        productionLines = in.readString();
        contractManufacturing = in.readString();
        annualOutputValue = in.readString();
        sort = in.readInt();
        grade = in.readString();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
        logo = in.readString();
    }

    public static final Creator<Merchant> CREATOR = new Creator<Merchant>() {
        @Override
        public Merchant createFromParcel(Parcel in) {
            return new Merchant(in);
        }

        @Override
        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(int merchantType) {
        this.merchantType = merchantType;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getOperationalAddress() {
        return operationalAddress;
    }

    public void setOperationalAddress(String operationalAddress) {
        this.operationalAddress = operationalAddress;
    }

    public Object getMainMarket() {
        return mainMarket;
    }

    public void setMainMarket(Object mainMarket) {
        this.mainMarket = mainMarket;
    }

    public Object getTotalAnnualRevenue() {
        return totalAnnualRevenue;
    }

    public void setTotalAnnualRevenue(Object totalAnnualRevenue) {
        this.totalAnnualRevenue = totalAnnualRevenue;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getMoreBusiness() {
        return moreBusiness;
    }

    public void setMoreBusiness(String moreBusiness) {
        this.moreBusiness = moreBusiness;
    }

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getCompanyDetail() {
        return companyDetail;
    }

    public void setCompanyDetail(String companyDetail) {
        this.companyDetail = companyDetail;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(int countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFactorySize() {
        return factorySize;
    }

    public void setFactorySize(String factorySize) {
        this.factorySize = factorySize;
    }

    public String getFactoryAddress() {
        return factoryAddress;
    }

    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }

    public Object getProductionEquipment() {
        return productionEquipment;
    }

    public void setProductionEquipment(Object productionEquipment) {
        this.productionEquipment = productionEquipment;
    }

    public String getProductionLines() {
        return productionLines;
    }

    public void setProductionLines(String productionLines) {
        this.productionLines = productionLines;
    }

    public String getContractManufacturing() {
        return contractManufacturing;
    }

    public void setContractManufacturing(String contractManufacturing) {
        this.contractManufacturing = contractManufacturing;
    }

    public String getAnnualOutputValue() {
        return annualOutputValue;
    }

    public void setAnnualOutputValue(String annualOutputValue) {
        this.annualOutputValue = annualOutputValue;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Object getIsLike() {
        return isLike;
    }

    public void setIsLike(Object isLike) {
        this.isLike = isLike;
    }

    public Object getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(Object goodsList) {
        this.goodsList = goodsList;
    }

    public Object getMerchantImages() {
        return merchantImages;
    }

    public void setMerchantImages(Object merchantImages) {
        this.merchantImages = merchantImages;
    }

    public Object getMerchantVideos() {
        return merchantVideos;
    }

    public void setMerchantVideos(Object merchantVideos) {
        this.merchantVideos = merchantVideos;
    }

    public Object getMerchantCertification() {
        return merchantCertification;
    }

    public void setMerchantCertification(Object merchantCertification) {
        this.merchantCertification = merchantCertification;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(merchantType);
        dest.writeString(storeId);
        dest.writeString(merchantName);
        dest.writeString(registeredAddress);
        dest.writeString(operationalAddress);
        dest.writeString(mainBusiness);
        dest.writeString(moreBusiness);
        dest.writeString(registeredTime);
        dest.writeInt(employeeNum);
        dest.writeString(webAddress);
        dest.writeString(companyDetail);
        dest.writeString(legalPerson);
        dest.writeString(workArea);
        dest.writeString(manager);
        dest.writeString(phone);
        dest.writeString(mobile);
        dest.writeString(email);
        dest.writeInt(countryCode);
        dest.writeString(countryName);
        dest.writeInt(provinceCode);
        dest.writeString(provinceName);
        dest.writeInt(cityCode);
        dest.writeString(cityName);
        dest.writeInt(countyCode);
        dest.writeString(countyName);
        dest.writeString(fullAddress);
        dest.writeString(zipCode);
        dest.writeInt(state);
        dest.writeString(factorySize);
        dest.writeString(factoryAddress);
        dest.writeString(productionLines);
        dest.writeString(contractManufacturing);
        dest.writeString(annualOutputValue);
        dest.writeInt(sort);
        dest.writeString(grade);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
        dest.writeString(logo);
    }
}
