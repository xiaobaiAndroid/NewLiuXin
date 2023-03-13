#include <jni.h>

/**
 *@author: baizf
 *@date: 2023/3/10
*/
//

#include "AudioPlayerController.h"

static AudioPlayerController *mController = nullptr;

extern "C"
JNIEXPORT void JNICALL
Java_module_audioplayer_1lib_AudioPlayerEngine_nPrepare(JNIEnv *env, jobject thiz,
                                                        jint audio_output_device_id, jstring url) {

    if(mController){
        mController->release();
        delete mController;
    }
    mController = new AudioPlayerController();

    const char *cUrl = env->GetStringUTFChars(url, nullptr);
    string musicUrl(cUrl);
    mController->prepare(musicUrl, audio_output_device_id);
    env->ReleaseStringUTFChars(url,cUrl);

}

extern "C"
JNIEXPORT void JNICALL
Java_module_audioplayer_1lib_AudioPlayerEngine_nPlay(JNIEnv *env, jobject thiz) {
    if(mController){
        mController->play();
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_module_audioplayer_1lib_AudioPlayerEngine_nPause(JNIEnv *env, jobject thiz) {

}
extern "C"
JNIEXPORT void JNICALL
Java_module_audioplayer_1lib_AudioPlayerEngine_nResume(JNIEnv *env, jobject thiz) {

}
extern "C"
JNIEXPORT void JNICALL
Java_module_audioplayer_1lib_AudioPlayerEngine_nStop(JNIEnv *env, jobject thiz) {

}
extern "C"
JNIEXPORT void JNICALL
Java_module_audioplayer_1lib_AudioPlayerEngine_nRelease(JNIEnv *env, jobject thiz) {
    if(mController){
        mController->release();
        delete mController;
        mController = nullptr;
    }
}
