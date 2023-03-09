/**
 *@author: baizf
 *@date: 2023/2/4
*/
//

#include "SakuraSampler.h"


void SakuraSampler::init(JNIEnv *env, jobject *assetsManager) {
    string vertPath = "glsl/common_vert.glsl";
    string fragPath = "glsl/romance_frag.glsl";
    BaseFilterSampler::initCommon(env,assetsManager,vertPath,fragPath);

    mCurveTexture = new TextureProxy(GL_TEXTURE1);
}

void SakuraSampler::drawBefore() {
    if(mCurveTexture){
        string curveName = "curve";
        mCurveTexture->mapToFragShader(mProgramProxy->mProgram, curveName);
    }
}

void SakuraSampler::setFilterImage(vector<NativeImage *> nativeImages) {
    unsigned char arrayOfByte[1024];
    int arrayOfInt[] = { 95, 95, 96, 97, 97, 98, 99, 99, 100, 101, 101, 102, 103, 104, 104, 105, 106, 106, 107, 108, 108, 109, 110, 111, 111, 112, 113, 113, 114, 115, 115, 116, 117, 117, 118, 119, 120, 120, 121, 122, 122, 123, 124, 124, 125, 126, 127, 127, 128, 129, 129, 130, 131, 131, 132, 133, 133, 134, 135, 136, 136, 137, 138, 138, 139, 140, 140, 141, 142, 143, 143, 144, 145, 145, 146, 147, 147, 148, 149, 149, 150, 151, 152, 152, 153, 154, 154, 155, 156, 156, 157, 158, 159, 159, 160, 161, 161, 162, 163, 163, 164, 165, 165, 166, 167, 168, 168, 169, 170, 170, 171, 172, 172, 173, 174, 175, 175, 176, 177, 177, 178, 179, 179, 180, 181, 181, 182, 183, 184, 184, 185, 186, 186, 187, 188, 188, 189, 190, 191, 191, 192, 193, 193, 194, 195, 195, 196, 197, 197, 198, 199, 200, 200, 201, 202, 202, 203, 204, 204, 205, 206, 207, 207, 208, 209, 209, 210, 211, 211, 212, 213, 213, 214, 215, 216, 216, 217, 218, 218, 219, 220, 220, 221, 222, 223, 223, 224, 225, 225, 226, 227, 227, 228, 229, 229, 230, 231, 232, 232, 233, 234, 234, 235, 236, 236, 237, 238, 239, 239, 240, 241, 241, 242, 243, 243, 244, 245, 245, 246, 247, 248, 248, 249, 250, 250, 251, 252, 252, 253, 254, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255 };
    for (int i = 0; i < 256; i++)
    {
        arrayOfByte[(i * 4)] = ((unsigned char)arrayOfInt[i]);
        arrayOfByte[(1 + i * 4)] = ((unsigned char)arrayOfInt[i]);
        arrayOfByte[(2 + i * 4)] = ((unsigned char)arrayOfInt[i]);
        arrayOfByte[(3 + i * 4)] = ((unsigned char)arrayOfInt[i]);
    }

    NativeImage curveImage;
    curveImage.width = 256;
    curveImage.height = 1;
    curveImage.format = AndroidBitmapFormat::ANDROID_BITMAP_FORMAT_RGBA_8888;
    curveImage.data = arrayOfByte;
    if(mCurveTexture){
        mCurveTexture->setImage2D(&curveImage);
    }
}

void SakuraSampler::releaseGL() {
    BaseFilterSampler::releaseGL();
    if(mCurveTexture){
        mCurveTexture->destroy();
        delete mCurveTexture;
        mCurveTexture = nullptr;
    }
}