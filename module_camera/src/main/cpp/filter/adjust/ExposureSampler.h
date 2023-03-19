/**
 * 曝光
 *@author: baizf
 *@date: 2023/2/13
*/
//

#ifndef NATIVEIMAGEEDITOR_EXPOSURESAMPLER_H
#define NATIVEIMAGEEDITOR_EXPOSURESAMPLER_H

#include "base/AdjustSampler.h"

//默认饱和度
#define DEFAULT_EXPOSURE 0.0f
#define MAX_EXPOSURE 1.0f
#define MIN_EXPOSURE -1.0f

class ExposureSampler: public AdjustSampler{
protected:

    float getDefaultValue() override;
    string getFragSrc() override;
    string getValueName() override;
    float computeSetValue(float value) override;
    float computeGetValue(float value) override;

};
#endif //NATIVEIMAGEEDITOR_EXPOSURESAMPLER_H
