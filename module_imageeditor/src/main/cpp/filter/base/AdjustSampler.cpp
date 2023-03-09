/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "AdjustSampler.h"
#include "Logutils.h"


void AdjustSampler::init(JNIEnv *env, jobject *assetsManager) {
    string vertPath = "glsl/common_vert.glsl";
    string fragPath = getFragPath();
    initCommon(env,assetsManager,vertPath,fragPath);

    mAdjustValue = getDefaultValue();
    LOGI("default----mAdjustValue=%f", mAdjustValue);
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