package module.common.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @describe: 评论实体
 * @date: 2020/3/10
 * @author: Mr Bai
 */
@Parcelize
class Comment: Parcelable {
    var id: String? = null
    var mediaId: String? = null
    var userId: String? = null
    var nickName: String? = null
    var avatar: String? = null
    var content: String? = null

    /*父评论的id*/
    var commentId: String? = null
    var updateTime: String? = null
    var replyUserId: String? = null

    /*回复用户昵称*/
    var replyNickName: String? = null

    /*回复数量*/
    var countSon: String? = null
}