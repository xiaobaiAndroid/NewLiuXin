package module.common.base

/**
 * @describe: 列表请求基类
 * @date: 2019/8/22
 * @author: Mr Bai
 */
open class BaseListReq<T>(val queryObj: T) {
    var pageNumber = 1
    var pageSize = 20
}