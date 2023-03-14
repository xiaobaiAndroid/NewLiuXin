package com.bzf.module_imageeditor.music

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 *@author: baizf
 *@date: 2023/3/13
 */
class MusicPlayAnimationView: View {

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int):super(context,attributeSet,defStyle){

    }
    constructor(context: Context, attributeSet: AttributeSet?):this(context,attributeSet,0)
    constructor(context: Context):this(context,null,0)

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

    }
}