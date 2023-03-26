package module.address.city;

import java.util.Comparator;

/**
 * @author: baizhengfu
 * @date: 2017/12/27
 */

public class CityComparator implements Comparator<CitySuspension> {


    @Override
    public int compare(CitySuspension o1, CitySuspension o2) {
        if (o1.getSuspensionTag().equals("@")
                || o2.getSuspensionTag().equals("#")) {
            return -1;
        } else if (o1.getSuspensionTag().equals("#")
                || o2.getSuspensionTag().equals("@")) {
            return 1;
        } else {
            return o1.getSuspensionTag().compareTo(o2.getSuspensionTag());
        }
    }
}
