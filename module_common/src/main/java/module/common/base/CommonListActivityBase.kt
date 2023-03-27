package module.common.base

import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import module.common.R
import module.common.utils.DensityUtil

/**
 *@author: baizf
 *@date: 2023/3/26
 */
abstract class CommonListActivityBase<T : ViewBinding, V: BaseViewModel>: BaseActivity<T,V>() {

    private var loadingIV: ImageView?=null
    private var loadFailView: View?=null

    fun getLoadingView(): View {
        val loadingView  =
            LayoutInflater.from(this).inflate(R.layout.layout_loading_view, null)

        loadingIV = loadingView.findViewById<ImageView>(R.id.loadingIV)

        loadingView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View, left: Int, top: Int, right: Int, bottom: Int,
                oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int
            ) {
                loadingView.removeOnLayoutChangeListener(this)

                val centerX: Float = loadingIV!!.width/2.0f
                val centerY: Float = loadingIV!!.height/2.0f
               val rotateAnimation =
                    RotateAnimation(0f, 360f, centerX, centerY).apply {
                        repeatMode = Animation.RESTART
                        repeatCount = Animation.INFINITE
                        duration = 2000
                        fillAfter = true
                        interpolator = LinearInterpolator()
                    }
                loadingIV?.startAnimation(rotateAnimation)
            }
        })
        return  loadingView
    }

    fun getEmptyView(): View{
        return LayoutInflater.from(this).inflate(module.common.R.layout.layout_empty_view, null)
    }

    fun getFailView(): View{
        return LayoutInflater.from(this).inflate(module.common.R.layout.layout_fail_view, null)
    }

    fun cancelLoadingAnimation(){
        loadingIV?.clearAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingIV?.clearAnimation()
    }

}