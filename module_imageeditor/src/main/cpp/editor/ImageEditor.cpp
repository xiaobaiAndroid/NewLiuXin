/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "ImageEditor.h"
#include "Logutils.h"
#include <typeinfo>

ImageEditor::ImageEditor() {
    mCameraProxy = new CameraProxy();

    mImageSampler = new ImageSampler(mCameraProxy);
    mFiltersSampler = new FiltersSampler(mCameraProxy);

}

void ImageEditor::init(JNIEnv *env, jobject *assetsManager) {
    if(mImageSampler){
        mImageSampler->init(env,assetsManager);
    }
    if(mFiltersSampler){
        mFiltersSampler->init(env,assetsManager);
    }
}

void ImageEditor::resize(int screenW, int screenH) {
    mWindowWidth = screenW;
    mWindowHeight = screenH;
    mRatioWH = (float)mWindowWidth / (float) mWindowHeight;
    if(mImageSampler){
        mImageSampler->setRenderSize(screenW, screenH);
    }
    if(mFiltersSampler){
        mFiltersSampler->setRenderSize(screenW, screenH);
    }
}


void ImageEditor::setSourceImage(NativeImage &nativeImage) {
    float ratioW = (float)nativeImage.width / (float)mWindowWidth;
    float ratioH =  (float)nativeImage.height / (float)mWindowHeight;

    float imageRatio = (float)nativeImage.width / (float)nativeImage.height;
    float ratioMax = max(ratioW, ratioH);
    LOGI("ratioMin=%f", ratioMax);

    const float errorValue = 0.00001f;
    if((ratioMax -ratioW) < errorValue){
        mScaleImageWidth = mWindowWidth;
        mScaleImageHeight = (int)((float)mScaleImageWidth / imageRatio);
    }else{
        mScaleImageHeight = mWindowHeight;
        mScaleImageWidth = (int)((float)mScaleImageHeight * imageRatio);
    }

    mImageScaleRation = imageRatio;

    if(mFiltersSampler){
        mFiltersSampler->setSourceImage(nativeImage, mScaleImageWidth, mScaleImageHeight);
    }
    if(mImageSampler){
        mImageSampler->setImageScaleSize(mScaleImageWidth,mScaleImageHeight);
    }
}


void ImageEditor::draw() {
    if(!mImageSampler || !mFiltersSampler){
        return;
    }

    VAOProxy *vao = mImageSampler->getVAO();
    if(vao){
        mFiltersSampler->draw(*vao);
    }

    auto *blendAfterTexture = mFiltersSampler->getBlendTexture();
    if(blendAfterTexture){
        mImageSampler->draw(*blendAfterTexture);
    }
}

//void ImageEditor::initVertex(int imageWidth, int imageHeight) {
//    float ratioW = static_cast<float>(mWindowWidth) / static_cast<float>(imageWidth);
//    float ratioH = static_cast<float>(mWindowHeight) / static_cast<float>(imageHeight);
//
//
//    float ratioMin = min(ratioW, ratioH);
//    LOGI("ratioMin=%f", ratioMin);
//
//    mImageScaleRation = ratioMin;
//
//    mScaleImageWidth = static_cast<int>(round(ratioMin * static_cast<float>(imageWidth)));
//    mScaleImageHeight = static_cast<int>(round(ratioMin * static_cast<float>(imageHeight)));
//
//    LOGI("mScaleImageWidth=%d, mScaleImageHeight=%d, imageWidth=%d, imageHeight=%d",
//         mScaleImageWidth, mScaleImageHeight, imageWidth, imageHeight);
//
//    float ratioWidth;
//    float ratioHeight;
//
//    if (mScaleImageWidth == mWindowWidth) { // mScaleImageWidth大于mScaleImageHeight
//        ratioWidth = 1.0f;
//        ratioHeight = (float) mScaleImageHeight / (float) mScaleImageWidth;
//    } else { // mScaleImageWidth小于mScaleImageHeight
//        ratioWidth = (float) mScaleImageWidth / (float) mScaleImageHeight;
//        ratioHeight = 1.0f;
//    }
//
//
//    LOGI("ratioWidth=%f, ratioHeight=%f", ratioWidth, ratioHeight);
//
//    float vertices[30] = {
//            -ratioWidth, ratioHeight, 0.0f, 0.0f, 1.0f, //左上
//            -ratioWidth, -ratioHeight, 0.0f, 0.0f, 0.0f, //左下
//            ratioWidth, ratioHeight, 0.0, 1.0f, 1.0f, //右上
//
//            ratioWidth, ratioHeight, 0.0, 1.0f, 1.0f, //右上
//            -ratioWidth, -ratioHeight, 0.0f, 0.0f, 0.0f, //左下
//            ratioWidth, -ratioHeight, 0.0f, 1.0f, 0.0f //右下
//    };
//
//    if (mVBO) {
//        mVBO->destroy();
//        mVBO = nullptr;
//    }
//
//    mVBO = new VBOProxy(vertices, 30);
//
//    if (mVAO) {
//        mVAO->destroy();
//        delete mVAO;
//    }
//    mVAO = new VAOProxy();
//    mVAO->bind();
//    mVAO->parseVertices(0, 3, 5, 0);
//    mVAO->parseVertices(1, 2, 5, 3);
//    mVAO->unbind();
//
//    initOffScreenVertex();
//    initTextureFlipVertex();
//}

void ImageEditor::releaseGL() {
    if(mFiltersSampler){
        mFiltersSampler->releaseGL();
        delete mFiltersSampler;
        mFiltersSampler = nullptr;
    }

    if(mImageSampler){
        mImageSampler->releaseGL();
        delete mImageSampler;
        mImageSampler = nullptr;
    }

    if(mCameraProxy){
        delete mCameraProxy;
        mCameraProxy = nullptr;
    }
}

void ImageEditor::setFilter(JNIEnv *env, jobject *asset, int filter_type) {
    if(mFiltersSampler){
        mFiltersSampler->setFilter(env,asset,filter_type);
    }
}

float ImageEditor::getAdjustValue(const int adjustType) {
    if(mFiltersSampler){
        return mFiltersSampler->getAdjustValue(adjustType);
    }else{
        return 0.0f;
    }

}

void ImageEditor::setAdjustValue(const int adjustType,float value) {
    if(mFiltersSampler){
        mFiltersSampler->setAdjustValue(adjustType, value);
    }

}


BlendSampler *ImageEditor::getFilterSampler() {
    if(mFiltersSampler){
        return mFiltersSampler->getFilterSampler();
    } else{
        return nullptr;
    }
}

void ImageEditor::getBitmapData(JNIEnv *env, jobject bitmap) const {
    if(!mImageSampler){
        return;
    }
    AndroidBitmapInfo info;

    NativeImage *image = nullptr;
    void *pixels = nullptr;
    //检索Bitmap对象信息，成功返回0，失败返回负数
    int result = AndroidBitmap_getInfo(env, bitmap, &info);
    if (result == 0) {
        //锁定像素缓存确保像素的内存不会被移动，返回一个像素缓存的地址赋给pixels 成功返回0，失败返回负数
        result = AndroidBitmap_lockPixels(env, bitmap, &pixels);

        int size = mImageSampler->mImageScaleWith * mImageSampler->mImageScaleHeight * 4;

        auto *pPixelData = new unsigned char[size];


    glReadBuffer(GL_FRONT);//保存窗口的渲染结果
//    glPixelStorei(GL_UNPACK_ALIGNMENT,4);////解压窗口数据结构

        glReadPixels(mImageSampler->mBottomLeftX,mImageSampler->mBottomLeftY, mImageSampler->mImageScaleWith, mImageSampler->mImageScaleHeight, GL_RGBA, GL_UNSIGNED_BYTE,
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

//        delete[] tmp;
        delete[] pPixelData;
    }

}

vector<int> ImageEditor::getRenderRect() {
    vector<int> vector;
    vector.push_back(mImageSampler->mBottomLeftX);
    vector.push_back(mImageSampler->mBottomLeftY);
    vector.push_back(mImageSampler->mImageScaleWith);
    vector.push_back(mImageSampler->mImageScaleHeight);
    return vector;
}
