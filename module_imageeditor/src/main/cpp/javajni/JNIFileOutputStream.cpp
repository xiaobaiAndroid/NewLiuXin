/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "JNIFileOutputStream.h"


JNIFileOutputStream::JNIFileOutputStream(JNIEnv *jniEnv) : JavaJNIBase(jniEnv){

}

jclass JNIFileOutputStream::getClass() {
    return mEnv->FindClass("java/io/FileOutputStream");
}

jobject JNIFileOutputStream::getInstance(jclass clazz, jobject file) {
    jmethodID methodId = mEnv->GetMethodID(clazz, "<init>",
                                                "(Ljava/io/File;)V");
    return mEnv->NewObject(clazz, methodId, file);
}