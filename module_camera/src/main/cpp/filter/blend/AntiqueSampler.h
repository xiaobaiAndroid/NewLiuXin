/**
 * 复古滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_ANTIQUESAMPLER_H
#define NATIVEIMAGEEDITOR_ANTIQUESAMPLER_H

#include "base/BlendSampler.h"

class AntiqueSampler: public BlendSampler{
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

#endif //NATIVEIMAGEEDITOR_ANTIQUESAMPLER_H
