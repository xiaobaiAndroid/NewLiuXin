package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 商品颜色对于恶的图片
 * @date: 2020/5/2
 * @author: Mr Bai
 */
public class GoodsColorImage implements Parcelable {


    /**
     * id : 405958424866992128
     * goodsId : 405958424497893376
     * colorId : 103
     * url : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/4/89989791457437561%20%281%29.jpg
     * createBy : 13688879888
     * createTime : 2020-04-30 18:22:00
     * updateBy : 13688879888
     * updateTime : 2020-04-30 18:22:00
     */

    private String id;
    private String goodsId;
    private String colorId;
    private String url;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    protected GoodsColorImage(Parcel in) {
        id = in.readString();
        goodsId = in.readString();
        colorId = in.readString();
        url = in.readString();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
    }

    public static final Creator<GoodsColorImage> CREATOR = new Creator<GoodsColorImage>() {
        @Override
        public GoodsColorImage createFromParcel(Parcel in) {
            return new GoodsColorImage(in);
        }

        @Override
        public GoodsColorImage[] newArray(int size) {
            return new GoodsColorImage[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        dest.writeString(goodsId);
        dest.writeString(colorId);
        dest.writeString(url);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
    }
}
