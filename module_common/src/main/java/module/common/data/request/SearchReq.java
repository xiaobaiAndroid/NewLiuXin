package module.common.data.request;


import module.common.base.BaseListReq;


/**
 * @describe: 搜索
 * @date: 2020/3/21
 * @author: Mr Bai
 */
public class SearchReq extends BaseListReq<SearchReq.QueryObj> {

    public static class QueryObj{
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
