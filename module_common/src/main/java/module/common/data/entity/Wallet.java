package module.common.data.entity;

/**
 * @describe: 钱包，余额
 * @date: 2020/3/8
 * @author: Mr Bai
 */
public class Wallet {

    private String id;
    private String money;

    /*冻结金额（分）*/
    private String freezeMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(String freezeMoney) {
        this.freezeMoney = freezeMoney;
    }
}
