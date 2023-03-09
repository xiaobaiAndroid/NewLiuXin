/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "JNIBitmapConfig.h"

JNIBitmapConfig::JNIBitmapConfig(JNIEnv *jniEnv) : JavaJNIBase(jniEnv){

}

jclass JNIBitmapConfig::getClass() {
    return mEnv->FindClass("android/graphics/Bitmap$Config");
}

jobject JNIBitmapConfig::valueOf(jclass clazz) {
    jstring configName = mEnv->NewStringUTF("ARGB_8888");
    jmethodID methodId = mEnv->GetStaticMethodID(
            clazz, "valueOf",
            "(Ljava/lang/String;)Landroid/graphics/Bitmap$Config;");

    return mEnv->CallStaticObjectMethod(clazz,methodId, configName);
}