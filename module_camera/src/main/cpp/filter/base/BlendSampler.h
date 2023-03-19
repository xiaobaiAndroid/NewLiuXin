/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef NATIVEIMAGEEDITOR_BLENDSAMPLER_H
#define NATIVEIMAGEEDITOR_BLENDSAMPLER_H

#include "BaseFilterSampler.h"

class BlendSampler: public BaseFilterSampler{
protected:
public:
    virtual void setFilterImage(vector<NativeImage*> nativeImages);
};

#endif //NATIVEIMAGEEDITOR_BLENDSAMPLER_H
