/**
 * 原图
 *@author: baizf
 *@date: 2023/2/2
*/

#ifndef NATIVEIMAGEEDITOR_ORIGINALSAMPLER_H
#define NATIVEIMAGEEDITOR_ORIGINALSAMPLER_H

#include "base/BlendSampler.h"

class OriginalSampler: public BlendSampler{
private:
    void drawBefore() override;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
};

#endif //NATIVEIMAGEEDITOR_ORIGINALSAMPLER_H
