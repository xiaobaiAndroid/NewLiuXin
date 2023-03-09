package com.bzf.module_imageeditor.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *@author: baizf
 *@date: 2023/2/19
 */
@Parcelize
data class ConcatBitmap(val id: String, val path: String, var position: Int = 0) : Parcelable
