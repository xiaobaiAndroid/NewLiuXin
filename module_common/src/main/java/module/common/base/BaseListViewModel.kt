package module.common.base

/**
 *@author: baizf
 *@date: 2023/3/21
 */
open class BaseListViewModel<V,T: BaseListReq<V>>(val req: T): BaseViewModel() {



}