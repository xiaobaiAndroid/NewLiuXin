package module.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.hardware.camera2.*
import android.hardware.camera2.params.OutputConfiguration
import android.hardware.camera2.params.SessionConfiguration
import android.media.ImageReader
import android.media.MediaCodec
import android.os.Build
import android.util.Range
import android.util.Size
import android.view.Surface
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.suspendCancellableCoroutine
import module.common.utils.LogUtils
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 *@author: baizf
 *@date: 2023/2/20
 */
class CameraLoader(private val activity: Activity) {

    private var mPreviewFrame: ((data: ByteArray, width: Int, height: Int) -> Unit)? = null


    private val mCameraManager: CameraManager by lazy {
        activity.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    }

    //默认选择后置摄像头
    var mCameraFacing = CameraCharacteristics.LENS_FACING_BACK
    var mWindowDegrees: Int = 0
    var mSensorDegrees: Int = 0

    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0

    private var mCameraDeviceInstance: CameraDevice? = null
    private var mPreviewImageReader: ImageReader? = null
    private var mCaptureSession: CameraCaptureSession? = null

    var mMediaCodecLoader: MediaCodecLoader? = null

    private var isInit = false

    private var mCameraId: String? = null

    init {
        mCameraId = getCameraId(mCameraFacing)
    }


    suspend fun onResume(width: Int, height: Int) {
        if (isInit) {
            return
        }
        isInit = true

        mViewWidth = width
        mViewHeight = height
        setupCamera()
    }

    fun onPause() {
        isInit = false
        releaseCamera()
    }

    private fun releaseCamera() {
        mPreviewImageReader?.close()
        mCameraDeviceInstance?.close()
        mCaptureSession?.close()
        mCaptureSession = null
        mCameraDeviceInstance = null
        mCaptureSession = null
    }

    private suspend fun setupCamera() {

        try {
            mCameraId?.let {
                mCameraDeviceInstance = openCamera(mCameraManager, it) ?: return
            }
            mWindowDegrees = getWindowDegrees()
            mSensorDegrees = getSensorDegrees(mCameraFacing)

            val size: Size = chooseOptimalSize()
            LogUtils.printI("chooseOptimalSize=$size")
            mPreviewImageReader = ImageReader.newInstance(
                size.width, size.height, ImageFormat.YUV_420_888, 2
            )//用户希望同时访问的图像的最大数量。这应该尽可能小，以限制内存使用
                .apply {
                    setOnImageAvailableListener({ render ->
                        val image =
                            render?.acquireNextImage() ?: return@setOnImageAvailableListener
                        mPreviewFrame?.invoke(image.generateNV21Data(), image.width, image.height)
                        image.close()
                    }, null)
                }

            mMediaCodecLoader =
                MediaCodecLoader(50000, 30, size.width, size.height,object: MediaCodecLoader.Listener{
                    override fun disposeInputData(codec: MediaCodec, index: Int) {
                        LogUtils.printI("${Thread.currentThread().name} ---${  Thread.currentThread().threadGroup.name}---disposeInputData----index=$index")
                    }

                    override fun disposeOutputData(codec: MediaCodec, index: Int) {
                        LogUtils.printI("disposeOutputData----index=$index")
                    }

                    override fun onError(errorMsg: String) {

                    }
                })

            val list = mutableListOf<Surface>(mPreviewImageReader!!.surface)
            mMediaCodecLoader!!.mInputSurface?.let {
                list.add(it)
            }

            mCaptureSession = createCaptureSession(mCameraDeviceInstance!!, list) ?: return

            val builder =
                mCameraDeviceInstance!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW).apply {
                    addTarget(mPreviewImageReader!!.surface)
                    //设置帧率
                    set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, Range(30, 30))
                    //开启自动曝光
                    set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON)
                    //自动连续对焦
                    set(
                        CaptureRequest.CONTROL_AF_MODE,
                        CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_VIDEO
                    )
                }
            mCaptureSession!!.setRepeatingRequest(builder.build(), null, null)

        } catch (e: CameraAccessException) {
            LogUtils.printE("setupCamera---${e.localizedMessage}")
        } catch (e: IllegalStateException) {
            LogUtils.printE("setupCamera--" + e.localizedMessage)
        }

    }

     fun startRecord() {
        mCameraDeviceInstance ?: return

        val builder =
            mCameraDeviceInstance!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW).apply {
                addTarget(mPreviewImageReader!!.surface)
                mMediaCodecLoader!!.mInputSurface?.let { addTarget(it) }
                val characteristics = mCameraManager.getCameraCharacteristics(mCameraId!!)
                val ranges: Array<Range<Int>>? =
                    characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES)
                ranges?.let {
                    for (range in ranges) {
                        LogUtils.printI("ranges=$range")
                    }
                }

                //设置FPS
                set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, Range(30, 30))
                //开启自动曝光
                set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON)
                //自动连续对焦
                set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_VIDEO)
            }
        mCaptureSession?.setRepeatingRequest(builder.build(), null, null)

        mMediaCodecLoader!!.start()
    }


    fun stopRecord() {
        mMediaCodecLoader!!.stop()
        mCameraDeviceInstance ?: return
        val builder =
            mCameraDeviceInstance!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        builder.addTarget(mPreviewImageReader!!.surface)
        mCaptureSession?.setRepeatingRequest(builder.build(), null, null)
    }


    private suspend fun createCaptureSession(
        device: CameraDevice,
        surfaces: List<Surface>
    ): CameraCaptureSession? = suspendCoroutine { cnt ->
        val stateCallback = object : CameraCaptureSession.StateCallback() {
            override fun onConfigured(session: CameraCaptureSession) {
                cnt.resume(session)
            }

            override fun onConfigureFailed(session: CameraCaptureSession) {
                LogUtils.printE("createCaptureSession----onConfigureFailed")
                cnt.resume(null)
            }
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val list = mutableListOf<OutputConfiguration>()
                for (surface in surfaces) {
                    val outputConfiguration = OutputConfiguration(surface)
                    list.add(outputConfiguration)
                }
                val configuration = SessionConfiguration(
                    SessionConfiguration.SESSION_REGULAR,
                    list,
                    { command -> command?.run() },
                    stateCallback
                )
                device.createCaptureSession(configuration)
            } else {
                val list = mutableListOf<Surface>()
                for (surface in surfaces) {
                    list.add(surface)
                }
                device.createCaptureSession(list, stateCallback, null)
            }
        } catch (e: CameraAccessException) {
            LogUtils.printE("access camera fail. message=" + e.message)
            cnt.resume(null)
        }
    }

    private fun getCameraId(cameraFacing: Int): String? {
        return mCameraManager.cameraIdList.find { id ->
            mCameraManager.getCameraCharacteristics(id)
                .get(CameraCharacteristics.LENS_FACING) == cameraFacing
        }
    }


    private suspend fun openCamera(cameraManager: CameraManager, cameraId: String): CameraDevice? =
        suspendCancellableCoroutine { cnt ->
            if (ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                cameraManager.openCamera(cameraId, object : CameraDevice.StateCallback() {
                    override fun onOpened(camera: CameraDevice) {
                        cnt.resume(camera)
                    }

                    override fun onDisconnected(camera: CameraDevice) {
                        camera.close();
                        mCameraDeviceInstance = null
                        cnt.resume(null)
                        LogUtils.printE("openCamera--onDisconnected")
                    }

                    override fun onError(camera: CameraDevice, error: Int) {
                        camera.close();
                        mCameraDeviceInstance = null
                        val msg = when (error) {
                            ERROR_CAMERA_DEVICE -> "Fatal (device)"
                            ERROR_CAMERA_DISABLED -> "Device policy"
                            ERROR_CAMERA_IN_USE -> "Camera in use"
                            ERROR_CAMERA_SERVICE -> "Fatal (service)"
                            ERROR_MAX_CAMERAS_IN_USE -> "Maximum cameras in use"
                            else -> "Unknown"
                        }
                        LogUtils.printE("openCamera--message=$msg")
                        cnt.resume(null)
                    }

                }, null)
            } else {
                cnt.resume(null)
            }
        }

    fun setPreviewFrameListener(onPreviewFrame: (data: ByteArray, width: Int, height: Int) -> Unit) {
        mPreviewFrame = onPreviewFrame
    }

    /*
    * @describe: 获取最佳的预览尺寸
    * @date: 2023/2/20
    */
    private fun chooseOptimalSize(): Size {
        if (mViewWidth == 0 || mViewHeight == 0) {
            return Size(0, 0)
        }
        val cameraId = getCameraId(mCameraFacing) ?: return Size(0, 0)
        val configurationMap = mCameraManager.getCameraCharacteristics(cameraId)
            .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
        val outputSizes = configurationMap?.getOutputSizes(ImageFormat.YUV_420_888)

        val orientation = getCameraOrientation()
        val maxPreviewWidth = if (orientation == 90 or 270) mViewHeight else mViewWidth
        val maxPreviewHeight = if (orientation == 90 or 270) mViewWidth else mViewHeight

        return outputSizes?.filter {
            it.width >= maxPreviewHeight && it.height >= maxPreviewWidth
        }?.minByOrNull {
            it.width * it.height
        } ?: Size(PREVIEW_WIDTH, PREVIEW_HEIGHT)
    }

    fun getCameraOrientation(): Int {
        val degrees = getWindowDegrees()

        val orientation: Int = getSensorDegrees(mCameraFacing)

        LogUtils.printI("android.sensor.orientation=$orientation")
        return if (mCameraFacing == CameraCharacteristics.LENS_FACING_FRONT) {
//            degrees + orientation % 360
            (orientation + degrees) % 360
        } else {
//            degrees + (orientation + 360) % 360
            (orientation - degrees) % 360
        }
    }

    /*
    * @describe: 获取传感器的角度
    * @date: 2023/2/21
    */
    private fun getSensorDegrees(cameraFacing: Int): Int {
        return getCameraId(cameraFacing)?.let {
            val characteristics = mCameraManager.getCameraCharacteristics(it)
            characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)
        } ?: 90
    }

    /*
    * @describe: 获取窗口的角度
    * @date: 2023/2/21
    */
    private fun getWindowDegrees(): Int {
        val windowOrientation: Int =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { //Android 11
                activity.display?.rotation ?: 0
            } else {
                activity.windowManager.defaultDisplay.orientation
            }
        val degrees = when (windowOrientation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> 0
        }
        return degrees
    }

    suspend fun switchLens() {
        mCameraFacing = if (mCameraFacing == CameraCharacteristics.LENS_FACING_FRONT) {
            CameraCharacteristics.LENS_FACING_BACK
        } else {
            CameraCharacteristics.LENS_FACING_FRONT
        }
        releaseCamera()
        mCameraId = getCameraId(mCameraFacing)
        setupCamera()
    }


    companion object {
        private const val TAG = "CameraLoader"

        private const val PREVIEW_WIDTH = 1280
        private const val PREVIEW_HEIGHT = 720
    }

}