package module.common.data.entity;

/**
 * @describe: 相册图片
 * @date: 2020/6/29
 * @author: Mr Bai
 */
public class AlbumPicture {


    /**
     * id : 427655632368578560
     * boundNum : 0
     * type : null
     * fileFolderId : 427335087982784512
     * merchantId : 0
     * url : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/picture/IMG_09E08787265A2CECB9421D06F439AC65.jpeg
     * coverUrl : null
     * keyword : 风景
     * size : 1440
     * sort : 1
     * time : null
     * createBy : 18926184331
     * createTime : 2020-06-29 15:18:58
     * updateBy : 18926184331
     * updateTime : 2020-06-29 15:18:58
     */

    private String id;
    private int boundNum;
    /*图片或视频*/
    private int type;
    private String fileFolderId;
    private String merchantId;
    private String url;
    private String coverUrl;
    private String keyword;
    private int size;
    private int sort;
    private Object time;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBoundNum() {
        return boundNum;
    }

    public void setBoundNum(int boundNum) {
        this.boundNum = boundNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFileFolderId() {
        return fileFolderId;
    }

    public void setFileFolderId(String fileFolderId) {
        this.fileFolderId = fileFolderId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
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
