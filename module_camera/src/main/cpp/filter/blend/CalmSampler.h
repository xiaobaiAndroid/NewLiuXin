/**
 * 平静滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_CALMSAMPLER_H
#define NATIVEIMAGEEDITOR_CALMSAMPLER_H

#include "base/BlendSampler.h"

class CalmSampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;
    TextureProxy *mGrey1FrameTexture = nullptr;
    TextureProxy *mGrey2FrameTexture = nullptr;

    void drawBefore() override;
protected:
    string getFragSrc() override;
public:
    void init() override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_CALMSAMPLER_H
