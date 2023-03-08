package module.common.data.request;

/**
 * @describe: 点赞请求
 * @date: 2020/3/9
 * @author: Mr Bai
 */
public class EndorseReq {


    /**
     * userId : 341393993948868608
     * mediaId : 354041073599131648
     * state : 0
     */

    private String userId;
    private String mediaId;

    /*点赞状态1-是 0-否*/
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
