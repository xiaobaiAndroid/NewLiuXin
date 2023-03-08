package module.common.data.request;

import module.common.base.BaseListReq;

public class GiftsReq extends BaseListReq<GiftsReq.QueryObj> {



    public static class QueryObj{

        /**
         * giftTypeId : 1
         */

        private int giftTypeId;

        public QueryObj(int giftTypeId) {
            this.giftTypeId = giftTypeId;
        }

        public int getGiftTypeId() {
            return giftTypeId;
        }

        public void setGiftTypeId(int giftTypeId) {
            this.giftTypeId = giftTypeId;
        }
    }
}
