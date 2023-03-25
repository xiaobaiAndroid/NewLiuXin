package module.common.data.request

import module.common.base.BaseListReq

class CollectDynamicReq(queryObj: QueryObj?) : BaseListReq<CollectDynamicReq.QueryObj?>(queryObj) {
    class QueryObj {
        /**
         * type : 1
         * state : 1
         */
        var type = 0

        /*固定传1*/
        var state = 1
    }
}