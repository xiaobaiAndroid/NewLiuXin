/**
 *@author: baizf
 *@date: 2023/3/5
*/
//

#ifndef VIDEOEDITOR_VIDEORENDER_H
#define VIDEOEDITOR_VIDEORENDER_H

#include <jni.h>
#include "NativeImage.h"
#include "VideoFinalEffectRender.h"

struct VAOParams{
    int cameraFacing = 0;
    int windowDegrees = 0;
    int sensorDegrees = 0;
};

class VideoRender{
private:
    JavaVM *mJavaVM = nullptr;

    VideoFinalEffectRender *mVideoFinalEffectRender = nullptr;

    VBOProxy *mVertexVBO = nullptr, *mTexCoordsVBO= nullptr;
    VAOProxy *mVAO = nullptr;
    TextureProxy *mTexture = nullptr;
public:
    VideoRender(JavaVM *javaVm);

    void releaseVBO();

    void releaseVAO();
    
    void updateFrame(NativeImage &nativeImage);

    float getAdjustValue(const int adjustType);

    void setAdjustValue(const int adjustType, float value);

    void iniVAO(VAOParams *vaoParams);


    void release();
    ~VideoRender() = default;

    void surfaceResize(int width, int height);
    void draw();
};

#endif //VIDEOEDITOR_VIDEORENDER_H
