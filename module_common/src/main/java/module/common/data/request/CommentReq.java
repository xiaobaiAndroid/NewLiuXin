package module.common.data.request;

/**
 * @describe: 评论请求
 * @date: 2020/3/10
 * @author: Mr Bai
 */
public class CommentReq {

    private String userId;

    /*动态id*/
    private String mediaId;

    /*被评论人id*/
    private String replyUserId;

    private String content;

    /*父评论ID，父评论时为0*/
    private String commentId = "0";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
