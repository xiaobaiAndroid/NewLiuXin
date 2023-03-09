/**
 *@author: baizf
 *@date: 2023/1/8
*/
//

#include "BaseSampler.h"
#include "Logutils.h"




void BaseSampler::setRenderSize(int width, int height) {
    mRenderWidth = width;
    mRenderHeight = height;
    mRatioWH = (float)(mRenderWidth) / (float)(mRenderHeight);
//    CoordinateSystems::setViewport(mRenderWidth, mRenderHeight);
}
