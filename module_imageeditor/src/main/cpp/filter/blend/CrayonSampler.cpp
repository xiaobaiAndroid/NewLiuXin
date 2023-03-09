/**
 *@author: baizf
 *@date: 2023/2/4
*/
//

#include "CrayonSampler.h"

void CrayonSampler::init(JNIEnv *env, jobject *assetsManager) {
    string vertPath = "glsl/common_vert.glsl";
    string fragPath = "glsl/crayon_frag.glsl";
    BaseFilterSampler::initCommon(env,assetsManager,vertPath,fragPath);

}

void CrayonSampler::drawBefore() {

    string singleStepOffsetName = "singleStepOffset";
    glm::vec2 singleStepOffset(1.0f / (float)mRenderWidth, 1.0f / (float)mRenderHeight);
    ShaderProxy::setVec2(mProgramProxy->mProgram,singleStepOffsetName,singleStepOffset);

    string strengthName = "strength";
    ShaderProxy::setFloat(mProgramProxy->mProgram, strengthName, 0.5f);
}
