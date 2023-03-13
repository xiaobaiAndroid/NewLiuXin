package module.audioplayer_lib

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder.AudioSource
import android.os.Build

class AudioPlayerEngine() {

    private val mMediaPlayer: MediaPlayer by lazy {
        val mediaPlayer = MediaPlayer()
        val builder = AudioAttributes.Builder()
        val attributes = builder.setLegacyStreamType(AudioManager.STREAM_MUSIC).build()
        mediaPlayer.setAudioAttributes(attributes)
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
            if(mMediaPlayer.isPlaying){
                stop()
            }
            mMediaPlayer.reset()
            mMediaPlayer.setDataSource(url)
            mMediaPlayer.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun play() {
//        nPlay()
        mMediaPlayer.start()
    }

    fun stop(){
        try {
            mMediaPlayer.stop()
        } catch (e: Exception) {
            e.printStackTrace()
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
}