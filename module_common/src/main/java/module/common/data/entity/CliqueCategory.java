package module.common.data.entity;

public class CliqueCategory {


    /**
     * id : 45
     * type : 1
     * typeName : 自拍
     * displayOrder : 5
     */

    /*	分类id*/
    private String id;
    /*媒体类型*/
    private int type;
    /*上级分类id*/
    private String typeName;

    /*排序的序号*/
    private String displayOrder;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public static class Type{

        /*推荐*/
        public static final String RECOMMEND = "1";

        /*好友*/
        public static final String FRIEND = "2";

        /*同城*/
        public static final String CITY = "4";

    }
}
