package module.common.data.request;

/**
 * @describe: 相册添加图片请求
 * @date: 2020/6/28
 * @author: Mr Bai
 */
public class AlbumAddPictureReq {


    /**
     * merchantId : 357329675070353408
     * fileFolderId : 1
     * url : 33333
     * boundNum : 0
     * keyword : 111
     * sort : 1
     */

    private long merchantId = 0;
    /*文件夹id*/
    private String fileFolderId;
    private String url;
    /*图片关联数*/
    private int boundNum = 0;

    /*图片关键字*/
    private String keyword;
    private int sort = 1;

    /*尺寸*/
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFileFolderId() {
        return fileFolderId;
    }

    public void setFileFolderId(String fileFolderId) {
        this.fileFolderId = fileFolderId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getBoundNum() {
        return boundNum;
    }

    public void setBoundNum(int boundNum) {
        this.boundNum = boundNum;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
