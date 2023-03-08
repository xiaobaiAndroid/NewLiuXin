package module.common.data.request;

import module.common.base.BaseListReq;

/**
 * @describe: 排行请求体
 * @date: 2020/4/8
 * @author: Mr Bai
 */
public class RankingReq extends BaseListReq<RankingReq.QueryObj> {

    public static class QueryObj{

//        时间类型1-昨日 2-上周 3-上月
        private String type = "1";

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
