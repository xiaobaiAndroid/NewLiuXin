/**
 *@author: baizf
 *@date: 2022/12/10
*/
//

#ifndef OPENGLDEMO_BASESAMPLER_H
#define OPENGLDEMO_BASESAMPLER_H

#include "CoreProxy.h"


static const string SHADER_VAR_NAME_PROJECTION = "uProjection";
static const string SHADER_VAR_NAME_VIEW = "uView";
static const string SHADER_VAR_NAME_MODEL = "uModel";

class BaseSampler {

public:
    CameraProxy *mCamera = nullptr;

    virtual void init(JNIEnv *env, jobject *assetsManager) = 0;

    virtual void setRenderSize(int width, int height);

    virtual void releaseGL() = 0;

public:
    int mRenderWidth = 0, mRenderHeight = 0;
    //宽高比
    float mRatioWH = 0.0f;

};


#endif //OPENGLDEMO_BASESAMPLER_H
