/**
 *@author: baizf
 *@date: 2023/2/2
*/
//

#include "OriginalSampler.h"

void OriginalSampler::init(JNIEnv *env, jobject *assetsManager) {
    string vertPath = "glsl/common_vert.glsl";
    string fragPath = "glsl/common_frag.glsl";

    BaseFilterSampler::initCommon(env,assetsManager,vertPath,fragPath);

}

void OriginalSampler::drawBefore() {

}