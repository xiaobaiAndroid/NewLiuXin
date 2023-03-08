package module.common.data.entity;




/**
 * @describe: 省
 * @date: 2020/4/5
 * @author: Mr Bai
 */
public class Province  {

//    private String id;
    private int code;
    private int parentCode;

    
    private String name;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getParentCode() {
        return parentCode;
    }

    public void setParentCode(int parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
