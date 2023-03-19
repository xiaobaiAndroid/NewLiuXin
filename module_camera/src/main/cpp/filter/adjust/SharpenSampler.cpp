/**
 *@author: baizf
 *@date: 2023/2/12
*/
//

#include "SharpenSampler.h"

#include "Logutils.h"


string SharpenSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "uniform sampler2D uSourceTex;\n"
           "\n"
           "//范围在0.0~2.0, 1.0最佳锐化效果，0.0为原图\n"
           "uniform float uSharpen;\n"
           "\n"
           "void main(){\n"
           "    //卷积内核中各个元素对应像素相对于待处理像素的纹理坐标偏移量\n"
           "    vec2 offset0 = vec2(-1.0f, -1.0f); vec2 offset1 = vec2(0.0f, 1.0f); vec2 offset2 = vec2(1.0f, 1.0f);\n"
           "    vec2 offset3 = vec2(-1.0f, 0.0f);  vec2 centerPosition = vec2(0.0f, 0.0f); vec2 offset4 = vec2(1.0f, 0.0f);\n"
           "    vec2 offset5 = vec2(-1.0, -1.0f);  vec2 offset6 = vec2(0.0f, -1.0f); vec2 offset7 = vec2(1.0f, -1.0f);\n"
           "\n"
           "    //卷积内核的各个值\n"
           "    float kernelValue0 = 0.0f; float kernelValue1 = -uSharpen; float kernelValue2 = 0.0f;\n"
           "    float kernelValue3 = -uSharpen; float centerValue = 4.0f*uSharpen + 1.0f; float kernelValue4 = -uSharpen;\n"
           "    float kernelValue5 = 0.0f; float kernelValue6 = -uSharpen; float kernelValue7 = 0.0f;\n"
           "\n"
           "    vec4 temp0 = texture(uSourceTex, vTexCoords.st + offset0.xy/512.0f);\n"
           "    vec4 temp1 = texture(uSourceTex, vTexCoords.st + offset1.xy/512.0f);\n"
           "    vec4 temp2 = texture(uSourceTex, vTexCoords.st + offset2.xy/512.0f);\n"
           "    vec4 temp3 = texture(uSourceTex, vTexCoords.st + offset3.xy/512.0f);\n"
           "    vec4 tempCenter = texture(uSourceTex, vTexCoords.st + centerPosition.xy/512.0f);\n"
           "    vec4 temp4 = texture(uSourceTex, vTexCoords.st + offset4.xy/512.0f);\n"
           "    vec4 temp5 = texture(uSourceTex, vTexCoords.st + offset5.xy/512.0f);\n"
           "    vec4 temp6 = texture(uSourceTex, vTexCoords.st + offset6.xy/512.0f);\n"
           "    vec4 temp7 = texture(uSourceTex, vTexCoords.st + offset7.xy/512.0f);\n"
           "\n"
           "    //最终的颜色和\n"
           "    vec4 sum = kernelValue0*temp0 + kernelValue1*temp1 + kernelValue2*temp2 +\n"
           "                kernelValue3*temp3 + centerValue*tempCenter + kernelValue4*temp4 +\n"
           "                kernelValue5*temp5 + kernelValue6*temp6 + kernelValue7*temp7;\n"
           "\n"
           "\n"
           "    const float scaleFactor = 1.0;//给出最终求和时的加权因子(为调整亮度)\n"
           "    FragColor = sum * scaleFactor;\n"
           "\n"
           "}";
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
