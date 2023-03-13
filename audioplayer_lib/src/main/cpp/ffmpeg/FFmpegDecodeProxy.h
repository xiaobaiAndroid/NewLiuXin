/**
 * 视频解码
 *@author: baizf
 *@date: 2023/3/3
*/
//


#ifndef NATIVEVIDEOPLAYER_FFMPEGDECODEPROXY_H
#define NATIVEVIDEOPLAYER_FFMPEGDECODEPROXY_H

extern "C"{
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
}

#include "FFmpegErrorProxy.h"
#include "FFmpegDemuxingProxy.h"
#include <stdio.h>

typedef void (*ParseAudioFrameCallBack)(AVFrame *avFrame, void *obj);

class FFmpegDecodeProxy{
private:
    AVFrame *mFrame = nullptr;
    AVPacket *mPacket = nullptr;

    ParseAudioFrameCallBack mParseAudioFrameCallBack = nullptr;
    int decode(AVCodecContext *decodeContext, AVFrame *pFrame, AVPacket *pPacket);

    void *mObj = nullptr;
public:
    FFmpegDecodeProxy(ParseAudioFrameCallBack parseAudioFrameCallBack, void *obj);

    bool startDecode(FFmpegDemuxingProxy *demuxingProxy);

    void release();


};

#endif //NATIVEVIDEOPLAYER_FFMPEGDECODEPROXY_H
