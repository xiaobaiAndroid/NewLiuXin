package lib.flowlayout

import android.util.Log
import android.view.View
import java.util.*

abstract class TagAdapter<T>(val mTagDatas: MutableList<T>?) {
    private var mOnDataChangedListener: OnDataChangedListener? = null

    @get:Deprecated("")
    @Deprecated("")
    val preCheckedList = HashSet<Int>()

    interface OnDataChangedListener {
        fun onChanged()
    }

    fun setOnDataChangedListener(listener: OnDataChangedListener?) {
        mOnDataChangedListener = listener
    }

    @Deprecated("")
    fun setSelectedList(vararg poses: Int) {
        val set: MutableSet<Int> = HashSet()
        for (pos in poses) {
            set.add(pos)
        }
        setSelectedList(set)
    }

    @Deprecated("")
    fun setSelectedList(set: Set<Int>?) {
        preCheckedList.clear()
        if (set != null) {
            preCheckedList.addAll(set)
        }
        notifyDataChanged()
    }

    val count: Int
        get() = mTagDatas?.size ?: 0

    fun notifyDataChanged() {
        if (mOnDataChangedListener != null) mOnDataChangedListener!!.onChanged()
    }

    fun getItem(position: Int): T {
        return mTagDatas!![position]
    }

    abstract fun getView(parent: FlowLayout?, position: Int, entity: T): View?


    open fun onSelected(position: Int, view: View?) {
        Log.d("zhy", "onSelected $position")
    }

    open fun unSelected(position: Int, view: View?) {
        Log.d("zhy", "unSelected $position")
    }

    fun setSelected(position: Int, t: T): Boolean {
        return false
    }

}