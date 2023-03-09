/**
 * 日落滤镜
 *@author: baizf
 *@date: 2023/2/3
*/
//

#ifndef NATIVEIMAGEEDITOR_SUNSETSAMPLER_H
#define NATIVEIMAGEEDITOR_SUNSETSAMPLER_H

#include "base/BlendSampler.h"

class SunsetSampler: public BlendSampler{
private:
    TextureProxy *mMaskGrey1Texture = nullptr;
    TextureProxy *mMaskGrey2Texture = nullptr;
    TextureProxy *mCurveTexture = nullptr;

    void drawBefore() override;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_SUNSETSAMPLER_H
