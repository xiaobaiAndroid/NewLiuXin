/**
 * 樱花滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_SAKURASAMPLER_H
#define NATIVEIMAGEEDITOR_SAKURASAMPLER_H

#include "base/BlendSampler.h"

class SakuraSampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;

    void drawBefore() override;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_SAKURASAMPLER_H
