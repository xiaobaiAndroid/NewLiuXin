package module.comment.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupAnimation
import com.lxj.xpopup.interfaces.SimpleCallback
import module.comment.R
import module.comment.databinding.CommentLayoutCommentDetailBinding
import module.comment.databinding.CommentLayoutDetailBinding
import module.comment.input.InputContentView
import module.common.base.BaseFragment
import module.common.data.DataResult
import module.common.data.entity.Comment
import module.common.data.entity.Dynamic
import module.common.event.MessageEvent
import module.common.utils.DateUtils
import module.common.utils.ImageLoadHelper
import module.common.utils.StringUtils
import module.common.utils.ToastUtils
import java.lang.Long
import java.util.*
import kotlin.collections.ArrayList

/**
 *@author: baizf
 *@date: 2023/3/21
 */
class CommentDetailFragment : BaseFragment<CommentLayoutDetailBinding, CommentDetailViewModel>() {

    var popupView: BasePopupView? = null

    val commentAdapter = ChildCommentAdapter(ArrayList<Comment>())

    private val mHeaderView: CommentDetailHeaderView by lazy {
        CommentDetailHeaderView(requireContext())
    }

    override fun createViewModel(): CommentDetailViewModel {
        return viewModels<CommentDetailViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CommentLayoutDetailBinding {
        return CommentLayoutDetailBinding.inflate(inflater, container, false)
    }


    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if (MessageEvent.Type.SEND_COMMENT === event?.type) {
            val content = event.obj as String
            val id = viewModel.commentLD.value?.id
            val dynamic = viewModel.dynamicLD.value
            viewModel.comment(content,id,dynamic)
        }
    }

    override fun initData() {
        viewModel.commentLD.value = arguments?.getParcelable<Comment?>("comment")
        viewModel.dynamicLD.value = arguments?.getParcelable<Dynamic?>("dynamic")

        viewModel.commentLD.value?.let {
            mHeaderView.setData(it)

        }
        viewModel.getChildCommentData()

    }

    override fun initView() {
        binding.contentRV.layoutManager = LinearLayoutManager(context)
        binding.contentRV.adapter = commentAdapter



        commentAdapter.setHeaderView(mHeaderView)
        commentAdapter.headerWithEmptyEnable = true
        binding.inputTV.setOnClickListener {
            popupView = XPopup.Builder(context)
                .hasShadowBg(false)
                .enableDrag(false)
                .autoOpenSoftInput(true)
                .popupAnimation(PopupAnimation.TranslateFromBottom)
                .setPopupCallback(object : SimpleCallback() {
                    override fun onBackPressed(popupView: BasePopupView?): Boolean {
                        popupView?.dismiss()
                        return true
                    }

                })
                .asCustom(InputContentView(requireContext()))
                .show()
        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.commentsResultLD.observe(this){
            if(it.status == DataResult.SUCCESS){
                val list = it.t
                if (viewModel.getCurrentPage() == 1) {
                    commentAdapter.setList(list)
                } else {
                    list?.let { commentAdapter.addData(it) }
                }
                list?.let {
                    if (list.size > viewModel.getPageSize()) {
                        commentAdapter.loadMoreModule.loadMoreComplete()
                    } else {
                        commentAdapter.loadMoreModule.loadMoreEnd()
                    }
                } ?: commentAdapter.loadMoreModule.loadMoreEnd()

                viewModel.setNextPage()
                if(commentAdapter.data.isEmpty()){
                    commentAdapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.comment_empty_view,null))
                }
            }else{
                ToastUtils.setMessage(requireContext(),it.message)
            }
        }
    }

}