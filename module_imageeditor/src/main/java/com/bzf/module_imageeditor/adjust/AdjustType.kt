package com.bzf.module_imageeditor.adjust

/**
 *@author: baizf
 *@date: 2023/2/10
 */
enum class AdjustType(val value: Int) {
    NONE(0),
    BRIGHTNESS(1),//亮度
    CONTRAST(2),//对比度
    SHARPEN(3),//锐化
    SATURATION(4),//饱和度
    EXPOSURE(5) //曝光
}