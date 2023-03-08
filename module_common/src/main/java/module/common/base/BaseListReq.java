package module.common.base;


/**
 * @describe: 列表请求基类
 * @date: 2019/8/22
 * @author: Mr Bai
*/
public class BaseListReq<T> {

    private int pageNumber=1;

    private int pageSize=10;

    private T queryObj;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(T queryObj) {
        this.queryObj = queryObj;
    }
}
