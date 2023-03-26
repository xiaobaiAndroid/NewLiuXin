package module.address.city;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: baizhengfu
 * @date: 2017/12/27
 */

public class CityMultiEntity implements MultiItemEntity {

    public static final int GPS = 1;
    public static final int HOST = 2;
    public static final int BODY = 3;

    private int type;

    private List<CitySuspension> cityList;
    private List<CitySuspension> hostList;
    private CitySuspension gpsCity;

    public List<CitySuspension> getCityList() {
        return cityList;
    }

    public void setCityList(List<CitySuspension> cityList) {
        this.cityList = cityList;
    }

    public List<CitySuspension> getHostList() {
        return hostList;
    }

    public void setHostList(List<CitySuspension> hostList) {
        this.hostList = hostList;
    }

    public CitySuspension getGpsCity() {
        return gpsCity;
    }

    public void setGpsCity(CitySuspension gpsCity) {
        this.gpsCity = gpsCity;
    }

    public CityMultiEntity(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
