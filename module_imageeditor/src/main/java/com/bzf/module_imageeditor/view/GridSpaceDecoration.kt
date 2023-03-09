package com.bzf.module_imageeditor.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * GridLayout间距
 * @author: Mr Bai
 * @date: 2018/1/11
 */
class GridSpaceDecoration(
    private val adapter: BaseQuickAdapter<*, *>,
    private val space: Int,
    val spanCount: Int
) :
    ItemDecoration() {

    /**
     * 绘制头部
     */
    private var drawHeader = true

    /**
     * 绘制尾部
     */
    private var drawFooter = true

    /**
     * 最左边的item是否只绘制一半的space
     */
    private var leftDrawHalf = true

    /**
     * 最右边的item是否只绘制一般的space
     */
    private var rightDrawHalf = true
    private var margin = 0


    /*最左和最后的距离*/
    private val leftmost = 0
    private val rightmost = 0
    private val halfSpace: Int

    init {
        halfSpace = space / 2
    }

    fun setDrawHeader(drawHeader: Boolean) {
        this.drawHeader = drawHeader
    }

    fun setDrawFooter(drawFooter: Boolean) {
        this.drawFooter = drawFooter
    }

    fun setLeftDrawHalf(leftDrawHalf: Boolean) {
        this.leftDrawHalf = leftDrawHalf
    }

    fun setRightDrawHalf(rightDrawHalf: Boolean) {
        this.rightDrawHalf = rightDrawHalf
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
        val position = parent.getChildAdapterPosition(view)
        if (position + 1 > adapter.headerLayoutCount) {
            if (isLeftmost(position)) {
                if (margin == 0) {
                    outRect[halfSpace, halfSpace, halfSpace] = halfSpace
                } else {
                    outRect[margin, halfSpace, halfSpace] = halfSpace
                }
            } else if (isRightmost(position)) {
                if (margin == 0) {
                    outRect[halfSpace, halfSpace, halfSpace] = halfSpace
                } else {
                    outRect[halfSpace, halfSpace, margin] = halfSpace
                }
            } else {
                outRect[halfSpace, halfSpace, halfSpace] = halfSpace
            }
        }
    }

    /**
     * @describe: 判断是否最左边
     * @date: 2019/8/19
     */
    private fun isLeftmost(position: Int): Boolean {
        var position = position
        val headerLayoutCount = adapter.headerLayoutCount
        position = position - headerLayoutCount
        return if (spanCount == 2) {
            position % 2 == 0
        } else false
    }

    /**
     * @describe: 判断是否最右边
     * @date: 2019/8/19
     * @author: Mr Bai
     */
    private fun isRightmost(position: Int): Boolean {
        var position = position
        val headerLayoutCount = adapter.headerLayoutCount
        position = position - headerLayoutCount
        return if (spanCount == 2) {
            position % 2 == 1
        } else false
    }

    fun setMargin(margin: Int) {
        this.margin = margin
    }
}