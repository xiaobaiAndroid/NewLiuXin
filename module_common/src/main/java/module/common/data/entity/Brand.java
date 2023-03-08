package module.common.data.entity;

/**
 * @describe: 品牌
 * @date: 2020/4/30
 * @author: Mr Bai
 */
public class Brand {


    /**
     * id : 367068316495654912
     * brandUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/1/4256903107366544%E8%A4%90.jpg
     * brandName : 褐马服装
     * state : 1
     * createBy : 13800138001
     * createTime : 2020-01-14 10:46:36
     * updateBy : 13800138001
     * updateTime : 2020-01-14 10:46:36
     */

    private String id;
    private String brandUrl;
    private String brandName;
    private int state;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    private boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
}
