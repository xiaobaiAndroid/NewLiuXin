/**
 *@author: baizf
 *@date: 2023/2/3
*/
//
#include "BitmapUtils.h"

NativeImage *BitmapUtils::loadImage(JNIEnv *env, jobject bitmap) {
    AndroidBitmapInfo info;

    NativeImage *image = nullptr;
    void *pixels = nullptr;
    //检索Bitmap对象信息，成功返回0，失败返回负数
    int result = AndroidBitmap_getInfo(env, bitmap, &info);
    if (result == 0) {

        //锁定像素缓存确保像素的内存不会被移动，返回一个像素缓存的地址赋给pixels 成功返回0，失败返回负数
        result = AndroidBitmap_lockPixels(env, bitmap, &pixels);
        if (result == 0) {
            image = new NativeImage();
            image->width = info.width;
            image->height = info.height;
            image->format = info.format;
            image->data = static_cast<unsigned char *>(pixels);
        }
    }
    return image;
}

void BitmapUtils::release(JNIEnv *env, jobject bitmap) {
    //释放原生像素缓存
    AndroidBitmap_unlockPixels(env, bitmap);
    env->DeleteLocalRef(bitmap);
}