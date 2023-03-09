package com.bzf.module_imageeditor.entity

/**
 *@author: baizf
 *@date: 2023/2/2
 */
class MessageEvent(val type: Type, val data: Any?=null) {

    enum class Type{
        RELEASE,
        //滤镜
        FILTER,
        //刷新
        REFRESH,
        REFRESH_ALL,
        //屏幕方向
        SCREEN_ORIENTATION,
        //添加贴图
        STICKER_ADD,
        SAVE,
        //融合处理的图片的结果
        CONCAT_BITMAP_RESULT
    }
}