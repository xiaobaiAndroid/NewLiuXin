/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "JNIFile.h"


JNIFile::JNIFile(JNIEnv *jniEnv) : JavaJNIBase(jniEnv){}

jclass JNIFile::getClass() {
    return mEnv->FindClass("java/io/File");
}

jobject JNIFile::getInstance(jclass clazz, jstring path) {
    jmethodID methodId = mEnv->GetMethodID(clazz, "<init>",
                                              "(Ljava/lang/String;)V");
    return mEnv->NewObject(clazz, methodId, path);
}