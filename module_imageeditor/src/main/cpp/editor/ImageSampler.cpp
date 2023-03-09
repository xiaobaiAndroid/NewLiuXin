/**
 *@author: baizf
 *@date: 2023/2/14
*/
//

#include "ImageSampler.h"

ImageSampler::ImageSampler(CameraProxy *camera) {
    mCamera = camera;
}

void ImageSampler::initVertex() {
    float vertices[] = {
            -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, //左上
            -1.0f, -1.0f, 0.0f, 0.0f, 0.0f, //左下
            1.0f, 1.0f, 0.0, 1.0f, 1.0f, //右上

            1.0f, 1.0f, 0.0, 1.0f, 1.0f, //右上
            -1.0f, -1.0f, 0.0f, 0.0f, 0.0f, //左下
            1.0f, -1.0, 0.0f, 1.0f, 0.0f //右下
    };

    if (mVBO) {
        mVBO->destroy();
        delete mVBO;
    }

    mVBO = new VBOProxy(vertices, 30);

    if (mVAO) {
        mVAO->destroy();
        delete mVAO;
    }
    mVAO = new VAOProxy();
    mVAO->bind();
    mVAO->parseVertices(0, 3, 5, 0);
    mVAO->parseVertices(1, 2, 5, 3);
    mVAO->unbind();
}

void ImageSampler::init(JNIEnv *env, jobject *assetsManager) {
    string vertPath = "glsl/common_vert.glsl";
    string fragPath = "glsl/common_frag.glsl";
    mProgramProxy = new ProgramProxy(env, assetsManager, vertPath, fragPath);

    initVertex();
}

void ImageSampler::draw(TextureProxy &texture) {
    if(!mProgramProxy){
        return;
    }

    //渲染到屏幕
    CoordinateSystems::setViewport(mImageScaleWith, mImageScaleHeight, mBottomLeftX, mBottomLeftY);
    glBindFramebuffer(GL_FRAMEBUFFER, 0);
    ProgramProxy::clearColor();
    ProgramProxy::clearBuffer();

    mProgramProxy->use();

    string sourceTexName = "uSourceTex";
    texture.mapToFragShader(mProgramProxy->mProgram, sourceTexName);

    ModelMatrix modelMatrix;
    glm::mat4 model = CoordinateSystems::createModel(modelMatrix);
    CoordinateSystems::setMatrixToShader(mProgramProxy->mProgram,SHADER_VAR_NAME_MODEL,model);

    if(mVAO){
        mVAO->bind();
        glDrawArrays(GL_TRIANGLES, 0, 6);
        mVAO->unbind();
    }
}

VAOProxy *ImageSampler::getVAO() {
    return mVAO;
}

void ImageSampler::releaseGL() {
    if (mProgramProxy) {
        mProgramProxy->destroy();
        delete mProgramProxy;
        mProgramProxy = nullptr;
    }
    if (mVBO) {
        mVBO->destroy();
        delete mVBO;
        mVBO = nullptr;
    }
    if (mVAO) {
        mVAO->destroy();
        delete mVAO;
        mVAO = nullptr;
    }
}

void ImageSampler::setImageScaleSize(int width, int height) {
    mImageScaleWith = width;
    mImageScaleHeight = height;

    mBottomLeftX = (mRenderWidth - mImageScaleWith)/2;
    mBottomLeftY = (mRenderHeight - mImageScaleHeight) / 2;
}

ProgramProxy *ImageSampler::getProgram() {
    return mProgramProxy;
}
