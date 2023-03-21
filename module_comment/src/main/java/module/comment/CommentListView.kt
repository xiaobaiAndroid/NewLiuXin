package module.comment

/**
 *@author: baizf
 *@date: 2023/3/21
 */
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.lxj.xpopup.core.BottomPopupView
import module.common.data.entity.Dynamic

/**
 * @describe: 评论列表的view
 * @date: 2020/3/10
 * @author: baizhengfu
 */
class CommentListView(context: Context,val dynamic: Dynamic?): BottomPopupView(context) {




    override fun getImplLayoutId(): Int {
        return R.layout.comment_layout_list
    }

    override fun onCreate() {
        super.onCreate()

        val bundle = Bundle()
        bundle.putParcelable("dynamic",dynamic)

        (context as FragmentActivity).supportFragmentManager.apply {
            commit {
                setReorderingAllowed(true)
                replace(R.id.commentListFCV,CommentListFragment::class.java,bundle)

            }
        }
    }


}