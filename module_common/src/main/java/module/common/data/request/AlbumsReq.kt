package module.common.data.request

import module.common.base.BaseListReq

class AlbumsReq: BaseListReq<AlbumsReq.Companion.QueryObject>(QueryObject()) {

    companion object{

        class QueryObject{
            var merchantId ="0"
            /*1：图片，2：视频*/
            var type:Int=1
            var userId:String?=null
        }
    }
}