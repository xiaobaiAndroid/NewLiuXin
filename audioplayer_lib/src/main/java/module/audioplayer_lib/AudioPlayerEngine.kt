package module.audioplayer_lib

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.MEDIA_ERROR_SERVER_DIED
import android.media.MediaPlayer.MEDIA_ERROR_UNKNOWN
import android.media.MediaRecorder.AudioSource
import android.os.Build
import android.util.Log

class AudioPlayerEngine(val listener: Listener) {

    private val mMediaPlayer: MediaPlayer by lazy {
        val mediaPlayer = MediaPlayer()
        val builder = AudioAttributes.Builder()
        val attributes = builder.setLegacyStreamType(AudioManager.STREAM_MUSIC)
            .build()
        mediaPlayer.setAudioAttributes(attributes)
        mediaPlayer.setVolume(0.5f, 0.5f)
        mediaPlayer.setOnErrorListener { mp, what, extra ->
            if (what == MEDIA_ERROR_UNKNOWN || what == MEDIA_ERROR_SERVER_DIED) {
                listener.onError()
            }
            true
        }
        mediaPlayer.setOnBufferingUpdateListener { mp, percent ->
            Log.i("bzf", "percent=$percent")
            if (percent == 0 || percent == 100) {
                listener.onPrepared()
            }
        }
        mediaPlayer.setOnCompletionListener {
            listener.onCompletion()
        }
        mediaPlayer
    }

    /**
     * A native method that is implemented by the 'audioplayer_lib' native library,
     * which is packaged with this application.
     */
    private external fun nPrepare(audioOutputDeviceId: Int, url: String)
    private external fun nPlay()
    private external fun nPause()
    private external fun nResume()
    private external fun nStop()
    private external fun nRelease()

    companion object {
        // Used to load the 'audioplayer_lib' library on application startup.
        init {
            System.loadLibrary("audioplayer_lib")
        }
    }

    fun prepare(url: String) {
        try {
            if (mMediaPlayer.isPlaying) {
                stop()
            }
            mMediaPlayer.reset()
            mMediaPlayer.setDataSource(url)
            mMediaPlayer.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
            listener.onError()
        }

    }

    fun play() {
//        nPlay()
        if (mMediaPlayer.isPlaying) {
            mMediaPlayer.stop()
        }
        mMediaPlayer.start()
    }

    fun stop() {
        try {
            if (mMediaPlayer.isPlaying) {
                mMediaPlayer.stop()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun pause() {
        if (mMediaPlayer.isPlaying) {
            mMediaPlayer.pause()
        }
    }

    fun release() {
        mMediaPlayer.release()
//        nRelease()
    }

    private fun getDeviceId(): Int {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//            //要使用蓝牙SCO，setCommunicationDevice()或startBluetoothSco() 必须被调用。
//            // audiommanager.startbluetoothsco()在Android T中已被弃用。
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
//
//            }else{
//
//            }
//        }else{
//            return 0
//        }
        return 0
    }

    interface Listener {
        fun onError()
        fun onPrepared()
        fun onCompletion()
    }
}