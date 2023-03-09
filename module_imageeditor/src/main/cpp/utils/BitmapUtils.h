/**
 *@author: baizf
 *@date: 2022/11/26
*/
//

#ifndef OPENGLDEMO_BITMAPUTILS_H
#define OPENGLDEMO_BITMAPUTILS_H

#include <android/bitmap.h>
#include "../entity/NativeImage.h"


class BitmapUtils{
public:
   static NativeImage* loadImage(JNIEnv *env, jobject bitmap);

    static void release(JNIEnv *env, jobject bitmap);
};


#endif //OPENGLDEMO_BITMAPUTILS_H
