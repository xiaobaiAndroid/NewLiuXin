package module.common.utils

import android.util.Log

object LogUtils {
    /*是否打印日志*/
    var isIsOpen = true
        private set

    private const val TAG = "LogUtils"

    fun printI(tag: String?, msg: String?) {
        if (isIsOpen) {
            Log.i(tag, msg!!)
        }
    }

    fun printE(tag: String?, msg: String?) {
        if (isIsOpen) {
            Log.e(tag, msg!!)
        }
    }

    fun printI(msg: String?) {
        if (isIsOpen) {
            Log.i(TAG, msg!!)
        }
    }

    fun printE(msg: String?) {
        if (isIsOpen) {
            Log.e(TAG, msg!!)
        }
    }

    fun setIsOpen(isOpen: Boolean) {
        isIsOpen = isOpen
    }
}