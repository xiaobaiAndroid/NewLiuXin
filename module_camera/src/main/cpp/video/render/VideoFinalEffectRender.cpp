/**
 *@author: baizf
 *@date: 2023/3/5
*/
//

#include "VideoFinalEffectRender.h"
#include "base/BaseFilterSampler.h"
#include "Logutils.h"

void VideoFinalEffectRender::initVertex() {
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

void VideoFinalEffectRender::init() {
    mProgramProxy = new ProgramProxy(DEFAULT_VERTEX_SRC, DEFAULT_FRAG_SRC);
    initVertex();
}

void VideoFinalEffectRender::draw(TextureProxy &texture, VAOProxy *vao) {
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

    glm::mat4 model = CoordinateSystems::createModel(mModelMatrix);
    CoordinateSystems::setMatrixToShader(mProgramProxy->mProgram,SHADER_VAR_NAME_MODEL,model);

    vao->bind();
    glDrawArrays(GL_TRIANGLES, 0, 6);
    vao->unbind();
}

VAOProxy *VideoFinalEffectRender::getVAO() {
    return mVAO;
}

void VideoFinalEffectRender::releaseGL() {
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

void VideoFinalEffectRender::setImageScaleSize(int width, int height) {
    if(mImageScaleWith== width && mImageScaleHeight == height){
        return;
    }
    if(width >= mRenderWidth){
        mImageScaleWith = mRenderWidth;
    }
    if(height >= mRenderHeight){
        mImageScaleHeight = mRenderWidth;
    }else{
        mImageScaleWith = width;
        mImageScaleHeight = height;
    }

    mBottomLeftX = (mRenderWidth - mImageScaleWith)/2;
    mBottomLeftY = (mRenderHeight - mImageScaleHeight) / 2;

    LOGI("mBottomLeftX=%d, mBottomLeftY=%d, mImageScaleWith=%d, mImageScaleHeight=%d", mBottomLeftX, mBottomLeftY, mImageScaleWith, mImageScaleHeight);

//    CoordinateSystems::setViewport(mImageScaleWith,mImageScaleHeight,mBottomLeftX,mBottomLeftY);

}

ProgramProxy *VideoFinalEffectRender::getProgram() {
    return mProgramProxy;
}
