/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "AdjustSampler.h"
#include "Logutils.h"



void AdjustSampler::init() {
    BaseFilterSampler::init();
    mAdjustValue = getDefaultValue();
}

void AdjustSampler::drawBefore() {
    ShaderProxy::setFloat(mProgramProxy->mProgram, getValueName(), mAdjustValue);
}

void AdjustSampler::setValue(float value) {
    LOGI("before----mAdjustValue=%f", value);
    mAdjustValue = computeSetValue(value);
    LOGI("after-----mAdjustValue=%f", mAdjustValue);
}

float AdjustSampler::getValue() {
    return computeGetValue(mAdjustValue);
}