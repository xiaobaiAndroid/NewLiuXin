package module.common.data.response;

import module.common.data.api.BaseResp;

public class GetRedPacketResp extends BaseResp<GetRedPacketResp.Data> {

    public static  class Data{


        /**
         * status : 2
         */

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}
