/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef GLES3XGAMEDEMO_JNIFILE_H
#define GLES3XGAMEDEMO_JNIFILE_H

#include "JavaJNIBase.h"

class JNIFile: public JavaJNIBase{
public:
    jclass getClass() override;

    JNIFile(JNIEnv *jniEnv);

    jobject getInstance(jclass clazz, jstring path);
};

#endif //GLES3XGAMEDEMO_JNIFILE_H
