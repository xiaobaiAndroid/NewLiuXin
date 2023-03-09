/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef GLES3XGAMEDEMO_JAVAJNIBASE_H
#define GLES3XGAMEDEMO_JAVAJNIBASE_H

#include <jni.h>
#include <string>

using namespace std;

class JavaJNIBase{
protected:
    JNIEnv *mEnv = nullptr;
    virtual jclass getClass() = 0;
public:
    JavaJNIBase(JNIEnv *env);

};

#endif //GLES3XGAMEDEMO_JAVAJNIBASE_H
