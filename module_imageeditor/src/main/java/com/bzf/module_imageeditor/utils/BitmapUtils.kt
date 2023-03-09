package com.bzf.module_imageeditor.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore

/**
 *@author: baizf
 *@date: 2023/2/12
 */
object BitmapUtils {

    /*
    * @describe: 获取图片的方向
    * @date: 2023/2/12
    */
    fun getBitmapOrientation(context: Context, path: String): Float{
        var exifInterface: ExifInterface? = null

        if (path.startsWith("content://")) {
            val uri = Uri.parse(path)
            context.contentResolver.openFileDescriptor(uri, "r")?.use {
                exifInterface = ExifInterface(it.fileDescriptor)

            }
        }else{
            exifInterface = ExifInterface(path)
        }

        val orientation = exifInterface?.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )

        var degree = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                90.0f
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                180.0f
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> {
                270.0f
            }
            else -> {
                0.0f
            }
        }
        return degree
    }
}