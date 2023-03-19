/**
 *@author: baizf
 *@date: 2023/2/27
*/
//

#ifndef NATIVEVIDEOPLAYER_EGLSHARECONTEXT_H
#define NATIVEVIDEOPLAYER_EGLSHARECONTEXT_H

#include "EGLCore.h"
#include "EGLErrorProxy.h"

class EGLShareContext{
protected:
   static EGLShareContext *mInstance;

   EGLContext mShareContext  =EGL_NO_CONTEXT;
   EGLDisplay mWindowDisplay = EGL_NO_DISPLAY;
    EGLConfig mEglConfig = nullptr;
   void init();

   EGLShareContext();
public:

    ~EGLShareContext() = default;
    EGLContext getShareContext();
    EGLConfig getEGLConfig();

    EGLDisplay getWindowDisplay();
    static EGLShareContext* getInstance();
    void release();
};

#endif //NATIVEVIDEOPLAYER_EGLSHARECONTEXT_H
