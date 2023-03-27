package module.common.data.request

import module.common.base.BaseListReq

/**
 * @describe: 搜索
 * @date: 2020/3/21
 * @author: Mr Bai
 */
class SearchReq : BaseListReq<SearchReq.QueryObj>(QueryObj()) {
    class QueryObj {
        var title: String? = null
    }
}