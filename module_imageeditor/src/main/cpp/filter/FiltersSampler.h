/**
 * 滤镜列表采样器
 *@author: baizf
 *@date: 2023/2/14
*/
//

#ifndef NATIVEIMAGEEDITOR_FILTERSSAMPLER_H
#define NATIVEIMAGEEDITOR_FILTERSSAMPLER_H

#include "CoreProxy.h"
#include "base/BaseFilterSampler.h"

#include "blend/FairyTaleSampler.h"
#include "BitmapUtils.h"
#include "blend/OriginalSampler.h"
#include "blend/SunriseSampler.h"
#include "blend/SunsetSampler.h"
#include "blend/WhiteCatSampler.h"
#include "blend/BlackCatSampler.h"
#include "blend/SkinWhitenSampler.h"
#include "blend/HealthySampler.h"
#include "blend/SweetsSampler.h"
#include "blend/RomanceSampler.h"
#include "blend/SakuraSampler.h"
#include "blend/WarmSampler.h"
#include "blend/AntiqueSampler.h"
#include "blend/NostalgiaSampler.h"
#include "blend/CalmSampler.h"
#include "blend/LatteSampler.h"
#include "blend/TenderSampler.h"
#include "blend/CoolSampler.h"
#include "blend/EmeraldSampler.h"
#include "blend/EvergreenSampler.h"
#include "blend/CrayonSampler.h"
#include "blend/SketchSampler.h"
#include "blend/OriginalSampler.h"
#include "adjust/BrightnessSampler.h"
#include "adjust/ContrastSampler.h"

#include "adjust/SharpenSampler.h"
#include "adjust/SaturationSampler.h"
#include "adjust/ExposureSampler.h"

class FiltersSampler: public BaseSampler{
private:
    int mImageScaleWidth = 0, mImageScaleHeight = 0;

    VAOProxy *mFlipVAO = nullptr;
    VBOProxy *mFlipVBO = nullptr;


    //当前使用的滤镜
    BaseFilterSampler *mCurrentFilter = nullptr;

    vector<GLuint> mFrameBuffers;
    vector<TextureProxy*> mTextures;

    TextureProxy *mSourceTex = nullptr;

    vector<BaseFilterSampler*> mFilterSamplers;

    float mImageScaleRation = 0.0f;

    ModelMatrix mModelMatrix;

    void initFrameBuffers();
    void deleteFrameBuffers();

    void initFlipVertex();
public:
    FiltersSampler(CameraProxy *camera);
    void init(JNIEnv *env, jobject *assetsManager) override;

    void setSourceImage(NativeImage &nativeImage, int imageScaleWidth, int imageScaleHeight);

    void releaseGL() override;
    void draw(VAOProxy &vao);

    void setFilter(JNIEnv *env, jobject *asset, int filter_type);

    float getAdjustValue(const int adjustType);

    void setAdjustValue(const int adjustType, float value);

    BlendSampler *getFilterSampler();

    TextureProxy* getBlendTexture();
};

#endif //NATIVEIMAGEEDITOR_FILTERSSAMPLER_H
