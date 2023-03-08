package module.common.data.entity;

/**
 * @describe: 打赏个我的礼物
 * @date: 2020/3/20
 * @author: Mr Bai
 */
public class MyGift {


    /**
     * id : 389527639356223488
     * orderType : 1
     * redType : null
     * orderTime : 2020-03-16 10:11:56
     * userId : 359575145163403264
     * accountId : 360365744309284864
     * giftId : 360652899506073600
     * topUpId : null
     * num : 1
     * payNo : null
     * payWay : 5
     * payStatus : 1
     * orderPrice : 88
     * redPacketList : null
     * redRemark : null
     * remark : 1个浏信1号共88个浏币
     * createBy : 18926184331
     * createTime : 2020-03-16 10:11:56
     * updateBy : 18926184331
     * updateTime : 2020-03-16 10:11:56
     * userExtend : {"id":"359575145163403264","no":"18926184331","nickName":"安卓-潘","intro":"老潘","avatar":"http://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/2020/12/icon/11-43-22359575145163403264.png","name":"","mobile":"18926184331","sex":1,"photo":"http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/BGImage/12-25-45359575145163403264.png","birthday":"1976-07-15","job":"IT","colleges":"大学","showRealInfo":1,"onLineState":1,"provinceCode":440000,"provinceName":"广东省","cityCode":440100,"cityName":"广州市","countyCode":440105,"countyName":"海珠区","fullAddress":"广东省广州市海珠区","lat":null,"lng":null,"state":1,"createBy":"unknown","createTime":"2019-12-24 18:31:25","updateBy":"18926184331","updateTime":"2020-03-04 12:25:46","certification":null,"friend":null,"praiseNum":0,"likeNum":0,"fansNum":0,"storeId":null}
     * goldGift : {"id":"360652899506073600","giftTypeId":1,"giftTypeName":null,"giftName":"浏信1号","giftPrice":88,"giftUrl":"https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/goods/2019/12/2587402975492560%E7%81%AB%E7%AE%AD.png","giftType":"1","giftSvgaUrl":"https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/gift/%E7%81%AB%E7%AE%AD.svga","state":1,"displayOrder":2,"createBy":"13544588991","createTime":"2019-12-27 17:54:01","updateBy":"13800138001","updateTime":"2020-01-06 10:00:43"}
     */

    private String id;
    private int orderType;
    private Object redType;
    private String orderTime;
    private String userId;
    private String accountId;
    private String giftId;
    private Object topUpId;
    private int num;
    private Object payNo;
    private int payWay;
    private int payStatus;
    private int orderPrice;
    private Object redPacketList;
    private Object redRemark;
    private String remark;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private UserInfo userExtend;
    private GoldGift goldGift;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Object getRedType() {
        return redType;
    }

    public void setRedType(Object redType) {
        this.redType = redType;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public Object getTopUpId() {
        return topUpId;
    }

    public void setTopUpId(Object topUpId) {
        this.topUpId = topUpId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Object getPayNo() {
        return payNo;
    }

    public void setPayNo(Object payNo) {
        this.payNo = payNo;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Object getRedPacketList() {
        return redPacketList;
    }

    public void setRedPacketList(Object redPacketList) {
        this.redPacketList = redPacketList;
    }

    public Object getRedRemark() {
        return redRemark;
    }

    public void setRedRemark(Object redRemark) {
        this.redRemark = redRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public UserInfo getUserExtend() {
        return userExtend;
    }

    public void setUserExtend(UserInfo userExtend) {
        this.userExtend = userExtend;
    }

    public GoldGift getGoldGift() {
        return goldGift;
    }

    public void setGoldGift(GoldGift goldGift) {
        this.goldGift = goldGift;
    }

    public static class UserInfo {
        /**
         * id : 359575145163403264
         * no : 18926184331
         * nickName : 安卓-潘
         * intro : 老潘
         * avatar : http://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/2020/12/icon/11-43-22359575145163403264.png
         * name :
         * mobile : 18926184331
         * sex : 1
         * photo : http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/BGImage/12-25-45359575145163403264.png
         * birthday : 1976-07-15
         * job : IT
         * colleges : 大学
         * showRealInfo : 1
         * onLineState : 1
         * provinceCode : 440000
         * provinceName : 广东省
         * cityCode : 440100
         * cityName : 广州市
         * countyCode : 440105
         * countyName : 海珠区
         * fullAddress : 广东省广州市海珠区
         * lat : null
         * lng : null
         * state : 1
         * createBy : unknown
         * createTime : 2019-12-24 18:31:25
         * updateBy : 18926184331
         * updateTime : 2020-03-04 12:25:46
         * certification : null
         * friend : null
         * praiseNum : 0
         * likeNum : 0
         * fansNum : 0
         * storeId : null
         */

        private String id;
        private String no;
        private String nickName;
        private String intro;
        private String avatar;
        private String name;
        private String mobile;
        private int sex;
        private String photo;
        private String birthday;
        private String job;
        private String colleges;
        private int showRealInfo;
        private int onLineState;
        private int provinceCode;
        private String provinceName;
        private int cityCode;
        private String cityName;
        private int countyCode;
        private String countyName;
        private String fullAddress;
        private Object lat;
        private Object lng;
        private int state;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object certification;
        private Object friend;
        private int praiseNum;
        private int likeNum;
        private int fansNum;
        private Object storeId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getColleges() {
            return colleges;
        }

        public void setColleges(String colleges) {
            this.colleges = colleges;
        }

        public int getShowRealInfo() {
            return showRealInfo;
        }

        public void setShowRealInfo(int showRealInfo) {
            this.showRealInfo = showRealInfo;
        }

        public int getOnLineState() {
            return onLineState;
        }

        public void setOnLineState(int onLineState) {
            this.onLineState = onLineState;
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

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
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

        public Object getCertification() {
            return certification;
        }

        public void setCertification(Object certification) {
            this.certification = certification;
        }

        public Object getFriend() {
            return friend;
        }

        public void setFriend(Object friend) {
            this.friend = friend;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
            this.fansNum = fansNum;
        }

        public Object getStoreId() {
            return storeId;
        }

        public void setStoreId(Object storeId) {
            this.storeId = storeId;
        }
    }

    public static class GoldGift {
        /**
         * id : 360652899506073600
         * giftTypeId : 1
         * giftTypeName : null
         * giftName : 浏信1号
         * giftPrice : 88
         * giftUrl : https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/goods/2019/12/2587402975492560%E7%81%AB%E7%AE%AD.png
         * giftType : 1
         * giftSvgaUrl : https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/gift/%E7%81%AB%E7%AE%AD.svga
         * state : 1
         * displayOrder : 2
         * createBy : 13544588991
         * createTime : 2019-12-27 17:54:01
         * updateBy : 13800138001
         * updateTime : 2020-01-06 10:00:43
         */

        private String id;
        private int giftTypeId;
        private Object giftTypeName;
        private String giftName;
        private int giftPrice;
        private String giftUrl;
        private String giftType;
        private String giftSvgaUrl;
        private int state;
        private int displayOrder;
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

        public int getGiftTypeId() {
            return giftTypeId;
        }

        public void setGiftTypeId(int giftTypeId) {
            this.giftTypeId = giftTypeId;
        }

        public Object getGiftTypeName() {
            return giftTypeName;
        }

        public void setGiftTypeName(Object giftTypeName) {
            this.giftTypeName = giftTypeName;
        }

        public String getGiftName() {
            return giftName;
        }

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }

        public int getGiftPrice() {
            return giftPrice;
        }

        public void setGiftPrice(int giftPrice) {
            this.giftPrice = giftPrice;
        }

        public String getGiftUrl() {
            return giftUrl;
        }

        public void setGiftUrl(String giftUrl) {
            this.giftUrl = giftUrl;
        }

        public String getGiftType() {
            return giftType;
        }

        public void setGiftType(String giftType) {
            this.giftType = giftType;
        }

        public String getGiftSvgaUrl() {
            return giftSvgaUrl;
        }

        public void setGiftSvgaUrl(String giftSvgaUrl) {
            this.giftSvgaUrl = giftSvgaUrl;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
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
