package module.address.city;

/**
 * 选择城市的实体
 * @date: 2017/12/27
 */

public class CitySuspension implements SuspensionDecoration.ISuspensionInterface{

    private String baseIndexTag;//所属的分类（城市的汉语拼音首字母）
    private String baseIndexPinyin;//城市的拼音
    private String city;//城市名字

    private Integer province_id;
    private Integer city_id;
    private String lon;
    private String lat;
    private Boolean hostCity;   // 是否为热门城市


    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Boolean getHostCity() {
        return hostCity;
    }

    public void setHostCity(Boolean hostCity) {
        this.hostCity = hostCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getBaseIndexPinyin() {
        return baseIndexPinyin;
    }

    public void setBaseIndexPinyin(String baseIndexPinyin) {
        this.baseIndexPinyin = baseIndexPinyin;
    }

    //是否需要被转化成拼音， 类似微信头部那种就不需要 美团的也不需要
    //微信的头部 不需要显示索引
    //美团的头部 索引自定义
    //默认应该是需要的
    public boolean isNeedToPinyin() {
        return true;
    }

    public String getBaseIndexTag() {
        return baseIndexTag;
    }

    public void setBaseIndexTag(String baseIndexTag) {
        this.baseIndexTag = baseIndexTag;
    }

    @Override
    public boolean isShowSuspension() {
        return true;
    }

    @Override
    public String getSuspensionTag() {
        return baseIndexTag;
    }


    @Override
    public String toString() {
        return "CitySuspension{" +
                "baseIndexTag='" + baseIndexTag + '\'' +
                ", baseIndexPinyin='" + baseIndexPinyin + '\'' +
                ", city='" + city + '\'' +
                ", province_id=" + province_id +
                ", city_id=" + city_id +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", hostCity=" + hostCity +
                '}';
    }
}
