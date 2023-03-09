/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef NATIVEIMAGEEDITOR_ADJUSTSAMPLER_H
#define NATIVEIMAGEEDITOR_ADJUSTSAMPLER_H

#include "BaseFilterSampler.h"

class AdjustSampler: public BaseFilterSampler{
protected:
    float mAdjustValue = 0.0f;
    void drawBefore() override;

    virtual string getValueName() = 0;
    virtual float getDefaultValue() = 0;
    virtual string getFragPath() = 0;
    virtual float computeSetValue(float value) = 0;
    virtual float computeGetValue(float value) = 0 ;
public:
    void init(JNIEnv *env, jobject *assetsManager) override;
    void setValue(float value);
    float getValue();

};

#endif //NATIVEIMAGEEDITOR_ADJUSTSAMPLER_H
