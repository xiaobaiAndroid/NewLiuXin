package com.bzf.module_imageeditor.filter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bzf.module_imageeditor.FilterType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *@author: baizf
 *@date: 2023/2/3
 */
object FilterBitmapLoadHelper {

    fun loadBitmaps(context: Context, filterType: FilterType): Array<Bitmap?> {
        when (filterType) {
            FilterType.FAIRYTALE -> {
                val bitmap = context.assets.open("filter/fairy_tale.png").use {
                    BitmapFactory.decodeStream(it)
                }
                return arrayOf(bitmap)
            }
            FilterType.SUNRISE -> {
                val mask1Bitmap = context.assets.open("filter/amaro_mask1.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                val mask2Bitmap = context.assets.open("filter/amaro_mask2.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                val mask3Bitmap = context.assets.open("filter/toy_mask1.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                return arrayOf(mask1Bitmap, mask2Bitmap, mask3Bitmap)
            }
            FilterType.SUNSET -> {
                val mask1Bitmap = context.assets.open("filter/rise_mask1.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                val mask2Bitmap = context.assets.open("filter/rise_mask2.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                return arrayOf(mask1Bitmap, mask2Bitmap)
            }
            FilterType.WHITE_CAT, FilterType.BLACK_CAT, FilterType.ROMANCE, FilterType.SAKURA, FilterType.ANTIQUE,
            FilterType.NOSTALGIA, FilterType.LATTE, FilterType.COOL, FilterType.EMERALD, FilterType.EVERGREEN,
            FilterType.CRAYON, FilterType.SKETCH -> {
                return arrayOfNulls(0)
            }
            FilterType.HEALTHY -> {
                val maskBitmap = context.assets.open("filter/healthy_mask_1.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                return arrayOf(maskBitmap)
            }
            FilterType.SWEETS -> {
                val maskBitmap = context.assets.open("filter/rise_mask2.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                return arrayOf(maskBitmap)
            }
            FilterType.WARM -> {
                val layerBitmap = context.assets.open("filter/warm_layer1.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                val maskBitmap = context.assets.open("filter/bluevintage_mask1.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                return arrayOf(maskBitmap, layerBitmap)
            }
            FilterType.CALM -> {
                val mask1Bitmap = context.assets.open("filter/calm_mask1.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                val mask2Bitmap = context.assets.open("filter/calm_mask2.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                return arrayOf(mask1Bitmap, mask2Bitmap)
            }
            FilterType.TENDER -> {
                val maskBitmap = context.assets.open("filter/bluevintage_mask1.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
                return arrayOf(maskBitmap)
            }
            else -> {
                return arrayOfNulls(0)
            }
        }
    }
}