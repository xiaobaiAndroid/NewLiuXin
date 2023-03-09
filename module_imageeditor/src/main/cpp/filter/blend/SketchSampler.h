/**
 * 素描滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_SKETCHSAMPLER_H
#define NATIVEIMAGEEDITOR_SKETCHSAMPLER_H

#include "base/BlendSampler.h"

class SketchSampler: public BlendSampler{
private:
    void drawBefore() override;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
};

#endif //NATIVEIMAGEEDITOR_SKETCHSAMPLER_H
