package module.common.data.request;

/**
 * @describe: 创建相册请求
 * @date: 2020/6/27
 * @author: Mr Bai
 */
public class CreateAlbumReq {


    /**
     * merchantId : 357329675070353408
     * storeId : 351886575548116992
     * userId : 379100103300231169
     * type : 2
     * name : 视频目录1
     * accessAuth : 3
     * accessPassword : 123456
     * sort : 1
     */

    private String merchantId = "0";
    private String storeId = "0";
    private String userId;

    /*1：图片，2：视频*/
    private int type = 1;
    private String name;

    /*访问权限：1：公开，2：密码访问，3：私人相册*/
    private int accessAuth = 1;
    private String accessPassword;
    private int sort;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccessAuth() {
        return accessAuth;
    }

    public void setAccessAuth(int accessAuth) {
        this.accessAuth = accessAuth;
    }

    public String getAccessPassword() {
        return accessPassword;
    }

    public void setAccessPassword(String accessPassword) {
        this.accessPassword = accessPassword;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
