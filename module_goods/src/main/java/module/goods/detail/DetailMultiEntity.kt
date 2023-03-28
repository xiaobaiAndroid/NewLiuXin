package module.goods.detail

import com.chad.library.adapter.base.entity.MultiItemEntity
import module.common.data.entity.Goods
import module.common.data.entity.GoodsDetailImage
import module.common.data.entity.Shop

class DetailMultiEntity : MultiItemEntity {
    override var itemType: Int
        private set
    var goods: Goods? = null
    var shop: Shop? = null
    var banners: MutableList<GoodsDetailImage>?=null
    var videoUrl: String?=null
    var detailImage: GoodsDetailImage? = null
    var slectedSkuContent: String? = null

    constructor(type: Int) {
        itemType = type
    }

    constructor(type: Int, goods: Goods?) {
        itemType = type
        this.goods = goods
    }

    internal enum class Type {
        BANNER, SKU, SHOP, DETAIL
    }
}