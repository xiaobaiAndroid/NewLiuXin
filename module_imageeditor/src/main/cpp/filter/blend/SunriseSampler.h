/**
 * 日出滤镜
 *@author: baizf
 *@date: 2023/2/2
*/
//

#ifndef NATIVEIMAGEEDITOR_SUNRISESAMPLER_H
#define NATIVEIMAGEEDITOR_SUNRISESAMPLER_H

#include "base/BlendSampler.h"


class SunriseSampler: public BlendSampler{
private:
    TextureProxy *mMaskGrey1Texture = nullptr;
    TextureProxy *mMaskGrey2Texture = nullptr;
    TextureProxy *mMaskGrey3Texture = nullptr;
    TextureProxy *mCurveTexture = nullptr;

    void drawBefore() override;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_SUNRISESAMPLER_H
