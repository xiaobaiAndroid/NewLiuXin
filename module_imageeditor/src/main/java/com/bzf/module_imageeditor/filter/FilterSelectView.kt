package com.bzf.module_imageeditor.filter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bzf.module_imageeditor.FilterType
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.entity.MessageEvent
import com.bzf.module_imageeditor.view.LinearSpaceDecoration
import com.lxj.xpopup.core.BottomPopupView
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/2/2
 */
class FilterSelectView(context: Context, val imageId: String): BottomPopupView(context) {

    private val mAdapter: FilterAdapter by lazy {
        FilterAdapter()
    }

    private var mLastPosition = 0

    override fun getImplLayoutId(): Int {
        return R.layout.imageeditor_layout_filter_select
    }

    override fun onCreate() {
        super.onCreate()


        val filterNames = resources.getStringArray(R.array.filter)
        val list = ArrayList<FilterEntity>()
        list.add(FilterEntity(FilterEntity.Type.SELECTED.ordinal,
            FilterType.ORIGINAL,R.drawable.filter_thumb_original, filterNames[0]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.FAIRYTALE,R.drawable.filter_thumb_fairytale, filterNames[1]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.SUNRISE,R.drawable.filter_thumb_sunrise, filterNames[2]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.SUNSET,R.drawable.filter_thumb_sunset, filterNames[3]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.WHITE_CAT,R.drawable.filter_thumb_whitecat, filterNames[4]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.BLACK_CAT,R.drawable.filter_thumb_blackcat, filterNames[5]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.SKIN_WHITE,R.drawable.filter_thumb_beauty, filterNames[6]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.HEALTHY,R.drawable.filter_thumb_healthy, filterNames[7]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.SWEETS,R.drawable.filter_thumb_sweets, filterNames[8]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.ROMANCE,R.drawable.filter_thumb_romance, filterNames[9]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.SAKURA,R.drawable.filter_thumb_sakura, filterNames[10]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.WARM,R.drawable.filter_thumb_warm, filterNames[11]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.ANTIQUE,R.drawable.filter_thumb_antique, filterNames[12]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.NOSTALGIA,R.drawable.filter_thumb_nostalgia, filterNames[13]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.CALM,R.drawable.filter_thumb_calm, filterNames[14]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.LATTE,R.drawable.filter_thumb_calm, filterNames[15]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.TENDER,R.drawable.filter_thumb_tender, filterNames[16]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.COOL,R.drawable.filter_thumb_cool, filterNames[17]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.EMERALD,R.drawable.filter_thumb_emerald, filterNames[18]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.EVERGREEN,R.drawable.filter_thumb_emerald, filterNames[19]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.CRAYON,R.drawable.filter_thumb_crayon, filterNames[20]))
        list.add(FilterEntity(FilterEntity.Type.UNSELECTED.ordinal,
            FilterType.SKETCH,R.drawable.filter_thumb_sketch, filterNames[21]))


        val filterRV = findViewById<RecyclerView>(R.id.filterRV)
        filterRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        val decoration = LinearSpaceDecoration(
            mAdapter,
            context.resources.getDimension(R.dimen.dp_16).toInt(),
            LinearLayoutManager.HORIZONTAL
        )
        decoration.setFirstLeftPadding(0)

        filterRV.addItemDecoration(decoration)

        mAdapter.setList(list)
        filterRV.adapter = mAdapter

        mAdapter.setOnItemClickListener { _, _, position ->
            if(position != mLastPosition){
                val item = mAdapter.getItem(position)
                item.itemType = FilterEntity.Type.SELECTED.ordinal
                mAdapter.notifyItemChanged(position)

                val lastItem = mAdapter.getItem(mLastPosition)
                lastItem.itemType = FilterEntity.Type.UNSELECTED.ordinal
                mAdapter.notifyItemChanged(mLastPosition)

                mLastPosition = position
                item.imageId = imageId
                EventBus.getDefault().post(MessageEvent(MessageEvent.Type.FILTER, item))
            }

        }
    }
}