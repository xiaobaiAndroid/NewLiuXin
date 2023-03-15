package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * @describe: 好友模块
 * @date: 2020/3/28
 * @author: Mr Bai
 */
public class Friend extends BaseNode implements Parcelable {


    /**
     * id : 390307702276239360
     * userId : 384489609499127808
     * groupId : 0
     * friendId : 384305454412869632
     * showCircle : 1
     * remark : null
     * state : 1
     * extend : {"id":"384305454412869632","no":"13296575093","nickName":"大龙猫","intro":"卡卡卡卡卡卡","avatar":"http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/icon/00-21-14384305454412869632.png","name":null,"mobile":"13296575093","sex":1,"photo":"http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/BGImage/21-01-42384305454412869632.png","birthday":"1993-03-17","job":"软件开发经验丰富","colleges":"博士后","showRealInfo":1,"onLineState":1,"provinceCode":120000,"provinceName":"天津市","cityCode":120100,"cityName":"天津市","countyCode":120101,"countyName":"和平区","fullAddress":"天津市天津市和平区","lat":null,"lng":null,"state":1,"createBy":"unknown","createTime":"2020-03-02 00:20:50","updateBy":"13296575093","updateTime":"2020-03-03 16:06:03","certification":null,"friend":null,"praiseNum":0,"likeNum":0,"fansNum":0,"storeId":null}
     */

    private String id;
    private String userId;
    private String groupId;
    private String friendId;

    //看他的圈子：0，不是 1，是
    private int showCircle;

    //黑名单：0，不是 1，是
    private int blacklist;
    private String remark;
    private int state;
    private IMUserInfo extend;

    private IMFriendGroup group;
    private int praiseNum;
    private int likeNum;
    private int fansNum;
    private int storeId;


    private boolean isSelected = false;

    protected Friend(Parcel in) {
        id = in.readString();
        userId = in.readString();
        groupId = in.readString();
        friendId = in.readString();
        showCircle = in.readInt();
        blacklist = in.readInt();
        remark = in.readString();
        state = in.readInt();
        extend = in.readParcelable(UserInfo.class.getClassLoader());
        group = in.readParcelable(IMFriendGroup.class.getClassLoader());
        praiseNum = in.readInt();
        likeNum = in.readInt();
        fansNum = in.readInt();
        storeId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeString(groupId);
        dest.writeString(friendId);
        dest.writeInt(showCircle);
        dest.writeInt(blacklist);
        dest.writeString(remark);
        dest.writeInt(state);
        dest.writeParcelable(extend, flags);
        dest.writeParcelable(group, flags);
        dest.writeInt(praiseNum);
        dest.writeInt(likeNum);
        dest.writeInt(fansNum);
        dest.writeInt(storeId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public IMUserInfo getExtend() {
        return extend;
    }

    public void setExtend(IMUserInfo extend) {
        this.extend = extend;
    }

    public IMFriendGroup getGroup() {
        return group;
    }

    public void setGroup(IMFriendGroup group) {
        this.group = group;
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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
