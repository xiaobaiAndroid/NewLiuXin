package com.bzf.module_imageeditor.adjust

/**
 *@author: baizf
 *@date: 2023/2/11
 */
object AdjustParams {

    private val CONTRAST_MAX = 100
    private val CONTRAST_MIN = -100

    private val BRIGHTNESS_MAX = 100
    private val BRIGHTNESS_MIN = -100

    private val SHARPEN_MAX = 100
    private val SHARPEN_MIN = -100

    private val SATURATION_MAX = 100
    private val SATURATION_MIN = -100

    private val EXPOSURE_MAX = 100
    private val EXPOSURE_MIN = -100

    fun getMax(type: AdjustType): Int{
        return when(type){
            AdjustType.BRIGHTNESS->{
                BRIGHTNESS_MAX
            }
            AdjustType.CONTRAST->{
                CONTRAST_MAX
            }
            AdjustType.SHARPEN->{
                SHARPEN_MAX
            }
            AdjustType.SATURATION->{
                SATURATION_MAX
            }
            AdjustType.EXPOSURE->{
                EXPOSURE_MAX
            }
            else->{
                0
            }
        }
    }

    fun getMin(type: AdjustType): Int{
        return when(type){
            AdjustType.BRIGHTNESS->{
                BRIGHTNESS_MIN
            }
            AdjustType.CONTRAST->{
                CONTRAST_MIN
            }
            AdjustType.SHARPEN->{
                SHARPEN_MIN
            }
            AdjustType.SATURATION->{
                SATURATION_MIN
            }
            AdjustType.EXPOSURE->{
                EXPOSURE_MIN
            }
            else->{
                0
            }
        }
    }


}