package module.dynamic.search

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import lib.flowlayout.FlowLayout
import lib.flowlayout.TagAdapter
import module.common.utils.DensityUtil
import module.dynamic.R

/**
 *@author: baizf
 *@date: 2023/3/26
 */
class HistoryTagAdapter(val context: Context,list: MutableList<HotSearchEntity>?): TagAdapter<HotSearchEntity>(list) {

    override fun getView(parent: FlowLayout?, position: Int, entity: HotSearchEntity): View? {
        val tagTV = LayoutInflater.from(context).inflate(R.layout.clique_item_hot_search, null) as TextView
        tagTV.text = entity.title
        val gradientDrawable = GradientDrawable()
        gradientDrawable.cornerRadius = DensityUtil.dip2pxFloat(context,6f)
        gradientDrawable.setColor(entity.color)
        tagTV.background  =gradientDrawable
        return tagTV
    }
}