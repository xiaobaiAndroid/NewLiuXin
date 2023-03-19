/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "BrightnessSampler.h"
#include "Logutils.h"
#include <iomanip>

string BrightnessSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "\n"
           "uniform sampler2D uSourceTex;\n"
           "\n"
           "//亮度 ~0.5f~0.5f\n"
           "uniform float uBrightness;\n"
           "\n"
           "void main(){\n"
           "    vec4 textureColor = texture(uSourceTex, vTexCoords);\n"
           "    FragColor = vec4(textureColor.rgb + vec3(uBrightness), textureColor.w);\n"
           "}";
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