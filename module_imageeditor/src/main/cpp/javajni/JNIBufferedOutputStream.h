/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef GLES3XGAMEDEMO_JNIBUFFEREDOUTPUTSTREAM_H
#define GLES3XGAMEDEMO_JNIBUFFEREDOUTPUTSTREAM_H

#include "JavaJNIBase.h"

class JNIBufferedOutputStream: public JavaJNIBase{
public:
    jclass getClass() override;

    JNIBufferedOutputStream(JNIEnv *jniEnv);

    jobject getInstance(jclass clazz, jobject outputStream);

    void close(jclass clazz,jobject instance);
};

#endif //GLES3XGAMEDEMO_JNIBUFFEREDOUTPUTSTREAM_H
