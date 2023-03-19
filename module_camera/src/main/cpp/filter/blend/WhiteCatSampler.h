/**
 * 白猫滤镜
 *@author: baizf
 *@date: 2023/2/3
*/
//

#ifndef NATIVEIMAGEEDITOR_WHITECATSAMPLER_H
#define NATIVEIMAGEEDITOR_WHITECATSAMPLER_H

#include "base/BlendSampler.h"

class WhiteCatSampler: public BlendSampler{
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

#endif //NATIVEIMAGEEDITOR_WHITECATSAMPLER_H
