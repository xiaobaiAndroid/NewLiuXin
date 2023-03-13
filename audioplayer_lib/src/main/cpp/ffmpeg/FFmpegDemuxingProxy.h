/**
 * 解封装
 *@author: baizf
 *@date: 2023/3/3
*/
//

#ifndef NATIVEVIDEOPLAYER_FFMPEGDEMUXINGPROXY_H
#define NATIVEVIDEOPLAYER_FFMPEGDEMUXINGPROXY_H

extern "C"{
#include "libavformat/avformat.h"
#include "libavcodec/avcodec.h"
};
#include "FFmpegErrorProxy.h"

class FFmpegDemuxingProxy{
private:
    AVCodecContext *mAudioDecodeContext = nullptr;
    AVFormatContext *mFormatContext = nullptr;
    int mAudioStreamIndex = -1;

    AVStream *mAudioStream = nullptr;

    bool openCodecContext(int *pStreamIndex, AVCodecContext **pDecodeContext, AVFormatContext * formatContext, enum AVMediaType mediaType);
public:
    bool demuxing(const string& path);

    int getAudioStreamIndex() const;

    AVFormatContext* getFormatContext();

    AVCodecContext* getAudioDecodeContext() const;

    AVStream* getAudioStream() const;
    void release();
};

#endif //NATIVEVIDEOPLAYER_FFMPEGDEMUXINGPROXY_H
