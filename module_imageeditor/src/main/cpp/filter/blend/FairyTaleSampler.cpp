/**
 *@author: baizf
 *@date: 2023/2/1
*/
//

#include "FairyTaleSampler.h"


void FairyTaleSampler::init(JNIEnv *env, jobject *assetsManager) {
    string vertPath = "glsl/fairytale_vert.glsl";
    string fragPath = "glsl/fairytale_frag.glsl";

    BaseFilterSampler::initCommon(env,assetsManager,vertPath,fragPath);

    mFilterTex = new TextureProxy(GL_TEXTURE1);

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

