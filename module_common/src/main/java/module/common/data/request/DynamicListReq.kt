package module.common.data.request

import module.common.base.BaseListReq

/**
 * @describe: 动态列表请求
 * @date: 2020/3/4
 * @author: Mr Bai
 */
class DynamicListReq: BaseListReq<DynamicListReq.QueryObj>(QueryObj()) {

    class QueryObj {
        var type = 0
        var typeId: String? = null
        var mediaStatus = 0

    }
}