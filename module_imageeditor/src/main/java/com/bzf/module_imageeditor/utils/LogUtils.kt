package com.bzf.module_imageeditor.utils

import android.util.Log

/**
 *@author: baizf
 *@date: 2023/2/15
 */
object LogUtils {

    const val TAG = "LogUtils"

    fun printI(message: String){
        Log.i(TAG,message)
    }
}