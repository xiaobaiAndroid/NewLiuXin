/**
 *@author: baizf
 *@date: 2023/2/27
*/
//

#include "EGLShareContext.h"
#include "Logutils.h"

EGLShareContext *EGLShareContext::mInstance = new EGLShareContext();

EGLShareContext::EGLShareContext() {
    init();
}

EGLContext EGLShareContext::getShareContext() {
    if (mInstance->mWindowDisplay == EGL_NO_DISPLAY) {
        mInstance->init();
    }
    return mInstance->mShareContext;
}

EGLShareContext *EGLShareContext::getInstance() {
    return mInstance;
}

void EGLShareContext::init() {
    mWindowDisplay = eglGetDisplay(EGL_DEFAULT_DISPLAY);

    if (mWindowDisplay == EGL_NO_DISPLAY) {
        LOGE("eglGetDisplay  error");
        return;
    }
    EGLint major; //主要版本
    EGLint minor; //次要版本
    if (!eglInitialize(mWindowDisplay, &major, &minor)) {
        LOGE("eglInitialize error");
        return;
    }
    LOGI("major=%d, minor=%d", major, minor);

    //配置属性列表数组，结尾以EGL_NONE结束
    const EGLint attributeConfigList[] = {
            EGL_BUFFER_SIZE, 32,
            EGL_RED_SIZE, 8,
            EGL_GREEN_SIZE, 8,
            EGL_BLUE_SIZE, 8,
            EGL_ALPHA_SIZE, 8,
            EGL_DEPTH_SIZE, 8, //深度缓冲区占用8位
            EGL_STENCIL_SIZE, 8, //模板缓冲区占用8位
            EGL_RENDERABLE_TYPE, EGL_OPENGL_ES3_BIT, //EGL渲染类型：OpenGL ES3
            EGL_SURFACE_TYPE, EGL_WINDOW_BIT,
            EGL_NONE
    };

    EGLint numConfigs;


    EGLBoolean result = eglChooseConfig(mWindowDisplay,
                                        attributeConfigList, //指定配置匹配所需的属性。
                                        &mEglConfig, //帧缓冲区配置的数组。
                                        1, //指定帧缓冲区配置数组的大小。
                                        &numConfigs); //返回的帧缓冲区配置数。
    if (result == EGL_FALSE) {
        LOGE("eglChooseConfig attributeConfigList error");
        return;
    } else if (result == EGL_BAD_ATTRIBUTE) {
        LOGE("eglChooseConfig attributeConfigList fail attribute_list contains an invalid frame buffer configuration attribute or an attribute value that is unrecognized or out of range.");
    } else if (result == EGL_BAD_DISPLAY) {
        LOGE("eglChooseConfig attributeConfigList fail display is not an EGL display connection");
    } else if (result == EGL_NOT_INITIALIZED) {
        LOGE("eglChooseConfig attributeConfigList fail display has not been initialized.");
    } else if (result == EGL_BAD_PARAMETER) {
        LOGE("eglChooseConfig attributeConfigList fail num_config is NULL.");
    }else{
        LOGI("frame buffer configurations success number=%d", numConfigs);
    }


    const EGLint contextAttribList[] = {
            EGL_CONTEXT_CLIENT_VERSION, 3, //确定OpenGL ES版本为3.x
            EGL_NONE
    };
    mShareContext = eglCreateContext(mWindowDisplay, mEglConfig,
                                     nullptr == mShareContext ? EGL_NO_CONTEXT : mShareContext,
                                     contextAttribList);

    string message;
    bool hasError = EGLErrorProxy::hasError(message);
    if(hasError){
        LOGE("eglCreateContext fail message=%s", message.c_str());
    }
}

EGLDisplay EGLShareContext::getWindowDisplay() {
    return mWindowDisplay;
}

EGLConfig EGLShareContext::getEGLConfig() {
    return mEglConfig;
}

void EGLShareContext::release() {
    if (mWindowDisplay != EGL_NO_DISPLAY) {
        // 解绑display上的eglContext和Surface
        eglMakeCurrent(mWindowDisplay, EGL_NO_SURFACE, EGL_NO_SURFACE, EGL_NO_CONTEXT);
    }

    if (mWindowDisplay != EGL_NO_CONTEXT) {
        eglDestroyContext(mWindowDisplay, mShareContext);
        mShareContext = EGL_NO_CONTEXT;
    }

    if (mWindowDisplay != EGL_NO_DISPLAY) {
        eglTerminate(mWindowDisplay);
        mWindowDisplay = EGL_NO_DISPLAY;
    }
}