package module.address.city;

/**
 *默认城市实体
 * @author: baizhengfu
 * @date: 2017/12/28
 */

public class DefaultCity extends CitySuspension{

    public DefaultCity() {
        setCity("广州市");
        setBaseIndexTag("G");
        setBaseIndexPinyin("GUANGZHOUSHI");
        setLat("23.16667");
        setLon("113.2333323");
        setHostCity(true);
        setCity_id(440100);
        setProvince_id(440000);
    }
}
