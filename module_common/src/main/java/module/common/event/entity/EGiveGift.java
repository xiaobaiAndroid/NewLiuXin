package module.common.event.entity;

import module.common.data.entity.Gift;

import java.io.Serializable;

/**
 * @describe: 赠送礼物
 * @date: 2020/3/8
 * @author: Mr Bai
 */
public class EGiveGift implements Serializable {

    private String selectedNumber;
    private Gift selectGift;


    public String getSelectedNumber() {
        return selectedNumber;
    }

    public void setSelectedNumber(String selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public Gift getSelectGift() {
        return selectGift;
    }

    public void setSelectGift(Gift selectGift) {
        this.selectGift = selectGift;
    }
}
