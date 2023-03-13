/**
 *@author: baizf
 *@date: 2023/3/10
*/
//

#include "AudioPlayerController.h"

AudioPlayerController::AudioPlayerController() {
    mDecoder = new AudioDecoder();
    mPlayer = new AudioPlayer();
    mDecoder->setCallback(onDecodeReadyCallback, onDecodeDataCallback, this);
}

void AudioPlayerController::prepare(const string &url, int mAudioOutputDeviceId) {
    mDecoder->prepare(url);
    mPlayer->mDeviceId = mAudioOutputDeviceId;
}

void AudioPlayerController::play() {
    mDecoder->startDecode();
    mPlayer->start();
}

void AudioPlayerController::onDecodeReadyCallback(void *obj, int samplerRate, int channelCount) {
    LOGI("samplerRate=%d, channelCount=%d",samplerRate,channelCount);
    auto *pPlayerController = static_cast<AudioPlayerController *>(obj);
    pPlayerController->decodeReadyCallback(samplerRate,channelCount);

}

void AudioPlayerController::onDecodeDataCallback(void *obj, uint8_t *data, int numFrames) {
    auto *pPlayerController = static_cast<AudioPlayerController *>(obj);
    pPlayerController->decodeDataCallback(data, numFrames);
}


void AudioPlayerController::stop() {

}

void AudioPlayerController::release() {
    if(mDecoder){
        mDecoder->release();
    }
}

AudioPlayerController::~AudioPlayerController() {
    delete mDecoder;
    delete mPlayer;
}

void AudioPlayerController::decodeReadyCallback(int samplerRate, int channelCount) {
    mPlayer->prepare(samplerRate, channelCount);
}

void AudioPlayerController::decodeDataCallback(uint8_t *data, int numFrames) {
    mPlayer->readData(data,numFrames);
}
