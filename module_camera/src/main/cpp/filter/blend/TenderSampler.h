/**
 * 温柔滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_TENDERSAMPLER_H
#define NATIVEIMAGEEDITOR_TENDERSAMPLER_H

#include "base/BlendSampler.h"

class TenderSampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;
    TextureProxy *mGreyFrameTexture = nullptr;
    void drawBefore() override;
protected:
    string getFragSrc() override;
public:
    void init() override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;

};

#endif //NATIVEIMAGEEDITOR_TENDERSAMPLER_H
