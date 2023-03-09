package com.bzf.module_imageeditor.view

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * com.qiaocat.app.widget
 *
 * @author: Mr Bai
 * @date: 2018/1/15
 */
class LinearSpaceDecoration : ItemDecoration {
    private var space: Int

    /**
     * 左边距
     */
    private var mPaddingLeft = 0

    /**
     * 右边距
     */
    private var mPaddingRight = 0

    /**
     * 是否绘制最后一项
     */
    private var mDrawLastItem = false

    /**
     * 绘制头部
     */
    private var drawHeader = false

    /**
     * 绘制尾部
     */
    private var drawFoot = false
    private var adapter: BaseQuickAdapter<*, *>

    /*第一项左边距*/
    private var firstLeftPadding = 0

    /*最后一项右边距*/
    private var lastRightPadding = 0

    /**
     * 方向
     */
    private var orientation: Int

    constructor(context: Context, adapter: BaseQuickAdapter<*, *>) {
        space = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            5f,
            context.resources.displayMetrics
        ).toInt()
        this.adapter = adapter
        orientation = LinearLayoutManager.VERTICAL
    }

    constructor(adapter: BaseQuickAdapter<*, *>, space: Int, orientation: Int) {
        this.space = space
        this.adapter = adapter
        this.orientation = orientation
    }

    constructor(adapter: BaseQuickAdapter<*, *>, space: Int) {
        this.space = space
        this.adapter = adapter
        orientation = LinearLayoutManager.VERTICAL
    }

    /**
     * @param adapter
     * @param space       单位：dp
     * @param paddingLeft  单位：dp
     * @param paddingRight 单位：dp
     * @param orientation  方向
     */
    constructor(
        adapter: BaseQuickAdapter<*, *>,
        space: Int,
        paddingLeft: Int,
        paddingRight: Int,
        orientation: Int
    ) {
        this.space = space
        this.adapter = adapter
        mPaddingLeft = paddingLeft
        mPaddingRight = paddingRight
        this.orientation = orientation
    }

    /**
     * @param adapter
     * @param space       单位：dp
     * @param paddingLeft  单位：dp
     * @param paddingRight 单位：dp
     */
    constructor(adapter: BaseQuickAdapter<*, *>, space: Int, paddingLeft: Int, paddingRight: Int) {
        this.space = space
        this.adapter = adapter
        mPaddingLeft = paddingLeft
        mPaddingRight = paddingRight
        orientation = LinearLayoutManager.VERTICAL
    }

    fun setmDrawLastItem(mDrawLastItem: Boolean) {
        this.mDrawLastItem = mDrawLastItem
    }

    fun setDrawHeader(drawHeader: Boolean) {
        this.drawHeader = drawHeader
    }

    fun setDrawFoot(drawFoot: Boolean) {
        this.drawFoot = drawFoot
    }

    fun setOrientation(orientation: Int) {
        this.orientation = orientation
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (adapter.data == null || adapter.data.size <= 0) {
            return
        }
        val headerCount = adapter.headerLayoutCount
        val footerCount = adapter.footerLayoutCount
        val dataSize = adapter.data.size
        val position = parent.getChildAdapterPosition(view)
        val halfSpace = space / 2
        if (orientation == LinearLayoutManager.VERTICAL) {
            if (isDrawHead(position, adapter, drawHeader)) {
                outRect[mPaddingRight, space, mPaddingRight] = 0
            } else if (isFirstItem(headerCount, position)) {
                outRect[mPaddingRight, space, mPaddingRight] = halfSpace
            } else if (isLastItem(dataSize, headerCount, position)) {
                outRect[mPaddingLeft, halfSpace, mPaddingRight] = space
            } else if (isNormalItem(dataSize, headerCount, position)) {
                outRect[mPaddingLeft, halfSpace, mPaddingRight] = halfSpace
            } else if (isDrawFooter(dataSize, headerCount, footerCount, position)) {
                outRect[mPaddingRight, 0, mPaddingRight] = space
            } else {
                outRect[0, 0, 0] = 0
            }
        } else {
            if (isDrawHead(position, adapter, drawHeader)) {
                outRect[space, space, 0] = space
            } else if (isFirstItem(headerCount, position)) {
                if (firstLeftPadding != 0) {
                    outRect[firstLeftPadding, space, halfSpace] = space
                } else {
                    outRect[space, space, halfSpace] = space
                }
            } else if (isLastItem(dataSize, headerCount, position)) {
                if (lastRightPadding != 0) {
                    outRect[halfSpace, space, lastRightPadding] = space
                } else {
                    outRect[halfSpace, space, space] = space
                }
            } else if (isNormalItem(dataSize, headerCount, position)) {
                outRect[halfSpace, space, halfSpace] = space
            } else if (isDrawFooter(dataSize, headerCount, footerCount, position)) {
                outRect[0, space, space] = space
            } else {
                outRect[0, 0, 0] = 0
            }
        }
    }

    /**
     * 是否绘制尾部
     * @param dataSize
     * @param headerCount
     * @param footerCount
     * @param position
     * @return
     */
    private fun isDrawFooter(
        dataSize: Int,
        headerCount: Int,
        footerCount: Int,
        position: Int
    ): Boolean {
        return position > dataSize - 1 + headerCount && position < dataSize - 1 + headerCount + footerCount
    }

    /**
     * 判断是否是正常的item
     * @param dataSize
     * @param headerCount
     * @param position
     * @return
     */
    private fun isNormalItem(dataSize: Int, headerCount: Int, position: Int): Boolean {
        return position > headerCount && position < dataSize - 1 + headerCount
    }

    /**
     * 判断是否是数据最后一项
     * @param dataSize
     * @param headerCount
     * @param position
     * @return
     */
    private fun isLastItem(dataSize: Int, headerCount: Int, position: Int): Boolean {
        return position == dataSize - 1 + headerCount
    }

    /**
     * 判断是否是数据第一项
     * @param headerCount
     * @param position
     * @return
     */
    private fun isFirstItem(headerCount: Int, position: Int): Boolean {
        return position == headerCount
    }

    /**
     * 是否绘制尾部
     * @param position
     * @param adapter
     * @param drawFooter
     * @return
     */
    private fun isDrawFooter(
        position: Int,
        adapter: BaseQuickAdapter<*, *>,
        drawFooter: Boolean
    ): Boolean {
        val headerLayoutCount = adapter.headerLayoutCount
        val footerLayoutCount = adapter.footerLayoutCount
        return position >= adapter.data.size + headerLayoutCount && position < adapter.data.size + headerLayoutCount + footerLayoutCount && drawFooter
    }

    /**
     * 是否绘制item
     * @param position
     * @param adapter
     * @return
     */
    private fun isDrawItem(position: Int, adapter: BaseQuickAdapter<*, *>): Boolean {
        val headerLayoutCount = adapter.headerLayoutCount
        return position >= headerLayoutCount && position < adapter.data.size + headerLayoutCount
    }

    /**
     * 是否绘制头部
     * @param position
     * @param adapter
     * @param drawHeader
     * @return
     */
    private fun isDrawHead(
        position: Int,
        adapter: BaseQuickAdapter<*, *>,
        drawHeader: Boolean
    ): Boolean {
        val headerCount = adapter.headerLayoutCount
        return drawHeader && position < headerCount
    }

    fun setFirstLeftPadding(firstLeftPadding: Int) {
        this.firstLeftPadding = firstLeftPadding
    }

    fun setLastRightPadding(lastRightPadding: Int) {
        this.lastRightPadding = lastRightPadding
    }
}