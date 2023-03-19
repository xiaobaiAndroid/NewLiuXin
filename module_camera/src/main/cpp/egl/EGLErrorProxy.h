/**
 *@author: baizf
 *@date: 2023/3/2
*/
//

#ifndef NATIVEVIDEOPLAYER_EGLERRORPROXY_H
#define NATIVEVIDEOPLAYER_EGLERRORPROXY_H

#include <string>
#include <EGL/egl.h>

using namespace std;

class EGLErrorProxy{
public:
    static bool hasError(string &message){
        bool hasError = true;
        EGLint error = eglGetError();
        switch (error) {
            case EGL_NOT_INITIALIZED:
                message = "EGL is not initialized, or could not be initialized, for the specified EGL display connection.";
                break;
            case EGL_BAD_ACCESS:
                message = "EGL cannot access a requested resource (for example a context is bound in another thread).";
                break;
            case EGL_BAD_ALLOC:
                message = "EGL failed to allocate resources for the requested operation.";
                break;
            case EGL_BAD_ATTRIBUTE:
                message = "An unrecognized attribute or attribute value was passed in the attribute list.";
                break;
            case EGL_BAD_CONTEXT:
                message = "An EGLContext argument does not name a valid EGL rendering context.";
                break;
            case EGL_BAD_CONFIG:
                message = "An EGLConfig argument does not name a valid EGL frame buffer configuration.";
                break;
            case EGL_BAD_CURRENT_SURFACE:
                message = "The current surface of the calling thread is a window, pixel buffer or pixmap that is no longer valid.";
                break;
            case EGL_BAD_DISPLAY:
                message = "An EGLDisplay argument does not name a valid EGL display connection.";
                break;
            case EGL_BAD_SURFACE:
                message = "An EGLSurface argument does not name a valid surface (window, pixel buffer or pixmap) configured for GL rendering.";
                break;
            case EGL_BAD_MATCH:
                message = "Arguments are inconsistent (for example, a valid context requires buffers not supplied by a valid surface).";
                break;
            case EGL_BAD_PARAMETER:
                message = "One or more argument values are invalid.";
                break;
            case EGL_BAD_NATIVE_PIXMAP:
                message = "A NativePixmapType argument does not refer to a valid native pixmap.";
                break;
            case EGL_BAD_NATIVE_WINDOW:
                message = "A NativeWindowType argument does not refer to a valid native window.";
                break;
            case EGL_CONTEXT_LOST:
                message = "A power management event has occurred. The application must destroy all contexts and reinitialise OpenGL ES state and objects to continue rendering.";
                break;
            default:
                message = "success";
                hasError = false;
                break;
        }
        return hasError;
    }
};

#endif //NATIVEVIDEOPLAYER_EGLERRORPROXY_H
