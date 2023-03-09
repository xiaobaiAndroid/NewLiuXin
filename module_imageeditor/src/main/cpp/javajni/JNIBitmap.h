/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#ifndef GLES3XGAMEDEMO_JNIBITMAP_H
#define GLES3XGAMEDEMO_JNIBITMAP_H

#include "JavaJNIBase.h"

class JNIBitmap: public JavaJNIBase{
protected:
public:
    jclass getClass() override;

    JNIBitmap(JNIEnv *jniEnv);
    jobject createBitmap(jclass bitmmapClass, jobject bitmapConfig,int width, int height);
    jboolean compress(jclass bitmmapClass, jobject bitmap, jobject compressFormat, jobject stream,int quality);
};

#endif //GLES3XGAMEDEMO_JNIBITMAP_H
