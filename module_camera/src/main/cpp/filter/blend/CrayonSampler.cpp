/**
 *@author: baizf
 *@date: 2023/2/4
*/
//

#include "CrayonSampler.h"


string CrayonSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "\n"
           "uniform sampler2D uSourceTex;\n"
           "\n"
           "uniform vec2 singleStepOffset;\n"
           "uniform float strength;\n"
           "\n"
           "const highp vec3 W = vec3(0.299,0.587,0.114);\n"
           "\n"
           "const mat3 rgb2yiqMatrix = mat3(\n"
           "0.299, 0.587, 0.114,\n"
           "0.596,-0.275,-0.321,\n"
           "0.212,-0.523, 0.311);\n"
           "\n"
           "const mat3 yiq2rgbMatrix = mat3(\n"
           "1.0, 0.956, 0.621,\n"
           "1.0,-0.272,-1.703,\n"
           "1.0,-1.106, 0.0);\n"
           "\n"
           "\n"
           "void main()\n"
           "{\n"
           "    vec4 oralColor = texture(uSourceTex, vTexCoords);\n"
           "\n"
           "    vec3 maxValue = vec3(0.0,0.0,0.0);\n"
           "\n"
           "    for(int i = -2; i<=2; i++)\n"
           "    {\n"
           "        for(int j = -2; j<=2; j++)\n"
           "        {\n"
           "            vec4 tempColor = texture(uSourceTex, vTexCoords+singleStepOffset*vec2(i,j));\n"
           "            maxValue.r = max(maxValue.r,tempColor.r);\n"
           "            maxValue.g = max(maxValue.g,tempColor.g);\n"
           "            maxValue.b = max(maxValue.b,tempColor.b);\n"
           "        }\n"
           "    }\n"
           "\n"
           "    vec3 textureColor = oralColor.rgb / maxValue;\n"
           "\n"
           "    float gray = dot(textureColor, W);\n"
           "    float k = 0.223529;\n"
           "    float alpha = min(gray,k)/k;\n"
           "\n"
           "    textureColor = textureColor * alpha + (1.-alpha)*oralColor.rgb;\n"
           "\n"
           "    vec3 yiqColor = textureColor * rgb2yiqMatrix;\n"
           "\n"
           "    yiqColor.r = max(0.0,min(1.0,pow(gray,strength)));\n"
           "\n"
           "    textureColor = yiqColor * yiq2rgbMatrix;\n"
           "\n"
           "    FragColor = vec4(textureColor, oralColor.w);\n"
           "}\n"
           "";
}

void CrayonSampler::drawBefore() {

    string singleStepOffsetName = "singleStepOffset";
    glm::vec2 singleStepOffset(1.0f / (float)mRenderWidth, 1.0f / (float)mRenderHeight);
    ShaderProxy::setVec2(mProgramProxy->mProgram,singleStepOffsetName,singleStepOffset);

    string strengthName = "strength";
    ShaderProxy::setFloat(mProgramProxy->mProgram, strengthName, 0.5f);
}
