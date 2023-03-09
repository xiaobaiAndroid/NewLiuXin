/**
 *@author: baizf
 *@date: 2023/2/12
*/
//

#include "SaturationSampler.h"
#include "Logutils.h"

string SaturationSampler::getFragPath() {
    return "glsl/saturation_frag.glsl";
}

string SaturationSampler::getValueName() {
    return "uSaturation";
}

float SaturationSampler::getDefaultValue() {
    return DEFAULT_SATURATION;
}

float SaturationSampler::computeSetValue(float value) {
    if(value <0.0f){
        value = (MIN_SATURATION * abs(value) / 100.0f);
    }else if(value > 0.0f){
        value = MAX_SATURATION * value / 100.0f;
    }else{
        value = DEFAULT_SATURATION;
    }
    return value;
}

float SaturationSampler::computeGetValue(float value) {
    if(value <0.0f){
        value = (MIN_SATURATION * abs(value) / 100.0f);
    }else if(value > 0.0f){
        value = MAX_SATURATION * value / 100.0f;
    }else{
        value = DEFAULT_SATURATION;
    }
    return value;
}
