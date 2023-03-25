package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 动态
 * @date: 2020/3/4
 * @author: Mr Bai
 */
public class Dynamic implements Parcelable {


    /**
     * id : 385184216520208384
     * type : 1
     * viewNum : 1
     * typeId : 56
     * title : A7
     * description : ✨提到奥迪A7，就不得不提他的两个对手，奔驰CLS最先换代并在2018年6月份上市，而在2018年年底现款A7也正式上市，而宝马6系的换代情况现在来看也还是个未知数。由此可见的是三大豪华品牌对该细分市场的重视程度。🙈其实轿车上的掀背设计，源自于奔驰CLS，不过很多人都说现款CLS经历了之前的换代后逐渐开始不被人接受
     ✨可能是A7要国产的消息从去年就开始被传播的关系，这也导致现款A7的销量受到影响，而上汽大众奥迪正式生产A7的长轴距车型也要等到明年了，上市时间很可能要等到明年中下旬了。👀原因是上汽其实并没有什么制造豪华品牌车型的经验，目前上汽大众生产的旗舰车型为MLBEvo平台的辉昂，而A7国产后也将会与他出自同一平台。👍如果顺利的话，A7L也将会成为这一细分市场第一个加长的车型，如果未来价格合理销量提升明显的话，相信奥迪也会将其他进口车型陆续国产。
     .
     ✨来看今天这款奥迪A7，它保留了上一代最沁人的身形曲线，它们的优美基因传承于1921年奥迪在柏林车展上展出的Type K概念车型，车尾如水滴般向末端自然收紧，这也是历代A7最为迷人之处。👏轴距相比上一代车型只增加了3mm，达到2926mm，自然不是为后排空间做出的妥协，溜背线条显得更为舒展，轿跑车型独有的无框车门的保留。车辆头、尾整体造型被刻画得更为锋利，硬朗的线条削出夸张的六边形中网，矩阵式前大灯与宽大进气格栅契合，营造宽而扁的低趴视觉效果。
     .
     ✨这一代奥迪A7搭载的是3.0T涡轮增压V6发动机，最大功率250kW（5000-6400r/min），峰值扭矩为500Nm（1370-4500r/min），官方零百公里加速时间为5.3s。48v轻度混合动力系统
     * resourceHeight : null
     * cityCode : 440100
     * imageNum : 9
     * lat : 23.131
     * lng : 113.2716
     * position : 广州市小北路
     * coverUrl : http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/%E9%9F%B3%E5%9B%BE%E6%96%87/10-32-423595751451634032640.png,http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/%E9%9F%B3%E5%9B%BE%E6%96%87/10-32-433595751451634032641.png,http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/%E9%9F%B3%E5%9B%BE%E6%96%87/10-32-433595751451634032642.png,http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/%E9%9F%B3%E5%9B%BE%E6%96%87/10-32-433595751451634032643.png
     * mediaUrl : null
     * updateTime : null
     * userId : 359575145163403264
     * nickName : 安卓-潘
     * avatar : http://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/2020/12/icon/11-43-22359575145163403264.png
     * musicUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/music/liuxin/2020/1/332266384875684515.mp3
     * resourceName : 15.mp3
     * mediaSourceList : null
     * praiseNum : 0
     * favoriteNum : 0
     * commentNum : 0
     * praiseStatus : 0
     * favoriteStatus : 0
     * goodsId : null
     * goodsUrl : null
     * goodsTitle : null
     * salePrice : null
     * income1 : null
     * createTime : 1583289164000
     */

    private String id;
    private int type;

    /*浏览数量*/
    private int viewNum;
    private int typeId;
    private String title;
    private String description;
    private Object resourceHeight;
    private int cityCode;
    private int imageNum;
    private double lat;
    private double lng;
    private String position;
    private String coverUrl;
    private String mediaUrl;
    private Object updateTime;
    private String userId;
    private String nickName;
    private String avatar;
    private String musicUrl;
    private String resourceName;
    private Object mediaSourceList;
    private String praiseNum;
    private String favoriteNum;
    private String commentNum;
    private int praiseStatus;

    //收藏状态
    private int favoriteStatus;
    private String goodsUrl;
    private String goodsTitle;
    private long createTime;


    /*关注用户的状态 关注状态1-是 0-否*/
    private int attentionUserStatus;

    public Dynamic() {
    }

    protected Dynamic(Parcel in) {
        id = in.readString();
        type = in.readInt();
        viewNum = in.readInt();
        typeId = in.readInt();
        title = in.readString();
        description = in.readString();
        cityCode = in.readInt();
        imageNum = in.readInt();
        lat = in.readDouble();
        lng = in.readDouble();
        position = in.readString();
        coverUrl = in.readString();
        mediaUrl = in.readString();
        userId = in.readString();
        nickName = in.readString();
        avatar = in.readString();
        musicUrl = in.readString();
        resourceName = in.readString();
        praiseNum = in.readString();
        favoriteNum = in.readString();
        commentNum = in.readString();
        praiseStatus = in.readInt();
        favoriteStatus = in.readInt();
        goodsUrl = in.readString();
        goodsTitle = in.readString();
        createTime = in.readLong();
    }

    public static final Creator<Dynamic> CREATOR = new Creator<Dynamic>() {
        @Override
        public Dynamic createFromParcel(Parcel in) {
            return new Dynamic(in);
        }

        @Override
        public Dynamic[] newArray(int size) {
            return new Dynamic[size];
        }
    };

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

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getResourceHeight() {
        return resourceHeight;
    }

    public void setResourceHeight(Object resourceHeight) {
        this.resourceHeight = resourceHeight;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getImageNum() {
        return imageNum;
    }

    public void setImageNum(int imageNum) {
        this.imageNum = imageNum;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Object getMediaSourceList() {
        return mediaSourceList;
    }

    public void setMediaSourceList(Object mediaSourceList) {
        this.mediaSourceList = mediaSourceList;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(String favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public int getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(int praiseStatus) {
        this.praiseStatus = praiseStatus;
    }

    public int getFavoriteStatus() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(int favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }


    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getAttentionUserStatus() {
        return attentionUserStatus;
    }

    public void setAttentionUserStatus(int attentionUserStatus) {
        this.attentionUserStatus = attentionUserStatus;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(type);
        dest.writeInt(viewNum);
        dest.writeInt(typeId);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(cityCode);
        dest.writeInt(imageNum);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(position);
        dest.writeString(coverUrl);
        dest.writeString(mediaUrl);
        dest.writeString(userId);
        dest.writeString(nickName);
        dest.writeString(avatar);
        dest.writeString(musicUrl);
        dest.writeString(resourceName);
        dest.writeString(praiseNum);
        dest.writeString(favoriteNum);
        dest.writeString(commentNum);
        dest.writeInt(praiseStatus);
        dest.writeInt(favoriteStatus);
        dest.writeString(goodsUrl);
        dest.writeString(goodsTitle);
        dest.writeLong(createTime);
    }
}
