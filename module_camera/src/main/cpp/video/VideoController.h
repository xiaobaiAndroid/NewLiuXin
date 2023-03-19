/**
 *@author: baizf
 *@date: 2023/3/5
*/
//

#ifndef VIDEOEDITOR_VIDEOCONTROLLER_H
#define VIDEOEDITOR_VIDEOCONTROLLER_H

#include <android/native_window.h>
#include <jni.h>
#include "VideoDisplay.h"

class VideoController{
private:
    VideoDisplay *mVideoDisplay = nullptr;
public:
    void prepare(JavaVM *javaVm,ANativeWindow *window);
    void startPreview();
    void surfaceChanged(int width, int height);

    void release();

    void updateFrame(NativeImage &nativeImage);
    void iniVAO(VAOParams *vaoParams);
};

#endif //VIDEOEDITOR_VIDEOCONTROLLER_H
