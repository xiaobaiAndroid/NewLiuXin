/**
 *@author: baizf
 *@date: 2023/2/20
*/
//

#ifndef VIDEOEDITOR_CAMERARENDERER_H
#define VIDEOEDITOR_CAMERARENDERER_H


#include <jni.h>
#include <android/native_window.h>
#include "NativeImage.h"
#include "FiltersSampler.h"
#include "ImageSampler.h"
#include "TriangleSampler.h"

#include "EGLThread.h"

class CameraRenderer{
private:


    int mWindowWidth = 0, mWindowHeight = 0;

    JNIEnv *mEnv = nullptr;
    jobject mAsset = nullptr;

    CameraProxy *mCameraProxy = nullptr;

    VBOProxy *mVertexVBO = nullptr, *mTexCoordsVBO= nullptr;
    VAOProxy *mVAO = nullptr;

    TriangleSampler *mTriangleSampler = nullptr;

    void releaseVBO();

    void releaseVAO();

    NativeImage *mSourceImage = nullptr;
public:
    FiltersSampler *mFiltersSampler = nullptr;
    ImageSampler *mImageSampler = nullptr;

    int cameraFacing = 0;
    int windowDegrees = 0;
    int sensorDegrees = 0;

    EGLThread *mEGLThread = nullptr;
    int mScaleImageWidth = 0, mScaleImageHeight = 0;

    int mFilterType = 0;

    CameraRenderer();

    void release();

    void draw();

    void setFilter(JNIEnv *env, jobject *asset, int filter_type);

    void init(JNIEnv *env, jobject *assetsManager, ANativeWindow *pWindow);
    void resize( int screenW, int screenH);

    void setSourceImage(NativeImage &nativeImage);

    float getAdjustValue(const int adjustType);

    void setAdjustValue(const int adjustType, float value);

    void iniVAO(JNIEnv *env, jint cameraFacing, jint windowDegrees, jint sensorDegrees);


    BlendSampler* getFilterSampler();

    void getTextureData();

    static void onSurfaceCreate(void *obj);
    static void onSurfaceChange(void *obj, int width, int height);
    static void onSurfaceDraw(void *obj);

    void onCreate();

    void onChange(int width, int height);

    void onDraw();
};

#endif //VIDEOEDITOR_CAMERARENDERER_H
