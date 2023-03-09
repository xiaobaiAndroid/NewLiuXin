package com.bzf.module_imageeditor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


/**
 *@author: baizf
 *@date: 2023/2/1
 */
@Parcelize
class PictureEntity(val id: String,val sourcePath: String, val savePath: String, var position: Int): Parcelable {


}