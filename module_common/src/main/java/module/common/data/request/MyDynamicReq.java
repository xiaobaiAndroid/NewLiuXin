package module.common.data.request;


import module.common.base.BaseListReq;

/**
 * @describe: 我的动态请求
 * @date: 2020/3/19
 * @author: Mr Bai
 */
public class MyDynamicReq extends BaseListReq<MyDynamicReq.QueryObj> {

    public MyDynamicReq(QueryObj queryObj) {
        super(queryObj);
    }

    public static class QueryObj{

        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
