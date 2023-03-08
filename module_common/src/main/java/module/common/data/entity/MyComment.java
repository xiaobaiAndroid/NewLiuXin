package module.common.data.entity;

public class MyComment {


    /**
     * id : 390728789292101632
     * coverUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/circle/1584611091237.png
     * mediaUrl : null
     * type : 2
     * title : 嗯
     * description : 嗯
     * mediaUserId : 384489609499127808
     * mediaNickName : 18664868453
     * commentId : 390990819529994240
     * content : 广东
     * userId : 390989556536979456
     * nickName : 17818837131
     * avatar : https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/im/head.png
     * updateTime : 1584673565000
     */

    private String id;
    private String coverUrl;
    private Object mediaUrl;
    private int type;
    private String title;
    private String description;
    private String mediaUserId;
    private String mediaNickName;
    private String commentId;
    private String content;
    private String userId;
    private String nickName;
    private String avatar;
    private long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Object getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(Object mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaUserId() {
        return mediaUserId;
    }

    public void setMediaUserId(String mediaUserId) {
        this.mediaUserId = mediaUserId;
    }

    public String getMediaNickName() {
        return mediaNickName;
    }

    public void setMediaNickName(String mediaNickName) {
        this.mediaNickName = mediaNickName;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
