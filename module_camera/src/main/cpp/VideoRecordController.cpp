/**
 *@author: baizf
 *@date: 2023/3/4
*/
//

#include "VideoRecordController.h"

void VideoRecordController::prepare(JavaVM *javaVm, ANativeWindow *window) {
    mVideoController = new VideoController();
    mVideoController->prepare(javaVm,window);
}

void VideoRecordController::release() {
    mVideoController->release();
}

void VideoRecordController::surfaceChanged(int width, int height) {
    mVideoController->surfaceChanged(width,height);
}

void VideoRecordController::updateFrame(NativeImage &nativeImage) {
    if(mVideoController){
        mVideoController->updateFrame(nativeImage);
    }
}

void VideoRecordController::iniVAO(VAOParams *vaoParams) {
    if(mVideoController){
        mVideoController->iniVAO(vaoParams);
    }
}