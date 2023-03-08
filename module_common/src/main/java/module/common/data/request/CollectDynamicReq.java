package module.common.data.request;

import module.common.base.BaseListReq;

public class CollectDynamicReq extends BaseListReq<CollectDynamicReq.QueryObj> {

    public static class QueryObj{

        /**
         * type : 1
         * state : 1
         */

        private int type;

        /*固定传1*/
        private int state = 1;

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
    }
}
