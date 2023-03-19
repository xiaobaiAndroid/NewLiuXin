/**
 *@author: baizf
 *@date: 2023/2/3
*/
//

#include "HealthySampler.h"

void HealthySampler::init() {
    BaseFilterSampler::init();
    mCurveTexture = new TextureProxy(GL_TEXTURE1);
    mMaskTexture = new TextureProxy(GL_TEXTURE2);
}

string HealthySampler::getFragSrc() {
    return "#version 310 es\n"
           "\n"
           "precision mediump float;\n"
           "\n"
           "out vec4 FragColor;\n"
           "\n"
           "in vec2 vTexCoords;\n"
           "\n"
           "uniform sampler2D uSourceTex;\n"
           "uniform sampler2D curve;\n"
           "uniform sampler2D mask;\n"
           "\n"
           "uniform float texelWidthOffset ;\n"
           "\n"
           "uniform float texelHeightOffset;\n"
           "\n"
           "vec4 level0c(vec4 color, sampler2D sampler)\n"
           "{\n"
           "    color.r = texture(sampler, vec2(color.r, 0.0)).r;\n"
           "    color.g = texture(sampler, vec2(color.g, 0.0)).r;\n"
           "    color.b = texture(sampler, vec2(color.b, 0.0)).r;\n"
           "    return color;\n"
           "}\n"
           "\n"
           "vec4 level1c(vec4 color, sampler2D sampler)\n"
           "{\n"
           "    color.r = texture(sampler, vec2(color.r, 0.0)).g;\n"
           "    color.g = texture(sampler, vec2(color.g, 0.0)).g;\n"
           "    color.b = texture(sampler, vec2(color.b, 0.0)).g;\n"
           "    return color;\n"
           "}\n"
           "\n"
           "vec4 level2c(vec4 color, sampler2D sampler)\n"
           "{\n"
           "    color.r = texture(sampler, vec2(color.r,0.0)).b;\n"
           "    color.g = texture(sampler, vec2(color.g,0.0)).b;\n"
           "    color.b = texture(sampler, vec2(color.b,0.0)).b;\n"
           "    return color;\n"
           "}\n"
           "\n"
           "vec3 rgb2hsv(vec3 c)\n"
           "{\n"
           "    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n"
           "    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));\n"
           "    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));\n"
           "\n"
           "    float d = q.x - min(q.w, q.y);\n"
           "    float e = 1.0e-10;\n"
           "    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);\n"
           "}\n"
           "\n"
           "vec3 hsv2rgb(vec3 c)\n"
           "{\n"
           "    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);\n"
           "    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);\n"
           "    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);\n"
           "}\n"
           "\n"
           "vec4 normal(vec4 c1, vec4 c2, float alpha)\n"
           "{\n"
           "    return (c2-c1) * alpha + c1;\n"
           "}\n"
           "\n"
           "vec4 multiply(vec4 c1, vec4 c2)\n"
           "{\n"
           "    return c1 * c2 * 1.01;\n"
           "}\n"
           "\n"
           "vec4 overlay(vec4 c1, vec4 c2)\n"
           "{\n"
           "    vec4 color = vec4(0.,0.,0.,1.);\n"
           "\n"
           "    color.r = c1.r < 0.5 ? 2.0*c1.r*c2.r : 1.0 - 2.0*(1.0-c1.r)*(1.0-c2.r);\n"
           "    color.g = c1.g < 0.5 ? 2.0*c1.g*c2.g : 1.0 - 2.0*(1.0-c1.g)*(1.0-c2.g);\n"
           "    color.b = c1.b < 0.5 ? 2.0*c1.b*c2.b : 1.0 - 2.0*(1.0-c1.b)*(1.0-c2.b);\n"
           "\n"
           "    return color;\n"
           "}\n"
           "\n"
           "vec4 screen(vec4 c1, vec4 c2)\n"
           "{\n"
           "    return vec4(1.) - ((vec4(1.) - c1) * (vec4(1.) - c2));\n"
           "}\n"
           "\n"
           "void main()\n"
           "{\n"
           "    // iOS ImageLiveFilter adjustment\n"
           "    // begin\n"
           "    vec4 textureColor;\n"
           "\n"
           "    vec4 t0 = texture(mask, vec2(vTexCoords.x, vTexCoords.y));\n"
           "\n"
           "    // naver skin\n"
           "    vec4 c2 = texture(uSourceTex, vTexCoords);\n"
           "    vec4 c5 = c2;\n"
           "\n"
           "    // healthy\n"
           "    vec3 hsv = rgb2hsv(c5.rgb);\n"
           "    lowp float h = hsv.x;\n"
           "    lowp float s = hsv.y;\n"
           "    lowp float v = hsv.z;\n"
           "\n"
           "    lowp float cF = 0.;\n"
           "    // color strength\n"
           "    lowp float cG = 0.;\n"
           "    // color gap;\n"
           "    lowp float sF = 0.06;\n"
           "    // saturation strength;\n"
           "\n"
           "    if(h >= 0.125 && h <= 0.208)\n"
           "    {\n"
           "        // 45 to 75\n"
           "        s = s - (s * sF);\n"
           "    }\n"
           "    else if (h >= 0.208 && h < 0.292)\n"
           "    {\n"
           "        // 75 to 105\n"
           "        cG = abs(h - 0.208);\n"
           "        cF = (cG / 0.0833);\n"
           "        s = s - (s * sF * cF);\n"
           "    }\n"
           "    else if (h > 0.042 && h <=  0.125)\n"
           "    {\n"
           "        // 15 to 45\n"
           "        cG = abs(h - 0.125);\n"
           "        cF = (cG / 0.0833);\n"
           "        s = s - (s * sF * cF);\n"
           "    }\n"
           "    hsv.y = s;\n"
           "\n"
           "    vec4 c6 = vec4(hsv2rgb(hsv),1.);\n"
           "\n"
           "    c6 = normal(c6, screen  (c6, c6), 0.275); // screen 70./255.\n"
           "    c6 = normal(c6, overlay (c6, vec4(1., 0.61176, 0.25098, 1.)), 0.04); // overlay 10./255.\n"
           "\n"
           "    c6 = normal(c6, multiply(c6, t0), 0.262); // multiply 67./255.\n"
           "\n"
           "    c6 = level1c(level0c(c6,curve),curve);\n"
           "\n"
           "    FragColor = c6;\n"
           "    // end\n"
           "}";
}

void HealthySampler::setFilterImage(vector<NativeImage *> nativeImages) {
    unsigned char arrayOfByte[1024];
    int arrayOfInt1[] = { 95, 95, 96, 97, 97, 98, 99, 99, 100, 101, 101, 102, 103, 104, 104, 105, 106, 106, 107, 108, 108, 109, 110, 111, 111, 112, 113, 113, 114, 115, 115, 116, 117, 117, 118, 119, 120, 120, 121, 122, 122, 123, 124, 124, 125, 126, 127, 127, 128, 129, 129, 130, 131, 131, 132, 133, 133, 134, 135, 136, 136, 137, 138, 138, 139, 140, 140, 141, 142, 143, 143, 144, 145, 145, 146, 147, 147, 148, 149, 149, 150, 151, 152, 152, 153, 154, 154, 155, 156, 156, 157, 158, 159, 159, 160, 161, 161, 162, 163, 163, 164, 165, 165, 166, 167, 168, 168, 169, 170, 170, 171, 172, 172, 173, 174, 175, 175, 176, 177, 177, 178, 179, 179, 180, 181, 181, 182, 183, 184, 184, 185, 186, 186, 187, 188, 188, 189, 190, 191, 191, 192, 193, 193, 194, 195, 195, 196, 197, 197, 198, 199, 200, 200, 201, 202, 202, 203, 204, 204, 205, 206, 207, 207, 208, 209, 209, 210, 211, 211, 212, 213, 213, 214, 215, 216, 216, 217, 218, 218, 219, 220, 220, 221, 222, 223, 223, 224, 225, 225, 226, 227, 227, 228, 229, 229, 230, 231, 232, 232, 233, 234, 234, 235, 236, 236, 237, 238, 239, 239, 240, 241, 241, 242, 243, 243, 244, 245, 245, 246, 247, 248, 248, 249, 250, 250, 251, 252, 252, 253, 254, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255 };
    int arrayOfInt2[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 5, 7, 8, 9, 12, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 24, 25, 26, 27, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 60, 61, 62, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 168, 169, 170, 171, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 189, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 204, 205, 206, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 219, 220, 221, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 234, 235, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 249, 249, 250, 251, 252, 253, 254, 255, 255, 255, 255, 255, 255, 255, 255, 255 };
    int arrayOfInt3[] = { 0, 1, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 95, 96, 97, 98, 99, 100, 101, 102, 103, 105, 106, 107, 108, 109, 110, 111, 112, 114, 115, 116, 117, 118, 119, 120, 121, 122, 124, 125, 126, 127, 128, 129, 130, 131, 132, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 249, 250, 251, 252, 253, 254, 255 };
    for (int i = 0; i < 256; i++)
    {
        arrayOfByte[(i * 4)] = ((unsigned char)arrayOfInt3[i]);
        arrayOfByte[(1 + i * 4)] = ((unsigned char)arrayOfInt2[i]);
        arrayOfByte[(2 + i * 4)] = ((unsigned char)arrayOfInt1[i]);
        arrayOfByte[(3 + i * 4)] = -1;
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
        mMaskTexture->setImage2D(nativeImages[0]);
    }

}

void HealthySampler::drawBefore() {
    if(mCurveTexture){
        string curveName = "curve";
        mCurveTexture->mapToFragShader(mProgramProxy->mProgram, curveName);
    }
    if(mMaskTexture){
        string maskName = "mask";
        mMaskTexture->mapToFragShader(mProgramProxy->mProgram, maskName);
    }
    ShaderProxy::setFloat(mProgramProxy->mProgram,"texelWidthOffset",(1.0f / (float)mRenderWidth));
    ShaderProxy::setFloat(mProgramProxy->mProgram,"texelHeightOffset",(1.0f / (float)mRenderHeight));
}

void HealthySampler::releaseGL() {
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