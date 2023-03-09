package com.bzf.module_imageeditor

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bzf.module_imageeditor.databinding.FragmentPictureEditBinding
import com.bzf.module_imageeditor.entity.ConcatBitmap
import com.bzf.module_imageeditor.entity.MessageEvent
import com.bzf.module_imageeditor.filter.FilterEntity
import com.bzf.module_imageeditor.sticker.Sticker
import com.bzf.module_imageeditor.sticker.StickerProxy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.io.FileOutputStream

/**
 *@author: baizf
 *@date: 2023/2/1
 */
class PictureEditFragment: Fragment() {

    private lateinit var binding: FragmentPictureEditBinding

    private  var entity: PictureEntity?=null

    private val mStickerProxy: StickerProxy by lazy {
        StickerProxy(binding.stickersImageView)
    }

    private val mSurfaceView: PictureSurfaceView by lazy {
        PictureSurfaceView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPictureEditBinding.inflate(inflater, container,false)
        mSurfaceView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        binding.contentFL.addView(mSurfaceView)

        binding.stickersImageView.clipToOutline = true

        binding.root
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        entity = arguments?.getParcelable<PictureEntity>("entity")
        entity?.let {
            mSurfaceView.mGPUImageView.originalPath = it.sourcePath
            mSurfaceView.mGPUImageView.imageId = it.id
        }

        mSurfaceView.mGPUImageView.mlistener = object: GPUImageView.Listener{
            override fun onGetBitmapSuccess(imagePath: String) {
                lifecycleScope.launch(Dispatchers.IO){
                    val bitmap = BitmapFactory.decodeFile(imagePath)
                    val drawBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                    val canvas = Canvas(drawBitmap)
                    val stickers = binding.stickersImageView.mStickers
                    for (sticker in stickers){
                        sticker.draw(canvas)
                    }

                    val file = File(imagePath)
                    if(file.exists()){
                        file.delete()
                    }
                    val outputStream = FileOutputStream(file)
                    drawBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream)

                    val concatBitmap = ConcatBitmap(mSurfaceView.mGPUImageView.imageId, imagePath)
                    EventBus.getDefault().post(MessageEvent(MessageEvent.Type.CONCAT_BITMAP_RESULT,concatBitmap))
                }
            }

            override fun onGetBitmapFail() {

            }

            override fun onRenderRect(rect: IntArray) {
                lifecycleScope.launch(Dispatchers.Main){
                    val marginLeft = rect[0]
                    val marginBottom = rect[1]
                    val width = rect[2]
                    val height = rect[3]
                    val layoutParams =  binding.stickersImageView.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.marginStart = marginLeft
                    layoutParams.marginEnd = marginLeft
                    layoutParams.topMargin = marginBottom
                    layoutParams.bottomMargin = marginBottom
                    layoutParams.width = width
                    layoutParams.height = height
                    binding.stickersImageView.layoutParams = layoutParams
                }
            }

        }

    }

//    override fun onPause() {
//        mSurfaceView.onPause()
//        super.onPause()
//    }
//
//    override fun onResume() {
//        mSurfaceView.onResume()
//        super.onResume()
//    }

    override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent){
        if(event.type == MessageEvent.Type.RELEASE){
            Log.i("imageeditor","release opengl")
            mSurfaceView.mGPUImageView.destroy()
        }else if(event.type == MessageEvent.Type.FILTER){
            val filterEntity = event.data as FilterEntity
            if(mSurfaceView.mGPUImageView.imageId == filterEntity.imageId){
                mSurfaceView.mGPUImageView.setupFilter(filterEntity.filterType)
            }
        }else if(event.type == MessageEvent.Type.REFRESH){
            val imageId = event.data as String
            if(mSurfaceView.mGPUImageView.imageId == imageId){
                mSurfaceView.requestRender()
            }
        }else if(event.type == MessageEvent.Type.SCREEN_ORIENTATION){
            val screenDegree = event.data as Int
            mSurfaceView.mGPUImageView.mScreenDegree = screenDegree
        }else if(event.type == MessageEvent.Type.STICKER_ADD){
            val sticker = event.data as Sticker
            if(mSurfaceView.mGPUImageView.imageId == sticker.imageId){
                mStickerProxy.addSticker(sticker)
            }
        }else if(event.type == MessageEvent.Type.SAVE){
            concatBitmap()
        }
    }

    private fun concatBitmap() {
        binding.stickersImageView.hideSelectedFrame()
        binding.stickersImageView.invalidate()
        mSurfaceView.mGPUImageView.getBitmap()
    }


    inner class PictureSurfaceView: GLSurfaceView(context){

        val mGPUImageView: GPUImageView =  GPUImageView(requireContext(), lifecycleScope, this)
        init {
            setEGLContextClientVersion(3)

            setRenderer(mGPUImageView)
            renderMode = RENDERMODE_WHEN_DIRTY
        }
    }

}