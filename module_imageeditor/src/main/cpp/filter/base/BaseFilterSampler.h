/**
 * 滤镜采样器基类
 *@author: baizf
 *@date: 2023/2/2
*/
//

#ifndef NATIVEIMAGEEDITOR_BASEFILTERSAMPLER_H
#define NATIVEIMAGEEDITOR_BASEFILTERSAMPLER_H

#include "base/BaseSampler.h"
#include "NativeImage.h"
#include <android/bitmap.h>

class BaseFilterSampler: public  BaseSampler{
protected:

    ProgramProxy *mProgramProxy = nullptr;

    int mVerticesCount = 6;

public:
    void initCommon(JNIEnv *env, jobject *assetsManager, string &vertPath, string &fragPath);

    virtual void draw(TextureProxy *textureProxy, VAOProxy *cVAO, ModelMatrix modelMatrix);
    virtual void drawBefore();

    virtual void releaseGL();

    ~BaseFilterSampler();
};

#endif //NATIVEIMAGEEDITOR_BASEFILTERSAMPLER_H
