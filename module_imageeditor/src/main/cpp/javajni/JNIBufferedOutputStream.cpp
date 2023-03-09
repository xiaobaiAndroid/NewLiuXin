/**
 *@author: baizf
 *@date: 2023/2/9
*/
//

#include "JNIBufferedOutputStream.h"


JNIBufferedOutputStream::JNIBufferedOutputStream(JNIEnv *jniEnv) : JavaJNIBase(jniEnv){

}

jclass JNIBufferedOutputStream::getClass() {
    return mEnv->FindClass("java/io/BufferedOutputStream");
}

jobject JNIBufferedOutputStream::getInstance(jclass clazz, jobject outputStream) {
    jmethodID methodId = mEnv->GetMethodID(clazz, "<init>",
                                                        "(Ljava/io/OutputStream;)V");
    return mEnv->NewObject(clazz, methodId, outputStream);
}

void JNIBufferedOutputStream::close(jclass clazz,jobject instance) {
    jmethodID closeMethodId = mEnv->GetMethodID(clazz, "close", "()V");
    mEnv->CallVoidMethod(instance,closeMethodId);
}