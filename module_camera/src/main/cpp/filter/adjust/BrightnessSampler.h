/**
 * 亮度调节
 *@author: baizf
 *@date: 2023/2/9
*/
/**
 *亮度（luminance）是表示人眼对发光体或被照射物体表面的发光或反射光强度实际感受的物理量，度和光强这两个量在一般的日常用语中往往被混淆使用。
 * 简而言之，当任两个物体表面在照相时被拍摄出的最终结果是一样亮、或被眼睛看起来两个表面一样亮，它们就是亮度相同。
 * 国际单位制中规定，“亮度”的符号是B，单位为尼特。
 * 亮度(明度)反应了色彩的明暗程度，它和色相(H)、饱和度(S)共同构成HSL色彩空间。调整亮度只需要RGB色彩空间里面同时加上一个程度值。
 */

#ifndef NATIVEIMAGEEDITOR_BRIGHTNESSSAMPLER_H
#define NATIVEIMAGEEDITOR_BRIGHTNESSSAMPLER_H
#include "base/AdjustSampler.h"

//默认亮度值
#define DEFAULT_BRIGHTNESS 0.0f
#define MAX_BRIGHTNESS 0.5f
#define MIN_BRIGHTNESS -0.5f

class BrightnessSampler: public AdjustSampler{
protected:
    float getDefaultValue() override;
    string getFragSrc() override;
    string getValueName() override;
    float computeSetValue(float value) override;
    float computeGetValue(float value) override;
};

#endif //NATIVEIMAGEEDITOR_BRIGHTNESSSAMPLER_H
