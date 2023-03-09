/**
 *@author: baizf
 *@date: 2023/2/11
*/
//

#include "ContrastSampler.h"
#include "Logutils.h"


string ContrastSampler::getFragPath() {
    return "glsl/contrast_frag.glsl";
}

string ContrastSampler::getValueName() {
    return "uContrast";
}

float ContrastSampler::getDefaultValue() {
    return DEFAULT_CONTRAST;
}

float ContrastSampler::computeSetValue(float value) {
    if(value <0.0f){
        value = (MIN_CONTRAST * abs(value) / 100.0f);
    }else if(value > 0.0f){
        value = MAX_CONTRAST * value / 100.0f;
    }else{
        value = DEFAULT_CONTRAST;
    }
    return value;
}

float ContrastSampler::computeGetValue(float value) {
    if(value <0.0f){
        value = abs(value) * 100.0f / MIN_CONTRAST;
    }else if(value > 0.0f){
        value = value * 100.0f / MAX_CONTRAST;
    }else{
        value = DEFAULT_CONTRAST;
    }
    return value;
}