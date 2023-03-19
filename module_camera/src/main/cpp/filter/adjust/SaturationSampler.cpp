/**
 *@author: baizf
 *@date: 2023/2/12
*/
//

#include "SaturationSampler.h"
#include "Logutils.h"

string SaturationSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "\n"
           "uniform sampler2D uSourceTex;\n"
           "\n"
           "//范围：0.0~2.0  1.0输出原图， 0.0输出灰度图 2.0饱和度最大\n"
           "uniform float uSaturation;\n"
           "\n"
           "const vec3 luminanceWeighting = vec3(0.2125f, 0.7154f, 0.0721f);\n"
           "\n"
           "void main(){\n"
           "    vec4 textureColor = texture(uSourceTex, vTexCoords);\n"
           "    float luminance = dot(textureColor.rgb, luminanceWeighting);\n"
           "    vec3 greyScaleColor = vec3(luminance);\n"
           "\n"
           "    FragColor = vec4(mix(greyScaleColor,textureColor.rgb, uSaturation),textureColor.w);\n"
           "}";
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
