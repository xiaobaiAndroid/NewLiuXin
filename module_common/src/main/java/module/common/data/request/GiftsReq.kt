package module.common.data.request

import module.common.base.BaseListReq

class GiftsReq: BaseListReq<GiftsReq.QueryObj>(QueryObj()) {
    class QueryObj {
        /**
         * giftTypeId : 1
         */
        var giftTypeId = 0
    }
}