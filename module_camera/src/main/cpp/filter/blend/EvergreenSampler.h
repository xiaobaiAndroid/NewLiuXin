/**
 * 常青滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_EVERGREENSAMPLER_H
#define NATIVEIMAGEEDITOR_EVERGREENSAMPLER_H

#include "base/BlendSampler.h"

class EvergreenSampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;
    void drawBefore() override;
protected:
    string getFragSrc() override;
public:
    void init() override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_EVERGREENSAMPLER_H
