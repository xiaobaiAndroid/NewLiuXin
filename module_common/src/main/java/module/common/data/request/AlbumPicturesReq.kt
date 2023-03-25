package module.common.data.request

import module.common.base.BaseListReq

/**
 * @describe: 图库图片列表
 * @date: 2020/6/28
 * @author: Mr Bai
 */
class AlbumPicturesReq() : BaseListReq<AlbumPicturesReq.QueryObj>(QueryObj()) {
    class QueryObj {
        var fileFolderId: String? = null
    }
}