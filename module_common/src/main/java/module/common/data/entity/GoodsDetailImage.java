package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 商品详情的图片
 * @date: 2020/5/2
 * @author: Mr Bai
 */
public class GoodsDetailImage implements Parcelable {


    /**
     * id : 405958425114456078
     * type : 2
     * goodsId : 405958424497893376
     * url : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/4/5455611612194700
     * sort : 15
     * createBy : 13688879888
     * createTime : 2020-04-30 18:22:01
     * updateBy : 13688879888
     * updateTime : 2020-04-30 18:22:01
     */

    private String id;
    private int type;
    private String goodsId;
    private String url;
    private int sort;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    protected GoodsDetailImage(Parcel in) {
        id = in.readString();
        type = in.readInt();
        goodsId = in.readString();
        url = in.readString();
        sort = in.readInt();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(type);
        dest.writeString(goodsId);
        dest.writeString(url);
        dest.writeInt(sort);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GoodsDetailImage> CREATOR = new Creator<GoodsDetailImage>() {
        @Override
        public GoodsDetailImage createFromParcel(Parcel in) {
            return new GoodsDetailImage(in);
        }

        @Override
        public GoodsDetailImage[] newArray(int size) {
            return new GoodsDetailImage[size];
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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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


}
