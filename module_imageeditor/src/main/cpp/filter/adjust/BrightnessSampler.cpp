/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "BrightnessSampler.h"
#include "Logutils.h"
#include <iomanip>

string BrightnessSampler::getFragPath() {
    return "glsl/brightness_frag.glsl";
}

string BrightnessSampler::getValueName() {
    return "uBrightness";
}

float BrightnessSampler::getDefaultValue() {
    return DEFAULT_BRIGHTNESS;
}

float BrightnessSampler::computeSetValue(float value) {
    if(value <0.0f){
        value = -(MIN_BRIGHTNESS * value / 100.0f);
    }else if(value > 0.0f){
        value = MAX_BRIGHTNESS * value / 100.0f;
    }else{
        value = DEFAULT_BRIGHTNESS;
    }
    return value;
}

float BrightnessSampler::computeGetValue(float value) {
    if(value <0.0f){
        value = -(value * 100.0f / MIN_BRIGHTNESS);
    }else if(value > 0.0f){
        value = value * 100.0f / MAX_BRIGHTNESS;
    }else{
        value = DEFAULT_BRIGHTNESS;
    }
    return value;
}