package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 相册类
 * @date: 2020/6/16
 * @author: Mr Bai
 */
public class Album implements Parcelable {


    /**
     * id : 427289265400328192
     * type : 2
     * storeId : 0
     * userId : 359575145163403264
     * merchantId : 0
     * name : 测试
     * coverUrl : null
     * accessAuth : 1
     * accessPassword : null
     * sort : 0
     * createBy : 18926184331
     * createTime : 2020-06-28 15:03:09
     * updateBy : 18926184331
     * updateTime : 2020-06-28 15:03:09
     */

    private String id;
    private int type;
    private String storeId;
    private String userId;
    private String merchantId;
    private String name;
    private String coverUrl;
    private int accessAuth;
    private String accessPassword;
    private int sort;
    /*图片关联数*/
    private int boundNum;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    protected Album(Parcel in) {
        id = in.readString();
        type = in.readInt();
        storeId = in.readString();
        userId = in.readString();
        merchantId = in.readString();
        name = in.readString();
        coverUrl = in.readString();
        accessAuth = in.readInt();
        accessPassword = in.readString();
        sort = in.readInt();
        boundNum = in.readInt();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public int getBoundNum() {
        return boundNum;
    }

    public void setBoundNum(int boundNum) {
        this.boundNum = boundNum;
    }

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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(type);
        dest.writeString(storeId);
        dest.writeString(userId);
        dest.writeString(merchantId);
        dest.writeString(name);
        dest.writeString(coverUrl);
        dest.writeInt(accessAuth);
        dest.writeString(accessPassword);
        dest.writeInt(sort);
        dest.writeInt(boundNum);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
    }
}
