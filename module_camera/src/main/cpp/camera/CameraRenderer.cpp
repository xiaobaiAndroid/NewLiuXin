/**
 *@author: baizf
 *@date: 2023/2/20
*/
//

#include "CameraRenderer.h"
#include "Logutils.h"


CameraRenderer::CameraRenderer() {
    mCameraProxy = new CameraProxy();

    mImageSampler = new ImageSampler(mCameraProxy);
    mFiltersSampler = new FiltersSampler(mCameraProxy);
}

void CameraRenderer::release() {
    mEGLThread->isFinish = true;
    delete mEGLThread;
    mEGLThread = nullptr;

    releaseVBO();

    releaseVAO();

    if (mFiltersSampler) {
        mFiltersSampler->releaseGL();
        delete mFiltersSampler;
        mFiltersSampler = nullptr;
    }

    if (mImageSampler) {
        mImageSampler->releaseGL();
        delete mImageSampler;
        mImageSampler = nullptr;
    }

    if (mCameraProxy) {
        delete mCameraProxy;
        mCameraProxy = nullptr;
    }
}

void CameraRenderer::releaseVAO() {
    if (mVAO) {
        mVAO->destroy();
        delete mVAO;
        mVAO = nullptr;
    }
}

void CameraRenderer::releaseVBO() {
    if (mVertexVBO) {
        mVertexVBO->destroy();
        delete mVertexVBO;
        mVertexVBO = nullptr;
    }

    if (mTexCoordsVBO) {
        mTexCoordsVBO->destroy();
        delete mTexCoordsVBO;
        mTexCoordsVBO = nullptr;
    }
}

void CameraRenderer::onSurfaceCreate(void *obj) {
    auto *pRenderer = static_cast<CameraRenderer *>(obj);
    pRenderer->onCreate();
}

void CameraRenderer::onSurfaceChange(void *obj, int width, int height) {
    auto *pRenderer = static_cast<CameraRenderer *>(obj);
    pRenderer->onChange(width, height);
}

void CameraRenderer::onSurfaceDraw(void *obj) {
    auto *pRenderer = static_cast<CameraRenderer *>(obj);
    pRenderer->onDraw();
}

void CameraRenderer::init(JNIEnv *env, jobject *assetsManager, ANativeWindow *pWindow) {
    mEnv = env;
    mAsset = *assetsManager;

    mEGLThread = new EGLThread();
    mEGLThread->setOnCreateCallBack(onSurfaceCreate,this);
    mEGLThread->setOnChangeCallBack(onSurfaceChange, this);
    mEGLThread->setOnDrawCallback(onSurfaceDraw, this);
    mEGLThread->start(pWindow);

}

void CameraRenderer::setFilter(JNIEnv *env, jobject *asset, int filter_type) {
    if (mFiltersSampler) {
        mFiltersSampler->setFilter(filter_type);
    }
}

float CameraRenderer::getAdjustValue(const int adjustType) {
    if (mFiltersSampler) {
        return mFiltersSampler->getAdjustValue(adjustType);
    } else {
        return 0.0f;
    }
}

void CameraRenderer::setAdjustValue(const int adjustType, float value) {
    if (mFiltersSampler) {
        mFiltersSampler->setAdjustValue(adjustType, value);
    }
}

void CameraRenderer::resize(int screenW, int screenH) {
    mWindowWidth = screenW;
    mWindowHeight = screenH;
    if (mImageSampler) {
        mImageSampler->setRenderSize(screenW, screenH);
    }
    if (mFiltersSampler) {
        mFiltersSampler->setRenderSize(screenW, screenH);
    }
}

void CameraRenderer::setSourceImage(NativeImage &nativeImage) {
//    float ratioW = (float) nativeImage.width / (float) mWindowWidth;
//    float ratioH = (float) nativeImage.height / (float) mWindowHeight;
//
//    float imageRatio = (float) nativeImage.width / (float) nativeImage.height;
//    float ratioMax = max(ratioW, ratioH);

//    const float errorValue = 0.00001f;
//    if((ratioMax -ratioW) < errorValue){
//        mScaleImageWidth = mWindowWidth;
//        mScaleImageHeight = (int)((float)mScaleImageWidth / imageRatio);
//    }else{
//        mScaleImageHeight = mWindowHeight;
//        mScaleImageWidth = (int)((float)mScaleImageHeight * imageRatio);
//    }

    mSourceImage = &nativeImage;


}

void CameraRenderer::draw() {
    if (!mImageSampler || !mFiltersSampler || !mVAO) {
        return;
    }

//    VAOProxy *vao = mImageSampler->getVAO();
//    if (vao) {
//        mFiltersSampler->draw(*vao);
//    }


    auto *blendAfterTexture = mFiltersSampler->getBlendTexture();
    if (blendAfterTexture) {
        mImageSampler->draw(*blendAfterTexture, *mVAO);
    }
}

void
CameraRenderer::iniVAO(JNIEnv *env, jint cameraFacing, jint windowDegrees, jint sensorDegrees) {

    LOGI("cameraFacing=%d, windowDegrees=%d, sensorDegrees=%d", cameraFacing, windowDegrees,
         sensorDegrees)

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


    jclass clazz = env->FindClass("android/hardware/camera2/CameraMetadata");
    jfieldID fieldIdFront = env->GetStaticFieldID(clazz, "LENS_FACING_FRONT", "I");
    jint frontLens = env->GetStaticIntField(clazz, fieldIdFront);

    jfieldID fieldIdBack = env->GetStaticFieldID(clazz, "LENS_FACING_BACK", "I");
    jint backLens = env->GetStaticIntField(clazz, fieldIdBack);

    float *textureCoords = nullptr;
    if (cameraFacing == frontLens) { //前置 270
        if (sensorDegrees == 270) {
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
    } else if (cameraFacing == backLens) { //后置 90
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
}

BlendSampler *CameraRenderer::getFilterSampler() {
    if (mFiltersSampler) {
        return mFiltersSampler->getFilterSampler();
    } else {
        return nullptr;
    }
}

void CameraRenderer::getTextureData() {
    int size = mImageSampler->mImageScaleWith * mImageSampler->mImageScaleHeight * 4;

    auto *pPixelData = new unsigned char[size];
    auto *pixels = new unsigned char[size];


    glReadBuffer(GL_FRONT);//保存窗口的渲染结果
//    glPixelStorei(GL_UNPACK_ALIGNMENT,4);////解压窗口数据结构

    glReadPixels(mImageSampler->mBottomLeftX, mImageSampler->mBottomLeftY,
                 mImageSampler->mImageScaleWith, mImageSampler->mImageScaleHeight, GL_RGBA,
                 GL_UNSIGNED_BYTE,
                 pPixelData);
    ErrorProxy::checkError("glReadPixels");

//        翻转图片
    int oneLineLength = mImageSampler->mImageScaleWith * 4;
    auto *tmp = new unsigned char[size];
    memcpy(tmp, pPixelData, size);
    memset(pPixelData, 0, size);
    for (int i = 0; i < mImageSampler->mImageScaleHeight; i++) {
        memcpy(pPixelData + oneLineLength * i, tmp + size - oneLineLength * (i + 1),
               oneLineLength);
    }
    memcpy(pixels, pPixelData, size);

    delete[] pixels;
    delete[] pPixelData;
}

void CameraRenderer::onCreate() {
    iniVAO(mEnv, cameraFacing,windowDegrees,sensorDegrees);
    if (mImageSampler) {
        mImageSampler->init();
    }
    if (mFiltersSampler) {
        mFiltersSampler->init();
    }

//    mTriangleSampler = new TriangleSampler();
}

void CameraRenderer::onChange(int width, int height) {
    resize(width, height);
}

void CameraRenderer::onDraw() {
    if(mSourceImage){
        mScaleImageHeight = mSourceImage->width;
        mScaleImageWidth = mSourceImage->height;

        if (mFiltersSampler) {
            mFiltersSampler->setSourceImage(*mSourceImage, mScaleImageWidth, mScaleImageHeight);
        }
        if (mImageSampler) {
            mImageSampler->setImageScaleSize(mScaleImageWidth, mScaleImageHeight);
        }
    }
    draw();
//    mTriangleSampler->draw();
}
