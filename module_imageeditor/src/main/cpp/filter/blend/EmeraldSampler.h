/**
 * 祖母绿滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_EMERALDSAMPLER_H
#define NATIVEIMAGEEDITOR_EMERALDSAMPLER_H

#include "base/BlendSampler.h"

class EmeraldSampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;

    void drawBefore() override;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_EMERALDSAMPLER_H
