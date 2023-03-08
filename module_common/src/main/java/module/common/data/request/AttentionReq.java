package module.common.data.request;

import module.common.base.BaseListReq;

/**
 * @describe: 获取关注状态请求
 * @date: 2020/3/10
 * @author: Mr Bai
 */
public class AttentionReq extends BaseListReq<AttentionReq.QueryObj> {

    public static class QueryObj{
        private String userId;

        /*关注人的id*/
        private String userLikeId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserLikeId() {
            return userLikeId;
        }

        public void setUserLikeId(String userLikeId) {
            this.userLikeId = userLikeId;
        }
    }
}
