package module.dynamic.detail.video

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import cn.jzvd.JzvdStd

class MyVideoPlayer(context: Context, attributeSet: AttributeSet): JzvdStd(context, attributeSet){

    init {
        topContainer.visibility = View.GONE
        posterImageView.scaleType = ImageView.ScaleType.FIT_CENTER
        fullscreenButton.visibility = View.GONE
        currentTimeTextView.visibility = View.GONE
        totalTimeTextView.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

}

