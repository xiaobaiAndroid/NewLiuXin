package module.common.data.request;

/**
 * @describe: 动态列表请求
 * @date: 2020/3/4
 * @author: Mr Bai
 */
public class DynamicListReq {

    private int pageNumber=1;

    private int pageSize=10;

    private QueryObj queryObj;

    public QueryObj getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(QueryObj queryObj) {
        this.queryObj = queryObj;
    }

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

    public static class QueryObj{
        private int type;

        public QueryObj(String typeId) {
            try {
                type =Integer.valueOf(typeId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
