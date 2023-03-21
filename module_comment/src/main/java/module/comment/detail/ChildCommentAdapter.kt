package module.comment.detail

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.comment.R
import module.common.data.entity.Comment
import module.common.utils.DateUtils
import module.common.utils.ImageLoadHelper
import module.common.utils.StringUtils
import java.util.*

class ChildCommentAdapter(data: MutableList<Comment>?) :
    BaseQuickAdapter<Comment, BaseViewHolder>(R.layout.comment_item_child_comment, data),
    LoadMoreModule {

    override fun convert(holder: BaseViewHolder, comment: Comment) {
        val avatarIV: ImageView = holder.getView<ImageView>(R.id.avatarIV)
        ImageLoadHelper.loadCircle(avatarIV, comment.avatar)
        val format: String =
            context.resources.getString(R.string.comment_format_reply_number)
        holder.setText(R.id.nicknameTV, StringUtils.packNull(comment.nickName))
            .setText(R.id.contentTV, StringUtils.packNull(comment.content))
            .setText(
                R.id.dateTV,
                comment.updateTime
            )
    }
}