package module.common.data.request;

/**
 * @describe: 更新关注状态的请求
 * @date: 2020/3/10
 * @author: Mr Bai
 */
public class UpdateAttentionReq {


    /**
     * userId : 341393993948868608
     * likeUserId : 352252674382901248
     * state : 0
     */

    private String userId;

    /*关注的用户id*/
    private String likeUserId;

    /*关注状态1-是 0-否*/
    private String state;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(String likeUserId) {
        this.likeUserId = likeUserId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
