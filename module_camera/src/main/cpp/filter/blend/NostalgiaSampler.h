/**
 * 怀旧滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_NOSTALGIASAMPLER_H
#define NATIVEIMAGEEDITOR_NOSTALGIASAMPLER_H

#include "base/BlendSampler.h"

class NostalgiaSampler: public BlendSampler{
private:
    TextureProxy *mCurveTexture = nullptr;
    TextureProxy *mCurve2Texture = nullptr;

    void drawBefore() override;
protected:
    string getFragSrc() override;
public:
    void init() override;
    void setFilterImage(vector<NativeImage*> nativeImages) override;
    void releaseGL() override;
};

#endif //NATIVEIMAGEEDITOR_NOSTALGIASAMPLER_H
