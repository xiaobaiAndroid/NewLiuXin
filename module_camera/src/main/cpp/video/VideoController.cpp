/**
 *@author: baizf
 *@date: 2023/3/5
*/
//

#include "VideoController.h"


void VideoController::prepare(JavaVM *javaVm, ANativeWindow *window) {
    mVideoDisplay = new VideoDisplay();
    mVideoDisplay->prepare(javaVm,window);
}

void VideoController::startPreview() {

}

void VideoController::surfaceChanged(int width, int height) {
    if(mVideoDisplay){
        mVideoDisplay->onSurfaceChange(width,height);
    }
}

void VideoController::release() {
    if(mVideoDisplay){
        mVideoDisplay->destroyGLThread();
    }
}

void VideoController::updateFrame(NativeImage &nativeImage) {
    if(mVideoDisplay){
        mVideoDisplay->updateFrame(nativeImage);
    }
}

void VideoController::iniVAO(VAOParams *vaoParams) {
    if(mVideoDisplay){
        mVideoDisplay->iniVAO(vaoParams);
    }
}
