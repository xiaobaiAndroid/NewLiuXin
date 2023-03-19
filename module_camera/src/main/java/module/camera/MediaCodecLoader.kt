package module.camera

import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.view.Surface
import module.common.utils.LogUtils

/**
 *@author: baizf
 *@date: 2023/2/22
 */
class MediaCodecLoader(bitRate: Int, frameRate: Int,width: Int, height: Int, val listener: Listener) {

    private val mMediaCodec: MediaCodec

    var mInputSurface: Surface ?= null

    init {

        val videoFormat = MediaFormat.createVideoFormat(
            MediaFormat.MIMETYPE_VIDEO_AVC,
            width,
            height
        )
        //COLOR_FormatSurface: 本机原始视频格式
        videoFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT,MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
//        videoFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Flexible)
        //设置比特率
        videoFormat.setInteger(MediaFormat.KEY_BIT_RATE,bitRate)
        //设置帧率
        videoFormat.setInteger(MediaFormat.KEY_FRAME_RATE,frameRate)
//        videoFormat.setString(MediaFormat.KEY_FRAME_RATE, null)

        //I帧间隔 一秒一个I帧
        videoFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL,1)

//        val mediaCodecList = MediaCodecList(MediaCodecList.REGULAR_CODECS)
//        val encoderForFormat = mediaCodecList.findEncoderForFormat(videoFormat)
        mMediaCodec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC)// H.264 Advanced Video
        try {
            setCallBack()
            mMediaCodec.configure(videoFormat,null,null,MediaCodec.CONFIGURE_FLAG_ENCODE)
            mInputSurface = mMediaCodec.createInputSurface()
        } catch (e: IllegalArgumentException) {
            LogUtils.printE(e.localizedMessage ?: "MediaCodec configure IllegalArgumentException")
        }catch (e: IllegalStateException) {
            LogUtils.printE("Uninitialized state")
        }catch (e: MediaCodec.CryptoException){
            LogUtils.printE("upon DRM error")
        }catch (e: MediaCodec.CodecException){
            LogUtils.printE("upon codec error")
        }

    }



    private fun setCallBack(){
        //异步模式
        mMediaCodec.setCallback(object: MediaCodec.Callback(){
            override fun onInputBufferAvailable(codec: MediaCodec, index: Int) {
                LogUtils.printI("onInputBufferAvailable")
                listener.disposeInputData(codec,index)
            }

            override fun onOutputBufferAvailable(
                codec: MediaCodec,
                index: Int,
                info: MediaCodec.BufferInfo
            ) {
                listener.disposeOutputData(codec, index)
                val outputBuffer = codec.getOutputBuffer(index)
                //必须释放，不然输出队列满了，画面会卡住
                codec.releaseOutputBuffer(index,false)
            }

            override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
                listener.onError(e.localizedMessage ?: "")
                LogUtils.printE(e.localizedMessage ?: "onError--CodecException")
                try {
                    codec.stop()
                } catch (e: IllegalStateException) {
                    LogUtils.printE(e.localizedMessage ?: "MediaCodec stop error")
                }
                codec.release()
            }

            override fun onOutputFormatChanged(codec: MediaCodec, format: MediaFormat) {

            }
        })
    }

    fun start(){
        try {
            mMediaCodec.start()
        } catch (e: IllegalStateException) {
            LogUtils.printE(e.localizedMessage ?: "   MediaCodec start error")
        }catch (e: MediaCodec.CodecException) {
            LogUtils.printE(e.localizedMessage ?: "   MediaCodec start error")
        }
    }

    fun stop(){
        try {
            mMediaCodec.stop()
        } catch (e: IllegalStateException) {
            LogUtils.printE(e.localizedMessage ?: "   MediaCodec stop error")
        }
    }

    fun release(){
        mMediaCodec.setCallback(null)
        mMediaCodec.release()
    }

    fun reset(){
        try {
            mMediaCodec.reset()
        } catch (e: Exception) {
            LogUtils.printE(e.localizedMessage ?: "   MediaCodec stop error")
        }
    }


    interface Listener{
        fun disposeInputData(codec: MediaCodec, index: Int)

        fun disposeOutputData(codec: MediaCodec, index: Int)

        fun onError(errorMsg: String)
    }

}