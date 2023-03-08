package module.common.data.response;

import module.common.data.api.BaseResp;

/**
 * @describe: 赠送礼物的响应实体
 * @date: 2020/3/8
 * @author: Mr Bai
 */
public class GiveGiftResp extends BaseResp<GiveGiftResp.Result> {


    public static class Result{


        /**
         * 0-浏币不足 1-成功
         */

        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }


    public static class Status{
        /*浏币不足*/
        public static final String NOT_MONEY = "0";

        /*成功*/
        public static final String SUCCESS = "1";
    }
}
