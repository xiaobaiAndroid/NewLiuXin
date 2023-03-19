/**
 *@author: baizf
 *@date: 2023/2/4
*/
//

#include "SketchSampler.h"

string SketchSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "\n"
           "uniform sampler2D uSourceTex;\n"
           "uniform vec2 singleStepOffset;\n"
           "uniform float strength;\n"
           "\n"
           "const highp vec3 W = vec3(0.299,0.587,0.114);\n"
           "\n"
           "\n"
           "void main()\n"
           "{\n"
           "    float threshold = 0.0;\n"
           "    //pic1\n"
           "    vec4 oralColor = texture(uSourceTex, vTexCoords);\n"
           "\n"
           "    //pic2\n"
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
           "            threshold += dot(tempColor.rgb, W);\n"
           "        }\n"
           "    }\n"
           "    //pic3\n"
           "    float gray1 = dot(oralColor.rgb, W);\n"
           "\n"
           "    //pic4\n"
           "    float gray2 = dot(maxValue, W);\n"
           "\n"
           "    //pic5\n"
           "    float contour = gray1 / gray2;\n"
           "\n"
           "    threshold = threshold / 25.;\n"
           "    float alpha = max(1.0,gray1>threshold?1.0:(gray1/threshold));\n"
           "\n"
           "    float result = contour * alpha + (1.0-alpha)*gray1;\n"
           "\n"
           "    FragColor = vec4(vec3(result,result,result), oralColor.w);\n"
           "}";
}

void SketchSampler::drawBefore() {
    string singleStepOffsetName = "singleStepOffset";
    glm::vec2 singleStepOffset(1.0f / (float)mRenderWidth, 1.0f / (float)mRenderHeight);
    ShaderProxy::setVec2(mProgramProxy->mProgram,singleStepOffsetName,singleStepOffset);

    string strengthName = "strength";
    ShaderProxy::setFloat(mProgramProxy->mProgram, strengthName, 0.5f);
}
