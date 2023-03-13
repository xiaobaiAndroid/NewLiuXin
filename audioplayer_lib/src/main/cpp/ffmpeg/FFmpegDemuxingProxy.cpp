/**
 *@author: baizf
 *@date: 2023/3/3
*/
//

#include "FFmpegDemuxingProxy.h"


bool FFmpegDemuxingProxy::demuxing(const string& path) {

    LOGI("audio url=%s",path.c_str());
    //打开一个文件 只是读文件头，并不会填充流信息 需要注意的是，此处的pFormatContext必须为NULL或由avformat_alloc_context分配得到
    //如果中断函数interruptCallBack返回1，statusCode将不为0，错误信息：Immediate exit requested
    int result = avformat_open_input(&mFormatContext, path.c_str(), nullptr, nullptr);
    if(FFmpegErrorProxy::hasError(result)){
        LOGE("avformat_open_input fail");
        return false;
    }

    result = avformat_find_stream_info(mFormatContext, nullptr);
    if(result < 0){
        LOGE("avformat_find_stream_info fail");
        FFmpegErrorProxy::printErrorMessage(result);
        return false;
    }
    if(!mFormatContext){
        return false;
    }

    if(openCodecContext(&mAudioStreamIndex, &mAudioDecodeContext, mFormatContext, AVMediaType::AVMEDIA_TYPE_AUDIO)){
        mAudioStream = mFormatContext->streams[mAudioStreamIndex];
    }

    if(mAudioStream == nullptr){
        return false;
    }
    return true;
}


bool FFmpegDemuxingProxy::openCodecContext(int *pStreamIndex, AVCodecContext **pDecodeContext,
                                           AVFormatContext *formatContext,
                                           enum AVMediaType mediaType) {
    AVStream *stream;
    int streamIndex = 0;
    AVDictionary *opts = nullptr;

    int result = av_find_best_stream(formatContext, mediaType, -1, -1, nullptr, 0);
    if(result < 0){
        FFmpegErrorProxy::printErrorMessage(result);
        return false;
    }
    streamIndex = result;
    LOGI("mediaType=%s, streamIndex=%d",av_get_media_type_string(mediaType), streamIndex);

    stream = formatContext->streams[streamIndex];

    AVCodec *pCodec = avcodec_find_decoder(stream->codecpar->codec_id);
    if(pCodec == nullptr){
        LOGE("avcodec_find_decoder fail mediaType=%s", av_get_media_type_string(mediaType));
        return false;
    }

    *pDecodeContext = avcodec_alloc_context3(pCodec);
    if(*pDecodeContext == nullptr){
        LOGE("avcodec_alloc_context3 fail mediaType=%s", av_get_media_type_string(mediaType));
        return false;
    }

    //将编解码器参数从输入流复制到输出编解码器上下文
    result = avcodec_parameters_to_context(*pDecodeContext, stream->codecpar);
    if(result < 0){
        LOGE("avcodec_parameters_to_context fail mediaType=%s", av_get_media_type_string(mediaType));
        FFmpegErrorProxy::printErrorMessage(result);
        return false;
    }

    result = avcodec_open2(*pDecodeContext, pCodec, &opts);
    if(result < 0){
        LOGE("avcodec_open2 fail mediaType=%s", av_get_media_type_string(mediaType));
        FFmpegErrorProxy::printErrorMessage(result);
        return false;
    }
    *pStreamIndex = streamIndex;

    return true;
}


int FFmpegDemuxingProxy::getAudioStreamIndex() const {
    return mAudioStreamIndex;
}

AVCodecContext* FFmpegDemuxingProxy::getAudioDecodeContext() const {
    return mAudioDecodeContext;
}


AVFormatContext *FFmpegDemuxingProxy::getFormatContext() {
    return mFormatContext;
}

AVStream *FFmpegDemuxingProxy::getAudioStream() const {
    return mAudioStream;
}



void FFmpegDemuxingProxy::release() {
    if(mFormatContext){
        avformat_close_input(&mFormatContext);
        mFormatContext = nullptr;
    }
    if(mAudioDecodeContext){
        avcodec_free_context(&mAudioDecodeContext);
        mAudioDecodeContext = nullptr;
    }
    mAudioStream = nullptr;
    mAudioStreamIndex = -1;

}