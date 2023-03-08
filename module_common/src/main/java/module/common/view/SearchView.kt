package module.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import module.common.R

class SearchView(context: Context, attributeSet: AttributeSet?): FrameLayout(context, attributeSet) {

    constructor(context: Context):this(context,null)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_search,this)
    }
}