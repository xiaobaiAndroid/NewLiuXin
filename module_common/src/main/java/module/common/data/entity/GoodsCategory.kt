package module.common.data.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @describe: 商品类别
 * @date: 2020/4/25
 * @author: Mr Bai
 */
@Parcelize
class GoodsCategory() : Parcelable {
    /**
     * id : 400862941395955712
     * parentId : 0
     * manageType : 泳裤
     * cateName : 泳裤
     * cateUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/4/1522593931239201678752875.jpg
     * displayOrder : 1
     * state : 0
     * createBy : 13800138001
     * createTime : 2020-04-16 16:54:23
     * updateBy : 13800138001
     * updateTime : 2020-04-16 21:33:25
     * childList : null
     * cateSkuList : null
     */
    var id: String = ""
    var parentId: String? = null
    var manageType: String? = null
    var cateName: String? = null
    var cateUrl: String? = null
    var displayOrder = 0
    var state = 0
    var createBy: String? = null
    var createTime: String? = null
    var updateBy: String? = null
    var updateTime: String? = null
    var childList: Any? = null
    var cateSkuList: Any? = null
    var resourceId = -1

    constructor(id: String, cateName: String?, resourceId: Int) : this() {
        this.id = id
        this.cateName = cateName
        this.resourceId = resourceId
    }

}