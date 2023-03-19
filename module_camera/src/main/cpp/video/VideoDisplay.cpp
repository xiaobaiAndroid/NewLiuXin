/**
 *@author: baizf
 *@date: 2023/3/5
*/
//

#include "VideoDisplay.h"
#include "Logutils.h"

void VideoDisplay::prepare(JavaVM *javaVM,ANativeWindow *window) {
    mJavaVM = javaVM;
    mVaoParams = new VAOParams();

    mOriginalImage = new NativeImage();
    
    mEGLThread = new EGLThread();
    mEGLThread->setOnCreateCallBack(onSurfaceCreateCallback, this);
    mEGLThread->setOnChangeCallBack(onSurfaceChangeCallback, this);
    mEGLThread->setOnDrawCallback(onSurfaceDrawCallback, this);
    mEGLThread->setOnDestroyCallBack(onSurfaceDestroyCallback,this);
    mEGLThread->start(window);

    pthread_mutex_init(&mUpdateTextureMutex, nullptr);
    pthread_cond_init(&mUpdateTextureCon, nullptr);
}

void VideoDisplay::startPreview() {

}


void VideoDisplay::onSurfaceCreateCallback(void *obj) {
    auto *pDisplay = static_cast<VideoDisplay *>(obj);
    pDisplay->surfaceCreate();
}

void VideoDisplay::onSurfaceChangeCallback(void *obj, int width, int height) {
    auto *pDisplay = static_cast<VideoDisplay *>(obj);
    pDisplay->resize(width, height);
}

void VideoDisplay::onSurfaceDrawCallback(void *obj) {
    auto *pDisplay = static_cast<VideoDisplay *>(obj);
    pDisplay->drawFrame();
}

void VideoDisplay::onSurfaceDestroyCallback(void *obj) {
    auto *pDisplay = static_cast<VideoDisplay *>(obj);
    pDisplay->release();
}

//运行在EGL线程中
void VideoDisplay::surfaceCreate() {
    if(mVideoRender){
        mVideoRender->release();
        delete mVideoRender;
    }
    mVideoRender = new VideoRender(mJavaVM);
}

//运行在EGL线程中
void VideoDisplay::resize(int width, int height) {
    if(mVideoRender){
        mVideoRender->surfaceResize(width,height);
    }

}

//运行在EGL线程中
void VideoDisplay::drawFrame() {
    if(mVideoRender){
        if(isInitVAO){
            mVideoRender->iniVAO(mVaoParams);
            isInitVAO = false;
        }
        if(mOriginalImage->data){
            pthread_mutex_lock(&mUpdateTextureMutex);
            mVideoRender->updateFrame(*mOriginalImage);
            pthread_cond_signal(&mUpdateTextureCon);
            pthread_mutex_unlock(&mUpdateTextureMutex);

            mVideoRender->draw();
        }
    }
}

//运行在EGL线程中
void VideoDisplay::release() {
    if(mVideoRender){

        mVideoRender->release();
        delete mVideoRender;
        mVideoRender = nullptr;
    }
}


void VideoDisplay::onSurfaceChange(int width, int height) {
    if(mEGLThread){
        mEGLThread->onSurfaceChange(width,height);
    }
}

void VideoDisplay::destroyGLThread() {
    if(mEGLThread){
        mEGLThread->isFinish = true;
    }
    if(mOriginalImage->data){
        delete[] mOriginalImage->data;
    }
    delete mOriginalImage;
    pthread_mutex_destroy(&mUpdateTextureMutex);
    pthread_cond_destroy(&mUpdateTextureCon);
}


void VideoDisplay::updateFrame(NativeImage &nativeImage) {
    if(nativeImage.data == nullptr){
        return;
    }

//    auto *cacheImage = new NativeImage();

    pthread_mutex_lock(&mUpdateTextureMutex);

    mOriginalImage->width = nativeImage.width;
    mOriginalImage->height = nativeImage.height;
    mOriginalImage->format = nativeImage.format;
    if(mOriginalImage->data){
        delete[] mOriginalImage->data;
    }

    size_t dataSize = nativeImage.width * nativeImage.height * sizeof(unsigned char) * 4;
    LOGI("nativeImage dataSize=%d", dataSize);
    mOriginalImage->data = new unsigned char[dataSize];
    memcpy(mOriginalImage->data, nativeImage.data, dataSize);

    pthread_cond_wait(&mUpdateTextureCon, &mUpdateTextureMutex);
    pthread_mutex_unlock(&mUpdateTextureMutex);
}

void VideoDisplay::iniVAO(VAOParams *vaoParams) {
    if(vaoParams && mVaoParams){
        mVaoParams->cameraFacing = vaoParams->cameraFacing;
        mVaoParams->windowDegrees = vaoParams->windowDegrees;
        mVaoParams->sensorDegrees = vaoParams->sensorDegrees;
    }
    isInitVAO = true;
}