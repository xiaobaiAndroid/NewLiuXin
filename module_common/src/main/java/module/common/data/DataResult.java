package module.common.data;

/**
 *  数据的结果和状态
 * com.qiaocat.app.data
 * @author: Mr Bai
 * @date: 2017/11/3
 */
public class DataResult<T> {


    /**
     * 结果集
     */
    private T t;

    /**
     * 状态
     */
    private int status = FAIL;


    /**
     * 未登陆的标识
     */
    public static final String NO_LOGIN_FLAG = "\"code\":13000";

    /**
     * 服务端返回的信息
     */
    private String message = "服务端未响应";

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /*服务端成功的标识*/
    public static final String  SERVICE_SUCCESS = "SYS1-0001";

    public static final String TOKEN_PAST_LABEL = "{\"code\":\"401\",\"detail\":\"授权过期\"}";

    /*失败*/
    public static final int FAIL = -1;

    /*余额不足*/
    public static final int NOT_MONEY = 201;


    /*未注册*/
    public static final int UNREGISTER = 202;

    /*token过期*/
    public static final int TOKEN_PAST = 401;

    /*需要登录*/
    public static final int NEED_LOGIN = 101;

}
