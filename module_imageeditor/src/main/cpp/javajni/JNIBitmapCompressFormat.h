/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef GLES3XGAMEDEMO_JNIBITMAPCOMPRESSFORMAT_H
#define GLES3XGAMEDEMO_JNIBITMAPCOMPRESSFORMAT_H

#include "JavaJNIBase.h"

class JNIBitmapCompressFormat: public JavaJNIBase{
public:
    jclass getClass() override;

    JNIBitmapCompressFormat(JNIEnv *jniEnv);

    jobject getInstance(jclass clazz);
};

#endif //GLES3XGAMEDEMO_JNIBITMAPCOMPRESSFORMAT_H
