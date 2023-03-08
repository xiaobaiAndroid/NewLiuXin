package module.common.data.entity;

/**
 * @describe: 充值数据
 * @date: 2020/4/4
 * @author: Mr Bai
 */
public class Recharge {

    private String id;
    /*充值平台1-安卓 2-苹果*/
    private String type;

    /*浏币个数*/
    private int goldNum;

    /*价格*/
    private int goldPrice;

    private boolean selected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(int goldNum) {
        this.goldNum = goldNum;
    }

    public int getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(int goldPrice) {
        this.goldPrice = goldPrice;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
