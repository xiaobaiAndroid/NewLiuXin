/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef GLES3XGAMEDEMO_JNIFILEOUTPUTSTREAM_H
#define GLES3XGAMEDEMO_JNIFILEOUTPUTSTREAM_H

#include "JavaJNIBase.h"

class JNIFileOutputStream : public JavaJNIBase{
public:
    jclass getClass() override;

    JNIFileOutputStream(JNIEnv *jniEnv);

    jobject getInstance(jclass clazz, jobject file);
};

#endif //GLES3XGAMEDEMO_JNIFILEOUTPUTSTREAM_H
