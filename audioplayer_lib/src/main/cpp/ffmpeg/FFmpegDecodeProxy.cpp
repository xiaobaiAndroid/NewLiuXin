/**
 *@author: baizf
 *@date: 2023/3/3
*/
//

#include "FFmpegDecodeProxy.h"

FFmpegDecodeProxy::FFmpegDecodeProxy(
                                     ParseAudioFrameCallBack parseAudioFrameCallBack, void *obj) {
    mParseAudioFrameCallBack = parseAudioFrameCallBack;
    mObj = obj;
}

bool
FFmpegDecodeProxy::startDecode(FFmpegDemuxingProxy *demuxingProxy) {
    if (demuxingProxy == nullptr) {
        return false;
    }

    mPacket = av_packet_alloc();
    if (mPacket == nullptr) {
        LOGE("audio decode av_packet_alloc fail");
        return false;
    }
    mFrame = av_frame_alloc();
    if (mFrame == nullptr) {
        LOGE("audio decode v_frame_alloc fail");
        return false;
    }
    AVCodecContext *audioDecodeContext = demuxingProxy->getAudioDecodeContext();

    int result = 0;
    while (av_read_frame(demuxingProxy->getFormatContext(), mPacket) == 0) {
        LOGI("av_read_frame---stream_index=%d", mPacket->stream_index);
        if (mPacket->stream_index == demuxingProxy->getAudioStreamIndex()) {
            result = decode(audioDecodeContext, mFrame, mPacket);
        }
        av_packet_unref(mPacket);
        if (result < 0) {
            break;
        }
    }

    //刷新一下解码器
    if (audioDecodeContext) {
        decode(audioDecodeContext, mFrame, nullptr);
    }
    return true;
}

void FFmpegDecodeProxy::release() {
    if (mPacket) {
        av_packet_free(&mPacket);
        mPacket = nullptr;
    }
    if (mFrame) {
        av_frame_free(&mFrame);
        mFrame = nullptr;
    }

}

int FFmpegDecodeProxy::decode(AVCodecContext *decodeContext, AVFrame *pFrame, AVPacket *pPacket) {
    if (!decodeContext) {
        return -1;
    }
    int result = avcodec_send_packet(decodeContext, pPacket);

    if (result < 0) {
        FFmpegErrorProxy::printErrorMessage(result, "audio avcodec_send_packet");
        return result;
    }

    while (result >= 0) {
        result = avcodec_receive_frame(decodeContext, pFrame);
        if (result < 0) {
            //这两个返回值很特殊，意味着没有输出
            //帧可用，但在解码期间没有错误
            if (result == AVERROR_EOF || result == AVERROR(EAGAIN)) {
                LOGI("avcodec_receive_frame no data");
                av_frame_unref(pFrame);
                return 0;
            } else {
                FFmpegErrorProxy::printErrorMessage(result, "video avcodec_receive_frame");
                return result;
            }
        }

        if (mParseAudioFrameCallBack) {
            mParseAudioFrameCallBack(pFrame, mObj);
        }

        av_frame_unref(pFrame);

        if (result < 0) {
            break;
        }
    }
    return result;
}
