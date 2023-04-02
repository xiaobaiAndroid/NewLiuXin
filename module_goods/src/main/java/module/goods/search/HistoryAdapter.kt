package module.goods.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.common.data.entity.HistorySearch
import module.common.utils.StringUtils.packNull
import module.goods.R

class HistoryAdapter(data: MutableList<HistorySearch>?) :
    BaseQuickAdapter<HistorySearch, BaseViewHolder>(R.layout.goods_item_history, data),
    LoadMoreModule {
    override fun convert(helper: BaseViewHolder, historySearch: HistorySearch) {
        helper.setText(R.id.contentTV, packNull(historySearch.keyWord))
    }
}