#include <jni.h>

/**
 *@author: baizf
 *@date: 2023/2/1
*/
//

#include "../editor/ImageEditor.h"
#include "adjust/BrightnessSampler.h"
#include "Logutils.h"
#include "JNIBitmapConfig.h"
#include "JNIBitmap.h"
#include "JNIFile.h"
#include "JNIFileOutputStream.h"
#include "JNIBufferedOutputStream.h"
#include "JNIBitmapCompressFormat.h"
#include <map>
#include <mutex>

using namespace std;

static map<string, ImageEditor*> imageEditorMap;

static std::mutex mtx;

extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_nInit(JNIEnv *env, jobject thiz, jstring image_id,jobject assets,
                                                    jint filter_type) {

//    const GLubyte *extensions = glGetString(GL_EXTENSIONS);
//    LOGI("extensions=%s",extensions);
//
//    const GLubyte *version = glGetString(GL_VERSION);
//    const GLubyte *vendor = glGetString(GL_VENDOR);
//    const GLubyte *renderer = glGetString(GL_RENDERER);
//    LOGI("vendor=%s, version=%s, renderer=%s",vendor, version, renderer);

    mtx.lock();

    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);
    if(imageEditorMap.empty()){
       auto pImageEditor = new ImageEditor();
        pImageEditor->init(env, &assets);
        imageEditorMap.insert(pair<string, ImageEditor*>(imageId,pImageEditor));
    }else{
        auto iterator = imageEditorMap.find(imageId);
        if(iterator != imageEditorMap.end()){ //存在
            ImageEditor *pImageEditor = iterator->second;
            if (pImageEditor) {
                pImageEditor->releaseGL();
                delete pImageEditor;
            }
            imageEditorMap.erase(iterator);//删除
        }
        auto pImageEditor = new ImageEditor();
        pImageEditor->init(env, &assets);
        imageEditorMap.insert(pair<string, ImageEditor*>(imageId,pImageEditor));
    }

    env->ReleaseStringUTFChars(image_id, cImageId);

    mtx.unlock();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_nResize(JNIEnv *env, jobject thiz, jstring image_id,
                                                      jint width,
                                                      jint height) {

    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;
        if (pImageEditor) {
            pImageEditor->resize(width, height);
        }
    }
    env->ReleaseStringUTFChars(image_id, cImageId);
    mtx.unlock();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_nDraw(JNIEnv *env, jobject thiz,jstring image_id) {
    mtx.lock();

    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;
        if (pImageEditor) {
            pImageEditor->draw();
        }
    }
    env->ReleaseStringUTFChars(image_id, cImageId);
    mtx.unlock();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_nReleaseGL(JNIEnv *env, jobject thiz,jstring image_id) {

    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;
        if (pImageEditor) {
            pImageEditor->releaseGL();
            delete pImageEditor;
        }
        imageEditorMap.erase(iterator);
    }
    env->ReleaseStringUTFChars(image_id, cImageId);
    mtx.unlock();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_setOriginalImage(JNIEnv *env, jobject thiz,jstring image_id,
                                                               jobject bitmap) {

    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;
        if (pImageEditor) {
            NativeImage *image = BitmapUtils::loadImage(env, bitmap);
            pImageEditor->setSourceImage(*image);
            BitmapUtils::release(env, bitmap);
        }
    }
    env->ReleaseStringUTFChars(image_id, cImageId);

    mtx.unlock();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_setFilterImage(JNIEnv *env, jobject thiz,
                                                             jstring image_id,jobjectArray bitmaps, jint filterType) {

    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;

        if (pImageEditor) {

            auto *blendSampler = pImageEditor->getFilterSampler();
            jsize length = env->GetArrayLength(bitmaps);
            vector<NativeImage *> images;
            for (int i = 0; i < length; ++i) {
                jobject jBitmap = env->GetObjectArrayElement(bitmaps, i);
                if (jBitmap) {
                    NativeImage *image = BitmapUtils::loadImage(env, jBitmap);
                    images.push_back(image);
                }
            }
            if(blendSampler){
                blendSampler->setFilterImage(images);
            }
            for (auto image: images) {
                delete image;
            }

            for (int i = 0; i < length; ++i) {
                jobject jBitmap = env->GetObjectArrayElement(bitmaps, i);
                if (jBitmap) {
                    BitmapUtils::release(env, jBitmap);
                }
            }
        }
    }
    env->ReleaseStringUTFChars(image_id, cImageId);

    mtx.unlock();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1imageeditor_adjust_AdjustSelectView_nSetAdjustValue(JNIEnv *env, jobject thiz,
                                                                         jstring image_id,jint adjust_type,jfloat value) {
    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;
        if (pImageEditor) {
            pImageEditor->setAdjustValue(adjust_type, value);
        }
    }
    env->ReleaseStringUTFChars(image_id, cImageId);
    mtx.unlock();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_nSetFilter(JNIEnv *env, jobject thiz, jstring image_id,jobject assets,
                                                         jint filter_type) {
    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;
        if(pImageEditor){
            pImageEditor->setFilter(env, &assets, filter_type);
        }
    }
    env->ReleaseStringUTFChars(image_id, cImageId);
    mtx.unlock();
}
extern "C"
JNIEXPORT jfloat JNICALL
Java_com_bzf_module_1imageeditor_adjust_AdjustSelectView_nGetAdjustValue(JNIEnv *env, jobject thiz,
                                                                         jstring image_id,jint adjust_type) {

    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);

    env->ReleaseStringUTFChars(image_id, cImageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;
        if (pImageEditor) {
            mtx.unlock();
            return pImageEditor->getAdjustValue(adjust_type);
        }
    }
    mtx.unlock();
    return 0.0f;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_nSaveBitmap(JNIEnv *env, jobject thiz, jstring image_id,
                                                          jobject assets, jstring save_path) {
    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);

    env->ReleaseStringUTFChars(image_id, cImageId);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;

        const char *cSaveFilePath = env->GetStringUTFChars(save_path, nullptr);
        string path(cSaveFilePath);

        JNIBitmapConfig jniBitmapConfig(env);
        jobject bitmapConfig = jniBitmapConfig.valueOf(jniBitmapConfig.getClass());

        JNIBitmap jniBitmap(env);
        jclass bitmapClass = jniBitmap.getClass();
        jobject bitmap = jniBitmap.createBitmap(bitmapClass, bitmapConfig,
                                                pImageEditor->mScaleImageWidth,
                                                pImageEditor->mScaleImageHeight);

        pImageEditor->getBitmapData(env, bitmap);

        JNIFile jniFile(env);
        jobject file = jniFile.getInstance(jniFile.getClass(), save_path);

        JNIFileOutputStream jniFileOutputStream(env);
        jobject fileOutputStream = jniFileOutputStream.getInstance(jniFileOutputStream.getClass(), file);

        JNIBufferedOutputStream jniBufferedOutputStream(env);
        jobject bufferedOutputStream = jniBufferedOutputStream.getInstance(jniBufferedOutputStream.getClass(),
                                                                           fileOutputStream);

        JNIBitmapCompressFormat jniBitmapCompressFormat(env);
        jobject bitmapCompressFormat = jniBitmapCompressFormat.getInstance(
                jniBitmapCompressFormat.getClass());

        jboolean isSuccess = jniBitmap.compress(bitmapClass, bitmap,bitmapCompressFormat,bufferedOutputStream,100);

        jniBufferedOutputStream.close(jniBufferedOutputStream.getClass(),bufferedOutputStream);

        //释放原生像素缓存
        AndroidBitmap_unlockPixels(env, bitmap);

        env->ReleaseStringUTFChars(save_path, cSaveFilePath);

        mtx.unlock();
        return isSuccess;
    }
    mtx.unlock();
    return false;
}
extern "C"
JNIEXPORT jintArray JNICALL
Java_com_bzf_module_1imageeditor_GPUImageView_nGetRenderRect(JNIEnv *env, jobject thiz,
                                                             jstring image_id) {
    mtx.lock();
    const char *cImageId = env->GetStringUTFChars(image_id, nullptr);
    string imageId(cImageId);

    auto iterator = imageEditorMap.find(imageId);

    env->ReleaseStringUTFChars(image_id, cImageId);

    jintArray renderRect = env->NewIntArray(4);
    jint *elements = env->GetIntArrayElements(renderRect, nullptr);
    if(iterator != imageEditorMap.end()) { //存在
        ImageEditor *pImageEditor = iterator->second;
        if(pImageEditor){
            vector<int> renderRectV = pImageEditor->getRenderRect();
            for (int i=0; i<renderRectV.size(); i++) {
                elements[i] = renderRectV.at(i);
            }
        }
    }
    env->ReleaseIntArrayElements(renderRect,elements,0);
    mtx.unlock();
    return renderRect;
}