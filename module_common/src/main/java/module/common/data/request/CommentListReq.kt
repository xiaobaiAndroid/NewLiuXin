package module.common.data.request

import module.common.base.BaseListReq

/**
 * @describe: 评论列表请求
 * @date: 2020/3/10
 * @author: Mr Bai
 */
class CommentListReq : BaseListReq<CommentListReq.QueryObj?>() {

    init {
        queryObj = QueryObj()
    }

    class QueryObj {
        /**
         * 动态id
         */
        var mediaId: String? = null

        /*父评论传0,子评论传评论id*/
        var commentId = "0"
    }
}