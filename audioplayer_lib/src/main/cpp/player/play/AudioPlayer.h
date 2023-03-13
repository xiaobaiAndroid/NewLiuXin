/**
 *@author: baizf
 *@date: 2023/3/10
*/
//

#ifndef NEWLIUXIN_AUDIOPLAYER_H
#define NEWLIUXIN_AUDIOPLAYER_H

#include <oboe/Oboe.h>
#include <queue>

class AudioPlayer: public oboe::AudioStreamDataCallback, oboe::AudioStreamErrorCallback{
private:
    oboe::AudioStream *mStream = nullptr;

    std::queue<uint8_t*> mInputQueue;
    bool isStart = false;
public:
    bool prepare(int i, int i1);

    bool start();
    bool pause();
    bool stop();
    bool release();

    void readData(uint8_t *data, int numFrames);

    int mDeviceId = 0;

    oboe::DataCallbackResult onAudioReady(oboe::AudioStream *audioStream, void *audioData, int32_t numFrames) override;


    bool onError(oboe::AudioStream * audioStream, oboe::Result result) override;
    void onErrorAfterClose(oboe::AudioStream * audioStream, oboe::Result result) override;
    void onErrorBeforeClose(oboe::AudioStream *audioStream, oboe::Result result) override;

};

#endif //NEWLIUXIN_AUDIOPLAYER_H
