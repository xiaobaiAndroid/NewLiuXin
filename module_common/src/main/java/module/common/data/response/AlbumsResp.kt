package module.common.data.response

import module.common.base.CommonListResp
import module.common.data.api.BaseResp
import module.common.data.entity.Album

class AlbumsResp: BaseResp<CommonListResp<Album>>() {
}