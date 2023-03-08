package module.common.data.request;

import module.common.base.BaseListReq;

/**
 * @describe: 评论列表请求
 * @date: 2020/3/10
 * @author: Mr Bai
 */
public class CommentListReq extends BaseListReq<CommentListReq.QueryObj> {

    public static class QueryObj{

        /**
         * 动态id
         */
        private String mediaId;

        /*父评论传0,子评论传评论id*/
        private String commentId = "0";

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }
    }
}
