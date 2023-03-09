/**
 * 锐化
 *@author: baizf
 *@date: 2023/2/12
*/
//

#ifndef NATIVEIMAGEEDITOR_SHARPENSAMPLER_H
#define NATIVEIMAGEEDITOR_SHARPENSAMPLER_H

#include "base/AdjustSampler.h"

//默认锐化
#define DEFAULT_SHARPEN 0.0f
#define MAX_SHARPEN 2.0f
#define MIN_SHARPEN -2.0f

class SharpenSampler: public AdjustSampler{
protected:

    float getDefaultValue() override;
    string getFragPath() override;
    string getValueName() override;
    float computeSetValue(float value) override;
    float computeGetValue(float value) override;

};
#endif //NATIVEIMAGEEDITOR_SHARPENSAMPLER_H
