/**
 *@author: baizf
 *@date: 2023/3/10
*/
//

#ifndef NEWLIUXIN_AUDIODECODER_H
#define NEWLIUXIN_AUDIODECODER_H

#include "FFmpegDemuxingProxy.h"
#include "FFmpegDecodeProxy.h"
#include <pthread.h>

typedef void (*OnDecodeReady)(void *obj, int samplerRate, int channelCount);
typedef void (*OnDecodeData)(void *obj,uint8_t *data, int numFrames);

class AudioDecoder{
private:
    FFmpegDemuxingProxy *mDemuxingProxy  = nullptr;
    FFmpegDecodeProxy *mDecodeProxy = nullptr;
    pthread_t mDecodeThread = -1;

    OnDecodeReady mOnDecodeReadyCallback = nullptr;
    OnDecodeData mOnDecodeDataCallback = nullptr;

    void *mPlayerController = nullptr;

    static void *decoding(void *obj);
public:



    bool prepare(const string &url);

    void startDecode();

    void stopDecode();

    void release();

    void setCallback(OnDecodeReady decodeReadyCallback, OnDecodeData decodeDataCallback, void * playerController);

    static void parseAudioCallback(AVFrame *avFrame, void *obj);

    void sendDecodeAudioData(uint8_t *audioData, int numFrames);
};

#endif //NEWLIUXIN_AUDIODECODER_H
