/**
 *@author: baizf
 *@date: 2023/2/24
*/
//

#ifndef EGLSAMPLE_EGLTHREAD_H
#define EGLSAMPLE_EGLTHREAD_H

#include "EGLCore.h"
#include <pthread.h>

//自动渲染
#define RENDER_MODULE_AUTO 1
//手动渲染
#define RENDER_MODULE_MANUAL 2

typedef void (*OnCreateCallback)(void *obj);
typedef void (*OnChangeCallback)(void *obj, int width, int height);
typedef void (*OnDrawCallback)(void *obj);
typedef void (*OnDestroyCallback)(void *obj);

class EGLThread{
public:

    pthread_t mThread = -1;
    pthread_mutex_t mPthreadMutex;
    pthread_cond_t mPthreadCondition;

    EGLNativeWindowType mWindow;

    bool isFinish = false;
    bool isCreate = false;
    bool isStart = false;
    bool isChange = false;

    int mSurfaceWidth = 0;
    int mSurfaceHeight = 0;

    int mRenderType = RENDER_MODULE_AUTO;


    OnCreateCallback mOnCreate;


    OnChangeCallback mOnChange;


    OnDrawCallback mOnDraw;
    OnDestroyCallback mOnDestroy;

    EGLThread();
    ~EGLThread();

    void start(EGLNativeWindowType window);
    void onSurfaceChange(int width, int height);

    //设置模式
    void setRenderModule(int renderModule);
    void notifyRender();


    void setOnCreateCallBack(OnCreateCallback onCreate, void *obj);
    void setOnChangeCallBack(OnChangeCallback onChange, void *obj);
    void setOnDrawCallback(OnDrawCallback onDraw, void *obj);
    void setOnDestroyCallBack(OnDestroyCallback onDestroy, void *obj);
    void startDraw();
private:
    EGLCore *mEGLCore = nullptr;
    void *mObj = nullptr;
};

#endif //EGLSAMPLE_EGLTHREAD_H
