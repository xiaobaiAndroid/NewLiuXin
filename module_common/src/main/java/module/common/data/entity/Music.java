package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * @describe: 音乐
 * @date: 2020/3/24
 * @author: Mr Bai
 */
public class Music  implements Parcelable {

    
    private String id;

    private String musicType;

    
    private String musicTypeName;

    
    private String musicName;
    
    private String musicUrl;

    /*缓存的路径*/
    
    private String cancelPath;

    
    private String createTime;

    
    private String updateTime;

    
    private String updateBy;

    /*音乐时长*/
    
    private String musicTime;

    
    private String musicLable;

    /*是否选中*/
    private boolean isSelected = false;

    protected Music(Parcel in) {
        id = in.readString();
        musicType = in.readString();
        musicTypeName = in.readString();
        musicName = in.readString();
        musicUrl = in.readString();
        cancelPath = in.readString();
        createTime = in.readString();
        updateTime = in.readString();
        updateBy = in.readString();
        musicTime = in.readString();
        musicLable = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(String musicTime) {
        this.musicTime = musicTime;
    }

    public String getMusicLable() {
        return musicLable;
    }

    public void setMusicLable(String musicLable) {
        this.musicLable = musicLable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getMusicTypeName() {
        return musicTypeName;
    }

    public void setMusicTypeName(String musicTypeName) {
        this.musicTypeName = musicTypeName;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getCancelPath() {
        return cancelPath;
    }

    public void setCancelPath(String cancelPath) {
        this.cancelPath = cancelPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(musicType);
        dest.writeString(musicTypeName);
        dest.writeString(musicName);
        dest.writeString(musicUrl);
        dest.writeString(cancelPath);
        dest.writeString(createTime);
        dest.writeString(updateTime);
        dest.writeString(updateBy);
        dest.writeString(musicTime);
        dest.writeString(musicLable);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
