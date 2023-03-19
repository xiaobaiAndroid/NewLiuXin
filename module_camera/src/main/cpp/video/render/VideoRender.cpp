/**
 *@author: baizf
 *@date: 2023/3/5
*/
//

#include "VideoRender.h"
#include "Logutils.h"

VideoRender::VideoRender(JavaVM *javaVm) {
    mJavaVM = javaVm;
    mVideoFinalEffectRender = new VideoFinalEffectRender();
}

void VideoRender::iniVAO(VAOParams *vaoParams) {
    LOGI("cameraFacing=%d, windowDegrees=%d, sensorDegrees=%d", vaoParams->cameraFacing,
         vaoParams->windowDegrees,
         vaoParams->sensorDegrees);

    releaseVBO();
    releaseVAO();

    float vertices[] = {
            -1.0f, -1.0f, 0.0f, //左下
            1.0f, 1.0f, 0.0,  //右上
            -1.0f, 1.0f, 0.0f,  //左上

            -1.0f, -1.0f, 0.0f,  //左下
            1.0f, -1.0, 0.0f, //右下
            1.0f, 1.0f, 0.0, //右上
    };

    mVertexVBO = new VBOProxy(vertices, 18);
    mVAO = new VAOProxy();
    mVAO->bind();
    mVAO->parseVertices(0, 3, 3, 0);

    JNIEnv *env = nullptr;
    jint status = mJavaVM->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6);
    if (status == JNI_EDETACHED || env == nullptr) {
        //将当前线程与JVM进行关联，获得JNIEnv对象
        status = mJavaVM->AttachCurrentThread(&env, nullptr);
        if(status < 0){
            LOGE("AttachCurrentThread fail");
            return;
        }
    }


    jclass clazz = env->FindClass("android/hardware/camera2/CameraMetadata");
    jfieldID fieldIdFront = env->GetStaticFieldID(clazz, "LENS_FACING_FRONT", "I");
    jint frontLens = env->GetStaticIntField(clazz, fieldIdFront);

    jfieldID fieldIdBack = env->GetStaticFieldID(clazz, "LENS_FACING_BACK", "I");
    jint backLens = env->GetStaticIntField(clazz, fieldIdBack);

    float *textureCoords = nullptr;
    if (vaoParams->cameraFacing == frontLens) { //前置 270
        if (vaoParams->sensorDegrees == 270) {
            //旋转90度再镜像
            textureCoords = new float[]{
                    0.0f, 1.0f, //左下角
                    1.0f, 0.0f, //右上角
                    1.0f, 1.0f, //左上角
                    0.0f, 1.0f,
                    0.0f, 0.0f, //右下角
                    1.0f, 0.0f
            };
        } else {
            textureCoords = new float[]{
                    0.0f, 1.0f, //左下角
                    1.0f, 0.0f, //右上角
                    0.0f, 0.0f, //左上角
                    0.0f, 1.0f,
                    1.0f, 1.0f,
                    1.0f, 0.0f
            };
        }
    } else if (vaoParams->cameraFacing == backLens) { //后置 90
        textureCoords = new float[]{
                1.0f, 1.0f, //左下角
                0.0f, 0.0f, //右上角
                0.0f, 1.0f, //左上角
                1.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 0.0f
        };
    } else {
        textureCoords = new float[]{
                0.0f, 1.0f, //左下角
                1.0f, 0.0f, //右上角
                0.0f, 0.0f, //左上角
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
    }

    mTexCoordsVBO = new VBOProxy(textureCoords, 12);
    mVAO->bind();
    mVAO->parseVertices(1, 2, 2, 0);
    mVAO->unbind();


    if(env){
        mJavaVM->DetachCurrentThread();
    }

    mVideoFinalEffectRender->init();
}

float VideoRender::getAdjustValue(const int adjustType) {
    return 0.0f;
}


void VideoRender::updateFrame(NativeImage &nativeImage) {
    if (mTexture == nullptr) {
        mTexture = new TextureProxy();
        mTexture->setImage2D(&nativeImage);
    }else{
        mTexture->modifyImage2D(&nativeImage);
    }
    mVideoFinalEffectRender->setImageScaleSize(nativeImage.width, nativeImage.height);
}

void VideoRender::setAdjustValue(const int adjustType, float value) {

}


void VideoRender::releaseVAO() {
    if(mVAO){
        mVAO->destroy();
        delete mVAO;
        mVAO = nullptr;
    }
}

void VideoRender::releaseVBO() {
    if(mVertexVBO){
        mVertexVBO->destroy();
        delete mVertexVBO;
        mVertexVBO = nullptr;
    }
    if(mTexCoordsVBO){
        mTexCoordsVBO->destroy();
        delete mTexCoordsVBO;
        mTexCoordsVBO = nullptr;
    }
}

void VideoRender::release() {
    releaseVBO();
    releaseVAO();

    if(mTexture){
        mTexture->destroy();
        delete mTexture;
        mTexture = nullptr;
    }
}

void VideoRender::surfaceResize(int width, int height) {
    mVideoFinalEffectRender->setRenderSize(width, height);
}

void VideoRender::draw() {
    if(mTexture){
        mVideoFinalEffectRender->draw(*mTexture, mVAO);
    }

}