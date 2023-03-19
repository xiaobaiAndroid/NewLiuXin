package module.camera

class NativeLib {


    companion object {
        // Used to load the 'camera' library on application startup.
        init {
            System.loadLibrary("camera")
        }
    }
}