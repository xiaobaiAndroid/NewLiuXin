package module.common.data.request;

/**
 * @describe: 生活圈分类类型查询
 * @date: 2020/3/4
 * @author: Mr Bai
 */
public class CliqueCategoryReq {

    /*	0-发布分类 1-首页分类*/
    private String id;

    /*媒体类型：1-音图文、2-IN视频*/
    private String type;

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

    public static class IdType{
        public static final String PUBLISH="0";
        public static final String HOME="1";
    }

}
