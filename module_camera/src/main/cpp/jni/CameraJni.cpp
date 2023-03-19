#include <jni.h>

/**
 *@author: baizf
 *@date: 2023/2/20
*/
//

#include "CameraRenderer.h"
#include "YUVCodecUtils.h"
#include "Logutils.h"
#include <mutex>
#include <android/native_window.h>
#include <android/native_window_jni.h>


//static CameraRenderer * cameraRenderer = nullptr;
//
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_bzf_module_1camera_CameraSurfaceView_nSetBitmap(JNIEnv *env, jobject thiz, jbyteArray data,
//                                                    jint bitmap_width, jint bitmap_height) {
//    if(cameraRenderer){
//        jbyte *yuv = env->GetByteArrayElements(data, nullptr);
//        auto *rgb = new int32_t[bitmap_width * bitmap_height];
//       YUVCodecUtils::toRGBA(&rgb, yuv, bitmap_width, bitmap_height);
//        NativeImage nativeImage;
//        nativeImage.format = AndroidBitmapFormat::ANDROID_BITMAP_FORMAT_RGBA_8888;
//        nativeImage.data = reinterpret_cast<unsigned char *>(rgb);
//        nativeImage.width = bitmap_width;
//        nativeImage.height = bitmap_height;
//        cameraRenderer->setSourceImage(nativeImage);
//
//        delete[] rgb;
//        env->ReleaseByteArrayElements(data,yuv,0);
//    }
//
//
//}
//
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_bzf_module_1camera_CameraSurfaceView_nSetupDegreeParams(JNIEnv *env, jobject thiz,
//                                                            jint cameraFacing, jint windowDegrees,
//                                                            jint sensorDegrees) {
//    if(cameraRenderer){
////        cameraRenderer->iniVAO(env,cameraFacing, windowDegrees, sensorDegrees);
//        cameraRenderer->cameraFacing = cameraFacing;
//        cameraRenderer->windowDegrees = windowDegrees;
//        cameraRenderer->sensorDegrees = sensorDegrees;
//    }
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_bzf_module_1camera_CameraSurfaceView_nSetFilterBitmap(JNIEnv *env, jobject thiz,jobject assetManager,
//                                                          jobjectArray bitmaps, jint filter_type) {
//    if(cameraRenderer){
//        if(filter_type == cameraRenderer->mFilterType){
//            return;
//        }
//        cameraRenderer->mFilterType = filter_type;
//
//        cameraRenderer->setFilter(env,&assetManager, filter_type);
//
//        auto *blendSampler = cameraRenderer->getFilterSampler();
//        jsize length = env->GetArrayLength(bitmaps);
//        vector<NativeImage *> images;
//        for (int i = 0; i < length; ++i) {
//            jobject jBitmap = env->GetObjectArrayElement(bitmaps, i);
//            if (jBitmap) {
//                NativeImage *image = BitmapUtils::loadImage(env, jBitmap);
//                images.push_back(image);
//            }
//        }
//        if(blendSampler){
//            blendSampler->setFilterImage(images);
//        }
//        for (auto image: images) {
//            delete image;
//        }
//
//        for (int i = 0; i < length; ++i) {
//            jobject jBitmap = env->GetObjectArrayElement(bitmaps, i);
//            if (jBitmap) {
//                BitmapUtils::release(env, jBitmap);
//            }
//        }
//    }
//}
//
//extern "C"
//JNIEXPORT jfloat JNICALL
//Java_com_bzf_module_1camera_adjust_AdjustSelectView_nGetAdjustValue(JNIEnv *env, jobject thiz,
//                                                                    jint adjust_type) {
//    if (cameraRenderer) {
//        return cameraRenderer->getAdjustValue(adjust_type);
//    }
//    return 0.0f;
//
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_bzf_module_1camera_adjust_AdjustSelectView_nSetAdjustValue(JNIEnv *env, jobject thiz,
//                                                                    jint adjust_type,
//                                                                    jfloat value) {
//    if(cameraRenderer){
//        cameraRenderer->setAdjustValue(adjust_type,value);
//    }
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_bzf_module_1camera_CameraSurfaceView_nRecorde(JNIEnv *env, jobject thiz) {
//    if(cameraRenderer){
////        cameraRenderer->getTextureData();
//    }
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_bzf_module_1camera_CameraSurfaceView_nSurfaceCreated(JNIEnv *env, jobject thiz,
//                                                              jobject surface, jobject asset_manager) {
//    if(cameraRenderer){
//        cameraRenderer->release();
//        delete cameraRenderer;
//    }
//    ANativeWindow *pWindow = ANativeWindow_fromSurface(env, surface);
//
//    cameraRenderer = new CameraRenderer();
//    cameraRenderer->init(env, &asset_manager, pWindow);
//
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_bzf_module_1camera_CameraSurfaceView_nSurfaceChanged(JNIEnv *env, jobject thiz, jint width,
//                                                              jint height) {
//    if(cameraRenderer){
//cameraRenderer->mEGLThread->
//
//onSurfaceChange(width, height);
//    }
//
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_bzf_module_1camera_CameraSurfaceView_nSurfaceDestroyed(JNIEnv *env, jobject thiz) {
//    if(cameraRenderer){
//        cameraRenderer->release();
//        delete cameraRenderer;
//        cameraRenderer = nullptr;
//    }
//}