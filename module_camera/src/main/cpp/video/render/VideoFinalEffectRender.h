/**
 * 最终显示效果
 *@author: baizf
 *@date: 2023/3/5
*/
//

#ifndef VIDEOEDITOR_VIDEOFINALEFFECTRENDER_H
#define VIDEOEDITOR_VIDEOFINALEFFECTRENDER_H

#include "CoreProxy.h"
#include "base/BaseSampler.h"

class VideoFinalEffectRender: public BaseSampler{
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

    VideoFinalEffectRender() = default;
    void init() override;

    void releaseGL() override;
    void draw(TextureProxy &texture, VAOProxy *vao);

    void setImageScaleSize(int width, int height);

    VAOProxy* getVAO();

    ProgramProxy *getProgram();
};

#endif //VIDEOEDITOR_VIDEOFINALEFFECTRENDER_H
