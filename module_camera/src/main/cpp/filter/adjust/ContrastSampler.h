/**
 * 对比度
 *@author: baizf
 *@date: 2023/2/11
*/
//

/**
 * 对比度是画面黑与白的比值，也就是从黑到白的渐变层次。比值越大，从黑到白的渐变层次就越多，从而色彩表现越丰富。
 *
 * 对比度对视觉效果的影响非常关键，一般来说对比度越大，图像越清晰醒目，色彩也越鲜明艳丽；而对比度小，则会让整个画面都灰蒙蒙的。
 *
 * 简单的说，对比度是像素颜色和某个中值的差，它可以让明亮的颜色更明亮，让灰暗的颜色更灰暗。
 *
 * 这里实现个简单的线性对比度算法：
 *
 * 结果=中值差*对比度+中值
 */

#ifndef NATIVEIMAGEEDITOR_CONTRASTSAMPLER_H
#define NATIVEIMAGEEDITOR_CONTRASTSAMPLER_H

#include "base/AdjustSampler.h"

//默认对比度
#define DEFAULT_CONTRAST 1.0f
#define MAX_CONTRAST 0.6f
#define MIN_CONTRAST 1.4f

class ContrastSampler: public AdjustSampler{
protected:

    float getDefaultValue() override;
    string getFragSrc() override;
    string getValueName() override;
    float computeSetValue(float value) override;
    float computeGetValue(float value) override;
};
#endif //NATIVEIMAGEEDITOR_CONTRASTSAMPLER_H
