/**
 *@author: baizf
 *@date: 2023/2/24
*/
//

#include "EGLCore.h"
#include "Logutils.h"
#include <exception>
#include <android/native_window_jni.h>


EGLCore::EGLCore() {
    EGLShareContext::getInstance()->getShareContext();
}

int EGLCore::swapBuffers(EGLSurface surface) const {
    EGLDisplay windowDisplay = EGLShareContext::getInstance()->getWindowDisplay();
    if (windowDisplay != EGL_NO_DISPLAY && surface != EGL_NO_SURFACE) {
        eglSwapBuffers(windowDisplay, surface);
        return 0;
    }
    return -1;
}

void EGLCore::destroyEGL() {
    EGLShareContext::getInstance()->release();

}


EGLSurface EGLCore::createOffscreenSurface(int width, int height) const {
    EGLDisplay windowDisplay = EGLShareContext::getInstance()->getWindowDisplay();
    EGLConfig eglConfig = EGLShareContext::getInstance()->getEGLConfig();
    if(windowDisplay == EGL_NO_DISPLAY){
        return nullptr;
    }
    LOGI("createOffscreenSurface---width=%d, height=%d",width,height);

    EGLint bufferAttributes[] = { EGL_WIDTH, width, EGL_HEIGHT, height, EGL_NONE, EGL_NONE };
    //创建一个EGL像素缓冲表面
    EGLSurface  eglSurface = eglCreatePbufferSurface(windowDisplay, eglConfig, bufferAttributes);
    string message;
    bool hasError = EGLErrorProxy::hasError(message);
    if (hasError) {
        LOGE("createOffscreenSurface error, %s", message.c_str());
        return nullptr;
    }
    LOGI("createOffscreenSurface success");
    return eglSurface;
}

void EGLCore::makeCurrent(EGLSurface surface) const {
    //将上下文绑定到当前渲染线程以及绘制和读取表面。
   bool result = eglMakeCurrent(EGLShareContext::getInstance()->getWindowDisplay(), surface, surface, EGLShareContext::getInstance()->getShareContext());
    if (!result) {
        LOGE("eglMakeCurrent error");
    }
}

void EGLCore::doneCurrent() const {
    eglMakeCurrent(EGLShareContext::getInstance()->getWindowDisplay(),EGL_NO_SURFACE, EGL_NO_SURFACE, EGL_NO_CONTEXT);

}

EGLSurface EGLCore::createWindowSurface(ANativeWindow *pWindow) {
    EGLSurface surface = nullptr;
    EGLint format;

    EGLDisplay windowDisplay = EGLShareContext::getInstance()->getWindowDisplay();
    EGLConfig eglConfig = EGLShareContext::getInstance()->getEGLConfig();
    if (pWindow == nullptr){
        LOGE("EGLCore::createWindowSurface  _window is NULL");
        return nullptr;
    }
    if (!eglGetConfigAttrib(windowDisplay, eglConfig, EGL_NATIVE_VISUAL_ID, &format)) {
        string message;
        EGLErrorProxy::hasError(message);
        LOGE("eglGetConfigAttrib() returned error %s", message.c_str());
        destroyEGL();
        return surface;
    }
    ANativeWindow_setBuffersGeometry(pWindow, 0, 0, format);
    if (!(surface = eglCreateWindowSurface(windowDisplay, eglConfig, pWindow, nullptr))) {
        string message;
        EGLErrorProxy::hasError(message);
        LOGE("eglCreateWindowSurface() returned error %s", message.c_str());
    }
    return surface;
}

