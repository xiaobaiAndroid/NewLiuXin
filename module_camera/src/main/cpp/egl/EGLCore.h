/**
 *@author: baizf
 *@date: 2023/2/24
*/
//

#ifndef EGLSAMPLE_EGLHELPER_H
#define EGLSAMPLE_EGLHELPER_H

#include <EGL/egl.h>
#include "EGLShareContext.h"
#include "EGLErrorProxy.h"

//纳秒为单位的时间，但时间的确切含义取决于本机窗口系统对呈现时间
//EGLAPIENTRYP: EGL API的入口
//给eglPresentationTimeANDROID函数定义一个别名：PresentationTimeAndroid
//time: 渲染到 EGLSurface 的每个帧指定所需的呈现时间
typedef EGLBoolean (EGLAPIENTRYP PresentationTimeAndroid)(EGLDisplay display, EGLSurface surface, khronos_stime_nanoseconds_t time);

class EGLCore{
private:
    PresentationTimeAndroid mPresentationTimeAndroid;

public:
    EGLCore();

    int swapBuffers(EGLSurface pVoid) const;

    void destroyEGL();


    EGLSurface createOffscreenSurface(int width, int height) const;

    void makeCurrent(EGLSurface surface) const;

    void doneCurrent() const;

    EGLSurface createWindowSurface(ANativeWindow *pWindow);

};


#endif //EGLSAMPLE_EGLHELPER_H
