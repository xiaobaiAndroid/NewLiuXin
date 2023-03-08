package module.common.type;

/**
 * @describe: 排行榜类型
 * @date: 2020/4/8
 * @author: Mr Bai
 */
public enum RankingType {
    YESTERDAY(1),
    LAST_WEEK(2),
    LAST_MONTH(3);

    private int value;

    RankingType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
