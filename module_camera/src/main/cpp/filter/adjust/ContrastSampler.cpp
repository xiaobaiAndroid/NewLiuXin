/**
 *@author: baizf
 *@date: 2023/2/11
*/
//

#include "ContrastSampler.h"
#include "Logutils.h"


string ContrastSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "\n"
           "uniform sampler2D uSourceTex;\n"
           "\n"
           "//对比度 0.6~1.4\n"
           "uniform float uContrast;\n"
           "\n"
           "void main(){\n"
           "    vec4 textureColor = texture(uSourceTex, vTexCoords);\n"
           "    FragColor = vec4(((textureColor.rgb - vec3(0.5f)) * uContrast + vec3(0.5f)), textureColor.w);\n"
           "}";
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