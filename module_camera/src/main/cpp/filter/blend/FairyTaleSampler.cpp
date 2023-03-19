/**
 *@author: baizf
 *@date: 2023/2/1
*/
//

#include "FairyTaleSampler.h"


void FairyTaleSampler::init() {
    BaseFilterSampler::init();
    mFilterTex = new TextureProxy(GL_TEXTURE1);
}

string FairyTaleSampler::getVertexSrc() {
    return "#version 310 es\n"
           "\n"
           "layout(location = 0) in vec3 aPos;\n"
           "layout(location = 1) in vec2 aTexCoords;\n"
           "\n"
           "out vec2 vTexCoords;\n"
           "\n"
           "void main(){\n"
           "    gl_Position = vec4(aPos, 1.0f);\n"
           "    vTexCoords = aTexCoords;\n"
           "}";
}

string FairyTaleSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "uniform sampler2D uSourceTex;\n"
           "uniform sampler2D uFairytaleTex;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "\n"
           "void main(){\n"
           "    lowp vec4 textureColor = texture(uSourceTex, vTexCoords);\n"
           "\n"
           "    mediump float blueColor = textureColor.b * 63.0f;\n"
           "    mediump vec2 quad1;\n"
           "    quad1.y = floor(floor(blueColor) / 8.0f);\n"
           "    quad1.x = floor(blueColor) - (quad1.y * 8.0f);\n"
           "\n"
           "    mediump vec2 quad2;\n"
           "    quad2.y = floor(ceil(blueColor) / 8.0f);\n"
           "    quad2.x = ceil(blueColor) - (quad2.y * 8.0f);\n"
           "\n"
           "    highp vec2 texPos1;\n"
           "    texPos1.x = (quad1.x * 0.125f) + 0.5f/512.0f + ((0.125f - 1.0f/512.0f) * textureColor.r);\n"
           "    texPos1.y = (quad1.y * 0.125f) + 0.5f/512.0f + ((0.125f - 1.0f/512.0f) * textureColor.g);\n"
           "\n"
           "    highp vec2 texPos2;\n"
           "    texPos2.x = (quad2.x * 0.125f) + 0.5f/512.0f + ((0.125f - 1.0/512.0f) * textureColor.r);\n"
           "    texPos2.y = (quad2.y * 0.125f) + 0.5f/512.0f+ ((0.125f - 1.0f/512.0f) * textureColor.g);\n"
           "\n"
           "    lowp vec4 newColor1 = texture(uFairytaleTex, texPos1);\n"
           "    lowp vec4 newColor2 = texture(uFairytaleTex, texPos2);\n"
           "\n"
           "    lowp vec4 newColor = mix(newColor1, newColor2, fract(blueColor));\n"
           "\n"
           "    FragColor = vec4(newColor.rgb, textureColor.w);\n"
           "}";
}

void FairyTaleSampler::drawBefore() {
    string  filterTexName = "uFairytaleTex";
    mFilterTex->mapToFragShader(mProgramProxy->mProgram,filterTexName);
}

void FairyTaleSampler::setFilterImage(vector<NativeImage*> nativeImages) {
    NativeImage *nativeImage = nativeImages[0];
    if(nativeImage){
        if(mFilterTex){
            mFilterTex->setImage2D(nativeImage);
        }
    }
}

void FairyTaleSampler::releaseGL() {
    BaseFilterSampler::releaseGL();
    if(mFilterTex){
        mFilterTex->destroy();
        delete mFilterTex;
        mFilterTex = nullptr;
    }
}

