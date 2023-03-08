package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Banner implements Parcelable {

    /**
     * id : 366808489286578176
     * cateId : 0
     * imgUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/4/90340084765125903.png
     * url : liuixn_Attract
     * state : 1
     * createBy : 13800138001
     * createTime : 2020-01-13 17:34:09
     * updateBy : 13800138001
     * updateTime : 2020-04-24 11:33:50
     */

    private String id;
    private String cateId;
    private String actId;
    private String imgUrl;
    private String url;
    private String bannerTypeCode;
    private String goodsId;
    private String storeId;
    private String languageMarket;
    private int state;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    protected Banner(Parcel in) {
        id = in.readString();
        cateId = in.readString();
        actId = in.readString();
        imgUrl = in.readString();
        url = in.readString();
        bannerTypeCode = in.readString();
        goodsId = in.readString();
        storeId = in.readString();
        languageMarket = in.readString();
        state = in.readInt();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
    }

    public static final Creator<Banner> CREATOR = new Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel in) {
            return new Banner(in);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBannerTypeCode() {
        return bannerTypeCode;
    }

    public void setBannerTypeCode(String bannerTypeCode) {
        this.bannerTypeCode = bannerTypeCode;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getLanguageMarket() {
        return languageMarket;
    }

    public void setLanguageMarket(String languageMarket) {
        this.languageMarket = languageMarket;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(cateId);
        dest.writeString(actId);
        dest.writeString(imgUrl);
        dest.writeString(url);
        dest.writeString(bannerTypeCode);
        dest.writeString(goodsId);
        dest.writeString(storeId);
        dest.writeString(languageMarket);
        dest.writeInt(state);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
    }
}