package module.user.my

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import module.user.R

class MyItemAdapter(list: MutableList<MyItemEntity>): BaseQuickAdapter<MyItemEntity, BaseViewHolder>(
    R.layout.user_item_my,list) {


    override fun convert(holder: BaseViewHolder, item: MyItemEntity) {
        holder.setText(R.id.titleTV,item.title)
                .setImageResource(R.id.iconIV, item.resId)
    }
}