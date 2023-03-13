/**
 *@author: baizf
 *@date: 2023/3/10
*/
//

#include "AudioDecoder.h"


bool AudioDecoder::prepare(const string &url) {
    mDemuxingProxy = new FFmpegDemuxingProxy();
    bool demuxing = mDemuxingProxy->demuxing(url);
    if(demuxing){
        mDecodeProxy = new FFmpegDecodeProxy(parseAudioCallback, this);
        AVCodecContext *decodeContext = mDemuxingProxy->getAudioDecodeContext();
        mOnDecodeReadyCallback(mPlayerController,decodeContext->sample_rate, decodeContext->channels);
    }
    return demuxing;
}

void AudioDecoder::startDecode() {
    if(mDecodeProxy && mDemuxingProxy){
        pthread_create(&mDecodeThread, nullptr,decoding,this);
        mDecodeProxy->startDecode(mDemuxingProxy);
    }
}

void *AudioDecoder::decoding(void *obj) {
    auto *pAudioDecoder = static_cast<AudioDecoder *>(obj);
    pAudioDecoder->mDecodeProxy->startDecode(pAudioDecoder->mDemuxingProxy);
}


void AudioDecoder::parseAudioCallback(AVFrame *avFrame, void *obj) {
    AudioDecoder *pAudioDecoder = static_cast<AudioDecoder *>(obj);
    pAudioDecoder->sendDecodeAudioData(avFrame->data[0],1);
}

void AudioDecoder::setCallback(OnDecodeReady decodeReadyCallback, OnDecodeData decodeDataCallback, void *playerController) {
    mOnDecodeReadyCallback = decodeReadyCallback;
    mOnDecodeDataCallback = decodeDataCallback;
    mPlayerController = playerController;
}


void AudioDecoder::release() {
    if(mDemuxingProxy){
        mDemuxingProxy->release();
        delete mDemuxingProxy;
        mDemuxingProxy = nullptr;
    }
    if(mDecodeProxy){
        mDecodeProxy->release();
        delete mDecodeProxy;
        mDecodeProxy = nullptr;
    }
    if(mDecodeThread != -1){
        pthread_detach(mDecodeThread);
        mDecodeThread = -1;
    }

}

void AudioDecoder::stopDecode() {

}

void AudioDecoder::sendDecodeAudioData(uint8_t *audioData, int numFrames) {
    mOnDecodeDataCallback(mPlayerController,audioData,numFrames);
}
