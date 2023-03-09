/**
 *@author: baizf
 *@date: 2023/2/12
*/
//

#include "SharpenSampler.h"

#include "Logutils.h"


string SharpenSampler::getFragPath() {
    return "glsl/sharpen_frag.glsl";
}

string SharpenSampler::getValueName() {
    return "uSharpen";
}

float SharpenSampler::getDefaultValue() {
    return DEFAULT_SHARPEN;
}

float SharpenSampler::computeSetValue(float value) {
    if(value <0.0f){
        value = -(abs(MIN_SHARPEN) * abs(value) / 100.0f);
    }else if(value > 0.0f){
        value = MAX_SHARPEN * value / 100.0f;
    }else{
        value = DEFAULT_SHARPEN;
    }
    return value;
}

float SharpenSampler::computeGetValue(float value) {
    if(value <0.0f){
        value = (MIN_SHARPEN * abs(value) / 100.0f);
    }else if(value > 0.0f){
        value = MAX_SHARPEN * value / 100.0f;
    }else{
        value = DEFAULT_SHARPEN;
    }
    return value;
}
