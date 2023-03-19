/**
 * 甜品滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_SWEETSSAMPLER_H
#define NATIVEIMAGEEDITOR_SWEETSSAMPLER_H

#include "base/BlendSampler.h"

class SweetsSampler: public BlendSampler{
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

#endif //NATIVEIMAGEEDITOR_SWEETSSAMPLER_H
