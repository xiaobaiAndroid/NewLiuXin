/**
 *@author: baizf
 *@date: 2023/2/4
*/
//

#include "SweetsSampler.h"

void SweetsSampler::init() {
    BaseFilterSampler::init();
    mCurveTexture = new TextureProxy(GL_TEXTURE1);
    mMaskTexture = new TextureProxy(GL_TEXTURE2);
}

string SweetsSampler::getFragSrc() {
    return "#version 310 es\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "\n"
           "uniform sampler2D uSourceTex;\n"
           "uniform sampler2D curve;\n"
           "\n"
           "uniform lowp sampler2D samplerMask;\n"
           "uniform lowp int lowPerformance;\n"
           "\n"
           "uniform float texelWidthOffset ;\n"
           "uniform float texelHeightOffset;\n"
           "\n"
           "vec4 sharpen(sampler2D sampler)\n"
           "{\n"
           "    vec4 color = texture(sampler, vTexCoords) * 2.;\n"
           "\n"
           "    color -= texture(sampler, vTexCoords-vec2(texelWidthOffset, 0. )) * 0.25;\n"
           "    color -= texture(sampler, vTexCoords+vec2(texelWidthOffset, 0. )) * 0.25;\n"
           "    color -= texture(sampler, vTexCoords-vec2(0., texelHeightOffset)) * 0.25;\n"
           "    color -= texture(sampler, vTexCoords+vec2(0., texelHeightOffset)) * 0.25;\n"
           "\n"
           "    return color;\n"
           "}\n"
           "\n"
           "vec4 gaussianBlur(sampler2D sampler)\n"
           "{\n"
           "    lowp float strength = 1.;\n"
           "\n"
           "    vec4 color = vec4(0.);\n"
           "    vec2 step  = vec2(0.);\n"
           "\n"
           "    color += texture(sampler,vTexCoords)* 0.0443 ;\n"
           "\n"
           "    step.x = 1.49583 * texelWidthOffset  * strength;\n"
           "    step.y = 1.49583 * texelHeightOffset * strength;\n"
           "    color += texture(sampler,vTexCoords+vec2(step.x, 0.)) * 0.04321;\n"
           "    color += texture(sampler,vTexCoords-vec2(step.x, 0.)) * 0.04321;\n"
           "    color += texture(sampler,vTexCoords+vec2(0., step.y)) * 0.04321;\n"
           "    color += texture(sampler,vTexCoords-vec2(0., step.y)) * 0.04321;\n"
           "\n"
           "    step.x = 2.4719250988753685 * texelWidthOffset  * strength;\n"
           "    step.y = 2.4719250988753685 * texelHeightOffset * strength;\n"
           "    color += texture(sampler,vTexCoords+step) * 0.041795;\n"
           "    color += texture(sampler,vTexCoords-step) * 0.041795;\n"
           "    color += texture(sampler,vTexCoords+vec2(-step.x, step.y)) * 0.041795;\n"
           "    color += texture(sampler,vTexCoords+vec2( step.x,-step.y)) * 0.041795;\n"
           "\n"
           "    step.x = 5.49583 * texelWidthOffset  * strength;\n"
           "    step.y = 5.49583 * texelHeightOffset * strength;\n"
           "    color += texture(sampler,vTexCoords+vec2(step.x, 0.)) * 0.040425;\n"
           "    color += texture(sampler,vTexCoords-vec2(step.x, 0.)) * 0.040425;\n"
           "    color += texture(sampler,vTexCoords+vec2(0., step.y)) * 0.040425;\n"
           "    color += texture(sampler,vTexCoords-vec2(0., step.y)) * 0.040425;\n"
           "\n"
           "    step.x = 5.300352223621558 * texelWidthOffset  * strength;\n"
           "    step.y = 5.300352223621558 * texelHeightOffset * strength;\n"
           "    color += texture(sampler,vTexCoords+step) * 0.0391;\n"
           "    color += texture(sampler,vTexCoords-step) * 0.0391;\n"
           "    color += texture(sampler,vTexCoords+vec2(-step.x, step.y)) * 0.0391;\n"
           "    color += texture(sampler,vTexCoords+vec2( step.x,-step.y)) * 0.0391;\n"
           "\n"
           "    step.x = 9.49583 * texelWidthOffset  * strength;\n"
           "    step.y = 9.49583 * texelHeightOffset * strength;\n"
           "    color += texture(sampler,vTexCoords+vec2(step.x, 0.)) * 0.037815;\n"
           "    color += texture(sampler,vTexCoords-vec2(step.x, 0.)) * 0.037815;\n"
           "    color += texture(sampler,vTexCoords+vec2(0., step.y)) * 0.037815;\n"
           "    color += texture(sampler,vTexCoords-vec2(0., step.y)) * 0.037815;\n"
           "\n"
           "    step.x = 8.128779348367749 * texelWidthOffset  * strength;\n"
           "    step.y = 8.128779348367749 * texelHeightOffset * strength;\n"
           "    color += texture(sampler,vTexCoords+step) * 0.03658;\n"
           "    color += texture(sampler,vTexCoords-step) * 0.03658;\n"
           "    color += texture(sampler,vTexCoords+vec2(-step.x, step.y)) * 0.03658;\n"
           "    color += texture(sampler,vTexCoords+vec2( step.x,-step.y)) * 0.03658;\n"
           "\n"
           "    return color;\n"
           "}\n"
           "\n"
           "vec4 level(vec4 color, sampler2D sampler)\n"
           "{\n"
           "    color.r = texture(sampler, vec2(color.r, 0.)).r;\n"
           "    color.g = texture(sampler, vec2(color.g, 0.)).g;\n"
           "    color.b = texture(sampler, vec2(color.b, 0.)).b;\n"
           "\n"
           "    return color;\n"
           "}\n"
           "\n"
           "vec4 normal(vec4 c1, vec4 c2, float alpha)\n"
           "{\n"
           "    return (c2-c1) * alpha + c1;\n"
           "}\n"
           "\n"
           "vec4 lighten(vec4 c1, vec4 c2)\n"
           "{\n"
           "    return max(c1,c2);\n"
           "}\n"
           "\n"
           "vec4 overlay(vec4 c1, vec4 c2)\n"
           "{\n"
           "    vec4 r1 = vec4(0.,0.,0.,1.);\n"
           "    r1.r = c1.r < 0.5 ? 2.0*c1.r*c2.r : 1.0 - 2.0*(1.0-c1.r)*(1.0-c2.r);\n"
           "    r1.g = c1.g < 0.5 ? 2.0*c1.g*c2.g : 1.0 - 2.0*(1.0-c1.g)*(1.0-c2.g);\n"
           "    r1.b = c1.b < 0.5 ? 2.0*c1.b*c2.b : 1.0 - 2.0*(1.0-c1.b)*(1.0-c2.b);\n"
           "\n"
           "    return r1;\n"
           "}\n"
           "\n"
           "vec3 lerp (vec3 x, vec3 y, float s)\n"
           "{\n"
           "    return x+s*(y-x);\n"
           "}\n"
           "\n"
           "vec4 adjust (vec4 color, float brightness, float contrast, float saturation)\n"
           "{\n"
           "    vec3 averageLuminance = vec3(0.5);\n"
           "    vec3 brightedColor    = color.rgb * (brightness+1.);\n"
           "    vec3 intensity        = vec3(dot(brightedColor, vec3(0.299, 0.587, 0.114)));\n"
           "    vec3 saturatedColor   = lerp(intensity, brightedColor, saturation+1.);\n"
           "    vec3 contrastedColor  = lerp(averageLuminance, saturatedColor, contrast+1.);\n"
           "\n"
           "    return vec4(contrastedColor,1.);\n"
           "}\n"
           "\n"
           "vec4 vibrance(vec4 color, float strength)\n"
           "{\n"
           "    float luminance = (color.r+color.g+color.b)/3.;\n"
           "    //dot(color.rgb, vec3(0.299,0.587,0.114));\n"
           "    float maximum   = max(color.r, max(color.g, color.b));\n"
           "    float amount    = (maximum-luminance)*-strength;\n"
           "\n"
           "    return vec4(color.rgb * (1.-amount) + maximum*amount, 1.);\n"
           "}\n"
           "\n"
           "void main()\n"
           "{\n"
           "    vec4 c1;\n"
           "    vec4 c2;\n"
           "    if (lowPerformance == 1)\n"
           "    {\n"
           "        c1 = texture(uSourceTex, vTexCoords);\n"
           "        c2 = texture(uSourceTex, vTexCoords);\n"
           "    }\n"
           "    else\n"
           "    {\n"
           "        c1 = sharpen(uSourceTex);\n"
           "        c2 = normal(c1, gaussianBlur(uSourceTex), 0.8); // radius = 13. sharpen?? gaussian blur? ???? ??, ?? blending?? ??\n"
           "    }\n"
           "    vec4 c3 = normal(c1, lighten(c1,c2), 0.6); // lighten (0.6)\n"
           "    c3 = adjust(c3, 0.12, 0., 0.05); // brightness = 12, saturation = 0.5;\n"
           "    c3 = vibrance(level(c3, curve), 0.5); // vibrance = 0.5;\n"
           "    c3 = normal(c3, overlay(c3, vec4(0.)), 1.-texture(samplerMask, vTexCoords).r); // vignetting\n"
           "\n"
           "    FragColor = c3;\n"
           "}";
}

void SweetsSampler::setFilterImage(vector<NativeImage *> nativeImages) {
    unsigned char arrayOfByte[1024];
    int arrayOfInt[] = { 0, 1, 2, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 19, 20, 21, 22, 23, 24, 24, 25, 26, 27, 28, 29, 30, 30, 31, 32, 33, 34, 35, 36, 37, 38, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 79, 80, 81, 82, 83, 84, 86, 87, 88, 89, 90, 92, 93, 94, 95, 96, 98, 99, 100, 101, 103, 104, 105, 106, 108, 109, 110, 111, 113, 114, 115, 116, 118, 119, 120, 121, 123, 124, 125, 126, 128, 129, 130, 132, 133, 134, 135, 137, 138, 139, 140, 142, 143, 144, 145, 147, 148, 149, 150, 152, 153, 154, 155, 157, 158, 159, 160, 161, 163, 164, 165, 166, 167, 169, 170, 171, 172, 173, 174, 176, 177, 178, 179, 180, 181, 182, 183, 184, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 209, 210, 211, 212, 213, 214, 215, 216, 217, 217, 218, 219, 220, 221, 222, 222, 223, 224, 225, 226, 227, 227, 228, 229, 230, 230, 231, 232, 233, 234, 234, 235, 236, 237, 237, 238, 239, 240, 240, 241, 242, 243, 243, 244, 245, 246, 246, 247, 248, 248, 249, 250, 251, 251, 252, 253, 254, 254, 255 };
    for (int i = 0; i < 256; i++){
        arrayOfByte[(i * 4)] = ((unsigned char)arrayOfInt[i]);
        arrayOfByte[(1 + i * 4)] = ((unsigned char)arrayOfInt[i]);
        arrayOfByte[(2 + i * 4)] = ((unsigned char)arrayOfInt[i]);
        arrayOfByte[(3 + i * 4)] = ((unsigned char)i);
    }

    NativeImage curveImage;
    curveImage.width = 256;
    curveImage.height = 1;
    curveImage.format = AndroidBitmapFormat::ANDROID_BITMAP_FORMAT_RGBA_8888;
    curveImage.data = arrayOfByte;
    if(mCurveTexture){
        mCurveTexture->setImage2D(&curveImage);
    }
    if(mMaskTexture){
        if(!nativeImages.empty()){
            mMaskTexture->setImage2D(nativeImages[0]);
        }
    }
}

void SweetsSampler::drawBefore() {
    if(mCurveTexture){
        string curveName = "curve";
        mCurveTexture->mapToFragShader(mProgramProxy->mProgram, curveName);
    }
    if(mMaskTexture){
        string maskName = "samplerMask";
        mMaskTexture->mapToFragShader(mProgramProxy->mProgram, maskName);
    }
    ShaderProxy::setInt(mProgramProxy->mProgram, "lowPerformance", 1);

    ShaderProxy::setFloat(mProgramProxy->mProgram,"texelWidthOffset",(1.0f / (float)mRenderWidth));
    ShaderProxy::setFloat(mProgramProxy->mProgram,"texelHeightOffset",(1.0f / (float)mRenderHeight));
}

void SweetsSampler::releaseGL() {
    BaseFilterSampler::releaseGL();
    if(mCurveTexture){
        mCurveTexture->destroy();
        delete mCurveTexture;
        mCurveTexture = nullptr;
    }
    if(mMaskTexture){
        mMaskTexture->destroy();
        delete mMaskTexture;
        mMaskTexture = nullptr;
    }
}