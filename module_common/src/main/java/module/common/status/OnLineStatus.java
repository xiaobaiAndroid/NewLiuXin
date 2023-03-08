package module.common.status;

/**
 * @describe: 在线状态
 * @date: 2020/4/11
 * @author: Mr Bai
 */
public enum  OnLineStatus {

    ONLINE(1),
    CLOAKING(2),
    BUSY(3);

    private int value;

    OnLineStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
