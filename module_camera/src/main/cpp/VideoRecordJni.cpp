/**
 *@author: baizf
 *@date: 2023/3/5
*/
//

#include "VideoRecordController.h"
#include "YUVCodecUtils.h"
#include "Logutils.h"
#include <android/native_window.h>
#include <android/native_window_jni.h>
#include <jni.h>
#include <android/bitmap.h>

static VideoRecordController *mController = nullptr;
static JavaVM *mJavaVM = nullptr;

extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1camera_CameraSurfaceView_nSurfaceCreated(JNIEnv *env, jobject thiz,
                                                              jobject surface) {

    if (mController) {
        mController->release();
        delete mController;
    }
    mController = new VideoRecordController();

    env->GetJavaVM(&mJavaVM);

    ANativeWindow *pWindow = ANativeWindow_fromSurface(env, surface);
    mController->prepare(mJavaVM, pWindow);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1camera_CameraSurfaceView_nSurfaceChanged(JNIEnv *env, jobject thiz, jint width,
                                                              jint height) {
    if (mController) {
        mController->surfaceChanged(width, height);
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1camera_CameraSurfaceView_nSurfaceDestroyed(JNIEnv *env, jobject thiz) {
    if (mController) {
        mController->release();
        delete mController;
        mController = nullptr;
        mJavaVM->DestroyJavaVM();
        mJavaVM = nullptr;
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1camera_CameraSurfaceView_nUpdateTexture(JNIEnv *env, jobject thiz,
                                                             jbyteArray data, jint bitmap_width,
                                                             jint bitmap_height) {
    if (mController) {
        jbyte *yuv = env->GetByteArrayElements(data, nullptr);
        auto *rgb = new int32_t[bitmap_width * bitmap_height];

        YUVCodecUtils::toRGBA(&rgb, yuv, bitmap_width, bitmap_height);
        NativeImage nativeImage;
        nativeImage.format = AndroidBitmapFormat::ANDROID_BITMAP_FORMAT_RGBA_8888;
        nativeImage.data = reinterpret_cast<unsigned char *>(rgb);
        nativeImage.width = bitmap_width;
        nativeImage.height = bitmap_height;
        mController->updateFrame(nativeImage);

        delete[] rgb;
        env->ReleaseByteArrayElements(data, yuv, 0);
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1camera_CameraSurfaceView_nSetupDegreeParams(JNIEnv *env, jobject thiz,
                                                                 jint camera_facing,
                                                                 jint window_degrees,
                                                                 jint sensor_degrees) {
    if(mController){
        VAOParams params;
        params.cameraFacing = camera_facing;
        params.windowDegrees = window_degrees;
        params.sensorDegrees = sensor_degrees;
        mController->iniVAO(&params);
    }
}