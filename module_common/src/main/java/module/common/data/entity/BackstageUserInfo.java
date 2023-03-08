package module.common.data.entity;

import java.util.List;

/**
 * @describe: 后台用户信息
 * @date: 2020/6/28
 * @author: Mr Bai
 */
public class BackstageUserInfo {


    /**
     * id : 359603221909876736
     * merchantId : 351827375040114688
     * storeId : 351886575548116992
     * state : 1
     * createBy : 13800138001
     * createTime : 2019-12-25 10:02:59
     * updateBy : 13800138001
     * updateTime : 2020-04-19 09:18:57
     * user : {"id":"359603221909876736","name":"13539713955","mobile":"13539713955","remandUserId":null,"levelId":3,"mediaSize":100,"mediaTime":30,"state":1,"createBy":"unknown","createTime":"2019-12-24 20:22:59","updateBy":"unknown","updateTime":"2019-12-24 20:22:59","userExtend":null}
     * role : {"id":"1","roleName":"商户管理员","creatorId":"323576809033969665","createBy":"admin","createTime":"2019-10-27 21:06:33","updateBy":"admin","updateTime":"2019-10-27 21:06:27","menus":null}
     * merchant : {"id":"351827375040114688","merchantType":2,"merchantName":"褐马内衣","manager":"刘倩","phone":"057-18815785","mobile":"13688879888","email":"13688879888@163.com","provinceCode":440000,"provinceName":"广东省","cityCode":440100,"cityName":"广州市","countyCode":440106,"countyName":"天河区","fullAddress":"广州市广州大道中新潮都8015档","zipCode":"528200","state":1,"createBy":"13800138001","createTime":"2019-12-03 09:24:32","updateBy":"13800138001","updateTime":"2020-05-05 21:59:50","goodsList":null,"merchantImages":null,"merchantVideos":null}
     * userAccounts : [{"id":"359603221893099520","account":"13539713955","userAccountType":2,"userId":"359603221909876736","state":1,"createBy":"unknown","createTime":"2019-12-24 20:22:59","updateBy":"13539713955","updateTime":"2020-06-23 16:26:27"},{"id":"359603221893099521","account":"13539713955","userAccountType":1,"userId":"359603221909876736","state":1,"createBy":"unknown","createTime":"2019-12-24 20:22:59","updateBy":"unknown","updateTime":"2019-12-24 20:22:59"}]
     */

    private String id;
    private String merchantId;
    private String storeId;
    private int state;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private User user;
    private RoleBean role;
    private MerchantBean merchant;
    private List<UserAccountsBean> userAccounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoleBean getRole() {
        return role;
    }

    public void setRole(RoleBean role) {
        this.role = role;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public List<UserAccountsBean> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccountsBean> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public static class User {
        /**
         * id : 359603221909876736
         * name : 13539713955
         * mobile : 13539713955
         * remandUserId : null
         * levelId : 3
         * mediaSize : 100
         * mediaTime : 30
         * state : 1
         * createBy : unknown
         * createTime : 2019-12-24 20:22:59
         * updateBy : unknown
         * updateTime : 2019-12-24 20:22:59
         * userExtend : null
         */

        private String id;
        private String name;
        private String mobile;
        private Object remandUserId;
        private int levelId;
        private int mediaSize;
        private int mediaTime;
        private int state;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object userExtend;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getRemandUserId() {
            return remandUserId;
        }

        public void setRemandUserId(Object remandUserId) {
            this.remandUserId = remandUserId;
        }

        public int getLevelId() {
            return levelId;
        }

        public void setLevelId(int levelId) {
            this.levelId = levelId;
        }

        public int getMediaSize() {
            return mediaSize;
        }

        public void setMediaSize(int mediaSize) {
            this.mediaSize = mediaSize;
        }

        public int getMediaTime() {
            return mediaTime;
        }

        public void setMediaTime(int mediaTime) {
            this.mediaTime = mediaTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUserExtend() {
            return userExtend;
        }

        public void setUserExtend(Object userExtend) {
            this.userExtend = userExtend;
        }
    }

    public static class RoleBean {
        /**
         * id : 1
         * roleName : 商户管理员
         * creatorId : 323576809033969665
         * createBy : admin
         * createTime : 2019-10-27 21:06:33
         * updateBy : admin
         * updateTime : 2019-10-27 21:06:27
         * menus : null
         */

        private String id;
        private String roleName;
        private String creatorId;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object menus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getMenus() {
            return menus;
        }

        public void setMenus(Object menus) {
            this.menus = menus;
        }
    }

    public static class MerchantBean {
        /**
         * id : 351827375040114688
         * merchantType : 2
         * merchantName : 褐马内衣
         * manager : 刘倩
         * phone : 057-18815785
         * mobile : 13688879888
         * email : 13688879888@163.com
         * provinceCode : 440000
         * provinceName : 广东省
         * cityCode : 440100
         * cityName : 广州市
         * countyCode : 440106
         * countyName : 天河区
         * fullAddress : 广州市广州大道中新潮都8015档
         * zipCode : 528200
         * state : 1
         * createBy : 13800138001
         * createTime : 2019-12-03 09:24:32
         * updateBy : 13800138001
         * updateTime : 2020-05-05 21:59:50
         * goodsList : null
         * merchantImages : null
         * merchantVideos : null
         */

        private String id;
        private int merchantType;
        private String merchantName;
        private String manager;
        private String phone;
        private String mobile;
        private String email;
        private int provinceCode;
        private String provinceName;
        private int cityCode;
        private String cityName;
        private int countyCode;
        private String countyName;
        private String fullAddress;
        private String zipCode;
        private int state;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object goodsList;
        private Object merchantImages;
        private Object merchantVideos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getMerchantType() {
            return merchantType;
        }

        public void setMerchantType(int merchantType) {
            this.merchantType = merchantType;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getManager() {
            return manager;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(int provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getCityCode() {
            return cityCode;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getCountyCode() {
            return countyCode;
        }

        public void setCountyCode(int countyCode) {
            this.countyCode = countyCode;
        }

        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }

        public String getFullAddress() {
            return fullAddress;
        }

        public void setFullAddress(String fullAddress) {
            this.fullAddress = fullAddress;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(Object goodsList) {
            this.goodsList = goodsList;
        }

        public Object getMerchantImages() {
            return merchantImages;
        }

        public void setMerchantImages(Object merchantImages) {
            this.merchantImages = merchantImages;
        }

        public Object getMerchantVideos() {
            return merchantVideos;
        }

        public void setMerchantVideos(Object merchantVideos) {
            this.merchantVideos = merchantVideos;
        }
    }

    public static class UserAccountsBean {
        /**
         * id : 359603221893099520
         * account : 13539713955
         * userAccountType : 2
         * userId : 359603221909876736
         * state : 1
         * createBy : unknown
         * createTime : 2019-12-24 20:22:59
         * updateBy : 13539713955
         * updateTime : 2020-06-23 16:26:27
         */

        private String id;
        private String account;
        private int userAccountType;
        private String userId;
        private int state;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getUserAccountType() {
            return userAccountType;
        }

        public void setUserAccountType(int userAccountType) {
            this.userAccountType = userAccountType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
