/**
 *@author: baizf
 *@date: 2023/3/5
*/
//

#ifndef VIDEOEDITOR_VIDEODISPLAY_H
#define VIDEOEDITOR_VIDEODISPLAY_H

#include "EGLThread.h"
#include "NativeImage.h"
#include "render/VideoRender.h"
#include <jni.h>
#include <pthread.h>
#include <queue>

#define INPUT_IMAGE_MAX_SIZE 10

class VideoDisplay {
private:
    EGLThread *mEGLThread = nullptr;
    VideoRender *mVideoRender = nullptr;
    JavaVM *mJavaVM = nullptr;

    VAOParams *mVaoParams = nullptr;
    NativeImage *mOriginalImage = nullptr;
    bool isInitVAO = false;

//    queue<NativeImage *> mInputImageQueue;
    pthread_mutex_t mUpdateTextureMutex = {};
    pthread_cond_t mUpdateTextureCon = {};
public:

    void prepare(JavaVM *javaVM, ANativeWindow *window);

    void startPreview();

    static void onSurfaceCreateCallback(void *obj);

    static void onSurfaceChangeCallback(void *obj, int width, int height);

    static void onSurfaceDrawCallback(void *obj);

    static void onSurfaceDestroyCallback(void *obj);

    void updateFrame(NativeImage &nativeImage);

    void iniVAO(VAOParams *vaoParams);

    void surfaceCreate();

    void resize(int width, int height);

    void drawFrame();

    void release();

    void destroyGLThread();

    void onSurfaceChange(int width, int height);
};

#endif //VIDEOEDITOR_VIDEODISPLAY_H
