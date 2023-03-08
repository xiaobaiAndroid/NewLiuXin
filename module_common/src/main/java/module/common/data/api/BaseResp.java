package module.common.data.api;


public class BaseResp<T>{


    /**
     * state : 1
     * transId : null
     * transTime : null
     * responseTime : 2020-03-04 08:58:29
     * transFor : null
     * userName : null
     * message : {"code":"SYS1-0001","detail":"","info":"操作成功"}
     * data : {}
     */

    private String state;
    private Object transTime;
    private String responseTime;
    private String userName;
    private MessageStatus message;
    private T data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Object getTransTime() {
        return transTime;
    }

    public void setTransTime(Object transTime) {
        this.transTime = transTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MessageStatus getMessage() {
        return message;
    }

    public void setMessage(MessageStatus message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static class MessageStatus {
        /**
         * code : SYS1-0001
         * detail :
         * info : 操作成功
         */

        private String code;
        private String detail;
        private String info;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

}
