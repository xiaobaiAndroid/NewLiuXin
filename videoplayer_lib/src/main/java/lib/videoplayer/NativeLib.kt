package lib.videoplayer

class NativeLib {

    /**
     * A native method that is implemented by the 'videoplayer' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'videoplayer' library on application startup.
        init {
            System.loadLibrary("videoplayer")
        }
    }
}