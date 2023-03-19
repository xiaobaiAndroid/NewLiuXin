/**
 * 蜡笔滤镜
 *@author: baizf
 *@date: 2023/2/4
*/
//

#ifndef NATIVEIMAGEEDITOR_CRAYONSAMPLER_H
#define NATIVEIMAGEEDITOR_CRAYONSAMPLER_H

#include "base/BlendSampler.h"

class CrayonSampler: public BlendSampler{
private:
    void drawBefore() override;
protected:
    string getFragSrc() override;
public:

};

#endif //NATIVEIMAGEEDITOR_CRAYONSAMPLER_H
