package module.common.data.entity;

/**
 * @describe: 身份认证信息
 * @date: 2020/4/21
 * @author: Mr Bai
 */
public class CertificationInfo {

    /*身份证号*/
    private String idCard;

    /*	身份证姓名*/
    private String name;
    private String userId;
    /*认证类型：1个人 2企业*/
    private String userType;

    /*证件类型,1为身份证，9为营业执照（社会统一信用代码）*/
    private String idType;
    /*正面图片*/
    private String frontImg;
    /*反面图片*/
    private String backImg;
    private String createTime;


    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
