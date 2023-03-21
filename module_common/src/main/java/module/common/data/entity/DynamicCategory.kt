package module.common.data.entity

class DynamicCategory {
    /**
     * id : 45
     * type : 1
     * typeName : 自拍
     * displayOrder : 5
     */
    /*	分类id*/
    var id: String? = null

    /*媒体类型*/
    var type = 0

    /*上级分类id*/
    var typeName: String? = null

    /*排序的序号*/
    var displayOrder: String? = null

    object Type {
        /*推荐*/
        const val RECOMMEND = "1"

        /*好友*/
        const val FRIEND = "2"

        /*同城*/
        const val CITY = "4"
    }
}