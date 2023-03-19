/**
 *@author: baizf
 *@date: 2023/2/13
*/
//

#include "ExposureSampler.h"

string ExposureSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "uniform sampler2D uSourceTex;\n"
           "\n"
           "//-1.0 ~ 1.0  0.0输出原图\n"
           "uniform float uExposure;\n"
           "\n"
           "void main(){\n"
           "    vec4 textureColor = texture(uSourceTex, vTexCoords);\n"
           "    FragColor = vec4(textureColor.rgb * pow(2.0f, uExposure), textureColor.w);\n"
           "}";
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
