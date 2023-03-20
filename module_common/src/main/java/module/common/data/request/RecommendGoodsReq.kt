package module.common.data.request

import module.common.base.BaseListReq

/**
 * @describe: 推荐商品请求
 * @date: 2020/3/17
 * @author: Mr Bai
 */
class RecommendGoodsReq : BaseListReq<RecommendGoodsReq.QueryObj?>() {
    class QueryObj {
        /*	分类id*/
//        var cateId: String? = null
        val state = "1"
    }
}