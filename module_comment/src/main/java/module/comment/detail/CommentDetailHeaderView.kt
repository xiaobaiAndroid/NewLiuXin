package module.comment.detail

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import module.comment.databinding.CommentHeaderDetailBinding
import module.common.data.entity.Comment
import module.common.utils.ImageLoadHelper

/**
 *@author: baizf
 *@date: 2023/3/21
 */
class CommentDetailHeaderView(context: Context): FrameLayout(context) {

    val binding:CommentHeaderDetailBinding

    init {
        binding = CommentHeaderDetailBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun setData(comment: Comment){
        ImageLoadHelper.loadCircle(binding.childComment.avatarIV, comment.avatar)
        binding.childComment.nicknameTV.text = comment.nickName ?: ""
        binding.childComment.contentTV.text = comment.content ?: ""
        binding.childComment.dateTV.text = comment.updateTime ?: ""
    }
}