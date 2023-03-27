package module.dynamic.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.HistorySearch
import module.dynamic.R

class HistoryAdapter(data: MutableList<HistorySearch>?) :
    BaseQuickAdapter<HistorySearch, BaseViewHolder>(R.layout.clique_item_history, data),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: HistorySearch) {
        holder.setText(R.id.contentTV, item.keyWord)
    }
}