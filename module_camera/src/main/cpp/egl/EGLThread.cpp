/**
 *@author: baizf
 *@date: 2023/2/24
*/
//

#include "EGLThread.h"
#include "Logutils.h"
#include <unistd.h>


EGLThread::EGLThread() {
    pthread_mutex_init(&mPthreadMutex, nullptr);
    pthread_cond_init(&mPthreadCondition, nullptr);
}

EGLThread::~EGLThread() {
    pthread_mutex_destroy(&mPthreadMutex);
    pthread_cond_destroy(&mPthreadCondition);
}

void *run(void *context) {
    auto *pThread = static_cast<EGLThread *>(context);
    pThread->startDraw();
    pthread_exit(nullptr);
}

void EGLThread::startDraw() {
    EGLSurface windowSurface = mEGLCore->createWindowSurface(mWindow);
    mEGLCore->makeCurrent(windowSurface);
    while (!isFinish){

        if(isCreate){
            isCreate = false;
            mOnCreate(mObj);
        }
        if(isChange){
            isChange = false;
            isStart = true;
            mOnChange(mObj,mSurfaceWidth, mSurfaceHeight);
        }

        if(isStart){
            mOnDraw(mObj);
            //交换缓冲区,显示到窗口
            mEGLCore->swapBuffers(windowSurface);

            if(mRenderType == RENDER_MODULE_AUTO){
                // sleep 1/60秒，近似1秒绘制60帧
//                usleep(1000000/60);
                usleep(1000000/30);
            }else{
                pthread_mutex_lock(&mPthreadMutex);
                pthread_cond_wait(&mPthreadCondition, &mPthreadMutex);
                pthread_mutex_unlock(&mPthreadMutex);
            }
        }

    }
    mOnDestroy(mObj);
    mEGLCore->destroyEGL();
    delete mEGLCore;
}

void EGLThread::start(EGLNativeWindowType window) {
    if(mThread == -1){
        isCreate = true;
        mWindow = window;

        mEGLCore = new EGLCore();

        pthread_attr_t attr;
        ////初始化和设置线程分离属性,声明成joinable的线程，可以被其他线程join。
        pthread_attr_init(&attr);
        pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);
        int result = pthread_create(&mThread, &attr, run, this);
        if(result != 0){
            LOGE("pthread_create error");
        }
        pthread_attr_destroy(&attr);
    }
}



void EGLThread::onSurfaceChange(int width, int height) {
    if(mThread != -1){
        mSurfaceWidth = width;
        mSurfaceHeight = height;
        isChange = true;
    }
    notifyRender();
}

void EGLThread::setRenderModule(int renderModule) {
    mRenderType = renderModule;
    notifyRender();
}

void EGLThread::notifyRender() {
    pthread_mutex_lock(&mPthreadMutex);
    pthread_cond_signal(&mPthreadCondition);
    pthread_mutex_unlock(&mPthreadMutex);
}

void EGLThread::setOnCreateCallBack(OnCreateCallback onCreate, void *obj) {
    mOnCreate = onCreate;
    mObj = obj;
}

void EGLThread::setOnChangeCallBack(OnChangeCallback onChange, void *obj) {
    mOnChange = onChange;
    mObj = obj;
}

void EGLThread::setOnDrawCallback(OnDrawCallback onDraw, void *obj) {
    mOnDraw = onDraw;
    mObj = obj;
}

void EGLThread::setOnDestroyCallBack(OnDestroyCallback onDestroy, void *obj) {
    mOnDestroy = onDestroy;
    mObj = obj;
}