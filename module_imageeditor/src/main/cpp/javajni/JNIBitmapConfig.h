/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef GLES3XGAMEDEMO_JNIBITMAPCONFIG_H
#define GLES3XGAMEDEMO_JNIBITMAPCONFIG_H

#include "JavaJNIBase.h"

class JNIBitmapConfig: public JavaJNIBase{
public:
    JNIBitmapConfig(JNIEnv *jniEnv);

    jclass getClass() override;

    jobject valueOf(jclass clazz);
};

#endif //GLES3XGAMEDEMO_JNIBITMAPCONFIG_H
