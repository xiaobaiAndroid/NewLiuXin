package module.common.data.request;

import module.common.base.BaseListReq;

/**
 * @describe: 推荐商品请求
 * @date: 2020/3/17
 * @author: Mr Bai
 */
public class RecommendGoodsReq extends BaseListReq<RecommendGoodsReq.QueryObj> {


    public static class QueryObj{

        /*	分类id*/
        private String cateId;

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }
    }
}
