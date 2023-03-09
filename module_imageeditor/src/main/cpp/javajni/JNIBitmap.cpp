/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "JNIBitmap.h"

jclass JNIBitmap::getClass() {
    return mEnv->FindClass("android/graphics/Bitmap");
}

JNIBitmap::JNIBitmap(JNIEnv *jniEnv) : JavaJNIBase(jniEnv) {

}

jobject JNIBitmap::createBitmap(jclass bitmmapClass, jobject bitmapConfig, int width, int height) {
    jmethodID methodId = mEnv->GetStaticMethodID(bitmmapClass,
                                                "createBitmap",
                                                "(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;");

    return mEnv->CallStaticObjectMethod(bitmmapClass,
                                        methodId,
                                        width,
                                        height, bitmapConfig);
}

jboolean JNIBitmap::compress(jclass bitmmapClass,jobject bitmap, jobject compressFormat, jobject stream, int quality) {
    jmethodID methodId = mEnv->GetMethodID(bitmmapClass, "compress",
                                                  "(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z");

    return mEnv->CallBooleanMethod(bitmap, methodId,compressFormat,quality,stream);
}
