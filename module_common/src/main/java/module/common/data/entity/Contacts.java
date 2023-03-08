package module.common.data.entity;

/**
 * @describe: 聊天：联系人的用户信息
 * @date: 2020/3/26
 * @author: Mr Bai
 */
public class Contacts {


    /**
     * id : 390989556536979456
     * no : 17818837131
     * nickName : 17818837131
     * intro : null
     * avatar : https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/im/head.png
     * name : null
     * mobile : 17818837131
     * sex : 0
     * photo : null
     * birthday : null
     * job : null
     * colleges : null
     * showRealInfo : 1
     * onLineState : 1
     * provinceCode : null
     * provinceName : null
     * cityCode : null
     * cityName : null
     * countyCode : null
     * countyName : null
     * fullAddress : null
     * lat : null
     * lng : null
     * state : 1
     * createBy : 18664868453
     * createTime : 2020-03-20 11:01:04
     * updateBy : 18664868453
     * updateTime : 2020-03-20 11:01:04
     * certification : null
     * friend : {"id":"390990316683276289","userId":"384489609499127808","groupId":"0","friendId":"390989556536979456","showCircle":1,"blacklist":0,"remark":null,"state":1,"createBy":"18664868453","createTime":"2020-03-20 11:04:05","updateBy":"18664868453","updateTime":"2020-03-20 11:04:05","group":{"id":"0","userId":null,"groupName":"我的好友","sort":0,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"userFriends":null}}
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
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String countyCode;
    private String countyName;
    private String fullAddress;
    private String lat;
    private String lng;
    private int state;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String certification;
    private FriendBean friend;
    private int praiseNum;
    private int likeNum;
    private int fansNum;
    private String storeId;

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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
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

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public FriendBean getFriend() {
        return friend;
    }

    public void setFriend(FriendBean friend) {
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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public static class FriendBean {
        /**
         * id : 390990316683276289
         * userId : 384489609499127808
         * groupId : 0
         * friendId : 390989556536979456
         * showCircle : 1
         * blacklist : 0
         * remark : null
         * state : 1
         * createBy : 18664868453
         * createTime : 2020-03-20 11:04:05
         * updateBy : 18664868453
         * updateTime : 2020-03-20 11:04:05
         * group : {"id":"0","userId":null,"groupName":"我的好友","sort":0,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"userFriends":null}
         */

        private String id;
        private String userId;
        private String groupId;
        private String friendId;
        private int showCircle;
        private int blacklist;
        private Object remark;
        private int state;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private GroupBean group;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getFriendId() {
            return friendId;
        }

        public void setFriendId(String friendId) {
            this.friendId = friendId;
        }

        public int getShowCircle() {
            return showCircle;
        }

        public void setShowCircle(int showCircle) {
            this.showCircle = showCircle;
        }

        public int getBlacklist() {
            return blacklist;
        }

        public void setBlacklist(int blacklist) {
            this.blacklist = blacklist;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
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

        public GroupBean getGroup() {
            return group;
        }

        public void setGroup(GroupBean group) {
            this.group = group;
        }

        public static class GroupBean {
            /**
             * id : 0
             * userId : null
             * groupName : 我的好友
             * sort : 0
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * userFriends : null
             */

            private String id;
            private Object userId;
            private String groupName;
            private int sort;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object userFriends;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUserFriends() {
                return userFriends;
            }

            public void setUserFriends(Object userFriends) {
                this.userFriends = userFriends;
            }
        }
    }
}
