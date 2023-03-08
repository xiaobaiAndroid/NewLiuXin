package module.common.type;

/**
 * @describe: 协议类型
 * @date: 2020/4/4
 * @author: Mr Bai
 */
public enum ProtocolType {

    /*注册协议*/
    REGISTER(1),
    /*发布协议*/
    PUBLISH(2),
    /*充值协议*/
    RECHARGE(3),
    /*隐私政策*/
    PRIVACY(4);

    private int value;

    ProtocolType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
