package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 音乐分类
 * @date: 2020/3/24
 * @author: Mr Bai
 */
public class MusicCategory implements Parcelable {
    private String musicType;

    private String musicTypeName;

    public MusicCategory() {
    }

    protected MusicCategory(Parcel in) {
        musicType = in.readString();
        musicTypeName = in.readString();
    }

    public static final Creator<MusicCategory> CREATOR = new Creator<MusicCategory>() {
        @Override
        public MusicCategory createFromParcel(Parcel in) {
            return new MusicCategory(in);
        }

        @Override
        public MusicCategory[] newArray(int size) {
            return new MusicCategory[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(musicType);
        dest.writeString(musicTypeName);
    }
}
