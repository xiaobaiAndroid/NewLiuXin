package com.bzf.module_imageeditor.adjust

import android.content.Context
import android.util.Log
import android.widget.RadioGroup
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.entity.MessageEvent
import com.lxj.xpopup.core.BottomPopupView
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams
import org.greenrobot.eventbus.EventBus

/**
 *@author: baizf
 *@date: 2023/2/10
 */
class AdjustSelectView(context: Context, val imageId: String) : BottomPopupView(context) {

    private external fun nGetAdjustValue(id: String,adjustType: Int): Float
    private external fun nSetAdjustValue(id: String,adjustType: Int, value: Float)

    private var adjustType = AdjustType.BRIGHTNESS

    override fun getImplLayoutId(): Int {
        return R.layout.imageeditor_layout_adjust_select
    }

    override fun onCreate() {
        super.onCreate()

        val progressSB = findViewById<IndicatorSeekBar>(R.id.progressSB)

        progressSB.max = AdjustParams.getMax(adjustType).toFloat()
        progressSB.min = AdjustParams.getMin(adjustType).toFloat()
        progressSB.setProgress(nGetAdjustValue(imageId,adjustType.value))


        progressSB.onSeekChangeListener = object : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams?) {
                nSetAdjustValue(imageId,adjustType.value,seekParams!!.progress.toFloat())
                EventBus.getDefault().post(MessageEvent(MessageEvent.Type.REFRESH, imageId))
            }

            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {

            }
        }

        val adjustRG = findViewById<RadioGroup>(R.id.adjustRG)
        adjustRG.setOnCheckedChangeListener { group, checkedId ->
            progressSB.tickCount = 0
            when (checkedId) {
                R.id.brightnessRB -> { //亮度
                    adjustType = AdjustType.BRIGHTNESS
                }
                R.id.contrastRB->{
                    adjustType = AdjustType.CONTRAST
                }
                R.id.sharpnessRB->{
                    adjustType = AdjustType.SHARPEN
                }
                R.id.saturationRB->{
                    adjustType = AdjustType.SATURATION
                }
                R.id.exposureRB->{
                    adjustType = AdjustType.EXPOSURE
                }
                else -> {
                    adjustType = AdjustType.NONE
                }
            }
            progressSB.max = AdjustParams.getMax(adjustType).toFloat()
            progressSB.min = AdjustParams.getMin(adjustType).toFloat()
            val adjustValue = nGetAdjustValue(imageId,adjustType.value)
            Log.i("bzf","adjustValue=$adjustValue")
            progressSB.setProgress(adjustValue)
        }
    }
}