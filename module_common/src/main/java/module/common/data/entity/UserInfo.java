package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 用户信息
 * @date: 2020/1/11
 * @author: Mr Bai
 */
public class UserInfo implements Parcelable {


    /**
     * userId : 384489609499127808
     * userName : 18664868453
     * levelId : null
     * mediaSize : null
     * mediaTime : null
     * access_token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzODQ0ODk2MDk0OTkxMjc4MDgiLCJhdWQiOiJBQ0NFU1NfVE9LRU4iLCJpYXQiOjE1ODMyODM0NzYsInN1YiI6IjE4NjY0ODY4NDUzIiwiZXhwIjoxNTgzMzEyMjc2fQ.5FydjDsauCEyyfV3wAekivD6kHIpPhf2Hd1BuIvIF6Y
     * refresh_token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzODQ0ODk2MDk0OTkxMjc4MDgiLCJhdWQiOiJSRUZSRVNIX1RPS0VOIiwiaWF0IjoxNTgzMjgzNDc2LCJzdWIiOiIxODY2NDg2ODQ1MyIsImV4cCI6MTU4NDU3OTQ3Nn0.RbYZNdAktiudHWEnae6qR_5GNwdh4qafMW_xtTBmJSY
     * isFirst : 0
     */
    
    private String userId;

    private String userName;

    
    private String nickName;
    
    private String intro;
    
    private String avatar;

    
    private String mobile;

    //    	性别，1男，2女
    
    private int sex;

    
    private String photo;

    
    private String birthday;

    
    private String job;

    
    private String colleges;

    
    private String lat;
    
    private String lng;

    
    private String provinceCode;

    
    private String provinceName;

    
    private String cityCode;

    
    private String cityName;

    
    private String countyCode;

    
    private String countyName;

    
    private String fullAddress;

    
    private int praiseNum;

    
    private int likeNum;

    
    private int fansNum;

    
    private String storeId;

    
    private String access_token;
    
    private String refresh_token;

    private int isFirst;

    
    private int isLogin;

    /*注册时间*/
    
    private String registerDate;


    private Friend friend;


    private String id;

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        userId = in.readString();
        userName = in.readString();
        nickName = in.readString();
        intro = in.readString();
        avatar = in.readString();
        mobile = in.readString();
        sex = in.readInt();
        photo = in.readString();
        birthday = in.readString();
        job = in.readString();
        colleges = in.readString();
        lat = in.readString();
        lng = in.readString();
        provinceCode = in.readString();
        provinceName = in.readString();
        cityCode = in.readString();
        cityName = in.readString();
        countyCode = in.readString();
        countyName = in.readString();
        fullAddress = in.readString();
        praiseNum = in.readInt();
        likeNum = in.readInt();
        fansNum = in.readInt();
        storeId = in.readString();
        access_token = in.readString();
        refresh_token = in.readString();
        isFirst = in.readInt();
        isLogin = in.readInt();
        registerDate = in.readString();
        friend = in.readParcelable(Friend.class.getClassLoader());
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(userName);
        dest.writeString(nickName);
        dest.writeString(intro);
        dest.writeString(avatar);
        dest.writeString(mobile);
        dest.writeInt(sex);
        dest.writeString(photo);
        dest.writeString(birthday);
        dest.writeString(job);
        dest.writeString(colleges);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(provinceCode);
        dest.writeString(provinceName);
        dest.writeString(cityCode);
        dest.writeString(cityName);
        dest.writeString(countyCode);
        dest.writeString(countyName);
        dest.writeString(fullAddress);
        dest.writeInt(praiseNum);
        dest.writeInt(likeNum);
        dest.writeInt(fansNum);
        dest.writeString(storeId);
        dest.writeString(access_token);
        dest.writeString(refresh_token);
        dest.writeInt(isFirst);
        dest.writeInt(isLogin);
        dest.writeString(registerDate);
        dest.writeParcelable(friend, flags);
    }

    public static class LoginStatus{

        /*未登录*/
        public static final int LOGOUT = 0;

        /*登录*/
        public static final int LOGIN = 1;
    }


}
