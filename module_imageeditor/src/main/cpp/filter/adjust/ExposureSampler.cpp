/**
 *@author: baizf
 *@date: 2023/2/13
*/
//

#include "ExposureSampler.h"

string ExposureSampler::getFragPath() {
    return "glsl/exposure_frag.glsl";
}

string ExposureSampler::getValueName() {
    return "uExposure";
}

float ExposureSampler::getDefaultValue() {
    return DEFAULT_EXPOSURE;
}

float ExposureSampler::computeSetValue(float value) {
    if(value <0.0f){
        value = (MIN_EXPOSURE * abs(value) / 100.0f);
    }else if(value > 0.0f){
        value = MAX_EXPOSURE * value / 100.0f;
    }else{
        value = DEFAULT_EXPOSURE;
    }
    return value;
}

float ExposureSampler::computeGetValue(float value) {
    if(value <0.0f){
        value = (MIN_EXPOSURE * abs(value) / 100.0f);
    }else if(value > 0.0f){
        value = MAX_EXPOSURE * value / 100.0f;
    }else{
        value = DEFAULT_EXPOSURE;
    }
    return value;
}
