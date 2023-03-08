package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @describe: 商品类别
 * @date: 2020/4/25
 * @author: Mr Bai
 */
public class GoodsCategory implements Parcelable {


    /**
     * id : 400862941395955712
     * parentId : 0
     * manageType : 泳裤
     * cateName : 泳裤
     * cateUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/4/1522593931239201678752875.jpg
     * displayOrder : 1
     * state : 0
     * createBy : 13800138001
     * createTime : 2020-04-16 16:54:23
     * updateBy : 13800138001
     * updateTime : 2020-04-16 21:33:25
     * childList : null
     * cateSkuList : null
     */

    private String id;
    private String parentId;
    private String manageType;
    private String cateName;
    private String cateUrl;
    private int displayOrder;
    private int state;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private Object childList;
    private Object cateSkuList;

    private int resourceId;

    public GoodsCategory() {
    }

    public GoodsCategory(String id, String cateName, int resourceId) {
        this.id = id;
        this.cateName = cateName;
        this.resourceId = resourceId;
    }


    protected GoodsCategory(Parcel in) {
        id = in.readString();
        parentId = in.readString();
        manageType = in.readString();
        cateName = in.readString();
        cateUrl = in.readString();
        displayOrder = in.readInt();
        state = in.readInt();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
        resourceId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(parentId);
        dest.writeString(manageType);
        dest.writeString(cateName);
        dest.writeString(cateUrl);
        dest.writeInt(displayOrder);
        dest.writeInt(state);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
        dest.writeInt(resourceId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GoodsCategory> CREATOR = new Creator<GoodsCategory>() {
        @Override
        public GoodsCategory createFromParcel(Parcel in) {
            return new GoodsCategory(in);
        }

        @Override
        public GoodsCategory[] newArray(int size) {
            return new GoodsCategory[size];
        }
    };

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateUrl() {
        return cateUrl;
    }

    public void setCateUrl(String cateUrl) {
        this.cateUrl = cateUrl;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
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

    public Object getChildList() {
        return childList;
    }

    public void setChildList(Object childList) {
        this.childList = childList;
    }

    public Object getCateSkuList() {
        return cateSkuList;
    }

    public void setCateSkuList(Object cateSkuList) {
        this.cateSkuList = cateSkuList;
    }

}
