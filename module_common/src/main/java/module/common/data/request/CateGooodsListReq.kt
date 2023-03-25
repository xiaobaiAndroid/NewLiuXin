package module.common.data.request

import module.common.base.BaseListReq

/**
 * @describe: 根据类别获取商品列表
 * @date: 2020/4/27
 * @author: Mr Bai
 */
class CateGooodsListReq : BaseListReq<CateGooodsListReq.QueryObj>(QueryObj()) {
    class QueryObj {
        /*分类id,首页商品列表不传*/
        var cateId: String? = null

        /*分类id集合*/
        var cateIds: List<String>? = null
        var state = 1

        /*商品标题*/
        var goodsTitle: String? = null

        /*品牌id*/
        var brandIds: List<String>? = null

        /*筛选后的skuid*/
        var skuIds: List<String>? = null

        /*价格从大到小*/
        var priceDesc: String? = null

        /*价格从小到大 Asc*/
        var priceAsc: String? = null

        /*销量排序：Desc或Asc*/
        var salesDesc: String? = null

        /*价格区间*/
        var minPrice: String? = null
        var maxPrice: String? = null
    }
}