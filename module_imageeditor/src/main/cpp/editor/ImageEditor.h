/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef NATIVEIMAGEEDITOR_IMAGEEDITOR_H
#define NATIVEIMAGEEDITOR_IMAGEEDITOR_H

#include "base/BaseSampler.h"
#include "base/BaseFilterSampler.h"

#include "BitmapUtils.h"
#include "FiltersSampler.h"
#include "ImageSampler.h"

class ImageEditor{

private:

    FiltersSampler *mFiltersSampler = nullptr;
    ImageSampler *mImageSampler = nullptr;

    int mWindowWidth = 0, mWindowHeight = 0;

    int mVerticesCount = 6;


    //图片缩放比
    float mImageScaleRation = 1.0f;

    float mRatioWH = 0.0f;
    CameraProxy *mCameraProxy = nullptr;

public:
    int mScaleImageWidth = 0, mScaleImageHeight = 0;

    ImageEditor();
    void init(JNIEnv *env, jobject *assetsManager);
    void resize( int screenW, int screenH);
    void releaseGL();
    void draw();

    void setFilter(JNIEnv *env, jobject *asset, int filter_type);

    void setSourceImage(NativeImage &nativeImage);

    float getAdjustValue(const int adjustType);

    void setAdjustValue(const int adjustType, float value);


    BlendSampler *getFilterSampler();

    void getBitmapData(JNIEnv *env, jobject bitmap) const;

    vector<int> getRenderRect();
};

#endif //NATIVEIMAGEEDITOR_IMAGEEDITOR_H
