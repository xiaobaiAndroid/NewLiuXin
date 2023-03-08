package module.common.type;

/**
 * @describe: 排序类型
 * @date: 2020/4/30
 * @author: Mr Bai
 */
public enum SortType {

    /*降序*/
    DESC("Desc"),
    /*升序*/
    ASC("Asc");

    private String value;

    SortType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
