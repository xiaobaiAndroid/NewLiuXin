/**
 * 饱和度
 *@author: baizf
 *@date: 2023/2/12
*/
//

#ifndef NATIVEIMAGEEDITOR_SATURATIONSAMPLER_H
#define NATIVEIMAGEEDITOR_SATURATIONSAMPLER_H

#include "base/AdjustSampler.h"

//默认饱和度
#define DEFAULT_SATURATION 1.0f
#define MAX_SATURATION 2.0f
#define MIN_SATURATION 0.0f

class SaturationSampler: public AdjustSampler{
protected:

    float getDefaultValue() override;
    string getFragPath() override;
    string getValueName() override;
    float computeSetValue(float value) override;
    float computeGetValue(float value) override;

};
#endif //NATIVEIMAGEEDITOR_SATURATIONSAMPLER_H
