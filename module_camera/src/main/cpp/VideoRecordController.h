/**
 *@author: baizf
 *@date: 2023/3/4
*/
//

#ifndef VIDEOEDITOR_VIDEORECORDCONTROLLER_H
#define VIDEOEDITOR_VIDEORECORDCONTROLLER_H

#include <android/native_window.h>
#include <jni.h>
#include "VideoController.h"


class VideoRecordController{
private:
    VideoController *mVideoController = nullptr;
protected:

public:
    void prepare(JavaVM *javaVm,ANativeWindow *window);
    void release();
    void surfaceChanged(int width, int height);
    void updateFrame(NativeImage &nativeImage);

    void iniVAO(VAOParams *vaoParams);
};

#endif //VIDEOEDITOR_VIDEORECORDCONTROLLER_H
