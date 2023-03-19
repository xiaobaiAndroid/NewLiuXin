/**
 * 美白滤镜
 *@author: baizf
 *@date: 2023/2/3
*/
//

#ifndef NATIVEIMAGEEDITOR_SKINWHITENSAMPLER_H
#define NATIVEIMAGEEDITOR_SKINWHITENSAMPLER_H

#include "base/BlendSampler.h"

class SkinWhitenSampler: public BlendSampler{
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

#endif //NATIVEIMAGEEDITOR_SKINWHITENSAMPLER_H
