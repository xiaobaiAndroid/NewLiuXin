package module.comment

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import module.comment.detail.CommentDetailView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupAnimation
import com.lxj.xpopup.interfaces.SimpleCallback
import module.comment.databinding.CommentFramentListBinding
import module.comment.input.InputContentView
import module.common.base.BaseFragment
import module.common.data.DataResult
import module.common.data.entity.Comment
import module.common.data.entity.Dynamic
import module.common.event.MessageEvent
import module.common.utils.ToastUtils

/**
 *@author: baizf
 *@date: 2023/3/21
 */
class CommentListFragment: BaseFragment<CommentFramentListBinding, CommentListViewModel>() {

    val commentAdapter = CommentAdapter(mutableListOf<Comment>())

    var popupView:BasePopupView? = null

    override fun createViewModel(): CommentListViewModel {
        return viewModels<CommentListViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CommentFramentListBinding {
       return CommentFramentListBinding.inflate(layoutInflater,container,false)
    }


    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if (MessageEvent.Type.SEND_COMMENT === event?.type) {
            val content = event.obj as String
            viewModel.comment(content,viewModel.dynamicLD.value)
        }

    }

    override fun initData() {
        val dynamic: Dynamic?
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            dynamic = requireArguments().getParcelable("dynamic",Dynamic::class.java)
        }else{
            dynamic = requireArguments().getParcelable<Dynamic>("dynamic")
        }

        viewModel.dynamicLD.value  = dynamic
        viewModel.getCommentList()
    }

    override fun initView() {
        binding.inputTV.setOnClickListener {
            XPopup.Builder(context)
                .hasShadowBg(false)
                .enableDrag(false)
                .autoOpenSoftInput(true)
                .popupAnimation(PopupAnimation.TranslateFromBottom)
                .asCustom(InputContentView(requireActivity()))
                .show()
        }

        binding.contentRV.layoutManager = LinearLayoutManager(context)
        binding.contentRV.adapter = commentAdapter

        commentAdapter.loadMoreModule.setOnLoadMoreListener{
            viewModel.getCommentList()
        }

        commentAdapter.setOnItemClickListener { adapter, view, position ->
            commentAdapter.getItem(position)?.let {comment->
                popupView = XPopup.Builder(context)
                    .isViewMode(true)
                    .hasShadowBg(false)
                    .enableDrag(false)
                    .moveUpToKeyboard(false)
                    .popupAnimation(PopupAnimation.TranslateFromBottom)
                    .setPopupCallback(object : SimpleCallback() {
                        override fun onBackPressed(popupView: BasePopupView?): Boolean {
                            popupView?.dismiss()
                            return true
                        }
                    })
                    .asCustom( CommentDetailView(requireContext(), comment,viewModel.dynamicLD.value))
                    .show()
            }

        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.commentsResultLD.observe(this){
            if(it.status == DataResult.SUCCESS){
                val format = resources.getString(R.string.comment_format_comment_number)
                binding.titleTV.text  = String.format(format,viewModel.totalCount.toString())

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

    override fun onDestroyView() {
        super.onDestroyView()
    }
}