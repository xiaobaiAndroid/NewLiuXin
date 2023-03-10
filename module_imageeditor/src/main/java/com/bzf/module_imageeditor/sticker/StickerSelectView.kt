package com.bzf.module_imageeditor.sticker

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.view.GridSpaceDecoration
import com.lxj.xpopup.core.BottomPopupView
import module.common.event.MessageEvent
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/2/13
 */
class StickerSelectView(context: Context, val imageId: String): BottomPopupView(context) {

    private val mAdapter: StickerAdapter by lazy {
        StickerAdapter(mutableListOf())
    }

    private var mLastPosition = -1

    override fun getImplLayoutId(): Int {
        return R.layout.imageeditor_layout_filter_select
    }

    override fun onCreate() {
        super.onCreate()

        val list = ArrayList<Sticker>()
        for ( i in 1..68){
            list.add(Sticker(i.toString(), imageId,"sticker/sticker$i.png"))
        }

        val filterRV = findViewById<RecyclerView>(R.id.filterRV)
        filterRV.layoutManager = GridLayoutManager(context, 4)
        mAdapter.setList(list)
        val decoration = GridSpaceDecoration(
            mAdapter,
            context.resources.getDimension(R.dimen.dp_16).toInt(),
            4
        )

        filterRV.addItemDecoration(decoration)

        mAdapter.setList(list)
        filterRV.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            if(position != mLastPosition){
                mLastPosition = position
                val messageEvent = MessageEvent(MessageEvent.Type.STICKER_ADD)
                messageEvent.obj =  mAdapter.getItem(position)
                EventBus.getDefault().post(messageEvent)
                this.dismiss()
            }

        }
    }

    override fun getMaxHeight(): Int {
        return resources.getDimension(R.dimen.dp_200).toInt()
    }
}