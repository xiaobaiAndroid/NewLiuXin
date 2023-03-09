/**
 *@author: baizf
 *@date: 2023/2/2
*/
//

#include "BaseFilterSampler.h"
#include "Logutils.h"


void BaseFilterSampler::initCommon(JNIEnv *env, jobject *assetsManager, string &vertPath,
                                   string &fragPath) {
    mProgramProxy = new ProgramProxy(env, assetsManager, vertPath, fragPath);

}

void BaseFilterSampler::releaseGL() {
    if(mProgramProxy){
        mProgramProxy->destroy();
        delete mProgramProxy;
        mProgramProxy = nullptr;
    }
}


void BaseFilterSampler::draw(TextureProxy *textureProxy, VAOProxy *cVAO, ModelMatrix modelMatrix) {
    if(!mProgramProxy){
        return;
    }
    mProgramProxy->use();

    drawBefore();

    string sourceTexName = "uSourceTex";
    textureProxy->mapToFragShader(mProgramProxy->mProgram, sourceTexName);

    glm::mat4 model = CoordinateSystems::createModel(modelMatrix);
    CoordinateSystems::setMatrixToShader(mProgramProxy->mProgram,SHADER_VAR_NAME_MODEL,model);

    cVAO->bind();
    glDrawArrays(GL_TRIANGLES,0, mVerticesCount);
    cVAO->unbind();

    textureProxy->unbind();
}


void BaseFilterSampler::drawBefore() {

}

BaseFilterSampler::~BaseFilterSampler() {

}