/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "JNIBitmapCompressFormat.h"

JNIBitmapCompressFormat::JNIBitmapCompressFormat(JNIEnv *jniEnv) : JavaJNIBase(jniEnv){

}

jclass JNIBitmapCompressFormat::getClass() {
    return mEnv->FindClass("android/graphics/Bitmap$CompressFormat");
}

jobject JNIBitmapCompressFormat::getInstance(jclass clazz) {
    jfieldID fieldId = mEnv->GetStaticFieldID(clazz, "PNG",
                                                "Landroid/graphics/Bitmap$CompressFormat;");
    return  mEnv->GetStaticObjectField(clazz, fieldId);
}