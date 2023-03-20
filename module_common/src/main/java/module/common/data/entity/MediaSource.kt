package module.common.data.entity

data class MediaSource(
    val createBy: String,
    val createTime: String,
    val id: String,
    val mediaId: String,
    val mediaImageTagList: List<Any>,
    val resourceName: Any,
    val typeId: Int,
    val updateBy: String,
    val updateTime: String,
    val url: String
)