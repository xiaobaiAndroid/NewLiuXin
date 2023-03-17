package com.bzf.module_imageeditor.label.select

import java.util.UUID

/**
 *@author: baizf
 *@date: 2023/3/15
 */
data class LabelEntity(val name: String? = null,val type: LabelType){
    val id: String = UUID.randomUUID().toString()
    var position: Int = 0
}


enum class LabelType{
    USER,
    ADDRESS,
    CUSTOM
}
