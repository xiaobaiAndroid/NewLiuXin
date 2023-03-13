/**
 *@author: baizf
 *@date: 2023/3/10
*/
//

#include "AudioPlayer.h"
#include "Logutils.h"

bool AudioPlayer::prepare(int sampleRate, int channelCount) {
    //配置音频流
    oboe::AudioStreamBuilder streamBuilder;
    oboe::Result result = streamBuilder.setSharingMode(oboe::SharingMode::Exclusive)
                    //使用更小的缓冲区和优化的数据路径以减少延迟
            ->setPerformanceMode(oboe::PerformanceMode::LowLatency)
            ->setFormat(oboe::AudioFormat::Float)
            ->setFormatConversionAllowed(true)
            ->setAudioApi(oboe::AudioApi::AAudio)
            ->setSampleRate(sampleRate)
            ->setChannelCount(channelCount)
            ->setDirection(oboe::Direction::Output)
            ->setDeviceId(mDeviceId)
            ->setDataCallback(this)
            ->setErrorCallback(this)
            ->openStream(&mStream);

    if(result != oboe::Result::OK){
        LOGE("Error opening stream %s", oboe::convertToText(result));
        return false;
    }
    start();
    //验证流配置和其他属性
    return true;
}

bool AudioPlayer::start() {
    if(mStream && !isStart){
        isStart = true;
        mStream->requestStart();
    }
    return true;
}


void AudioPlayer::readData(uint8_t *data, int numFrames) {
    LOGI("readData---numFrames=%d",numFrames);
    mInputQueue.push(data);
}

bool AudioPlayer::pause() {
    if(mStream){
        mStream->requestPause();
        mStream->requestFlush();
    }
    return true;
}

bool AudioPlayer::stop() {
    if(mStream && isStart){
        isStart = false;
        mStream->requestStop();
    }
    return true;
}

bool AudioPlayer::release() {
    if(mStream){
        mStream->close();
        delete mStream;
    }
    isStart = false;
    return true;
}


oboe::DataCallbackResult
AudioPlayer::onAudioReady(oboe::AudioStream *audioStream, void *audioData, int32_t numFrames) {
    uint8_t *inputData = mInputQueue.front();
    uint8_t *uint8AudioData = static_cast<uint8_t *>(audioData);
    if(inputData){
        uint8AudioData = inputData;
    }
    mInputQueue.pop();
    return oboe::DataCallbackResult::Continue;
}

bool AudioPlayer::onError(oboe::AudioStream *audioStream, oboe::Result result) {
    LOGE("AudioPlayer onError fail=%s", oboe::convertToText(result));
    return true;
}

void AudioPlayer::onErrorAfterClose(oboe::AudioStream *audioStream, oboe::Result result) {
    LOGE("AudioPlayer onErrorAfterClose fail=%s", oboe::convertToText(result));
}

void AudioPlayer::onErrorBeforeClose(oboe::AudioStream *audioStream, oboe::Result result) {
    LOGE("AudioPlayer onErrorBeforeClose fail=%s", oboe::convertToText(result));
}