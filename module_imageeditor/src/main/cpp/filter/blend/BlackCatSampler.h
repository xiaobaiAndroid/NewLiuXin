/**
 * 黑猫滤镜
 *@author: baizf
 *@date: 2023/2/3
*/
//

#ifndef NATIVEIMAGEEDITOR_BLACKCATSAMPLER_H
#define NATIVEIMAGEEDITOR_BLACKCATSAMPLER_H

#include "base/BlendSampler.h"

class BlackCatSampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;
    void drawBefore() override;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_BLACKCATSAMPLER_H
