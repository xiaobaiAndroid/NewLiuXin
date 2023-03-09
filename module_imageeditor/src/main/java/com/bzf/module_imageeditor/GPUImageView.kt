package com.bzf.module_imageeditor

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.opengl.GLSurfaceView
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.bzf.module_imageeditor.filter.FilterBitmapLoadHelper
import com.bzf.module_imageeditor.utils.BitmapUtils
import com.bzf.module_imageeditor.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 *@author: baizf
 *@date: 2023/2/1
 */
class GPUImageView(
    val context: Context,
    val lifecycleScope: LifecycleCoroutineScope,
    val mSurfaceView: GLSurfaceView
) : GLSurfaceView.Renderer {

    private external fun nInit(id: String, assets: AssetManager, filterType: Int)
    private external fun nSetFilter(id: String,assets: AssetManager, filterType: Int)
    private external fun nResize(id: String,width: Int, height: Int)
    private external fun nDraw(id: String)
    private external fun nReleaseGL(id: String)
    private external fun setFilterImage(id: String,bitmaps: Array<Bitmap?>, filterType: Int)
    private external fun setOriginalImage(id: String,bitmap: Bitmap)
    private external fun nSaveBitmap(id: String,assets: AssetManager,savePath: String): Boolean
    private external fun nGetRenderRect(id: String): IntArray


    private var mFilterType = FilterType.ORIGINAL

    var originalPath: String = ""

    var imageId: String = ""

    //屏幕选择的角度
    var mScreenDegree: Int = 0

    private val mImageSavePath: String by lazy{
        (context.externalCacheDir?.absolutePath ?:context. cacheDir.absolutePath) + "/" + imageId +".jpg"
    }


    var mlistener: Listener?=null

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        nInit(imageId,context.assets, mFilterType.value)
        LogUtils.printI("Thread---"+Thread.currentThread().name)

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        nResize(imageId, width, height)
        loadImage()
    }


    override fun onDrawFrame(gl: GL10?) {
        nDraw(imageId)
    }

    fun destroy() {
        mSurfaceView.queueEvent {
            nReleaseGL(imageId)
        }

    }

    fun setupFilter(filterType: FilterType) {
        mFilterType = filterType
        mSurfaceView.queueEvent {
            nSetFilter(imageId,context.assets, mFilterType.value)
        }
        loadImage()
    }


    private fun loadImage() {
        lifecycleScope.launch(Dispatchers.IO) {
            var originBitmap: Bitmap? = null
            var degree: Float = BitmapUtils.getBitmapOrientation(context, originalPath)

            if (originalPath.startsWith("content://")) {
                val uri = Uri.parse(originalPath)
                context.contentResolver.openFileDescriptor(uri, "r")?.use {
                    originBitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
                }

            } else {
                originBitmap = BitmapFactory.decodeFile(originalPath)
            }

            Log.i("bzf", "bitmap degree=$degree")
            Log.i("bzf", "mScreenDegree degree=$mScreenDegree")

            val configOrientation = context.resources.configuration.orientation
            if (configOrientation != Configuration.ORIENTATION_PORTRAIT && configOrientation != Configuration.ORIENTATION_LANDSCAPE) { //跟随屏幕
                degree += mScreenDegree
            }

            Log.i("bzf", "need rotate degree=$degree")
            if (degree != 0.0f) {
                val matrix = Matrix()
                matrix.postRotate(degree)
                originBitmap?.let { bitmap ->
                    originBitmap =
                        Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
                }

            }

            withContext(Dispatchers.Main) {
                mSurfaceView.queueEvent {
                    originBitmap?.let {
                        setOriginalImage(imageId,it)
                        val renderRect = nGetRenderRect(imageId)
                        mlistener?.onRenderRect(renderRect)
                    }

                }
            }

            val bitmaps = FilterBitmapLoadHelper.loadBitmaps(context, mFilterType)
            withContext(Dispatchers.Main) {
                mSurfaceView.queueEvent {
                    setFilterImage(imageId,bitmaps, mFilterType.value)
                }
                mSurfaceView.requestRender()
            }
        }

    }

    fun getBitmap() {
        mSurfaceView.queueEvent {
            nDraw(imageId)
            val isSuccess: Boolean = nSaveBitmap(imageId,context.assets,mImageSavePath)
            if(isSuccess){
                mlistener?.onGetBitmapSuccess(mImageSavePath)
            }else{
                mlistener?.onGetBitmapFail()
            }
        }
    }

    interface Listener{
        fun  onGetBitmapSuccess(imagePath: String)
        fun onGetBitmapFail()

        fun onRenderRect(rect: IntArray)
    }

}