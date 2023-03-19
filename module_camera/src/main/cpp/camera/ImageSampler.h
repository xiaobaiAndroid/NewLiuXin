/**
 *@author: baizf
 *@date: 2023/2/14
*/
//

#ifndef NATIVEIMAGEEDITOR_IMAGESAMPLER_H
#define NATIVEIMAGEEDITOR_IMAGESAMPLER_H


#include "base/BaseSampler.h"
#include "base/BaseFilterSampler.h"


class ImageSampler: public BaseSampler{
private:
    ProgramProxy *mProgramProxy = nullptr;
    VAOProxy *mVAO = nullptr;
    VBOProxy *mVBO = nullptr;

    void initVertex();

public:
    ModelMatrix mModelMatrix;
    int mBottomLeftX;
    int mBottomLeftY;
    int mImageScaleWith;
    int mImageScaleHeight;

    ImageSampler(CameraProxy *camera);
    void init() override;

    void releaseGL() override;
    void draw(TextureProxy &texture, VAOProxy &vao);

    void setImageScaleSize(int width, int height);

    VAOProxy* getVAO();

    ProgramProxy *getProgram();
};

#endif //NATIVEIMAGEEDITOR_IMAGESAMPLER_H
