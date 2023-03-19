/**
 * 浪漫滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_ROMANCESAMPLER_H
#define NATIVEIMAGEEDITOR_ROMANCESAMPLER_H

#include "base/BlendSampler.h"

class RomanceSampler: public BlendSampler{
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

#endif //NATIVEIMAGEEDITOR_ROMANCESAMPLER_H
