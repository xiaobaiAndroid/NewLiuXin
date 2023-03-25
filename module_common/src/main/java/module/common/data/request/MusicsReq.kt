package module.common.data.request

import module.common.base.BaseListReq

class MusicsReq() : BaseListReq<MusicsReq.QueryObj>(QueryObj()) {
    class QueryObj {
        /*音乐分类*/
        var musicType = 0
    }
}