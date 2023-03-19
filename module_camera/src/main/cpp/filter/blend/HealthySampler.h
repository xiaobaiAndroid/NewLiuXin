/**
 * 健康滤镜
 *@author: baizf
 *@date: 2023/2/3
*/
//

#ifndef NATIVEIMAGEEDITOR_HEALTHYSAMPLER_H
#define NATIVEIMAGEEDITOR_HEALTHYSAMPLER_H

#include "base/BlendSampler.h"

class HealthySampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;
    TextureProxy *mMaskTexture = nullptr;

    void drawBefore() override;
protected:
    string getFragSrc() override;
public:
    void init() override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_HEALTHYSAMPLER_H
