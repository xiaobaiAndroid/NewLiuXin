package module.common.data.entity;

/**
 * @describe: 点赞实体
 * @date: 2020/3/20
 * @author: Mr Bai
 */
public class Endorse {

    private String id;
    private int type;
    private String title;
    private String description;
    private String userId;
    private String nickName;
    private String avatar;
    private long updateTime;
    /**
     * coverUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/circle/1584611091237.png
     * type : 2
     * updateTime : 1584673546000
     */

    private String coverUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getUpdateTime() {
        return updateTime;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
