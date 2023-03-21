package module.comment.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.lxj.xpopup.core.BottomPopupView
import module.comment.CommentListFragment
import module.comment.R
import module.comment.databinding.CommentLayoutListBinding
import module.common.data.entity.Comment
import module.common.data.entity.Dynamic

/**
 * @describe:评论详情View
 * @date: 2020/3/10
 * @author: baizhengfu
 */
class CommentDetailView(context: Context, val comment: Comment, val dynamic: Dynamic?):BottomPopupView(context){

    override fun getImplLayoutId(): Int {
        return R.layout.comment_layout_comment_detail
    }

    override fun onCreate() {
        super.onCreate()

        val bundle = Bundle()
        bundle.putParcelable("dynamic",dynamic)
        bundle.putParcelable("comment",comment)

        (context as FragmentActivity).supportFragmentManager.apply {
            commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView,CommentDetailFragment::class.java,bundle)

            }
        }
    }


}