package module.dynamic.detail.imgtxt

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.Comment
import module.common.utils.DateUtils
import module.common.utils.ImageLoadHelper.loadCircle
import module.common.utils.StringUtils.packNull
import module.dynamic.R
import java.util.*

/**
 *@author: baizf
 *@date: 2023/3/19
 */
class CommentAdapter(list: MutableList<Comment>?) :
    BaseQuickAdapter<Comment, BaseViewHolder>(R.layout.dynamic_comment_item_comment, list),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, comment: Comment) {
        val avatarIV: ImageView = holder.getView<ImageView>(R.id.avatarIV)
        loadCircle(avatarIV, comment.getAvatar(), module.common.R.drawable.ic_default_avatar)

        val format = context.resources.getString(R.string.dynamic_format_reply_number)
        val replyNumber = String.format(format, comment.countSon)

        holder.setText(R.id.nicknameTV, packNull(comment.nickName))
            .setText(R.id.contentTV, packNull(comment.content))
            .setText(
                R.id.dateTV,
                comment.updateTime
            )
            .setText(R.id.replyNumberTV, replyNumber)
    }
}