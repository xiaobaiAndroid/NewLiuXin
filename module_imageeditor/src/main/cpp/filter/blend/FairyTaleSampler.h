/**
 * 童话滤镜
 *@author: baizf
 *@date: 2023/2/1
*/
//

#ifndef NATIVEIMAGEEDITOR_FAIRYTALESAMPLER_H
#define NATIVEIMAGEEDITOR_FAIRYTALESAMPLER_H

#include "base/BlendSampler.h"

class FairyTaleSampler: public BlendSampler{
private:
    TextureProxy *mFilterTex = nullptr;
protected:
    void drawBefore() override;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_FAIRYTALESAMPLER_H
