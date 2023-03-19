/**
 * 温暖滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_WARMSAMPLER_H
#define NATIVEIMAGEEDITOR_WARMSAMPLER_H

#include "base/BlendSampler.h"

class WarmSampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;
    TextureProxy *mGreyFrameTexture = nullptr;
    TextureProxy *mLayerImageTexture = nullptr;

    void drawBefore() override;
protected:
    string getFragSrc() override;
public:
    void init() override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_WARMSAMPLER_H
