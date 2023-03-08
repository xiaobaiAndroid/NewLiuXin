package module.common.data.entity;

/**
 * @describe: 好友请求实体类
 * @date: 2020/4/11
 * @author: Mr Bai
 */
public class FriendRequest {


    /**
     * id : 389340293721632768
     * userId : 384489609499127808
     * senderId : 359559726620880896
     * type : 1
     * operation : {"friendId":"384489609499127808","sender":"双击查原图","senderAvatar":"http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/icon/00-54-09359559726620880896.png","desc":"请求添加您为好友"}
     * state : 1
     * createBy : 13378434567
     * createTime : 2020-03-15 21:47:29
     * updateBy : 18664868453
     * updateTime : 2020-03-18 18:09:51
     */

    private String id;
    private String userId;
    private String senderId;
    private int type;
    private String operation;
    private int state;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public static class ExtraInfo{

        /**
         * friendId : 384489609499127808
         * sender : 双击查原图
         * senderAvatar : http://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/2020/03/icon/00-54-09359559726620880896.png
         * desc : 请求添加您为好友
         */

        /*请求id*/
        private String id;

        private String userId;
        private String senderId;
        private int type;

        /*1为通过，0为待处理*/
        private int state;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String friendId;
        private String sender;
        private String senderAvatar;
        private String desc;



        public String getFriendId() {
            return friendId;
        }

        public void setFriendId(String friendId) {
            this.friendId = friendId;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getSenderAvatar() {
            return senderAvatar;
        }

        public void setSenderAvatar(String senderAvatar) {
            this.senderAvatar = senderAvatar;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
